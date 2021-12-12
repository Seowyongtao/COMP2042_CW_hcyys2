package Brick_Destroy;

import javax.swing.*;

/**
 * For setting up properties for info button
 */
public class InfoButton extends JButton {
    /**
     * Constructor for InfoButton
     *
     * @param xCoordinate Value for x coordinate
     * @param yCoordinate Value for y coordinate
     */
    public InfoButton(int xCoordinate, int yCoordinate){
        this.setBounds(xCoordinate, yCoordinate, 150, 35);
        this.setText("INFO");
    }
}