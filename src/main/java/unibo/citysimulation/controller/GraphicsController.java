package unibo.citysimulation.controller;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.GraphicsModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;

public class GraphicsController implements ClockObserver {
    private GraphicsPanel graphicsPanel;
    private CityModel cityModel;
    private GraphicsModel graphicsModel;
    

    public GraphicsController(CityModel cityModel, GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        this.graphicsPanel = graphicsPanel;
        graphicsModel = cityModel.getGraphicsModel();
        cityModel.getClockModel().addObserver(this);

        graphicsPanel.createGraphics(graphicsModel.getNames(), graphicsModel.getDatasets());
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {

        //// parte di business

        graphicsModel.updateDataset(
            graphicsModel.getPeopleStateCounts(cityModel.getAllPeople()), 
            graphicsModel.getTransportLinesCongestion(cityModel.getTransportLines()),
            50,
            graphicsModel.getCounter());
        
    }
}
