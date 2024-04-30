package unibo.citysimulation.view.sidePanels;


import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
public class InfoPanel extends StyledPanel {
    private int x;
    private int y;
    private JLabel coordinates;
    private JLabel numberOfPeople;
    private JLabel numberOfZones;
    private JLabel numberOfTransportLines;

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

        numberOfZones = new JLabel("Number of Zones: ");
        gbc.gridy = 3;
        add(numberOfZones, gbc);

        numberOfTransportLines = new JLabel("Number of Transport Lines: ");
        gbc.gridy = 4;
        add(numberOfTransportLines, gbc);
    }

    public void updatePositionInfo(int x, int y){
        this.x = x;
        this.y = y;
        coordinates.setText("Coordinates: (" + x + ", " + y + ")");
        System.out.println("Coordinates: (" + x + ", " + y + ")");
    }

    public void updateNumberOfPeople(int people){
        numberOfPeople.setText("Number of People: " + people);
    }

    public void updateNumberOfZones(int zones){
        numberOfZones.setText("Number of Zones: " + zones);
    }

    public void updateNumberOfTransportLines(int lines){
        numberOfTransportLines.setText("Number of Transport Lines: " + lines);
    }
}