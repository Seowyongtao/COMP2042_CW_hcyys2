package test;

import javax.swing.*;

/**
 * For setting up properties for ok button
 */
public class OkButton extends JButton {
    /**
     * Constructor for OkButton
     *
     * @param xCoordinate Value for x coordinate
     * @param yCoordinate Value for y coordinate
     */
    public OkButton(int xCoordinate, int yCoordinate){
        this.setBounds(xCoordinate, yCoordinate, 150, 35);
        this.setText("OK");
    }
}
