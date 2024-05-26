package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.InputModel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;

/**
* Controller class responsible for handling user input from the input panel.
*/
public class InputController {
    private final CityModel cityModel;
    private final InputModel inputModel;
    private final InputPanel inputPanel;
    private final ClockPanel clockPanel;

    /**
     * Constructs an InputController object.
     *
     * @param cityModel the city model
     * @param inputModel the input model
     * @param inputPanel the input panel
     * @param clockPanel the clock panel
     */
    public InputController(final CityModel cityModel, final InputModel inputModel, 
    final InputPanel inputPanel, final ClockPanel clockPanel) {
        this.cityModel = cityModel;
        this.inputModel = inputModel;
        this.inputPanel = inputPanel;
        this.clockPanel = clockPanel;

        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                startSimulation(clockPanel);
                cityModel.getMapModel().startSimulation();

                inputPanel.getPeopleSlider().setEnabled(false);
                inputPanel.getCapacitySlider().setEnabled(false);
                inputPanel.getBusinessSlider().setEnabled(false);
                inputPanel.getRichnessSlider().setEnabled(false);
                inputPanel.getStartButton().setEnabled(false);
                inputPanel.getStopButton().setEnabled(true);
            }
        });

        inputPanel.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                stopSimulation(clockPanel);
                inputPanel.getPeopleSlider().setEnabled(true);
                inputPanel.getBusinessSlider().setEnabled(true);
                inputPanel.getRichnessSlider().setEnabled(true);
                inputPanel.getCapacitySlider().setEnabled(true);
                inputPanel.getStartButton().setEnabled(true);
                inputPanel.getStopButton().setEnabled(false);
            }
        });
    }

    /**
     * Starts the simulation when the start button is clicked.
     *
     * @param clockPanel The ClockPanel object representing the clock panel.
     */
    private void startSimulation(final ClockPanel clockPanel) {
        inputModel.setNumberOfPeople(inputPanel.getPeopleSlider().getValue());
        inputModel.setNumberOfBusiness(inputPanel.getBusinessSlider().getValue());
        inputModel.setCapacity(inputPanel.getCapacitySlider().getValue());
        inputModel.setRichness(inputPanel.getRichnessSlider().getValue());
        // Create entities
        cityModel.createEntities();
        // Restart the clock simulation
        cityModel.getClockModel().restartSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().isPaused());
        clockPanel.getPauseButton().setEnabled(true);
    }

    private void stopSimulation(final ClockPanel clockPanel) {
        // Restart the clock simulation
        cityModel.getClockModel().stopSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().isPaused());
        clockPanel.getPauseButton().setEnabled(false);
    }
}

// Note: Newline at the end of the file

