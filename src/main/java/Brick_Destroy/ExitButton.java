package Brick_Destroy;

import javax.swing.*;

/**
 * For setting up the properties of exit button
 */
public class ExitButton extends JButton{
    /**
     * Constructor for exit button
     *
     * @param xCoordinate Value for x coordinate
     * @param yCoordinate Value for y coordinate
     */
    public ExitButton(int xCoordinate, int yCoordinate){
        this.setBounds(xCoordinate, yCoordinate, 150, 35);
        this.setText("EXIT");
    }

}

