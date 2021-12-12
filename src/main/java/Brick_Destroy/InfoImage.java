package Brick_Destroy;

import javax.swing.*;

/**
 * For setting up the properties for info image
 */
public class InfoImage extends JLabel {

    public  InfoImage(){
        ImageIcon infoImage = new ImageIcon("src/main/resources/infoImage.png");
        this.setIcon(infoImage);
        this.setBounds(0,0,450,300);
    }
}
