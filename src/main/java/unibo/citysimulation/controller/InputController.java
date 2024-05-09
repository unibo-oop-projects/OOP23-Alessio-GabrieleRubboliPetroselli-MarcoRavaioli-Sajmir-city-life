package unibo.citysimulation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;

/**
 * Controller class responsible for handling user input from the input panel.
 */
public class InputController {
    private CityModel cityModel;
    private InputPanel inputPanel;
    private int numberOfPeople;
    private GraphicsPanel graphicsPanel;

    /**
     * Constructs an InputController object.
     *
     * @param cityModel   The CityModel object representing the city simulation.
     * @param inputPanel  The InputPanel object representing the input panel.
     * @param clockPanel  The ClockPanel object representing the clock panel.
     */
    public InputController(CityModel cityModel, InputPanel inputPanel, ClockPanel clockPanel,GraphicsPanel graphicsPanel) {
        this.cityModel = cityModel;
        this.inputPanel = inputPanel;
        numberOfPeople = inputPanel.getPeopleSlider().getValue();
        this.graphicsPanel = graphicsPanel;

        // Add action listener for the start button
        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation(clockPanel);
            }
        });

        // Add change listener for the people slider
        inputPanel.getPeopleSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateNumberOfPeople();
                int number = inputPanel.getPeopleSlider().getValue();
                graphicsPanel.updateDataset(number);
            }
        });
    }

    /**
     * Starts the simulation when the start button is clicked.
     *
     * @param clockPanel The ClockPanel object representing the clock panel.
     */
    private void startSimulation(ClockPanel clockPanel) {
        // Create entities with the specified number of people
        cityModel.createEntities(numberOfPeople);
        // Restart the clock simulation
        cityModel.getClockModel().restartSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().getIsPaused());
    }

    /**
     * Updates the number of people when the slider value changes.
     */
    private void updateNumberOfPeople() {
        numberOfPeople = inputPanel.getPeopleSlider().getValue();
        System.out.println("Selected number of people: " + numberOfPeople);
    }
}
