package unibo.citysimulation.model.view;
import javax.swing.*;
import java.awt.*;
import org.jfree.data.xy.XYSeries; // Import the XYSeries class
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class TransportLineGraphsView extends JFrame {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;

    public TransportLineGraphsView() {
        setTitle("Transport Line Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        setSize(1000, 600); // Imposta la dimensione della finestra
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

        // Left panel with capacity plot and info
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.RED);

        // Create the XYSeries and add data (for demonstration purposes)
        XYSeries series = new XYSeries("Capacity");
        series.add(1, 1);
        series.add(2, 2);
        series.add(3, 3);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Capacity Plot",
                "X-Axis",
                "Y-Axis",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 300));

        // Create the capacity panel and add the chart panel
        JPanel capacityPanel = new JPanel();
        capacityPanel.setBackground(Color.BLUE);
        capacityPanel.setBorder(BorderFactory.createTitledBorder("Capacity"));
        capacityPanel.add(chartPanel);

        // Add the capacity panel to the left panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        leftPanel.add(capacityPanel, gbc);

        // Create the info panel and add it to the left panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.YELLOW);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Info"));
        gbc.gridy = 1;
        leftPanel.add(infoPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;  // Set the weight to 25% of the total width
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbc);

        // Central panel for the main graphs
        centerPanel = new JPanel(); // Empty panel for white space
        centerPanel.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.weightx = 0.5;  // Set the weight to 50% of the total width
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
        gbc.weightx = 0.25;  // Set the weight to 25% of the total width
        add(rightPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TransportLineGraphsView();
            }
        });
    }
}



