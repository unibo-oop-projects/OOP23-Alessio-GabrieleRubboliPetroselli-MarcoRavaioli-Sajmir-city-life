package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.GraphicsModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;
import unibo.citysimulation.view.sidePanels.LegendPanel;

import java.util.stream.Collectors;

public class GraphicsController implements ClockObserver {
    private final CityModel cityModel;
    private final GraphicsModel graphicsModel;
    
    public GraphicsController(final CityModel cityModel, final GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        graphicsModel = cityModel.getGraphicsModel();

        initialize(graphicsPanel);
    }

    private void initialize(final GraphicsPanel graphicsPanel) {
        cityModel.getClockModel().addObserver(this);

        graphicsPanel.getLegendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                new LegendPanel(graphicsPanel.getColors(), cityModel.getTransportLines().stream().map(t -> t.getName()).collect(Collectors.toList()));
            }
        });

        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets());
    }

    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        graphicsModel.updateDataset(
            graphicsModel.getPeopleStateCounts(cityModel.getAllPeople()), 
            graphicsModel.getTransportLinesCongestion(cityModel.getTransportLines()),
            graphicsModel.getBusinessesOccupation(cityModel.getBusinesses()),
            graphicsModel.getCounter());
    }
}
