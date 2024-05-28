package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.GraphicsModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.view.sidepanels.GraphicsPanel;
import unibo.citysimulation.view.sidepanels.LegendPanel;

import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * Controller for handling graphics updates in the city simulation.
 */
public class GraphicsController implements ClockObserver {
    private final CityModel cityModel;
    private final GraphicsModel graphicsModel;

    /**
     * Constructs a new GraphicsController with the specified CityModel and GraphicsPanel.
     *
     * @param cityModel the city model
     * @param graphicsPanel the graphics panel
     */
    public GraphicsController(final CityModel cityModel, final GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        graphicsModel = cityModel.getGraphicsModel();
        initialize(graphicsPanel);
    }

    /**
     * Initializes the controller by setting up listeners and creating graphics.
     *
     * @param graphicsPanel the graphics panel
     */
    private void initialize(final GraphicsPanel graphicsPanel) {
        cityModel.getClockModel().addObserver(this);

        graphicsPanel.getLegendButton().addActionListener(e -> showLegendPanel());
        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets(), graphicsModel.getColors());
    }

    /**
     * Shows the legend panel when the legend button is pressed.
     */
    private void showLegendPanel() {
        new LegendPanel(
            graphicsModel.getColors(),
            cityModel.getTransportLines().stream()
                     .map(t -> t.getName())
                     .collect(Collectors.toList())
        );
    }

    /**
     * Updates the graphics model when the time is updated.
     *
     * @param currentTime the current time
     * @param currentDay the current day
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        graphicsModel.updateDataset(
            cityModel.getAllPeople(),
            cityModel.getTransportLines(),
            cityModel.getBusinesses());
    }
}
