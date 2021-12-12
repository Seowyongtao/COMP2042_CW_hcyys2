package Brick_Destroy;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * For setting up the properties of a cement brick
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * Constructor for cement brick
     *
     * @param point Location point of the brick
     * @param size Size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Return false if the brick is broken
     * Make a crack on the brick and return false if the brick is not broken
     *
     * @param point Point of the impact
     * @param dir Value for the direction
     *
     * @return true/false
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * Get the shape of the brick
     *
     * @return Shape of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Reset the brick to its initial look
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}

