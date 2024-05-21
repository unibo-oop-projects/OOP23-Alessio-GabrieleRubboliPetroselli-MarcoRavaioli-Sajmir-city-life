package unibo.citysimulation.view.sidePanels;

import javax.swing.*;

import unibo.citysimulation.model.transport.TransportLine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LegendPanel extends JFrame {
    private List<Color> colors;
    private List<String> linesName;

    public LegendPanel(List<Color> colors, List<String> linesName) {
        this.colors = colors;
        this.linesName = linesName;
        this.setTitle("Legend");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);

        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Graph Legend");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(title);
        legendPanel.add(Box.createVerticalStrut(10)); // Spacing
        JLabel divisionTitle = new JLabel("People State:");
        divisionTitle.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(divisionTitle);

        legendPanel.add(createLegendItem("WORKING", Color.RED));
        legendPanel.add(createLegendItem("AT_HOME", Color.BLUE));
        legendPanel.add(createLegendItem("MOVING", Color.YELLOW));

        legendPanel.add(Box.createVerticalStrut(10)); // Spacing
        JLabel transportTitle = new JLabel("Transport Congestion:");
        transportTitle.setFont(new Font("Serif", Font.BOLD, 18));
        legendPanel.add(transportTitle);


        for (int i = 0; i < linesName.size(); i++) {
            String lineName = linesName.get(i);
            Color color = colors.get(i % colors.size());
            legendPanel.add(createLegendItem(lineName, color));
        }

        JScrollPane scrollPane = new JScrollPane(legendPanel);
        add(scrollPane);
        setVisible(true);
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

    // The rest of the methods for creating the legend frame go here
}