package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.GraphicsModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.view.sidepanels.GraphicsPanel;
import unibo.citysimulation.view.sidepanels.LegendPanel;

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

        graphicsPanel.getLegendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new LegendPanel(graphicsPanel.getColors(), cityModel.getTransportLines().stream()
                                                            .map(t -> t.getName()).collect(Collectors.toList()));
            }
        });
        
        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets());
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
