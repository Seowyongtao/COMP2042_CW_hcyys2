package test;

import javax.swing.*;

/**
 * For setting up the properties for home menu background image
 */
public class BackGroundImage extends JLabel {

    public  BackGroundImage(){
        ImageIcon bgImage = new ImageIcon("src/test/bgImage.png");
        this.setIcon(bgImage);
        this.setBounds(0,0,450,300);
    }
}
