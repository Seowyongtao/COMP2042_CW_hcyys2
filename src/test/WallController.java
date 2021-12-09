package test;

import javax.swing.*;
import java.awt.*;

/**
 * Responsible for creating the behaviours/logics for wall's components
 */
public class WallController{

    private WallView wallView;
    private GameBoard gameBoard;
    private ImpactManager impactManager;
    private ScoreList scoreList;
    LevelManager levelManager;

    /**
     * Constructor for WallController
     *
     * @param wallView WallView object
     * @param gameBoard GameBoard object
     * @param lineCount Number of lines for bricks
     * @param brickDimensionRatio Dimension ratio for brick
     */
    public WallController(WallView wallView, GameBoard gameBoard, int lineCount, double brickDimensionRatio){

        this.wallView = wallView;
        this.gameBoard = gameBoard;

        impactManager = new ImpactManager(wallView,wallView.area);
        levelManager = new LevelManager(wallView,wallView.area, wallView.brickCount.getBrickCount(), lineCount, brickDimensionRatio);

        levelManager.nextLevel();

        gameBoard.gameTimer = new Timer(10, e ->{

            if(levelManager.getLevel() == 6){
                wallView.block1.move();
                wallView.block2.move();
            }

            wallView.player.move();
            wallView.ball.move();
            impactManager.findImpacts();
            wallView.message = String.format("Bricks: %d Balls: %d Score: %d",wallView.brickCount.getBrickCount(),wallView.ball.getCount(), wallView.score.getScore());
            if(wallView.ball.getIsLost()){

                if(wallView.ball.getCount() == 0){
                    wallReset();
                    wallView.message = "Game over";

                    scoreList = new ScoreList(wallView.score.getScore());
                    gameBoard.setShowHighScore(true);
                }

                wallView.player.reset(new Point(300,430));
                wallView.ball.reset(new Point(300,430));

                if(levelManager.getLevel()==5 || levelManager.getLevel()==6){
                    wallView.ball.setXSpeed(3);
                    wallView.ball.setYSpeed(-3);
                }

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

                    if(levelManager.getLevel()==5 || levelManager.getLevel()==6){
                        wallView.ball.setXSpeed(3);
                        wallView.ball.setYSpeed(-3);
                    }

                    wallReset();
                    levelManager.nextLevel();
                }
                else{
                    wallView.message = "ALL WALLS DESTROYED";
                    gameBoard.gameTimer.stop();

                    scoreList = new ScoreList(wallView.score.getScore());
                    gameBoard.setShowHighScore(true);
                }
            }

            gameBoard.repaint();
        });

    }

    /**
     * Repair the bricks, set the brick count to its initial value, and set the ball count to its initial value
     */
    public void wallReset(){

        for(Brick b : wallView.bricks)
            b.repair();
        wallView.brickCount.setBrickCount(wallView.bricks.length);
        wallView.ball.setCount(3);

    }

    /**
     * Return scoreList
     *
     * @return scoreList
     */
    public ScoreList getScoreList(){
        return this.scoreList;
    }

}

