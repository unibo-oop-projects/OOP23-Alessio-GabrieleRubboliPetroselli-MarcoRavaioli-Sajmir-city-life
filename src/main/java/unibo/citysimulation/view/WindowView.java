package unibo.citysimulation.view;

import unibo.citysimulation.controller.MapController;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.WindowModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;

public class WindowView extends JFrame {
    private WindowModel windowModel;
    private InfoPanel infoPanel = new InfoPanel(Color.GREEN);
    private ClockPanel clockPanel = new ClockPanel(Color.RED);
    private InputPanel inputPanel = new InputPanel(Color.BLUE);
    private GraphicsPanel graphicsPanel = new GraphicsPanel(Color.YELLOW);
    private MapModel mapModel;
    private MapController mapController;

    public WindowView(WindowModel windowModel, MapModel mapModel){
        this.windowModel = windowModel;

        this.mapModel = mapModel;
        mapController = new MapController(mapModel, infoPanel);

        setTitle(ConstantAndResourceLoader.APPLICATION_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        configureLayout();
        createComponents();
        setVisible(true);



        System.out.println("windowView initialized, istanziato mapModel e mapController");
    }

    public void addResizeListener(ComponentAdapter adapter) {
        addComponentListener(adapter);
    }

    public void updatePanelSize(){
        int panelWidth = windowModel.getWidth() / 4;

        inputPanel.setPreferredSize(new Dimension(panelWidth, windowModel.getHeight()));
        infoPanel.setPreferredSize(new Dimension(panelWidth, windowModel.getHeight()));
        clockPanel.setPreferredSize(new Dimension(panelWidth, windowModel.getHeight()));
        graphicsPanel.setPreferredSize(new Dimension(panelWidth, windowModel.getHeight()));

        revalidate();
        repaint();
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(windowModel.getWidth(), windowModel.getHeight()));
        pack();
    }

    private void createComponents() {

        // Aggiungi il pannello della mappa al centro
        MapPanel mapPanel = new MapPanel(mapModel, infoPanel);
        add(mapPanel, BorderLayout.CENTER);

        //Aggiungi i pannelli laterali
        createSidePanels();
    }

    private void createSidePanels() {

        int sidePanelWidth = getSize().width / 4;
        int sidePanelsHeight = getSize().height;

        JPanel leftPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        

        constraints.gridy = 0;
        constraints.weighty = 0.75;
        inputPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 4 * 3));
        leftPanel.add(inputPanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.25;
        infoPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 4));
        leftPanel.add(infoPanel, constraints);        
        

        constraints.gridy = 0;
        constraints.weighty = 0.125;
        clockPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8));
        rightPanel.add(clockPanel, constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.875;
        graphicsPanel.setPreferredSize(new Dimension(sidePanelWidth, sidePanelsHeight / 8 * 7));
        rightPanel.add(graphicsPanel, constraints);

        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

}
