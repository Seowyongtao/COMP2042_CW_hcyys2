package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeMenuController {

    private HomeMenuView theView;
    private GameFrame owner;

    public HomeMenuController(HomeMenuView theView, GameFrame owner){

        this.theView = theView;
        this.owner = owner;

        this.theView.addExitListener(new ExitListener());
        this.theView.addStartListener(new StartListener());

    }

    class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }

    }

    class StartListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Start Game");
            owner.enableGameBoard();
        }

    }
}