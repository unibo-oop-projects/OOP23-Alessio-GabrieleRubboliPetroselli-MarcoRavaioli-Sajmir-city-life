package unibo.citysimulation.view;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;
import unibo.citysimulation.view.map.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;

/**
 * Represents the main window of the application.
 */
public class WindowView extends JFrame {
    private int width;
    private int height;

    private MapPanel mapPanel = new MapPanel();                                 // questi andrebbero dentro ad una lista
    private InfoPanel infoPanel = new InfoPanel(Color.GREEN);                   // List<StyledPanel> e gestirli tutti insieme
    private ClockPanel clockPanel = new ClockPanel(Color.RED);
    private InputPanel inputPanel = new InputPanel(Color.BLUE);
    private GraphicsPanel graphicsPanel = new GraphicsPanel(Color.YELLOW);

    /**
     * Constructs a WindowView with the specified window model and map model.
     */
    public WindowView() {
        
        setMinimumSize(new Dimension(ConstantAndResourceLoader.SCREEN_MINIMUM_WIDTH_PIXEL, ConstantAndResourceLoader.SCREEN_MINIMUM_HEIGHT_PIXEL));

        setTitle(ConstantAndResourceLoader.APPLICATION_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        configureLayout();
        createComponents();
        setVisible(true);

        pack();
    }

    /**
     * Adds a component resize listener.
     *
     * @param adapter The component adapter to add.
     */
    public void addResizeListener(ComponentAdapter adapter) {
        addComponentListener(adapter);
    }

    /**
     * Updates the size of the panels based on the window size.
     */
    public void updateFrame(int width, int height) {
        setSize(new Dimension(width, height));

        inputPanel.setPreferredSize(new Dimension(width / 4, height));
        infoPanel.setPreferredSize(new Dimension(width / 4, height));
        clockPanel.setPreferredSize(new Dimension(width / 4, height));
        graphicsPanel.setPreferredSize(new Dimension(width / 4, height));
        mapPanel.setPreferredSize(new Dimension(width / 2, height));

        revalidate();
        repaint();
    }

    /**
     * Configures the layout of the window.
     */
    private void configureLayout() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        pack();
    }

    /**
     * Creates the components of the window.
     */
    private void createComponents() {
        // Add the map panel to the center
        add(mapPanel, BorderLayout.CENTER);

        // Add the side panels
        createSidePanels();
    }

    /**
     * Creates the side panels of the window.
     */
    private void createSidePanels() {
        int sidePanelWidth = width / 4;
        int sidePanelsHeight = height;

        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        // Add input panel and info panel to left panel
        constraints.gridy = 0;
        constraints.weighty = 0.625;
        inputPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8 * 5));
        leftPanel.add(inputPanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.375;
        infoPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8 * 3));
        leftPanel.add(infoPanel, constraints);

        // Add clock panel and graphics panel to right panel
        constraints.gridy = 0;
        constraints.weighty = 0.125;
        clockPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8));
        rightPanel.add(clockPanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.875;
        graphicsPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8 * 7));
        rightPanel.add(graphicsPanel, constraints);

        // Add left and right panels to the window
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    public void setFrame(Pair<Integer, Integer> frameSize) {
        this.width = frameSize.getFirst();
        this.height = frameSize.getSecond();
        //setSize(width, height);

        updateFrame(width, height);
    }


    /**
     * Retrieves the info panel.
     *
     * @return The info panel.
     */
    public InfoPanel getInfoPanel(){
        return infoPanel;
    }

    /**
     * Retrieves the clock panel.
     *
     * @return The clock panel.
     */
    public ClockPanel getClockPanel(){
        return clockPanel;
    }

    /**
     * Retrieves the input panel.
     *
     * @return The input panel.
     */
    public InputPanel getInputPanel(){
        return inputPanel;
    }

    /**
     * Retrieves the graphics panel.
     *
     * @return The graphics panel.
     */
    public GraphicsPanel getGraphicsPanel(){
        return graphicsPanel;
    }

    /**
     * Retrieves the map panel.
     *
     * @return The map panel.
     */
    public MapPanel getMapPanel(){
        return mapPanel;
    }
}
