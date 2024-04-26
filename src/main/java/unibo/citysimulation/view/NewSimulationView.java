package unibo.citysimulation.view;

import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.map.MapPanel;
import unibo.citysimulation.view.sidePanels.InfoPanel;
import unibo.citysimulation.view.sidePanels.InputPanel;
import unibo.citysimulation.view.sidePanels.ClockPanel;
import unibo.citysimulation.view.sidePanels.GraphicsPanel;

import javax.swing.*;
import java.awt.*;

public class NewSimulationView extends JFrame {
    InfoPanel infoPanel = new InfoPanel(Color.GREEN);
    ClockPanel clockPanel = new ClockPanel(Color.RED);
    InputPanel inputPanel = new InputPanel(Color.BLUE);
    GraphicsPanel graphicsPanel = new GraphicsPanel(Color.YELLOW);

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
    }

    private void createComponents() {
        MapModel mapModel = new MapModel();

        // Aggiungi il pannello della mappa al centro
        MapPanel mapPanel = new MapPanel(mapModel, infoPanel);
        add(mapPanel, BorderLayout.CENTER);

        //Aggiungi i pannelli laterali
        createSidePanels();
    }

    private void createSidePanels() {

        int sidePanelWidth = getSize().width / 4;

        // Creazione del pannello sinistro superiore con due sottopannelli di colore diverso
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        inputPanel.setSize(new Dimension(sidePanelWidth, 100));//getSize().height / 4 * 3));
        leftPanel.add(inputPanel, BorderLayout.CENTER);
        infoPanel.setPreferredSize(new Dimension(sidePanelWidth, getSize().height / 4));
        //leftPanel.add(infoPanel, BorderLayout.SOUTH);

        // Creazione del pannello destro superiore con due sottopannelli di colore diverso
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        clockPanel.setPreferredSize(new Dimension(sidePanelWidth, getHeight() / 8));
        rightPanel.add(clockPanel, BorderLayout.NORTH);
        graphicsPanel.setPreferredSize(new Dimension(sidePanelWidth, getHeight() / 8 * 7));
        //rightPanel.add(graphicsPanel, BorderLayout.CENTER);

        // Aggiunta dei pannelli laterali al frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        revalidate();
        repaint();

        System.out.println("Side panel width: " + sidePanelWidth);
        System.out.println("Input panel height: " + inputPanel.getPreferredSize().height);
        System.out.println("Info panel height: " + infoPanel.getPreferredSize().height);
        System.out.println("Clock panel height: " + clockPanel.getPreferredSize().height);
        System.out.println("Graphics panel height: " + graphicsPanel.getPreferredSize().height);

    }

}
