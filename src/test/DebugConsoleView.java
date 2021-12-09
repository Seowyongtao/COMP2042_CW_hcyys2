package test;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The container for skip level button, reset ball button, ballXSpeed slider, ballYSpeed slider, and night mode radio button
 */
public class DebugConsoleView extends JDialog implements WindowListener {

    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private JRadioButton nightModeOn;
    private JRadioButton nightModeOff;
    private ButtonGroup nightMode;

    /**
     * Constructor for DebugConsoleView
     */
    public DebugConsoleView(){

        initialize();

        JPanel debugConsolePanel = new JPanel();

        debugConsolePanel.setBackground(Color.WHITE);
        debugConsolePanel.setLayout(new GridLayout(3,2));

        skipLevel = makeButton("Skip Level");
        resetBalls = makeButton("Reset Balls");

        ballXSpeed = makeSlider(-4,4);
        ballYSpeed = makeSlider(-4,4);

        nightModeOn = makeRadioButton("NIGHT MODE ON");
        nightModeOff = makeRadioButton("NIGHT MODE OFF");

        nightMode = makeButtonGroup();
        nightMode.add(nightModeOn);
        nightMode.add(nightModeOff);

        debugConsolePanel.add(skipLevel);
        debugConsolePanel.add(resetBalls);

        debugConsolePanel.add(ballXSpeed);
        debugConsolePanel.add(ballYSpeed);

        debugConsolePanel.add(nightModeOn);
        debugConsolePanel.add(nightModeOff);

        this.add(debugConsolePanel,BorderLayout.CENTER);
        this.pack();

    }

    private void initialize(){
        this.setModal(true);
        this.setTitle("Debug Console");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
    }

    private JButton makeButton(String title){
        JButton out = new JButton(title);
        return  out;
    }

    private JSlider makeSlider(int min, int max){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        return out;
    }

    private JRadioButton makeRadioButton(String title){
        JRadioButton out = new JRadioButton(title);
        return out;
    }

    private ButtonGroup makeButtonGroup(){
        ButtonGroup out = new ButtonGroup();
        return out;
    }

    /**
     * Get ballXSpeed value
     *
     * @return ballXSpeed
     */
    public JSlider getBallXSpeed(){
        return ballXSpeed;
    }

    /**
     * Get ballYSpeed value
     *
     * @return ballYSpeed
     */
    public JSlider getBallYSpeed(){
        return ballYSpeed;
    }

    /**
     * Set the value for ballXSpeed and ballYSpeed
     *
     * @param x value for ballXSpeed
     * @param y value for ballYSpeed
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

    /**
     * Add ActionListener to skipLevel
     *
     * @param listenForSkipLevelButton ActionListener object
     */
    public void addSkipLevelListener(ActionListener listenForSkipLevelButton){

        skipLevel.addActionListener(listenForSkipLevelButton);

    }

    /**
     * Add ActionListener to resetBalls
     *
     * @param listenForResetBallsButton ActionListener object
     */
    public void addResetBallsListener(ActionListener listenForResetBallsButton){

        resetBalls.addActionListener(listenForResetBallsButton);

    }

    /**
     * Get JRadioButton object nightModeOn
     *
     * @return JRadioButton object nightModeOn
     */
    public JRadioButton getNightModeOn(){
        return  nightModeOn;
    }

    /**
     * Get JRadioButton object nightModeOff
     *
     * @return JRadioButton object nightModeOff
     */
    public JRadioButton getNightModeOff(){
        return  nightModeOff;
    }

    /**
     * Add ActionListener to nightModeOn and nightModeOff
     *
     * @param listenForNightModeRadioButtons ActionListener object
     */
    public void addNightModeListener(ActionListener listenForNightModeRadioButtons){

        nightModeOn.addActionListener(listenForNightModeRadioButtons);
        nightModeOff.addActionListener(listenForNightModeRadioButtons);
    }

    /**
     * Add ChangeListener to ballXSpeed
     *
     * @param listenForBallXSpeedSlider ChangeListener object
     */
    public void addBallXSpeedListener(ChangeListener listenForBallXSpeedSlider){

        ballXSpeed.addChangeListener(listenForBallXSpeedSlider);
    }

    /**
     * Add ChangeListener to ballYSpeed
     *
     * @param listenForBallYSpeedSlider ChangeListener object
     */
    public void addBallYSpeedListener(ChangeListener listenForBallYSpeedSlider){

        ballYSpeed.addChangeListener(listenForBallYSpeedSlider);
    }

    /**
     * Add WindowListener to DebugConsoleView itself
     *
     * @param listenForDebugConsoleWindow WindowListener object
     */
    public void addDebugConsoleWindowListener(WindowListener listenForDebugConsoleWindow){

        this.addWindowListener(listenForDebugConsoleWindow);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

