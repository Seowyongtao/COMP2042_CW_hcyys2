package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * For setting up the properties of start button
 */
public class StartButton extends JButton{
    /**
     * Constructor for StartButton
     *
     * @param xCoordinate Value for x coordinate
     * @param yCoordinate Value for y coordinate
     */
    public StartButton(int xCoordinate, int yCoordinate){

        this.setBounds(xCoordinate, yCoordinate, 150, 35);
        this.setText("START");
    }


}
