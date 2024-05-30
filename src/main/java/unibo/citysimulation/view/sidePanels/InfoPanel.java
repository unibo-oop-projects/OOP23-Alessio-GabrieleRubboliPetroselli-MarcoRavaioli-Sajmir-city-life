package unibo.citysimulation.view.sidepanels;

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
                JFrame legendFrame = new JFrame("Graph Legend");
                legendFrame.setSize(300, 300);
                legendFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel legendPanel = new JPanel();
                legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

                JLabel title = new JLabel("Graph Legend");
                title.setFont(new Font("Serif", Font.BOLD, 18));
                legendPanel.add(title);
                legendPanel.add(Box.createVerticalStrut(10)); // Spacing
                JLabel divisionTitle = new JLabel("People State:");
                divisionTitle.setFont(new Font("Serif", Font.BOLD, 18));
                legendPanel.add(divisionTitle);

                legendPanel.add(createLegendItem("AT_WORKING", Color.RED));
                legendPanel.add(createLegendItem("AT_HOME", Color.BLUE));
                legendPanel.add(createLegendItem("MOVING", Color.YELLOW));

                legendPanel.add(Box.createVerticalStrut(10)); // Spacing
                JLabel transportTitle = new JLabel("Transport Congestion:");
                transportTitle.setFont(new Font("Serif", Font.BOLD, 18));
                legendPanel.add(transportTitle);

                // Add transport lines to the legend
                for (int i = 0; i < transportLines.size(); i += 3) {
                    JPanel groupPanel = new JPanel();
                    groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
                    for (int j = 0; j < 3 && (i + j) < transportLines.size(); j++) {
                        TransportLine line = transportLines.get(i + j);
                        Color color = colors.get((i + j) % colors.size());
                        groupPanel.add(createLegendItem(line.getName(), color));
                    }
                    legendPanel.add(groupPanel);
                }

                JScrollPane scrollPane = new JScrollPane(legendPanel);
                legendFrame.add(scrollPane);
                legendFrame.setVisible(true);
            }
        });
        gbc.gridy = 4;
        add(legendButton, gbc);
    }
    private JPanel createLegendItem(String text, Color color) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel colorLabel = new JLabel();
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setPreferredSize(new Dimension(10, 10));
        itemPanel.add(colorLabel);
        itemPanel.add(new JLabel(text));
        return itemPanel;
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
