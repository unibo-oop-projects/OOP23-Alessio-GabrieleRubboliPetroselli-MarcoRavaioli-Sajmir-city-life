package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;

public class InputController {
    private CityModel cityModel;
    private InputPanel inputPanel;
    private int numberOfPeople = 1000;
    
    public InputController(CityModel cityModel, InputPanel inputPanel, ClockPanel clockPanel) {
        this.cityModel = cityModel;
        this.inputPanel = inputPanel;


        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cityModel.createEntities(numberOfPeople);
                cityModel.getClockModel().restartSimulation();
                clockPanel.updatePauseButton(cityModel.getClockModel().getIsPaused());
            }
        });
    }


}
