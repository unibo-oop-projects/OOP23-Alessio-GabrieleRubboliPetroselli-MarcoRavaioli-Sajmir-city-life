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
    private CityModel cityModel;
    private InputModel inputModel;

    private InputPanel inputPanel;
 
    /**
     * Constructs an InputController object.
     *
     * @param cityModel   The CityModel object representing the city simulation.
     * @param inputPanel  The InputPanel object representing the input panel.
     * @param clockPanel  The ClockPanel object representing the clock panel.
     */
    public InputController(CityModel cityModel, InputModel inputModel, InputPanel inputPanel, ClockPanel clockPanel) {
        this.cityModel = cityModel;

        this.inputModel = inputModel;

        this.inputPanel = inputPanel;

        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PREMUTO IL TASTO START SIMULATION");
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
            public void actionPerformed(ActionEvent e) {
                System.out.println("PREMUTO IL TASTO STOP SIMULATION");
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
    private void startSimulation(ClockPanel clockPanel) {

        inputModel.setNumberOfPeople(inputPanel.getPeopleSlider().getValue()); 
        inputModel.setNumberOfBusiness(inputPanel.getBusinessSlider().getValue());
        inputModel.setCapacity(inputPanel.getCapacitySlider().getValue());
        inputModel.setRichness(inputPanel.getRichnessSlider().getValue());
        
        // Create entities with the specified number of people
        cityModel.createEntities(inputModel.getNumberOfPeople());
        // Restart the clock simulation
        cityModel.getClockModel().restartSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().getIsPaused());

        clockPanel.getPauseButton().setEnabled(true);
    }
    
    private void stopSimulation(ClockPanel clockPanel) {
        // Restart the clock simulation
        cityModel.getClockModel().stopSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().getIsPaused());

        clockPanel.getPauseButton().setEnabled(false);
    }
}
 