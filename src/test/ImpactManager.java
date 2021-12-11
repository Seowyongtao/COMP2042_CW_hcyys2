package test;

import java.awt.*;
import java.awt.geom.Point2D;
/**
 * For handling all the logics that are related to the impact between ball, wall, paddle and brick
 */
public class ImpactManager {

    private final WallView wall;
    private final Rectangle area;

    /**
     * Constructor for ImpactManager
     *
     * @param wall WallView object
     * @param area Area of the wall screen
     */
    public ImpactManager(WallView wall, Rectangle area){

        this.wall = wall;
        this.area = area;

    }

    /**
     * If the ball impact with the paddle, reverse the ball's Y value <br>
     * If the ball impact with the brick, increase the score by 10 and decrement brick count by 1<br>
     * If the ball impact with the side border, reverse the ball's X value <br>
     * If the ball impact with the upper side border, reverse the ball's Y value <br>
     * If the ball go beyond the downside border, decrement ball count by 1, decrement score by 10, and set ball's islost to true
     *
     */
    public void findImpacts(){
        if(wall.getPaddle().impact(wall.getBall())){
            wall.getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            wall.getScore().scoreIncrement(10);
            wall.getBrickCount().brickCountDecrement();
        }
        else if(impactBorder()) {
            wall.getBall().reverseX();
        }
        else if(wall.getBall().getPosition().getY() < area.getY()){
            wall.getBall().reverseY();
        }
        else if(wall.getBall().getPosition().getY() > area.getY() + area.getHeight()){

            wall.getBall().count_decrement();
            wall.getScore().scoreDecrement(10);
            wall.getBall().setIsLost(true);
        }
        else if(wall.getBlock1().impact(wall.getBall())){
            wall.getBall().reverseY();
        }
        else if(wall.getBlock2().impact(wall.getBall())){
            wall.getBall().reverseY();
        }
    }

    private boolean impactWall(){
        for(Brick b : wall.getBricks()){
            switch (b.findImpact(wall.getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT -> {
                    wall.getBall().reverseY();
                    return b.setImpact(wall.getBall().getDown(), Brick.Crack.UP);
                }
                case Brick.DOWN_IMPACT -> {
                    wall.getBall().reverseY();
                    return b.setImpact(wall.getBall().getUp(), Brick.Crack.DOWN);
                }

                //Horizontal Impact
                case Brick.LEFT_IMPACT -> {
                    wall.getBall().reverseX();
                    return b.setImpact(wall.getBall().getRight(), Brick.Crack.RIGHT);
                }
                case Brick.RIGHT_IMPACT -> {
                    wall.getBall().reverseX();
                    return b.setImpact(wall.getBall().getLeft(), Brick.Crack.LEFT);
                }
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = wall.getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }
}