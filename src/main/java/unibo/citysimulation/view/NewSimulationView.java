package unibo.citysimulation.view;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.view.map.MapPanel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;

public class NewSimulationView extends JFrame {

    public NewSimulationView() {
        setTitle(ConstantAndResourceLoader.APPLICATION_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
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
        
        // Aggiungi i pannelli laterali con colori diversi
        JPanel leftPanel = new JPanel(new GridLayout(0, 1));
        leftPanel.setBackground(Color.BLUE); // Imposta il colore blu per il pannello sinistro
        leftPanel.add(new JLabel("Left Panel")); // Aggiungi una etichetta di testo al pannello sinistro
        add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new GridLayout(0, 1));
        rightPanel.setBackground(Color.RED); // Imposta il colore rosso per il pannello destro
        rightPanel.add(new JLabel("Right Panel")); // Aggiungi una etichetta di testo al pannello destro
        add(rightPanel, BorderLayout.EAST);

    }
    
    private void createComponents() {
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new BorderLayout()); // Cambiato il layout a BorderLayout per ospitare il MapPanel
    
        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");
    
        topPanel.add(startButton);
        topPanel.add(pauseButton);
        topPanel.add(stopButton);
    
        // Creazione e aggiunta del MapPanel al centro
        MapPanel mapPanel = new MapPanel();
        centerPanel.add(mapPanel, BorderLayout.CENTER);
    
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    

    public static void main(String[] args) {
        new NewSimulationView();
    }
}
