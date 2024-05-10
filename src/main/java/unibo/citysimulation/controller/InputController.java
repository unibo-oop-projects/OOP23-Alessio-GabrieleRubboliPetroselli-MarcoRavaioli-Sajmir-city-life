package unibo.citysimulation.controller;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
 
import unibo.citysimulation.model.CityModel;
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
    private int numberOfPeople;
    private GraphicsPanel graphicsPanel;
    private MapPanel mapPanel;
 
    /**
     * Constructs an InputController object.
     *
     * @param cityModel   The CityModel object representing the city simulation.
     * @param inputPanel  The InputPanel object representing the input panel.
     * @param clockPanel  The ClockPanel object representing the clock panel.
     */
    public InputController(CityModel cityModel, InputPanel inputPanel, ClockPanel clockPanel,GraphicsPanel graphicsPanel, MapPanel mapPanel) {
        this.cityModel = cityModel;
        this.inputPanel = inputPanel;
        numberOfPeople = inputPanel.getPeopleSlider().getValue();
        this.graphicsPanel = graphicsPanel;
        this.mapPanel = mapPanel;
 
        // Add action listener for the start button
        inputPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PREMUTO IL TASTO START SIMULATION");
                startSimulation(clockPanel);
            }
        });
 
        // Add change listener for the people slider
        inputPanel.getPeopleSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numberOfPeople = inputPanel.getPeopleSlider().getValue();
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
 
        mapPanel.setZones(cityModel.getZones());
    }  
}
 