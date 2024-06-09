package unibo.citysimulation.view.sidepanelsfffff.clock;

import java.awt.event.ActionListener;
/**
 * Interface for the ClockPanel.
 */
public interface ClockPanel {
    /**
     * Sets the text of the clock label.
     *
     * @param day  The day to set
     * @param hour The hour to set
     */
    void setClockText(String day, String hour);

    /**
     * Changes the simulation speed and updates the speed button text.
     *
     * @return The new simulation speed.
     */
    int changeSpeed();

    /**
     * Updates the text of the pause button based on the simulation state.
     *
     * @param isPaused True if the simulation is paused, false otherwise
     */
    void updatePauseButton(boolean isPaused);

    /**
     * Sets the preferred size of the clock panel.
     *
     * @param width  The width to set
     * @param height The height to set
     */
    void setPreferredSize(int width, int height);

    /**
     * Adds an action listener to the pause button.
     * @param actionListener The action listener to add
     */
    void addPauseButtonActionListener(ActionListener actionListener);

    /** 
     * Adds an action listener to the speed button.
     * @param actionListener The action listener to add
    */
    void addSpeedButtonActionListener(ActionListener actionListener);

    /**
     * Sets the pause button enabled state.
     *
     * @param isEnabled True if the pause button is enabled, false otherwise
     */
    void setPauseButtonEnabled(boolean isEnabled);
}
