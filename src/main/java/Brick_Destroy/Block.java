package Brick_Destroy;

import java.awt.*;

/**
 * For setting up the properties of a block
 */
public class Block {

    private Color borderColor = Color.red.darker().darker();
    private Color innerColor = Color.red;
    private Rectangle blockFace;
    private int moveAmount;
    private int xPosition;
    private final int yPosition;
    private final int width;
    private final int height;

    /**
     * Constructor for Block
     *
     * @param xPosition Value for the x coordinate of the block
     * @param yPosition Value for the y coordinate of the block
     * @param width Value for the width of the block
     * @param height Value for the height of the block
     */
    public Block(int xPosition, int yPosition,int width, int height){

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.moveAmount = -1;

        blockFace = makeRectangle(-xPosition, -yPosition, width, height);
    }

    /**
     * Get the current inner color of the block
     *
     * @return Current inner color of the block
     */
    public Color getInnerColor(){
        return this.innerColor;
    }

    /**
     * Get the current border color of the block
     *
     * @return Current border color of the block
     */
    public Color getBorderColor(){
        return this.borderColor;
    }

    /**
     * Update the inner color of the block
     *
     * @param color New inner color
     */
    public void setInnerColor(Color color){
        this.innerColor = color;
    }

    /**
     * Update the border color of the block
     *
     * @param color New border color
     */
    public void setBorderColor(Color color){
        this.borderColor = color;
    }

    /**
     * Get the shape of the block
     *
     * @return Shape of the block
     */
    public Shape getBlockFace(){
        return  blockFace;
    }

    /**
     * Check whether the ball impact with block
     *
     * @param b Ball object
     * @return  true/false
     */
    public boolean impact(Ball b){
        return blockFace.contains(b.getPosition());
    }

    /**
     * Update the location of the block so that it is visible for the user
     */
    public void setVisible(){
        this.blockFace = makeRectangle(this.xPosition, this.yPosition, this.width, this.height);
    }

    /**
     * Move the block based on the value of the moveAmount
     */
    public void move(){

        if (this.xPosition == 10){
            moveAmount = 1;
        }

        if (this.xPosition == 500){
            moveAmount = -1;
        }

        this.xPosition = this.xPosition + moveAmount;
        blockFace.setLocation(this.xPosition, this.yPosition);
    }

    private Rectangle makeRectangle(int xPosition, int yPosition,int width,int height){
        Point p = new Point(xPosition,yPosition);
        return  new Rectangle(p,new Dimension(width,height));
    }
}
