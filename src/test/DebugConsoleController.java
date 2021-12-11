package test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Responsible for creating the behaviours/logics for debug console's components
 */
public class DebugConsoleController {

    private final DebugConsoleView theView;
    private final WallView wallView;
    private final WallController wallController;
    private final GameBoard gameBoard;
    private final JFrame gameFrame;

    /**
     * Constructor for DebugConsoleController.
     *
     * @param theView DebugConsoleView object
     * @param gameFrame JFrame object
     * @param wallView WallView object
     * @param wallController WallController object
     * @param gameBoard GameBoard object
     */
    public DebugConsoleController(DebugConsoleView theView, JFrame gameFrame, WallView wallView,WallController wallController, GameBoard gameBoard){

        this.theView = theView;
        this.gameFrame = gameFrame;
        this.wallView = wallView;
        this.wallController = wallController;
        this.gameBoard = gameBoard;

        this.theView.addDebugConsoleWindowListener(new DebugConsoleWindowListener());
        this.theView.addResetBallsListener(new ResetBallsListener());
        this.theView.addSkipLevelListener(new SkipLevelListener());
        this.theView.addBallXSpeedListener(new BallXSpeedSlider());
        this.theView.addBallYSpeedListener(new BallYSpeedSlider());
        this.theView.addNightModeListener(new NightModeListener(theView.getNightModeOn(), theView.getNightModeOff()));

    }

    /**
     * ActionListener for skip level button
     */
    class SkipLevelListener implements ActionListener {
        /**
         * Go to next level when the skip level button is clicked
         *
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
            wallController.getLevelManager().nextLevel();
        }

    }

    /**
     * ActionListener for reset ball button
     */
    class ResetBallsListener implements ActionListener {
        /**
         * Reset the ball's count and the paddle's width when the reset ball button is clicked
         *
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset Ball Count");
            wallView.getBall().resetBallCount();
            wallView.getPaddle().resetWidth();
        }

    }

    /**
     * ActionListener for night mode radio buttons
     */
    class NightModeListener implements  ActionListener{

        private final JRadioButton NightModeOn;
        private final JRadioButton NightModeOff;

        /**
         * Constructor for NightModeListener
         *
         * @param NightModeOn JRadioButton object
         * @param NightModeOff JRadioButton object
         */
        public NightModeListener(JRadioButton NightModeOn, JRadioButton NightModeOff){

            this.NightModeOn = NightModeOn;
            this.NightModeOff = NightModeOff;
        }

        /**
         * Change to night mode if the action event is NightModeOn, and turn off the night mode if the action event is NightModeOff
         *
         * @param e ActionEvent object
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==NightModeOn){
                gameBoard.setBgColor(Color.BLACK);
                wallView.getBall().setInnerColor(new Color(255, 255, 224));
                wallView.getBall().setBorderColor(new Color(255, 255, 224));
                wallView.getPaddle().setInnerColor(new Color(144, 238, 144));
                wallView.getPaddle().setBorderColor(new Color(144, 238, 144));
                wallView.setMessageColor(Color.CYAN);
                wallView.getBlock1().setInnerColor(Color.pink);
                wallView.getBlock1().setBorderColor(Color.pink);
                wallView.getBlock2().setInnerColor(Color.pink);
                wallView.getBlock2().setBorderColor(Color.pink);
            }
            else if(e.getSource()==NightModeOff){
                gameBoard.setBgColor(Color.WHITE);
                wallView.getBall().setInnerColor(new Color(255, 219, 88));
                wallView.getBall().setBorderColor(new Color(255, 219, 88).darker().darker());
                wallView.getPaddle().setInnerColor(Color.GREEN);
                wallView.getPaddle().setBorderColor(Color.GREEN.darker().darker());
                wallView.setMessageColor(Color.BLUE);
                wallView.getBlock1().setInnerColor(Color.red);
                wallView.getBlock1().setBorderColor(Color.red.darker().darker());
                wallView.getBlock2().setInnerColor(Color.red);
                wallView.getBlock2().setBorderColor(Color.red.darker().darker());
            }
        }
    }

    /**
     * ChangeListener for ballXSpeed slider
     */
    class BallXSpeedSlider implements ChangeListener {
        /**
         * Update the value for Ball's XSpeed according to the value getting from the ballXSpeed slider
         * @param e ChangeEvent object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            wallView.getBall().setXSpeed(theView.getBallXSpeed().getValue());
        }
    }

    /**
     * ChangeListener for ballYSpeed slider
     */
    class BallYSpeedSlider implements ChangeListener {
        /**
         * Update the value for Ball's YSpeed according to the value getting from the ballYSpeed slider
         * @param e ChangeEvent Object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            wallView.getBall().setYSpeed(theView.getBallYSpeed().getValue());
        }
    }

    /**
     * WindowListener for debug console
     */
    class DebugConsoleWindowListener implements WindowListener {


        @Override
        public void windowOpened(WindowEvent e) {

        }

        /**
         * Call gameBoard's repaint method when the debug console's window is closed
         *
         * @param e WindowEvent object
         */
        @Override
        public void windowClosing(WindowEvent e) {
            gameBoard.repaint();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        /**
         * Set the location of the debug console to the middle when its window is activated
         *
         * @param e WindowEvent object
         */
        @Override
        public void windowActivated(WindowEvent e) {

            int x = ((gameFrame.getWidth() - theView.getWidth()) / 2) + gameFrame.getX();
            int y = ((gameFrame.getHeight() - theView.getHeight()) / 2) + gameFrame.getY();
            theView.setLocation(x,y);

            Ball b = wallView.getBall();
            theView.setValues(b.getSpeedX(),b.getSpeedY());
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }
}


