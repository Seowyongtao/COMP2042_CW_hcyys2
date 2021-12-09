package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The container for StartButton and ExitButton
 *
 */
public class HomeMenuView extends JPanel {

    private ExitButton exitButton = new ExitButton();
    private StartButton startButton = new StartButton();

    HomeMenuView(){

        this.setBackground(Color.green);
        this.setBounds(0,0, 450,300);
        this.setLayout(null);


        this.add(startButton);
        this.add(exitButton);

    }

    /**
     * Add ActionListener to exitButton
     *
     * @param listenForExitButton ActionListener object
     */
    public void addExitListener(ActionListener listenForExitButton){

        exitButton.addActionListener(listenForExitButton);

    }

    /**
     * Add ActionListener to startButton
     *
     * @param listenForStartButton ActionListener object
     */
    public void addStartListener(ActionListener listenForStartButton){

        startButton.addActionListener(listenForStartButton);

    }

}