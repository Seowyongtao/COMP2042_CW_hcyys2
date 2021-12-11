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
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;


/**
 * The container for Wall and Debug Console and responsible for drawing components for wall, pause menu and high score board
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static Color bgColor = Color.WHITE;

    private int strLen;
    private boolean showPauseMenu;
    private boolean showHighScore;
    private final Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;

    private final DebugConsoleView debugConsoleView;
    private final DebugConsoleController debugConsoleController;
    private final WallView wallView;
    private final WallController wallController;
    public  Timer gameTimer;

    /**
     * Constructor for GameBoard
     *
     * @param owner JFrame object
     */
    public GameBoard(JFrame owner){

        super();
        strLen = 0;
        showPauseMenu = false;
        showHighScore = false;
        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        this.initialize();
        wallView = new WallView(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        wallController = new WallController(wallView, this,3,6/2);

        debugConsoleView = new DebugConsoleView();
        debugConsoleController = new DebugConsoleController(debugConsoleView, owner, wallView, wallController, this);

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Draw ball, bricks, paddle, blocks, pause menu, high score board
     *
     * @param g Graphics object
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(wallView.getMessageColor());
        g2d.drawString(wallView.getMessage(),250,225);

        drawBall(wallView.getBall(),g2d);

        for(Brick b : wallView.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPaddle(wallView.getPaddle(),g2d);
        drawBlock(wallView.getBlock1(), wallView.getBlock2(), g2d);

        if(showPauseMenu)
            drawObscurePauseMenu(g2d);

        if(showHighScore)
            drawObscureHighScoreBoard(g2d, wallController.getScoreList().getHighScoreList());

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(bgColor);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());

        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPaddle(Paddle p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPaddleFace();
        g2d.setColor(p.getInnerColor());
        g2d.fill(s);

        g2d.setColor(p.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawBlock(Block b1, Block b2, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s1 = b1.getBlockFace();
        g2d.setColor(b1.getInnerColor());
        g2d.fill(s1);

        g2d.setColor(b1.getBorderColor());
        g2d.draw(s1);

        Shape s2 = b2.getBlockFace();
        g2d.setColor(b2.getInnerColor());
        g2d.fill(s2);

        g2d.setColor(b2.getBorderColor());
        g2d.draw(s2);

        g2d.setColor(tmp);
    }

    private void drawObscurePauseMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void drawObscureHighScoreBoard(Graphics2D g2d, ArrayList<Integer> top3ScoreList){
        obscureGameBoard(g2d);
        drawHighScoreBoard(g2d, top3ScoreList);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){

        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;

        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    private void drawHighScoreBoard(Graphics2D g2d, ArrayList<Integer> top3ScoreList){

        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();

        g2d.setFont(new Font("Monospaced",Font.PLAIN,TEXT_SIZE));
        g2d.setColor(Color.GREEN);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds("High Score:",frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString("TOP 3 SCORE",x,y);

        x = this.getWidth() / 4;
        y = this.getHeight() / 4;

        try
        {
            String top1 = "1. " + top3ScoreList.get(0);
            g2d.drawString(top1,x,y);
        }
        catch(Exception e)
        {
            String top1 = "1. 0" ;
            g2d.drawString(top1,x,y);
        }

        y *= 2;

        try
        {
            String top2 = "2. " + top3ScoreList.get(1);
            g2d.drawString(top2,x,y);
        }
        catch(Exception e)
        {
            String top2 = "2. 0";
            g2d.drawString(top2,x,y);
        }

        y *= 3.0/2;

        try
        {
            String top3 = "3. " + top3ScoreList.get(2);
            g2d.drawString(top3,x,y);
        }
        catch(Exception e)
        {
            String top3 = "3. 0";
            g2d.drawString(top3,x,y);
        }

        x = (this.getWidth() - strLen) / 3;
        y *= 1.25;

        String playerScore = "YOUR SCORE: " + this.wallView.getScore().getScore();
        g2d.drawString(playerScore,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    /**
     * Update bgColor
     *
     * @param color Desired color
     */
    public void setBgColor (Color color){
        bgColor = color;
    }

    /**
     * Update the showHighScore
     *
     * @param status true/false
     */
    public void setShowHighScore(boolean status){
        showHighScore = status;
    }

    /**
     * Stop the gameTimer,change the message to "Focus Lost", and repaint
     */
    public void onLostFocus(){
        gameTimer.stop();
        wallView.setMessage("Focus Lost");
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Press A to move the paddle left <br>
     * Press D to move the paddle right <br>
     * Press ESC to show pause menu <br>
     * Press SPACE to pause or resume the game <br>
     * Press ALT + SHIFT + F1 to show debug console
     *
     * @param keyEvent keyEvent object
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wallView.getPaddle().moveLeft();
                break;
            case KeyEvent.VK_D:
                wallView.getPaddle().movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                if(showHighScore){
                    setShowHighScore(false);
                }
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsoleView.setVisible(true);
            default:
                wallView.getPaddle().stop();

        }
    }

    /**
     * Stop the paddle when the key is released
     *
     * @param keyEvent keyEvent object
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wallView.getPaddle().stop();
    }

    /**
     * Click CONTINUE to resume the game <br>
     * Click RESTART to restart the game <br>
     * Click EXIT to quit the game
     *
     * @param mouseEvent mouseEvent object
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        Point p = mouseEvent.getPoint();

        if(!showPauseMenu)
            return;

        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){

            wallView.setMessage("Restarting Game...");
            wallView.getPaddle().resetWidth();
            wallView.getPaddle().reset(new Point(300,430));
            wallView.getBall().reset(new Point(300,430));

            if(wallController.getLevelManager().getLevel()==5 || wallController.getLevelManager().getLevel()==6){
                wallView.getBall().setXSpeed(4);
                wallView.getBall().setYSpeed(-4);
            }

            wallView.getScore().reset();
            wallController.wallReset();
            showPauseMenu = false;
            repaint();

        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Change the cursor's looks to hand if the cursor points at CONTINUE, RESTART, EXIT
     *
     * @param mouseEvent MouseEvent object
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        Point p = mouseEvent.getPoint();

        if(exitButtonRect != null && showPauseMenu) {

            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());

        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

}

