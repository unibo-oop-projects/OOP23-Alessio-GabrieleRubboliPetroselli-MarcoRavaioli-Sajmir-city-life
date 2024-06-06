package unibo.citylife.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import unibo.citysimulation.controller.InputController;
import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.InputModel;
import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.view.sidepanels.InputPanel;
import unibo.citysimulation.view.sidepanels.clock.ClockPanel;
import unibo.citysimulation.model.map.impl.MapModelImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the InputController.
 */
public class InputControllerTest {
    private CityModel cityModel;
    private InputModel inputModel;
    private InputPanel inputPanel;
    private ClockPanel clockPanel;
    private MapModelImpl mapModel; 
    private static final int BUSINESS_SLIDER_VALUE = 5;
    private static final int CAPACITY_SLIDER_VALUE = 20;
    private static final int PEOPLE_SLIDER_VALUE = 10;

    /**
     * This method sets up the test environment.
     */
    @BeforeEach
    public void setup() {
        cityModel = mock(CityModel.class);
        inputModel = mock(InputModel.class);
        inputPanel = mock(InputPanel.class);
        clockPanel = mock(ClockPanel.class);
        ClockModel clockModel = mock(ClockModel.class);
        mapModel = mock(MapModelImpl.class); // Crea un mock di MapModelImpl
        // Configura i mock
        when(cityModel.getClockModel()).thenReturn(clockModel);
        when(cityModel.getMapModel()).thenReturn(mapModel); // Assicurati che getMapModel() ritorni il mock
        new InputController(cityModel, inputModel, inputPanel, clockPanel);
    }

    /**
     * This test verifies the action performed when the start button is clicked.
     */
    @Test
    public void testStartButtonActionPerformed() {
        // Arrange
        when(inputPanel.getPeopleSliderValue()).thenReturn(PEOPLE_SLIDER_VALUE);
        when(inputPanel.getBusinessSliderValue()).thenReturn(BUSINESS_SLIDER_VALUE);
        when(inputPanel.getCapacitySliderValue()).thenReturn(CAPACITY_SLIDER_VALUE);
        // Act
        ArgumentCaptor<ActionListener> captor = ArgumentCaptor.forClass(ActionListener.class);
        verify(inputPanel).addStartButtonListener(captor.capture());
        ActionListener startButtonListener = captor.getValue();
        startButtonListener.actionPerformed(mock(ActionEvent.class));
        // Assert
        verify(inputModel).setNumberOfPeople(PEOPLE_SLIDER_VALUE);
        verify(inputModel).addNumberOfBusiness(BUSINESS_SLIDER_VALUE);
        verify(inputModel).setCapacity(CAPACITY_SLIDER_VALUE);
        verify(cityModel).createEntities();
        verify(cityModel.getClockModel()).restartSimulation();
        verify(clockPanel).updatePauseButton(cityModel.getClockModel().isPaused());
        verify(clockPanel).setPauseButtonEnabled(true);
        verify(mapModel).startSimulation(); // Verifica che startSimulation() sia chiamato su mapModel
    }

    /**
     * This test verifies that the simulation stops when the stop button is pressed.
     */
    @Test
    public void testStopButtonActionPerformed() {
        // Arrange
        ArgumentCaptor<ActionListener> captor = ArgumentCaptor.forClass(ActionListener.class);
        verify(inputPanel).addStopButtonListener(captor.capture());
        ActionListener stopButtonListener = captor.getValue();
        // Act
        stopButtonListener.actionPerformed(mock(ActionEvent.class));
        // Assert
        verify(cityModel.getClockModel()).stopSimulation();
        verify(clockPanel).updatePauseButton(cityModel.getClockModel().isPaused());
        verify(clockPanel).setPauseButtonEnabled(false);
    }
}
