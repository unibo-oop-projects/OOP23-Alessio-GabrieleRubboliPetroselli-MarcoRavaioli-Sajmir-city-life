package unibo.citysimulation.controller;

import java.time.LocalTime;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;

public class GraphicsController implements ClockObserver{
    private GraphicsPanel graphicsPanel;
    private CityModel cityModel;
    private double counter = 0.0;


    public GraphicsController(CityModel cityModel, GraphicsPanel graphicsPanel){
        this.cityModel = cityModel;
        this.graphicsPanel = graphicsPanel;

        cityModel.getClockModel().addObserver(this);

    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {

        System.out.println("dentro al onTimeUpdate di GraphicsController");

        graphicsPanel.updateDataset(
            cityModel.getTransportLines(),
            50,
            80,
            this.counter++);
    
    }

    
}
