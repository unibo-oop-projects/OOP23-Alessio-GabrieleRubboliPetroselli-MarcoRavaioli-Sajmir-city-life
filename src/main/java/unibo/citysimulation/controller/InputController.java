package unibo.citysimulation.controller;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.InputModel;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;
 
/**
* Controller class responsible for handling user input from the input panel.
*/
public class InputController {
    private CityModel cityModel;
    private InputPanel inputPanel;
    private int numberOfPeople = 0; //da mettere in inputModel
    private int numberOfBusiness = 0; //da mettere
    private int capacity = 0; //da mettere
    private int richness = 0; //da mettere
    private GraphicsPanel graphicsPanel;
    private MapPanel mapPanel;
    private MapModel mapModel;
 
    /**
     * Constructs an InputController object.
     *
     * @param cityModel   The CityModel object representing the city simulation.
     * @param inputPanel  The InputPanel object representing the input panel.
     * @param clockPanel  The ClockPanel object representing the clock panel.
     */
    public InputController(CityModel cityModel, InputModel inputModel, InputPanel inputPanel, ClockPanel clockPanel, GraphicsPanel graphicsPanel, MapPanel mapPanel) {
        this.cityModel = cityModel;

        this.inputPanel = inputPanel;

        this.graphicsPanel = graphicsPanel;

        this.mapPanel = mapPanel;

        numberOfPeople = inputPanel.getPeopleSlider().getValue();

        //cityModel.getMapModel().startSimulation();
        // Add action listener for the start button
        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PREMUTO IL TASTO START SIMULATION");
                startSimulation(clockPanel);
                cityModel.getMapModel().startSimulation();
                inputPanel.getPeopleSlider().setEnabled(false);
                inputPanel.getBusinessSlider().setEnabled(false);
                inputPanel.getRichnessSlider().setEnabled(false);
            }
        });

 

    }
 
    /**
     * Starts the simulation when the start button is clicked.
     *
     * @param clockPanel The ClockPanel object representing the clock panel.
     */
    private void startSimulation(ClockPanel clockPanel) {

        numberOfPeople = inputPanel.getPeopleSlider().getValue() * (ConstantAndResourceLoader.MAX_PEOPLE - ConstantAndResourceLoader.MIN_PEOPLE) + ConstantAndResourceLoader.MIN_PEOPLE;
        numberOfBusiness = inputPanel.getBusinessSlider().getValue();
        capacity = inputPanel.getCapacitySlider().getValue();
        richness = inputPanel.getRichnessSlider().getValue();

        // Create entities with the specified number of people
        cityModel.createEntities(numberOfPeople);
        // Restart the clock simulation
        cityModel.getClockModel().restartSimulation();
        // Update the pause button state on the clock panel
        clockPanel.updatePauseButton(cityModel.getClockModel().getIsPaused());
    }  
}
 