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

import static org.mockito.Mockito.*;

public class InputControllerTest {
    private CityModel cityModel;
    private InputModel inputModel;
    private InputPanel inputPanel;
    private ClockPanel clockPanel;
    private MapModelImpl mapModel; // Aggiungi questo
    private InputController inputController;

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

        inputController = new InputController(cityModel, inputModel, inputPanel, clockPanel);
    }

    @Test
    public void testStartButtonActionPerformed() {
        // Arrange
        when(inputPanel.getPeopleSliderValue()).thenReturn(10);
        when(inputPanel.getBusinessSliderValue()).thenReturn(5);
        when(inputPanel.getCapacitySliderValue()).thenReturn(20);

        // Act
        ArgumentCaptor<ActionListener> captor = ArgumentCaptor.forClass(ActionListener.class);
        verify(inputPanel).addStartButtonListener(captor.capture());
        ActionListener startButtonListener = captor.getValue();
        startButtonListener.actionPerformed(mock(ActionEvent.class));

        // Assert
        verify(inputModel).setNumberOfPeople(10);
        verify(inputModel).addNumberOfBusiness(5);
        verify(inputModel).setCapacity(20);
        verify(cityModel).createEntities();
        verify(cityModel.getClockModel()).restartSimulation();
        verify(clockPanel).updatePauseButton(cityModel.getClockModel().isPaused());
        verify(clockPanel).setPauseButtonEnabled(true);
        verify(mapModel).startSimulation(); // Verifica che startSimulation() sia chiamato su mapModel
    }

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
