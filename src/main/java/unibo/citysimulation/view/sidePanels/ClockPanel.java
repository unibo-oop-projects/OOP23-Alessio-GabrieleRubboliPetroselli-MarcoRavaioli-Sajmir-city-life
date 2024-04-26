package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;

public class ClockPanel extends StyledPanel {
    public ClockPanel(Color bgColor) {
        super(bgColor);

        // Crea una JLabel con il testo desiderato
        JLabel label = new JLabel("CLOCKPANEL", SwingConstants.CENTER); // Allinea il testo al centro
        label.setForeground(Color.WHITE); // Imposta il colore del testo

        // Aggiungi la JLabel al pannello al centro
        add(label, BorderLayout.CENTER);
    }
}
