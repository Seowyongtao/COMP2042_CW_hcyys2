package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton implements ActionListener {

    private GameFrame owner;

    public StartButton(GameFrame owner){

        this.owner = owner;

        this.setBounds(150, 215, 150, 35);
        this.setText("START");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            owner.enableGameBoard();
        }

    }

}
