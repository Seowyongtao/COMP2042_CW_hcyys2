package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

/**
 * For setting up the properties of a ball
 *
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    private boolean isLost;
    private int count;
    private Random rnd;

    /**
     * Constructor for Ball
     *
     * @param center Point center location
     * @param radiusA Value for radius
     * @param radiusB Value for radius
     * @param inner Inner color
     * @param border Border color
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;

        isLost = false;
        count = 3;
        rnd = new Random();
    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Move the ball according to speedX and speedY
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Update the value for speedX and speedY
     *
     * @param x Value for speedX
     * @param y Value for speedY
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Update the value for speedX
     *
     * @param s Value for speedX
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Update the value for speedY
     *
     * @param s Value for speedY
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverse the value of speedX
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the value of speedY
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Get the current border color
     *
     * @return Current border color
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Get the current inner color
     *
     * @return Current inner color
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     *Update inner color
     *
     * @param color New inner color
     */
    public void setInnerColor(Color color){
        inner = color;
    }

    /**
     * Update border color
     *
     * @param color New border color
     */
    public void setBorderColor(Color color){
        border = color;
    }

    /**
     * Get the location of the center of the point
     *
     * @return Location of the center of the point
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Get the shape of the ball
     *
     * @return Shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Move the move to the given point
     *
     * @param p New point
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * Get the current value of speedX
     *
     * @return Current value of speedX
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Get the current value of speedY
     *
     * @return Current value of speedY
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * Get isLost status
     *
     * @return true/false
     */
    public boolean getIsLost(){
        return isLost;
    }

    /**
     * Update the status of isLost
     *
     * @param lostStatus true/false
     */
    public void setIsLost(boolean lostStatus){
        isLost = lostStatus;
    }

    /**
     * Get the value of count
     *
     * @return Value of count
     */
    public int getCount(){return count; }

    /**
     * Update the value of count
     *
     * @param num new count number
     */
    public void setCount(int num){count = num; }

    /**
     * Decrement the count by 1
     */
    public void count_decrement(){count--; }

    /**
     * Move the ball back to its staring point, randomly set speedX and speedY, and update isLost to false
     *
     * @param startingPoint Starting point of the ball
     */
    public void reset(Point startingPoint){
        moveTo(startingPoint);
        setSpeed(randomSpeedX(), randomSpeedY());
        setIsLost(false);
    }

    /**
     * Return random value within the range of -2 to 2
     *
     * @return Value within the range of -2 to 2
     */
    public int randomSpeedX(){

        int speedX;

        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);

        return speedX;
    }

    /**
     * Return random value within the range of 0 to -2
     *
     * @return Value within the range of 0 to -2
     */
    public int randomSpeedY(){

        int speedY ;

        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        return speedY;

    }

    /**
     * Reset the count to 3
     */
    public void resetBallCount(){
        setCount(3);
    }


}