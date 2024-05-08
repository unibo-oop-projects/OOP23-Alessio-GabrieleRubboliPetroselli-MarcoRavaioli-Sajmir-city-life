package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying the clock and controlling simulation speed.
 */
public class ClockPanel extends StyledPanel {
    private JLabel timeDay = new JLabel("Day: 1 Hour: 00:00", SwingConstants.CENTER);
    private JButton speedButton;
    private JButton pauseButton;
    private int[] speeds = {1, 2, 10};
    private int currentSpeedIndex = 0;

    /**
     * Constructs a ClockPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public ClockPanel(Color bgColor) {
        super(bgColor);

        speedButton = new JButton("1x");

        pauseButton = new JButton("Stop");
        pauseButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size

        // Set up layout
        JPanel speedPanel = new JPanel(new BorderLayout());
        speedPanel.add(speedButton, BorderLayout.WEST);
        speedPanel.add(pauseButton, BorderLayout.EAST);

        add(timeDay, BorderLayout.SOUTH);
        add(speedPanel);
    }

    /**
     * Sets the text of the clock label.
     *
     * @param text The text to set.
     */
    public void setClockText(String text){
        timeDay.setText(text);
    }

    /**
     * Updates the text of the pause button based on the simulation state.
     *
     * @param isPaused Boolean indicating if simulation is paused.
     */
    public void updatePauseButton(boolean isPaused) {
        pauseButton.setText(isPaused ? "Resume" : "Stop");
    }

    /**
     * Retrieves the pause button.
     *
     * @return The pause button.
     */
    public JButton getPauseButton() {
        return pauseButton;
    }

    /**
     * Retrieves the speed button.
     *
     * @return The speed button.
     */
    public JButton getSpeedButton() {
        return speedButton;
    }

    /**
     * Changes the simulation speed and updates the speed button text.
     *
     * @return The new simulation speed.
     */
    public int changeSpeed() {
        currentSpeedIndex = (currentSpeedIndex + 1) % speeds.length;
        int newSpeed = speeds[currentSpeedIndex];
        speedButton.setText(newSpeed + "x");
        return newSpeed;
    }
}
