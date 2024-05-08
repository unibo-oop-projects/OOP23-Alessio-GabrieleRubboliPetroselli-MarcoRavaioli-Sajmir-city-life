package unibo.citysimulation.view.sidePanels;


import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class InfoPanel extends StyledPanel {
    private int x;
    private int y;
    private JLabel coordinates;
    private JLabel numberOfPeople;
    private JLabel numberOfZones;
    private JLabel numberOfTransportLines;
    private List<Zone> zones;
    private List<TransportLine> transports;

    public InfoPanel(Color bgColor) { 
        super(bgColor);

        // Set the layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a JLabel with the desired text
        JLabel label = new JLabel("INFOPANEL", SwingConstants.CENTER); // Align the text to the center
        label.setForeground(Color.WHITE); // Set the color of the text

        // Add the JLabel to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(label, gbc);

        coordinates = new JLabel("Coordinates: ");
        gbc.gridy = 1;
        add(coordinates, gbc);

        numberOfPeople = new JLabel("Number of People: ");
        gbc.gridy = 2;
        add(numberOfPeople, gbc);
    }

    public void updatePositionInfo(int x, int y){
        this.x = x;
        this.y = y;
        coordinates.setText("Coordinates: (" + x + ", " + y + ")");
        System.out.println("Coordinates: (" + x + ", " + y + ")");
    }

    public void updateNumberOfPeople(int peopleNumber){
        numberOfPeople.setText("Number of People: " + peopleNumber);
    }
}