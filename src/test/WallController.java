package test;

import javax.swing.*;
import java.awt.*;

public class WallController{

    private LevelManager levelManeger;
    private WallView wallView;
    private GameBoard gameBoard;
    private DebugConsoleView debugConsoleView;
    private ImpactManager impactManager;
    LevelManager levelManager;


    public WallController(WallView wallView, GameBoard gameBoard, int lineCount, double brickDimensionRatio){

        this.wallView = wallView;
        this.gameBoard = gameBoard;

        impactManager = new ImpactManager(wallView,wallView.area);
        levelManager = new LevelManager(wallView,wallView.area, wallView.brickCount.getBrickCount(), lineCount, brickDimensionRatio);

        levelManager.nextLevel();


        gameBoard.gameTimer = new Timer(10, e ->{
            wallView.player.move();
            wallView.ball.move();
            impactManager.findImpacts();
            wallView.message = String.format("Bricks: %d Balls: %d Score: %d",wallView.brickCount.getBrickCount(),wallView.ball.getCount(), wallView.score.getScore());
            if(wallView.ball.getIsLost()){
                if(wallView.ball.getCount() == 0){
                    wallReset();
                    wallView.message = "Game over";
                }
                wallView.player.reset(new Point(300,430));
                wallView.ball.reset(new Point(300,430));
                gameBoard.gameTimer.stop();
            }
            else if(wallView.brickCount.getBrickCount() == 0){

                if(wallView.ball.getCount() == 3){
                    wallView.score.scoreIncrement(30);
                }

                if(levelManager.hasLevel()){
                    wallView.message = "Go to Next Level";
                    gameBoard.gameTimer.stop();
                    wallView.player.reset(new Point(300,430));
                    wallView.ball.reset(new Point(300,430));
                    wallReset(); //new
                    levelManager.nextLevel();
                }
                else{
                    wallView.message = "ALL WALLS DESTROYED";
                    gameBoard.gameTimer.stop();
                }
            }

            gameBoard.repaint();
        });

    }

    public void wallReset(){
        for(Brick b : wallView.bricks)
            b.repair();
        wallView.brickCount.setBrickCount(wallView.bricks.length);
        wallView.ball.setCount(3);
    }

}
