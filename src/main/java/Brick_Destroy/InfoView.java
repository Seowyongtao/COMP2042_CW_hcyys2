package Brick_Destroy;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The container for OkButton and InfoImage
 */
public class InfoView extends JPanel {

    private final OkButton okButton = new OkButton(150, 255);

    /**
     * Constructor for InfoView
     */
    public InfoView(){
        this.setBounds(0,0, 450,301);
        this.setLayout(null);

        this.add(okButton);
        InfoImage infoImage = new InfoImage();
        this.add(infoImage);
    }

    /**
     * Add ActionListener to OkButton
     *
     * @param listenForOkButton ActionListener object
     */
    public void addOkListener(ActionListener listenForOkButton){

        okButton.addActionListener(listenForOkButton);

    }
}
