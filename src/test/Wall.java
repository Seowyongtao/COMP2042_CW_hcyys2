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
import java.util.Random;


public class Wall {

    private Random rnd;
    private Rectangle area;

    Brick[] bricks;
    Ball ball;
    Player player;
    ImpactManager impactManager;
    LevelManager levelManager;
    BrickCount brickCount;

    private Point startPoint;

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        rnd = new Random();

        ball = new RubberBall(ballPos);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;

        impactManager = new ImpactManager(this, area);

        levelManager = new LevelManager(this,area, brickCount, lineCount, brickDimensionRatio);

        this.brickCount = new BrickCount(brickCount);

    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount.setBrickCount(bricks.length);
        ball.setCount(3);
    }

}
