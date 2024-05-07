package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserver;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;

public class ClockController implements ClockObserver{
    private ClockPanel clockPanel;
    private ClockModel clockModel;
    private CityModel cityModel;


    public ClockController(ClockModel clockModel, CityModel cityModel, ClockPanel clockPanel, InputPanel inputPanel) {
        this.clockPanel = clockPanel;
        this.clockModel = clockModel;

        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockModel.restartSimulation();
                cityModel.createEntities();
            }
        });

        clockPanel.getPauseButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockModel.pauseSimulation();
                clockPanel.updatePauseButton();
            }
        });

        clockPanel.getSpeedButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Incrementa l'indice della velocità e riportalo a 0 se arrivi all'ultimo valore
                int newSpeed = clockPanel.changeSpeed();
                setClockSpeed(newSpeed);
            }
        });

        this.clockModel.addObserver(this);
    }

    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        
        clockPanel.setClockText("Giorno: " + currentDay + " ora: " + currentTime);
    }

    public void setClockSpeed(int speed) {
        clockModel.startSimulation(ConstantAndResourceLoader.TIME_UPDATE_RATE/speed);
    }

    public void pauseSimulation() {
        clockModel.pauseSimulation();
    }

    
}
