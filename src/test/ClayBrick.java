package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * For setting up the properties of a clay brick
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * Constructor for ClayBrick
     *
     * @param point Location point of the brick
     * @param size Size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Get the shape of the brick
     *
     * @return Shape of the brick
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }


}

