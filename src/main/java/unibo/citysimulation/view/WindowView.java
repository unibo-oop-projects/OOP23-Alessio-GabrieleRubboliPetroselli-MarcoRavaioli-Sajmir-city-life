package unibo.citysimulation.view;

import java.awt.event.ComponentAdapter;

import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidepanels.ClockPanel;
import unibo.citysimulation.view.sidepanels.GraphicsPanel;
import unibo.citysimulation.view.sidepanels.InfoPanel;
import unibo.citysimulation.view.sidepanels.InputPanel;

public interface WindowView {

    /**
     * Adds a component resize listener.
     *
     * @param adapter The component adapter to add.
     */
    void addResizeListener(final ComponentAdapter adapter);

    /**
     * Updates the size of the panels based on the window size.
     *
     * @param width the new width of the window.
     * @param height the new height of the window.
     */
    public void updateFrame(final int width, final int height);

    /**
     * Retrieves the info panel.
     *
     * @return The info panel.
     */
    public InfoPanel getInfoPanel();

    /**
     * Retrieves the clock panel.
     *
     * @return The clock panel.
     */
    public ClockPanel getClockPanel();

    /**
     * Retrieves the input panel.
     *
     * @return The input panel.
     */
    public InputPanel getInputPanel();

    /**
     * Retrieves the graphics panel.
     *
     * @return The graphics panel.
     */
    public GraphicsPanel getGraphicsPanel();

    /**
     * Retrieves the map panel.
     *
     * @return The map panel.
     */
    public MapPanel getMapPanel();
}
