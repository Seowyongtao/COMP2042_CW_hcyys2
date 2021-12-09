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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * For setting up the properties of a player
 */
public class Player {

    private Color borderColor = Color.GREEN.darker().darker();
    private Color innerColor = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;

    /**
     * Constructor for Player
     *
     * @param ballPoint Starting point of the ball
     * @param width     Value for the width
     * @param height    Value for the height
     * @param container Area of the wall screen
     */
    public Player(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (ballPoint.getX() - (width / 2)), (int) ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * To check whether the ball impact with player or not
     *
     * @param b Ball object
     * @return true/false
     */
    public boolean impact(Ball b) {
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down);
    }

    /**
     * Move the player according to the moveAmount
     */
    public void move() {
        double x = ballPoint.getX() + moveAmount;
        if (x < min || x > max)
            return;
        ballPoint.setLocation(x, ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int) playerFace.getWidth() / 2, ballPoint.y);
    }

    /**
     * Turn moveAmount to negative
     */
    public void moveLeft() {
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Turn moveAmount to positive
     */
    public void movRight() {
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Turn moveAmount into 0
     */
    public void stop() {
        moveAmount = 0;
    }

    /**
     * Get the shape of the player
     *
     * @return Shape of the player
     */
    public Shape getPlayerFace() {
        return playerFace;
    }

    /**
     * Move player to give point
     *
     * @param p Point object
     */
    public void moveTo(Point p) {
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int) playerFace.getWidth() / 2, ballPoint.y);
    }

    /**
     * Move player back to the starting point
     *
     * @param startingPoint Starting point
     */
    public void reset(Point startingPoint) {
        moveTo(startingPoint);
    }

    /**
     * Get the current inner color of the player
     *
     * @return Current inner color of the player
     */
    public Color getInnerColor() {
        return innerColor;
    }

    /**
     * Get the current border color of the player
     *
     * @return Current border color of the player
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Update the inner color of the player
     *
     * @param color New inner color
     */
    public void setInnerColor(Color color) {
        innerColor = color;
    }

    /**
     * Update the border color of the player
     *
     * @param color New border color
     */
    public void setBorderColor(Color color) {
        borderColor = color;
    }

    /**
     * Get the value of moveAmount
     *
     * @return Value of moveAmount
     */
    public int getMoveAmount() {
        return moveAmount;
    }

    /**
     * Get the value of DEF_MOVE_AMOUNT
     *
     * @return Value of DEF_MOVE_AMOUNT
     */
    public int getDefMoveAmount() {
        return DEF_MOVE_AMOUNT;
    }

    /**
     * Get the current ballPoint
     *
     * @return Current ballPoint
     */
    public Point getBallPoint() {
        return ballPoint;
    }

}
