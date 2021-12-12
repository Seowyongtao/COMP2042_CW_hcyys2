package Brick_Destroy;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The container for StartButton, ExitButton, InfoButton and BackGroundImage
 *
 */
public class HomeMenuView extends JPanel {

    private final ExitButton exitButton = new ExitButton(150, 250);
    private final StartButton startButton = new StartButton(150, 180);
    private final InfoButton infoButton = new InfoButton(150, 215);

    HomeMenuView(){

        this.setBounds(0,0, 450,300);
        this.setLayout(null);

        this.add(startButton);
        this.add(infoButton);
        this.add(exitButton);
        BackGroundImage backGroundImage = new BackGroundImage();
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