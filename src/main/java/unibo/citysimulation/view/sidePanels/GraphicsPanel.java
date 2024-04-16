package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import javax.swing.*;

public class GraphicsPanel extends StyledPanel {
    public GraphicsPanel() {
        setBackground(Color.BLUE);

        // Crea una JLabel con il testo desiderato
        JLabel label = new JLabel("GRAPHICSPANEL", SwingConstants.CENTER); // Allinea il testo al centro
        label.setForeground(Color.WHITE); // Imposta il colore del testo

        // Aggiungi la JLabel al pannello al centro
        add(label, BorderLayout.CENTER);
    }
}
