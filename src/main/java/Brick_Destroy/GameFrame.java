/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Brick_Destroy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * The container for GameBoard,HomeMenuView, and InfoView.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private boolean gaming;

    private final GameBoard gameBoard;
    private final HomeMenuView homeMenuView;
    private final InfoView infoView;

    /**
     * Constructor for GameFrame.
     */
    public GameFrame(){

        this.initialize();
        gaming = false;

        homeMenuView = new HomeMenuView();
        HomeMenuController homeMenuController = new HomeMenuController(homeMenuView, this);
        this.add(homeMenuView);
        this.setVisible(true);

        infoView = new InfoView();
        InfoController infoContoller = new InfoController(infoView, this);

        gameBoard = new GameBoard(this);
    }

    /**
     * Initialize the initial properties of frame.
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 300);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * Open Info Page
     */
    public void openInfoView(){
        this.dispose();
        this.remove(homeMenuView);
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 300);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.add(infoView);
        this.setVisible(true);
    }

    /**
     * Open Home Menu page
     */
    public void openHomeMenuView(){
        this.dispose();
        this.remove(infoView);
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450, 300);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.add(homeMenuView);
        this.setVisible(true);
    }

    /**
     *Start Game
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuView);
        this.setSize(600, 480);
        this.setUndecorated(false);
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.autoLocate();
        this.setLayout(new BorderLayout());
        this.add(gameBoard,BorderLayout.CENTER);
        this.setVisible(true);
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * Update gaming to true when the window gained focus.
     *
     * @param windowEvent WindowEvent object
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * Call the onLostFocus() method when the window lost focus and gaming is equal to true.
     *
     * @param windowEvent WindowEvent object
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
