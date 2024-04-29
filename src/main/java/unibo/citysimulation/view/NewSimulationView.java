package unibo.citysimulation.view;

import unibo.citysimulation.controller.ClockController;
import unibo.citysimulation.model.ClockModel;
import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;

import javax.swing.*;
import java.awt.*;
import java.time.Clock;

public class NewSimulationView extends JFrame {
    InfoPanel infoPanel = new InfoPanel(Color.GREEN);
    ClockPanel clockPanel = new ClockPanel(Color.PINK);

    public NewSimulationView() {
        setTitle(ConstantAndResourceLoader.APPLICATION_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        configureLayout();
        createComponents();
        setVisible(true);
    }

    private void configureLayout() {
        setLayout(new BorderLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE),
                (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE));
        /*
        Container pane = getContentPane();
        pane.setLayout(null);
        pane.setBackground(Color.LIGHT_GRAY);*/
    }

    private void createComponents() {
        MapModel mapModel = new MapModel();
        ClockController clockController = new ClockController(clockPanel);
        ClockModel clockModel = new ClockModel(7, clockController);
        clockModel.startSimulation();

        // Aggiungi il pannello della mappa al centro
        MapPanel mapPanel = new MapPanel(mapModel, infoPanel);
        add(mapPanel, BorderLayout.CENTER);

        //Aggiungi i pannelli laterali
        createSidePanels();
    }

    private void createSidePanels() {
        // Creazione del pannello sinistro superiore con due sottopannelli di colore diverso
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(new InputPanel(Color.BLUE), BorderLayout.CENTER);
        leftPanel.add(infoPanel, BorderLayout.SOUTH);

        // Creazione del pannello destro superiore con due sottopannelli di colore diverso
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(clockPanel, BorderLayout.NORTH);
        rightPanel.add(new GraphicsPanel(Color.RED), BorderLayout.CENTER);

        // Aggiunta dei pannelli laterali al frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

}
