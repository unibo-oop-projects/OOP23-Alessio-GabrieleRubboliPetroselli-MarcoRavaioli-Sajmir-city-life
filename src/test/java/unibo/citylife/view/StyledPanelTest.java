package unibo.citylife.view;

import unibo.citysimulation.view.StyledPanel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import javax.swing.border.Border;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StyledPanelTest {
    
    private StyledPanel styledPanel;
    private final Color testColor = Color.RED;

    @BeforeEach
    void setUp() {
        styledPanel = new StyledPanel(testColor);
    }

    @Test
    void testStyledPanelConstructor() {
        // Test if the border is set correctly
        Border border = styledPanel.getBorder();
        assertTrue(border != null, "Border should not be null");
        // Further assertions to check the type and components of the compound border can be added if necessary

        // Test if the background color is set correctly
        Color bgColor = styledPanel.getBackground();
        assertEquals(testColor, bgColor, "Background color should be set correctly");
    }

    @Test
    void testGetBgColor() {
        // Test if the background color is returned correctly
        Color bgColor = styledPanel.getBgColor();
        assertEquals(testColor, bgColor, "Background color should be returned correctly");
    }

}
