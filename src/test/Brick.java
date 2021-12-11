package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * For setting up the properties of a player
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    /**
     * For setting up properties of crack on a brick
     */
    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;

        private final GeneralPath crack;

        private final int crackDepth;
        private final int steps;

        /**
         * Constructor for Crack
         *
         * @param crackDepth Value for the depth of the crack
         * @param steps Value for the steps
         */
        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }


        /**
         * Return crack for drawing purpose
         *
         * @return GeneralPath object
         */
        public GeneralPath draw(){

            return crack;
        }

        /**
         * Reset the crack
         */
        public void reset(){
            crack.reset();
        }

        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch (direction) {
                case HORIZONTAL -> {
                    pos = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos, to.y);
                }
                case VERTICAL -> {
                    pos = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x, pos);
                }
            }
            return out;
        }

    }

    private static Random rnd;

    private final Shape brickFace;

    private final Color border;
    private final Color inner;

    private final int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Constructor for Brick
     *
     * @param name Name of the brick
     * @param pos Position of the brick
     * @param size Size of the brick
     * @param border Border color of the brick
     * @param inner Inner color of the brick
     * @param strength Value for the strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * If the brick is broken return false, if not call impact() method and return broken after it
     *
     * @param point Point of the impact
     * @param dir Value for the direction
     *
     * @return true/false
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();

    /**
     * Get current border color of the brick
     *
     * @return Current border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Get current inner color of the brick
     *
     * @return Current inner color of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Return value according to the direction of the impact and return 0 if the brick is broken
     *
     * @param b Bal object
     *
     * @return Value according to the direction of the impact or 0 if the brick is broken
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Check whether the brick is broken
     *
     * @return true/false
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Set broken to false and reset the strength of the brick
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Decrement the strength of the brick and update broken based on the strength
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * Get the current value of the strength of the brick
     *
     * @return Current value of the strength of the brick
     */
    public int getStrength(){return strength;}

    public Shape getBrickFace(){
        return brickFace;
    }


}





