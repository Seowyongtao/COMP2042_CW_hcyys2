package test;

import java.awt.*;

public class Block {

    private Color borderColor = Color.red.darker().darker();
    private Color innerColor = Color.red;

    private Rectangle blockFace;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private int moveAmount;

    public Block(int xPosition, int yPosition,int width, int height){

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.moveAmount = -1;

        blockFace = makeRectangle(-xPosition, -yPosition, width, height);
    }

    public Color getInnerColor(){
        return this.innerColor;
    }

    public Color getBorderColor(){
        return this.borderColor;
    }

    public void setInnerColor(Color color){
        this.innerColor = color;
    }

    public void setBorderColor(Color color){
        this.borderColor = color;
    }

    public Shape getBlockFace(){
        return  blockFace;
    }

    public boolean impact(Ball b){
        return blockFace.contains(b.getPosition());
    }

    public void setVisible(){
        this.blockFace = makeRectangle(this.xPosition, this.yPosition, this.width, this.height);
    }

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
