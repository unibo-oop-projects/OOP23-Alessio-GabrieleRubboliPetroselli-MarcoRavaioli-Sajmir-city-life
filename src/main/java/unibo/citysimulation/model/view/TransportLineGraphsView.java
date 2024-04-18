package unibo.citysimulation.model.view;

import javax.swing.*;
import java.awt.*;

/**
 * A JFrame class for displaying transport line graphs.
 */
public class TransportLineGraphsView extends JFrame {

    /**
     * Constructs a new instance of TransportLineGraphsView.
     */
    public TransportLineGraphsView() {
        setTitle("Transport Line Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        setPreferredSize(new Dimension(1000, 600)); // Set the preferred size of the window
        configureLayout();
        createComponents();
        pack(); // Resize the window based on the components
        setVisible(true);
    }

    /**
     * Configures the layout of the frame.
     */
    private void configureLayout() {
        setLayout(new BorderLayout());
    }

    /**
     * Creates the components of the frame.
     */
    private void createComponents() {
        // Main panel divided into three sections
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Central panel for the main graphs
        JPanel centerPanel = new JPanel(); // Empty panel for white space
        centerPanel.setBackground(Color.WHITE);

        // Left panel for "Info" and "Clock" panels
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(createInfoPanel());
        leftPanel.add(createClockPanel());

        // Right panel for secondary graphs "Capacity" and "Congestion"
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(createGraphPanel("Capacity", Color.BLUE));
        rightPanel.add(createGraphPanel("Congestion", Color.GREEN));

        // Add the central panel to the main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Add the side panels to the main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the "Info" panel on the left side.
     *
     * @return The "Info" panel.
     */
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Info");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.YELLOW); // Change the color as desired
        panel.setPreferredSize(new Dimension(150, 600)); // Increase the width of the left panel
        return panel;
    }

    /**
     * Creates the "Clock" panel on the left side.
     *
     * @return The "Clock" panel.
     */
    private JPanel createClockPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Clock");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.ORANGE); // Change the color as desired
        panel.setPreferredSize(new Dimension(150, 600)); // Increase the width of the left panel
        return panel;
    }

    /**
     * Creates a graph panel with the specified title and color on the right side.
     *
     * @param title The title of the graph panel.
     * @param color The background color of the graph panel.
     * @return The graph panel.
     */
    private JPanel createGraphPanel(String title, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(200, 600)); // Increase the width of the right panel
        return panel;
    }

    /**
     * Main method to launch the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TransportLineGraphsView::new);
    }
}