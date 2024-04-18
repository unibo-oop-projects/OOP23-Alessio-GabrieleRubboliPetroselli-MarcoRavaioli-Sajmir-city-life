package unibo.citysimulation.model.transport.view;

import javax.swing.*;
import java.awt.*;

public class TransportLineGraphsView extends JFrame {

    public TransportLineGraphsView() {
        setTitle("Transport Line Graphs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setFocusable(true);
        setPreferredSize(new Dimension(1000, 600)); // Imposta la dimensione preferita della finestra
        configureLayout();
        createComponents();
        pack(); // Ridimensiona la finestra in base ai componenti
        setVisible(true);
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
    }

    private void createComponents() {
        // Pannello principale diviso in tre sezioni
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Pannello centrale per i grafici principali
        JPanel centerPanel = new JPanel(); // Pannello vuoto per lo spazio bianco
        centerPanel.setBackground(Color.WHITE);

        // Pannello sinistro per i pannelli "Info" e "Clock"
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(createInfoPanel());
        leftPanel.add(createClockPanel());

        // Pannello destro per i grafici secondari "Capacità" e "Congestione"
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.add(createGraphPanel("Capacità", Color.BLUE));
        rightPanel.add(createGraphPanel("Congestione", Color.GREEN));

        // Aggiungi il pannello centrale al pannello principale
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Aggiungi i pannelli laterali al pannello principale
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Aggiungi il pannello principale alla finestra
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Info");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.YELLOW); // Cambia il colore come desiderato
        panel.setPreferredSize(new Dimension(150, 600)); // Aumenta la larghezza del pannello sinistro
        return panel;
    }

    private JPanel createClockPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Clock");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(Color.ORANGE); // Cambia il colore come desiderato
        panel.setPreferredSize(new Dimension(150, 600)); // Aumenta la larghezza del pannello sinistro
        return panel;
    }

    private JPanel createGraphPanel(String title, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(200, 600)); // Aumenta la larghezza del pannello destro
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TransportLineGraphsView::new);
    }
}
