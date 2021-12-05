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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.geom.Point2D;
import java.util.Random;


public class WallView{

    private Random rnd;
    private Point startPoint;

    Rectangle area;
    Brick[] bricks;
    Ball ball;
    Player player;
    ImpactManager impactManager;
    LevelManager levelManager;
    BrickCount brickCount;
    String message;
    Color messageColor;

    public WallView(Rectangle drawArea, Point ballPos){

        message ="";
        messageColor = Color.BLUE;

        this.startPoint = new Point(ballPos);

        rnd = new Random();

        ball = new RubberBall(ballPos);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        this.brickCount = new BrickCount(30);

    }


}
