package unibo.citysimulation.view.sidepanels.clock;

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
import java.awt.event.ActionListener;

/**
 * Panel for displaying the clock and controlling simulation speed.
 */
public class ClockPanelImpl extends StyledPanel implements ClockPanel {
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
    public ClockPanelImpl(final Color bgColor) {
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
    @Override
    public void setClockText(final String dayText, final String hourText) {
        timeDay.setText("Day: " + dayText);
        timeHour.setText("Hour: " + hourText);
    }

    /**
     * Updates the text of the pause button based on the simulation state.
     *
     * @param isPaused Boolean indicating if simulation is paused.
     */
    @Override
    public void updatePauseButton(final boolean isPaused) {
        pauseButton.setText(isPaused ? "Resume" : "Pause");
    }

    /**
     * Changes the simulation speed and updates the speed button text.
     *
     * @return The new simulation speed.
     */
    @Override
    public int changeSpeed() {
        currentSpeedIndex = (currentSpeedIndex + 1) % speeds.size();
        final int newSpeed = speeds.get(currentSpeedIndex);
        speedButton.setText(newSpeed + "x");
        return newSpeed;
    }

    /**
     * Sets the preferred size of the clock panel.
     *
     * @param width  The width to set.
     * @param height The height to set.
     */
    @Override
    public void setPreferredSize(final int width, final int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Adds an action listener to the pause button.
     */
    @Override
    public void addPauseButtonActionListener(final ActionListener actionListener) {
        pauseButton.addActionListener(actionListener);
    }

    /**
     * Adds an action listener to the speed button.
     */
    @Override
    public void addSpeedButtonActionListener(final ActionListener actionListener) {
        speedButton.addActionListener(actionListener);
    }

    /**
     * Sets the pause button enabled state.
     *
     * @param enabled Boolean indicating if the pause button is enabled.
     */
    @Override
    public void setPauseButtonEnabled(final boolean enabled) {
        pauseButton.setEnabled(enabled);
    }
}
