package test;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsoleView extends JDialog implements WindowListener {

    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;


    public DebugConsoleView(){

        initialize();

        JPanel debugConsolePanel = new JPanel();

        debugConsolePanel.setBackground(Color.WHITE);
        debugConsolePanel.setLayout(new GridLayout(2,2));

        skipLevel = makeButton("Skip Level");
        resetBalls = makeButton("Reset Balls");

        ballXSpeed = makeSlider(-4,4);
        ballYSpeed = makeSlider(-4,4);

        debugConsolePanel.add(skipLevel);
        debugConsolePanel.add(resetBalls);

        debugConsolePanel.add(ballXSpeed);
        debugConsolePanel.add(ballYSpeed);

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

    public JSlider getBallXSpeed(){
        return ballXSpeed;
    }

    public JSlider getBallYSpeed(){
        return ballYSpeed;
    }


    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

    void addSkipLevelListener(ActionListener listenForSkipLevelButton){

        skipLevel.addActionListener(listenForSkipLevelButton);

    }

    void addResetBallsListener(ActionListener listenForResetBallsButton){

        resetBalls.addActionListener(listenForResetBallsButton);

    }

    void addBallXSpeedListener(ChangeListener listenForBallXSpeedSlider){

        ballXSpeed.addChangeListener(listenForBallXSpeedSlider);
    }

    void addBallYSpeedListener(ChangeListener listenForBallYSpeedSlider){

        ballYSpeed.addChangeListener(listenForBallYSpeedSlider);
    }

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

