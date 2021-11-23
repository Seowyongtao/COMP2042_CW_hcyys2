package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends JButton implements ActionListener {

    public ExitButton(){
        this.setBounds(150, 250, 150, 35);
        this.setText("EXIT");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

}

