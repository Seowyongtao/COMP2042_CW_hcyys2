package test;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * For handling all the logics that are related to the impact between ball, wall, player and brick
 */
public class ImpactManager {

    private WallView wall;
    private Rectangle area;

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
     * If the ball impact with the player, reverse the ball's Y value <br>
     * If the ball impact with the brick, increase the score by 10 and decrement brick count by 1<br>
     * If the ball impact with the side border, reverse the ball's X value <br>
     * If the ball impact with the upper side border, reverse the ball's Y value <br>
     * If the ball go beyong the down side border, decrement ball count by 1, decrement score by 10, and set ball's islost to true
     *
     */
    public void findImpacts(){
        if(wall.player.impact(wall.ball)){
            wall.ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            wall.score.scoreIncrement(10);
            wall.brickCount.brickCountDecrement();
        }
        else if(impactBorder()) {
            wall.ball.reverseX();
        }
        else if(wall.ball.getPosition().getY() < area.getY()){
            wall.ball.reverseY();
        }
        else if(wall.ball.getPosition().getY() > area.getY() + area.getHeight()){

            wall.ball.count_decrement();
            wall.score.scoreDecrement(10);
            wall.ball.setIsLost(true);
        }
        else if(wall.block1.impact(wall.ball)){
            wall.ball.reverseY();
        }
        else if(wall.block2.impact(wall.ball)){
            wall.ball.reverseY();
        }
    }

    private boolean impactWall(){
        for(Brick b : wall.bricks){
            switch(b.findImpact(wall.ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    wall.ball.reverseY();
                    return b.setImpact(wall.ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    wall.ball.reverseY();
                    return b.setImpact(wall.ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    wall.ball.reverseX();
                    return b.setImpact(wall.ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    wall.ball.reverseX();
                    return b.setImpact(wall.ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = wall.ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }
}