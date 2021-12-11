/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;
import java.util.Random;

/**
 * Responsible for creating objects that are related to the game such as ball, bricks, player for GameBoard to draw
 */
public class WallView{

    private final Rectangle area;
    private Brick[] bricks;
    private final Ball ball;
    private final Player player;
    private final BrickCount brickCount;
    private String message;
    private Color messageColor;
    private final Score score;
    private final Block block1;
    private final Block block2;

    /**
     * Constructor for WallView
     *
     * @param drawArea Area for the wall screen
     * @param ballPos Staring point for the ball
     */
    public WallView(Rectangle drawArea, Point ballPos){

        message ="";
        messageColor = Color.BLUE;

        ball = new RubberBall(ballPos);

        block1 = new Block(120, 190, 80,10);
        block2 = new Block(390, 140, 80,10);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        brickCount = new BrickCount(30);

        score = new Score();
    }

    /**
     * Get the area of the game screen
     *
     * @return Area for the game screen
     */
    public Rectangle getArea(){
        return area;
    }

    /**
     * Get a set of bricks
     *
     * @return A set of bricks
     */
    public Brick[] getBricks(){
        return bricks;
    }

    /**
     * Update the set of bricks
     *
     * @param bricks Set of brick object
     */
    public void setBricks(Brick[] bricks){
        this.bricks = bricks;
    }

    /**
     * Get the ball object
     *
     * @return Ball object
     */
    public Ball getBall(){
        return ball;
    }

    /**
     * Get the player object
     *
     * @return Player object
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Get BrickCount object
     *
     * @return BrickCount object
     */
    public BrickCount getBrickCount(){
        return brickCount;
    }

    /**
     * Get the message strings
     *
     * @return Message strings
     */
    public String getMessage(){
        return message;
    }

    /**
     * Update the message
     *
     * @param newMessage New message
     */
    public void setMessage(String newMessage){
        message = newMessage;
    }

    /**
     * Get the current color of the message
     *
     * @return Current color of the message
     */
    public Color getMessageColor(){
        return messageColor;
    }

    /**
     * Update the color of the message
     *
     * @param newColor New color for the message
     */
    public void setMessageColor(Color newColor){
        messageColor = newColor;
    }

    /**
     * Get the Score object
     *
     * @return Score object
     */
    public Score getScore(){
        return score;
    }

    /**
     * Get the Block object block1
     *
     * @return Block object block1
     */
    public Block getBlock1(){
        return block1;
    }

    /**
     * Get the Block object block2
     *
     * @return Block object block2
     */
    public Block getBlock2(){
        return block2;
    }


}

