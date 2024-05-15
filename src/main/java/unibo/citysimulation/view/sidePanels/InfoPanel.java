package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying information.
 */
public class InfoPanel extends StyledPanel {
    private JLabel coordinates;
    private JLabel numberOfPeople;
    private JLabel zoneNJLabel;

    /**
     * Constructs an InfoPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
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

        zoneNJLabel = new JLabel("Zone: ");
        gbc.gridy = 3;
        add(zoneNJLabel, gbc);

        JButton legendButton = new JButton("Legend");
        legendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame legendFrame = new JFrame(" Graph Legend");
                legendFrame.setSize(300, 300);
                legendFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JLabel legendLabel = new JLabel("<html><h1>Graph Legend</h1><br><h2>Red: People</h2><br><h2>Green: Business</h2><br><h2>Blue: Richness</h2></html>");
                legendFrame.add(legendLabel);
                legendFrame.setVisible(true);
            }
        });
        gbc.gridy = 4;
        add(legendButton, gbc);
    }


    /**
     * Updates the position information displayed on the panel.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void updatePositionInfo(int x, int y){
        coordinates.setText("Coordinates: (" + x + ", " + y + ")");
        System.out.println("Coordinates: (" + x + ", " + y + ")");
    }

    /**
     * Updates the number of people displayed on the panel.
     *
     * @param peopleNumber The number of people.
     */
    public void updateNumberOfPeople(int peopleNumber){
        numberOfPeople.setText("Number of People: " + peopleNumber);
    }

    public void updateZoneName(String zoneName){
        zoneNJLabel.setText("Zone: " + zoneName);
    }
}
