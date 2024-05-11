package unibo.citysimulation.controller;

import java.time.LocalTime;
import java.util.List;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;

public class GraphicsController implements ClockObserver {
    private GraphicsPanel graphicsPanel;
    private CityModel cityModel;
    private double counter = 0.0;

    public GraphicsController(CityModel cityModel, GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        this.graphicsPanel = graphicsPanel;

        cityModel.getClockModel().addObserver(this);

    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {

        graphicsPanel.updateDataset(
                (int) cityModel.getTransportLines().get(0).getCongestion(),
                (int) cityModel.getTransportLines().get(1).getCongestion(),

                this.counter++);

        int atHomeCount = 0;
        int movingCount = 0;
        int workingCount = 0;

        // Ottieni tutte le persone dal CityModel
        List<DynamicPerson> allPeople = cityModel.getAllPeople();

        // Itera su tutte le persone e controlla lo stato di ciascuna di esse
        for (var person : allPeople) {
            switch (person.getState()) {
                case AT_HOME:
                    atHomeCount++;
                    break;
                case MOVING:
                    movingCount++;
                    break;
                case WORKING:
                    workingCount++;
                    break;
                default:
                    break;
            }
        }

        graphicsPanel.updateStateDataset(atHomeCount, movingCount, workingCount, this.counter++);

    }
}
