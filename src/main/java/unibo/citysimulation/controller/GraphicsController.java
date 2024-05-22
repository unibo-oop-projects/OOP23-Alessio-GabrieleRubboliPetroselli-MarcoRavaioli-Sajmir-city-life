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
    private CityModel cityModel;
    private GraphicsModel graphicsModel;
    
    public GraphicsController(CityModel cityModel, GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        graphicsModel = cityModel.getGraphicsModel();
        cityModel.getClockModel().addObserver(this);

        graphicsPanel.getLegendButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new LegendPanel(graphicsPanel.getColors(), cityModel.getTransportLines().stream().map(t -> t.getName()).collect(Collectors.toList()));
            }
            
        });

        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets());
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        graphicsModel.updateDataset(
            graphicsModel.getPeopleStateCounts(cityModel.getAllPeople()), 
            graphicsModel.getTransportLinesCongestion(cityModel.getTransportLines()),

            graphicsModel.getBusinessesOccupation(cityModel.getBusinesses()),




            graphicsModel.getCounter());
            System.out.println(cityModel.getTransportLines().stream().map(t -> "PERSONE PRESENTI : " + t.getPersonInLine()).collect(Collectors.toList()));
        
    }
}
