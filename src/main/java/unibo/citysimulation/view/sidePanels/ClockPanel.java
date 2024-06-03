package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

/**
 * Panel for displaying the clock and controlling simulation speed.
 */
public class ClockPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel timeDay = new JLabel("Day: 1", SwingConstants.CENTER);
    private final JLabel timeHour = new JLabel("Hour: 00:00", SwingConstants.CENTER);
    private final JButton speedButton;
    private final JButton pauseButton;
    private final List<Integer> speeds = ConstantAndResourceLoader.SPEEDS;
    private int currentSpeedIndex;

    /**
     * Constructs a ClockPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public ClockPanel(final Color bgColor) {
        super(bgColor);
        speedButton = new JButton("1x");
        speedButton.setForeground(Color.black);
        speedButton.setPreferredSize(new Dimension(ConstantAndResourceLoader.CLOCK_PANEL_PANEL_WIDTH,
                ConstantAndResourceLoader.CLOCK_PANEL_PANEL_HEIGHT)); // Set the preferred size

        pauseButton = new JButton("Pause");
        pauseButton.setForeground(Color.black);
        pauseButton.setPreferredSize(new Dimension(ConstantAndResourceLoader.CLOCK_PANEL_PANEL_WIDTH,
                ConstantAndResourceLoader.CLOCK_PANEL_PANEL_HEIGHT)); // Set the preferred size

        final JPanel timePanel = new JPanel(new GridLayout(2, 1));
        timePanel.setBackground(bgColor);
        timeDay.setFont(new Font("Arial", Font.BOLD, ConstantAndResourceLoader.CLOCK_PANEL_FONT_SIZE));
        timeHour.setFont(new Font("Arial", Font.BOLD, ConstantAndResourceLoader.CLOCK_PANEL_FONT_SIZE));
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
        currentSpeedIndex = (currentSpeedIndex + 1) % speeds.size();
        final int newSpeed = speeds.get(currentSpeedIndex);
        speedButton.setText(newSpeed + "x");
        return newSpeed;
    }
}
