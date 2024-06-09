package unibo.citylife.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.awt.Component;
import unibo.citysimulation.WelcomeScreen;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is responsible for integration testing of the WelcomeScreen class.
 * It verifies that the simulation starts when the start button is pressed.
 * 
 * @ExtendWith(MockitoExtension.class) is used to initialize mocks and inject them.
 */
@ExtendWith(MockitoExtension.class)
class SimulationLaucherTest {
    private WelcomeScreen welcomeScreen;
    /**
     * This method is executed before each test. It initializes the WelcomeScreen.
     */
    @BeforeEach
    void setUp() {
        welcomeScreen = new WelcomeScreen();
    }
    /**
     * This method is executed after each test. It disposes the WelcomeScreen.
     */
    @AfterEach
    void tearDown() {
        welcomeScreen.dispose();
    }
     /**
     * This test verifies that the simulation starts when the start button is pressed.
     * It first finds the start button in the WelcomeScreen, then simulates a button click,
     * and finally asserts that the WelcomeScreen is no longer visible.
     */
    @Test
    void testSimulationStartsWhenStartButtonPressed() {
        // Arrange
        JButton startButton = null;
        for (final Component component : welcomeScreen.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                final JPanel buttonPanel = (JPanel) component;
                for (final Component buttonComponent : buttonPanel.getComponents()) {
                    if (buttonComponent instanceof JButton && "START".equals(((JButton) buttonComponent).getText())) {
                        startButton = (JButton) buttonComponent;
                        break;
                    }
                }
                break;
            }
        }
        assertNotNull(startButton, "START button not found");
        // Act
        startButton.doClick();
        // Assert
        assertFalse(welcomeScreen.isVisible(), "Welcome screen should be closed");
    }
}
