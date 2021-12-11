package test;

import javax.swing.*;
import java.awt.*;

/**
 * Responsible for creating the behaviours/logics for wall's components
 */
public class WallController{

    private final WallView wallView;
    private final ImpactManager impactManager;
    private ScoreList scoreList;
    private final LevelManager levelManager;

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

        impactManager = new ImpactManager(wallView,wallView.getArea());
        levelManager = new LevelManager(wallView,wallView.getArea(), wallView.getBrickCount().getBrickCount(), lineCount, brickDimensionRatio);

        levelManager.nextLevel();
        gameBoard.gameTimer = new Timer(10, e ->{

            if(levelManager.getLevel() == 6){
                wallView.getBlock1().move();
                wallView.getBlock2().move();
            }

            wallView.getPaddle().move();
            wallView.getBall().move();

            impactManager.findImpacts();
            wallView.setMessage(String.format("Balls: %d Score: %d",wallView.getBall().getCount(), wallView.getScore().getScore()));

            if(wallView.getBall().getIsLost()){

                if(wallView.getBall().getCount() == 0){
                    wallReset();
                    wallView.setMessage("Game Over");

                    scoreList = new ScoreList(wallView.getScore().getScore());
                    gameBoard.setShowHighScore(true);
                }

                wallView.getPaddle().widthDecrement();
                wallView.getPaddle().reset(new Point(300,430));
                wallView.getBall().reset(new Point(300,430));

                if(levelManager.getLevel()==5 || levelManager.getLevel()==6){
                    wallView.getBall().setXSpeed(3);
                    wallView.getBall().setYSpeed(-3);
                }

                gameBoard.gameTimer.stop();
            }
            else if(wallView.getBrickCount().getBrickCount() == 0){

                if(wallView.getBall().getCount() == 3){
                    wallView.getScore().scoreIncrement(30);
                }

                if(levelManager.hasLevel()){
                    wallView.setMessage("Go to Next Level");
                    gameBoard.gameTimer.stop();
                    wallView.getPaddle().resetWidth();
                    wallView.getPaddle().reset(new Point(300,430));
                    wallView.getBall().reset(new Point(300,430));

                    if(levelManager.getLevel()==5 || levelManager.getLevel()==6){
                        wallView.getBall().setXSpeed(3);
                        wallView.getBall().setYSpeed(-3);
                    }

                    wallReset();
                    levelManager.nextLevel();
                }
                else{
                    wallView.setMessage("ALL WALLS DESTROYED");
                    gameBoard.gameTimer.stop();

                    scoreList = new ScoreList(wallView.getScore().getScore());
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

        for(Brick b : wallView.getBricks())
            b.repair();
        wallView.getBrickCount().setBrickCount(wallView.getBricks().length);
        wallView.getBall().setCount(3);

    }

    /**
     * Return scoreList
     *
     * @return scoreList
     */
    public ScoreList getScoreList(){
        return this.scoreList;
    }

    /**
     * Get LevelManager object
     *
     * @return LevelManager object
     */
    public LevelManager getLevelManager(){
        return levelManager;
    }

}

