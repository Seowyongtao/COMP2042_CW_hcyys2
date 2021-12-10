package test;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The container for StartButton, ExitButton, InfoButton and BackGroundImage
 *
 */
public class HomeMenuView extends JPanel {

    private ExitButton exitButton = new ExitButton(150, 250);
    private StartButton startButton = new StartButton(150, 180);
    private InfoButton infoButton = new InfoButton(150, 215);
    private BackGroundImage backGroundImage = new BackGroundImage();

    HomeMenuView(){

        this.setBounds(0,0, 450,300);
        this.setLayout(null);

        this.add(startButton);
        this.add(infoButton);
        this.add(exitButton);
        this.add(backGroundImage);
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

    /**
     * Add ActionListener to infoButton
     *
     * @param listenForInfoButton ActionListener object
     */
    public void addInfoListener(ActionListener listenForInfoButton){

        infoButton.addActionListener(listenForInfoButton);

    }

}