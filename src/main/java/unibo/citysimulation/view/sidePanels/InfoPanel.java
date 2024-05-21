package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.view.StyledPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel for displaying information.
 */
public class InfoPanel extends StyledPanel {
    private JLabel coordinates;
    private JLabel numberOfPeople;
    private JLabel zoneNJLabel;
    private JLabel numberOfBusiness;
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN);
    private List<TransportLine> transportLines;
    private List<Zone> zones = ZoneFactory.createZonesFromFile();

    /**
     * Constructs an InfoPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public InfoPanel(Color bgColor) {
        super(bgColor);
        //private List<Zone> zones = ZoneFactory.createZonesFromFile();
        this.transportLines = TransportFactory.createTransportsFromFile(zones);
        

        // Set the layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a JLabel with the desired text
        zoneNJLabel = new JLabel("Zone:", SwingConstants.CENTER); // Align the text to the center
        zoneNJLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font of the text


        // Add the JLabel to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(zoneNJLabel, gbc);

        coordinates = new JLabel("Coordinates: ");
        gbc.gridy = 1;
        add(coordinates, gbc);

        numberOfPeople = new JLabel("Number of People: ");
        gbc.gridy = 2;
        add(numberOfPeople, gbc);
        
        numberOfBusiness = new JLabel("Number of Business: ");
        gbc.gridy = 3;
        add(numberOfBusiness, gbc);
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

    public void updateNumberOfBusiness(int businessNumber){
        numberOfBusiness.setText("Number of Business: " + businessNumber);
    }
}
