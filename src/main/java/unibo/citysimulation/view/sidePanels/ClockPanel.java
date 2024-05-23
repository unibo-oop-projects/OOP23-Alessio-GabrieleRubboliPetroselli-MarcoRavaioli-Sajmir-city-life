package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
/**
 * Panel for displaying the clock and controlling simulation speed.
 */
public class ClockPanel extends StyledPanel {
    private final JLabel timeDay = new JLabel("Day: 1", SwingConstants.CENTER);
    private final JLabel timeHour = new JLabel("Hour: 00:00", SwingConstants.CENTER);
    private final JButton speedButton;
    private final JButton pauseButton;
    private final int[] speeds = ConstantAndResourceLoader.SPEEDS;
    private int currentSpeedIndex = 0;

    /**
     * Constructs a ClockPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public ClockPanel(final Color bgColor) {
        super(bgColor);
        speedButton = new JButton("1x");
        speedButton.setForeground(Color.black);
        speedButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size

        pauseButton = new JButton("Pause");
        pauseButton.setForeground(Color.black);
        pauseButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size

        final JPanel timePanel = new JPanel(new GridLayout(2, 1));
        timePanel.setBackground(Color.RED);
        timeDay.setFont(new Font("Arial", Font.BOLD, 15));
        timeHour.setFont(new Font("Arial", Font.BOLD, 15));
        timePanel.add(timeDay);
        timePanel.add(timeHour);

        setLayout(new BorderLayout());
        add(speedButton, BorderLayout.WEST);
        add(timePanel, BorderLayout.CENTER);
        add(pauseButton, BorderLayout.EAST);
    }

    /**
     * Sets the text of the clock label.
     *
     * @param dayText  The text for the day label.
     * @param hourText The text for the hour label.
     */
    public void setClockText(final String dayText, final String hourText) {
        timeDay.setText("Day: " + dayText);
        timeHour.setText("Hour: " + hourText);
    }

    /**
     * Updates the text of the pause button based on the simulation state.
     *
     * @param isPaused Boolean indicating if simulation is paused.
     */
    public void updatePauseButton(final boolean isPaused) {
        pauseButton.setText(isPaused ? "Resume" : "Pause");
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
        final int newSpeed = speeds[currentSpeedIndex];
        speedButton.setText(newSpeed + "x");
        return newSpeed;
    }
}
