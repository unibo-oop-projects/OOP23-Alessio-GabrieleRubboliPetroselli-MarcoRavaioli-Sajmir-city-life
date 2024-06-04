package unibo.citylife;


import org.junit.jupiter.api.Test;

import unibo.citysimulation.view.sidepanels.InfoPanel;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

public class InfoPanelTest {
    @Test
    public void testUpdateMethods() {
        InfoPanel panel = new InfoPanel(Color.WHITE);

        // Test updatePositionInfo
        panel.updatePositionInfo(10, 20);
        assertEquals("Coordinates: (10, 20)", panel.coordinates.getText());

        // Test updateNumberOfPeople
        panel.updateNumberOfPeople(100);
        assertEquals("Number of People: 100", panel.numberOfPeople.getText());

        // Test updateZoneName
        panel.updateZoneName("Test Zone");
        assertEquals("Zone: Test Zone", panel.zoneNJLabel.getText());

        // Test updateNumberOfBusiness
        panel.updateNumberOfBusiness(50);
        assertEquals("Number of Business: 50", panel.numberOfBusiness.getText());

        // Test updateAvaragePay
        panel.updateAvaragePay(5000.00);
        assertEquals("Average Pay: 5000,00", panel.numberOfAvaregePay.getText());

        // Test updateNumberOfDirectLines
        panel.updateNumberOfDirectLines(5);
        assertEquals("Number of Direct Lines: 5", panel.numberDirectLines.getText());
    }
}