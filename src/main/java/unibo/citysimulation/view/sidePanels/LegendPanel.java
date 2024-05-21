package unibo.citysimulation.view.sidePanels;

import javax.swing.*;

import unibo.citysimulation.model.transport.TransportLine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LegendPanel extends JButton {
    private List<Color> colors;
    private List<TransportLine> transportLines;

    public LegendPanel(List<Color> colors, List<TransportLine> transportLines) {
        this.colors = colors;
        this.transportLines = transportLines;
        this.setText("Legend");
        this.setPreferredSize(new Dimension(20, 20)); // Set the preferred size of the button to make it small
        this.addActionListener(new ActionListener() {
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