package unibo.citysimulation.view.sidepanels;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * This class represents a legend panel for the city simulation.
 * It extends JFrame to create a separate window for the legend.
 */
public class LegendPanel extends JFrame {
    private static final long serialVersionUID = 1L;

     /**
     * Constructor for the LegendPanel class.
     * @param colors The list of colors to be used in the legend.
     * @param linesName The list of names for each line in the legend.
     */
    public LegendPanel(final List<Color> colors, final List<String> linesName) {
        this.setTitle("Legend");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);

        final JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Graph Legend");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(title);
        legendPanel.add(Box.createVerticalStrut(10)); // Spacing
        final JLabel divisionTitle = new JLabel("People State:");
        divisionTitle.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(divisionTitle);

        legendPanel.add(createLegendItem("WORKING", Color.RED));
        legendPanel.add(createLegendItem("AT_HOME", Color.BLUE));
        legendPanel.add(createLegendItem("MOVING", Color.YELLOW));

        legendPanel.add(Box.createVerticalStrut(10)); // Spacing
        final JLabel transportTitle = new JLabel("Transport Congestion:");
        transportTitle.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(transportTitle);


        for (int i = 0; i < linesName.size(); i++) {
            final String lineName = linesName.get(i);
            final Color color = colors.get(i % colors.size());
            legendPanel.add(createLegendItem(lineName, color));
        }

        final JScrollPane scrollPane = new JScrollPane(legendPanel);
        add(scrollPane);
        setVisible(true);
    }
     /**
     * Creates a JPanel representing a single item in the legend.
     * @param text The text for the legend item.
     * @param color The color for the legend item.
     * @return A JPanel representing the legend item.
     */
    private JPanel createLegendItem(final String text, final Color color) {
        final JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        final JLabel colorLabel = new JLabel();
        colorLabel.setOpaque(true);
        colorLabel.setBackground(color);
        colorLabel.setPreferredSize(new Dimension(10, 10));
        itemPanel.add(colorLabel);
        itemPanel.add(new JLabel(text));
        return itemPanel;
    }
}