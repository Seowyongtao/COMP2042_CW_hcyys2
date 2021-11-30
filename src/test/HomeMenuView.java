package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

    void addExitListener(ActionListener listenForExitButton){

        exitButton.addActionListener(listenForExitButton);

    }

    void addStartListener(ActionListener listenForStartButton){

        startButton.addActionListener(listenForStartButton);

    }

}