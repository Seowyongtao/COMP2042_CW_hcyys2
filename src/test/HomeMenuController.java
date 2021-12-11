package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Responsible for creating the behaviours/logics for home menu's components
 */
public class HomeMenuController {

    private final GameFrame owner;

    /**
     * Constructor for HomeMenu Controller.
     *
     * @param theView HomeMenuView object
     * @param owner GameFrame object
     */
    public HomeMenuController(HomeMenuView theView, GameFrame owner){

        this.owner = owner;

        theView.addExitListener(new ExitListener());
        theView.addStartListener(new StartListener());
        theView.addInfoListener(new InfoListener());

    }

    /**
     * Action listener for exit button
     */
    public class ExitListener implements ActionListener {
        /**
         * Quit the game when the exit button is clicked
         *
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }

    }

    /**
     * Action listener for start button
     */
    public class StartListener implements ActionListener {

        /**
         * Start the game when the start button is clicked
         *
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
            owner.enableGameBoard();
        }
    }

    /**
     * Action listener for info button
     */
    public class InfoListener implements ActionListener {

        /**
         * Open Info page when info button is clicked
         *
         * @param e ActionEvent object
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            owner.openInfoView();
        }
    }
}