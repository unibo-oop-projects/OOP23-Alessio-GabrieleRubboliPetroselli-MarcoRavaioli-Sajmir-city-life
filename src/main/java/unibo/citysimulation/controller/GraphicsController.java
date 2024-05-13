package unibo.citysimulation.controller;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
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

        List<Double> transportLinesCongestion = cityModel.getTransportLines().stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());

        List<DynamicPerson> allPeople = cityModel.getAllPeople();

        List<Integer> peopleStateCounts = Arrays.asList(
                (int) allPeople.stream().filter(person -> person.getState() == PersonState.AT_HOME).count(),
                (int) allPeople.stream().filter(person -> person.getState() == PersonState.MOVING).count(),
                (int) allPeople.stream().filter(person -> person.getState() == PersonState.WORKING).count());

        graphicsPanel.updateDataset(
                peopleStateCounts,
                transportLinesCongestion,
                50,
                this.counter++);

    }
}
