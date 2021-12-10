package test;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The container for OkButton and InfoImage
 */
public class InfoView extends JPanel {

    private InfoImage infoImage = new InfoImage();
    private OkButton okButton = new OkButton(150, 255);

    /**
     * Constructor for InfoView
     */
    public InfoView(){

        this.setBounds(0,0, 450,301);
        this.setLayout(null);

        this.add(okButton);
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
