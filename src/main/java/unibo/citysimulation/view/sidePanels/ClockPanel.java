package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;
import javax.swing.*;


import java.awt.*;

public class ClockPanel extends StyledPanel {
    private JLabel timeDay = new JLabel("Giorno: 1 ora: 00:00", SwingConstants.CENTER);
    private JButton speedButton;
    private JButton pauseButton;
    private int[] speeds = {1, 2, 10};
    private int currentSpeedIndex = 0;
    boolean isPaused = false;

    public ClockPanel(Color bgColor) {
        super(bgColor);

        speedButton = new JButton("1x");

        pauseButton = new JButton("stop");
        pauseButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size

        // Set up layout
        JPanel speedPanel = new JPanel(new BorderLayout());
        speedPanel.add(speedButton,BorderLayout.WEST);
        speedPanel.add(pauseButton,BorderLayout.EAST);

        add(timeDay, BorderLayout.SOUTH);

        add(speedPanel);
    }

    public void setClockText(String text){
        timeDay.setText(text);
    }

    public void updatePauseButton() {
        this.isPaused = !isPaused;
        pauseButton.setText(isPaused ? "resume" : "stop");
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getSpeedButton() {
        return speedButton;
    }

    public int changeSpeed() {
        currentSpeedIndex = (currentSpeedIndex + 1) % speeds.length;
        int newSpeed = speeds[currentSpeedIndex];
        speedButton.setText(newSpeed + "x");
        return newSpeed;
    }
    
}