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


public class WallView{

    private Random rnd;
    private Point startPoint;

    Rectangle area;
    Brick[] bricks;
    Ball ball;
    Player player;
    BrickCount brickCount;
    String message;
    Color messageColor;
    Score score;
    Block block1;
    Block block2;

    public WallView(Rectangle drawArea, Point ballPos){

        message ="";
        messageColor = Color.BLUE;

        this.startPoint = new Point(ballPos);

        rnd = new Random();

        ball = new RubberBall(ballPos);

        block1 = new Block(120, 190, 80,10);
        block2 = new Block(390, 140, 80,10);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        this.brickCount = new BrickCount(30);

        score = new Score();
    }


}
