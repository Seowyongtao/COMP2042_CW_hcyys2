package test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsoleController {

    private DebugConsoleView theView;
    private Wall wall;
    private GameBoard gameBoard;
    private JFrame gameFrame;

    public DebugConsoleController(DebugConsoleView theView, JFrame gameFrame, Wall wall, GameBoard gameBoard){

        this.theView = theView;
        this.gameFrame = gameFrame;
        this.wall = wall;
        this.gameBoard = gameBoard;

        this.theView.addDebugConsoleWindowListener(new DebugConsoleWindowListener());
        this.theView.addResetBallsListener(new ResetBallsListener());
        this.theView.addSkipLevelListener(new SkipLevelListener());
        this.theView.addBallXSpeedListener(new BallXSpeedSlider());
        this.theView.addBallYSpeedListener(new BallYSpeedSlider());

    }

    class SkipLevelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Skip Level");
            wall.levelManager.nextLevel();
        }

    }

    class ResetBallsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset Ball Count");
            wall.ball.resetBallCount();
        }

    }

    class BallXSpeedSlider implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            wall.ball.setXSpeed(theView.getBallXSpeed().getValue());
        }
    }

    class BallYSpeedSlider implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            wall.ball.setYSpeed(theView.getBallYSpeed().getValue());
        }
    }

    class DebugConsoleWindowListener implements WindowListener {


        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("WINDOW CLOSED");
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

        @Override
        public void windowActivated(WindowEvent e) {
            System.out.println("WINDOW ACTIVATED");

            int x = ((gameFrame.getWidth() - theView.getWidth()) / 2) + gameFrame.getX();
            int y = ((gameFrame.getHeight() - theView.getHeight()) / 2) + gameFrame.getY();
            theView.setLocation(x,y);

            Ball b = wall.ball;
            theView.setValues(b.getSpeedX(),b.getSpeedY());
        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

    }
}
