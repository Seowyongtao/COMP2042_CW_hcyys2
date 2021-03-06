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
package Brick_Destroy;

import java.awt.*;

/**
 * For setting up the properties of a paddle
 */
public class Paddle {

    private Color borderColor = Color.GREEN.darker().darker();
    private Color innerColor = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle paddleFace;
    private final Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;
    private final int initialWidth;
    private int width;
    private final int height;
    private final Rectangle container;

    /**
     * Constructor for Paddle
     *
     * @param ballPoint Starting point of the ball
     * @param width     Value for the width
     * @param height    Value for the height
     * @param container Area of the wall screen
     */
    public Paddle(Point ballPoint, int width, int height, Rectangle container) {
        moveAmount = 0;
        this.ballPoint = ballPoint;
        this.initialWidth = width;
        this.width = width;
        this.height = height;
        this.container = container;
        paddleFace = makeRectangle(this.width, this.height);
        min = container.x + (this.width / 2);
        max = min + container.width - this.width;
    }

    private Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (ballPoint.getX() - (width / 2)), (int) ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * To check whether the ball impact with paddle or not
     *
     * @param b Ball object
     * @return true/false
     */
    public boolean impact(Ball b) {
        return paddleFace.contains(b.getPosition()) && paddleFace.contains(b.getDown());
    }

    /**
     * Move the paddle according to the moveAmount
     */
    public void move() {
        double x = ballPoint.getX() + moveAmount;
        if (x < min || x > max)
            return;
        ballPoint.setLocation(x, ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth() / 2, ballPoint.y);
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
     * Get the shape of the paddle
     *
     * @return Shape of the paddle
     */
    public Shape getPaddleFace() {
        return paddleFace;
    }

    /**
     * Move paddle to give point
     *
     * @param p Point object
     */
    public void moveTo(Point p) {
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth() / 2, ballPoint.y);
    }

    /**
     * Move paddle back to the starting point
     *
     * @param startingPoint Starting point
     */
    public void reset(Point startingPoint) {
        moveTo(startingPoint);
    }

    /**
     * Get the current inner color of the paddle
     *
     * @return Current inner color of the paddle
     */
    public Color getInnerColor() {
        return innerColor;
    }

    /**
     * Get the current border color of the paddle
     *
     * @return Current border color of the paddle
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Update the inner color of the paddle
     *
     * @param color New inner color
     */
    public void setInnerColor(Color color) {
        innerColor = color;
    }

    /**
     * Update the border color of the paddle
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

    /**
     * Decrement the width by 30
     */
    public void widthDecrement(){
        this.width = this.width - 25;
        min = container.x + (this.width / 2);
        max = min + container.width - this.width;
        paddleFace = makeRectangle(width, height);
    }

    /**
     * Reset the width to its initial width
     */
    public void resetWidth(){
        this.width = initialWidth;
        min = container.x + (this.width / 2);
        max = min + container.width - this.width;
        paddleFace = makeRectangle(width, height);
    }

}
