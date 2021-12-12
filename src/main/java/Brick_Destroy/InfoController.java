package Brick_Destroy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Responsible for creating the behaviours/logics for info page's components
 */
public class InfoController {

    private final InfoView theView;
    private final GameFrame owner;

    /**
     * Constructor for InfoController
     *
     * @param theView InfoView object
     * @param owner GameFrame object
     */
    public InfoController(InfoView theView, GameFrame owner){

        this.theView = theView;
        this.owner = owner;

        this.theView.addOkListener(new OkListener());

    }

    /**
     * Action listener for ok button
     */
    public class OkListener implements ActionListener {

        /**
         * Open home menu page when the ok button is clicked
         *
         * @param e ActionEvent object
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            owner.openHomeMenuView();
        }
    }
}
