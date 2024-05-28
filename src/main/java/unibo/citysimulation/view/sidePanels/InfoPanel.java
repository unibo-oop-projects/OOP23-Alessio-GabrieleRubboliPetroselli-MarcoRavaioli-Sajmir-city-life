package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * Panel that displays information about the zone clicked.
 */
public final class InfoPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel coordinates;
    private final JLabel numberOfPeople;
    private final JLabel zoneNJLabel;
    private final JLabel numberOfBusiness;

    /**
     * Constructs an InfoPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public InfoPanel(final Color bgColor) {
        super(bgColor);

        // Set the layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        // Create a JLabel with the desired text
        zoneNJLabel = new JLabel("Zone:", SwingConstants.CENTER); // Align the text to the center
        zoneNJLabel.setFont(new Font("Arial", Font.BOLD, ConstantAndResourceLoader.INFO_PANEL_FONT_SIZE));

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
    public void updatePositionInfo(final int x, final int y) {
        coordinates.setText("Coordinates: (" + x + ", " + y + ")");
    }

    /**
     * Updates the number of people displayed on the panel.
     *
     * @param peopleNumber The number of people.
     */
    public void updateNumberOfPeople(final int peopleNumber) {
        numberOfPeople.setText("Number of People: " + peopleNumber);
    }
    /**
     * Updates the zone name displayed on the panel.
     *
     * @param zoneName The name of the zone.
     */
    public void updateZoneName(final String zoneName) {
        zoneNJLabel.setText("Zone: " + zoneName);
    }
    /**
     * Updates the number of businesses displayed on the panel.
     *
     * @param businessNumber The number of businesses.
     */
    public void updateNumberOfBusiness(final int businessNumber) {
        numberOfBusiness.setText("Number of Business: " + businessNumber);
    }
}
