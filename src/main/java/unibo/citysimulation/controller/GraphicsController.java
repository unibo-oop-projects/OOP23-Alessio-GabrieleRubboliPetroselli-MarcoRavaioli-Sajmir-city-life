package unibo.citysimulation.controller;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.graphics.api.GraphicsModel;
import unibo.citysimulation.view.sidepanelsfffff.graphics.GraphicsPanel;
import unibo.citysimulation.view.sidepanelsfffff.graphics.LegendPanel;

import java.time.LocalTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Controller for handling graphics updates in the city simulation.
 * Implements the ClockObserver to react to time updates and refresh graphics accordingly.
 */
public final class GraphicsController implements ClockObserver {
    private final CityModel cityModel;
    private final GraphicsModel graphicsModel;
    private final GraphicsPanel graphicsPanel;

    /**
     * Constructs a new GraphicsController with the specified CityModel and GraphicsPanel.
     *
     * @param cityModel     the city model
     * @param graphicsPanel the graphics panel
     */
    public GraphicsController(final CityModel cityModel, final GraphicsPanel graphicsPanel) {
        // Ensure that the provided cityModel and graphicsPanel are not null
        this.cityModel = Objects.requireNonNull(cityModel, "cityModel must not be null");
        graphicsModel = cityModel.getGraphicsModel();
        this.graphicsPanel = Objects.requireNonNull(graphicsPanel, "graphicsPanel must not be null");
        initialize();
    }

    /**
     * Initializes the controller by setting up observers and configuring the graphics panel.
     */
    private void initialize() {
        // Register this controller as an observer of the clock model
        cityModel.getClockModel().addObserver(this);

        // Add an action listener to the legend button to show the legend panel when pressed
        graphicsPanel.addLegendButtonActionListener(e -> showLegendPanel());
        // Create graphics in the graphics panel using data from the graphics model
        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets(), graphicsModel.getColors());
    }

    /**
     * Displays the legend panel when the legend button is pressed.
     */
    private void showLegendPanel() {
        new LegendPanel(
                graphicsModel.getColors(),
                cityModel.getTransportLines().stream()
                        .map(t -> t.getName())
                        .collect(Collectors.toList()));
    }

    /**
     * Updates the graphics model when the time is updated.
     * This method is called by the clock model, demonstrating the observer pattern.
     *
     * @param currentTime the current time
     * @param currentDay  the current day
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        graphicsModel.updateDataset(
            cityModel.getAllPeople(),
            cityModel.getTransportLines(),
            cityModel.getBusinesses(),
            cityModel.getClockModel().getUpdateRate());
    }
}
