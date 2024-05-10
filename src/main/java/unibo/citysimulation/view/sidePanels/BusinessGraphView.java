package unibo.citysimulation.view.sidePanels;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class BusinessGraphView extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;

    public BusinessGraphView() {
        setTitle("Transport Line Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window full screen
        configureLayout();
        createComponents();
        setVisible(true);
    }

    /**
     * Configures the layout of the frame.
     */
    private void configureLayout() {
        setLayout(new GridBagLayout());
    }

    /**
     * Creates the components of the frame.
     */
    private void createComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0; // Set the weight to 100% of the total height

        // Left panel with "Capacity" and "Data" panels
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.RED);

        JFreeChart chart = ChartFactory.createXYLineChart(
            "Title",
            "X-Axis",
            "Y-Axis",
            null,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        ChartPanel chartPanel = new ChartPanel(chart);

        leftPanel.add(chartPanel, gbc);

        JPanel capacityPanel = new JPanel();
        capacityPanel.setBackground(Color.GREEN);
        capacityPanel.setBorder(BorderFactory.createTitledBorder("Capacity"));
        gbc.weightx = 0.2;  // Set the weight to 20% of the total width
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(capacityPanel, gbc);

        gbc.weightx = 0.2;  // Reset the weight
        gbc.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbc);

        // Central panel for the main graphs
        centerPanel = new JPanel(); // Empty panel for white space
        centerPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.weightx = 0.6;  // Set the weight to 60% of the total width
        add(centerPanel, gbc);

        // Right panel with "Congestion" and "Time" panels
        rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setBackground(Color.BLACK);

        JPanel congestionPanel = new JPanel();
        congestionPanel.setBackground(Color.BLUE);
        congestionPanel.setBorder(BorderFactory.createTitledBorder("Congestion"));
        rightPanel.add(congestionPanel);

        JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.YELLOW);
        timePanel.setBorder(BorderFactory.createTitledBorder("Time"));
        rightPanel.add(timePanel);

        gbc.gridx = 2;
        gbc.weightx = 0.2;  // Set the weight to 20% of the total width
        add(rightPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BusinessGraphView();
            }
        });
    }
}
