package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends StyledPanel {
    public InputPanel(Color bgColor) {
        super(bgColor);
        
        

        // Crea una JLabel con il testo desiderato
        JLabel label = new JLabel("INPUTPANEL", SwingConstants.CENTER); // Allinea il testo al centro
        label.setForeground(Color.WHITE); // Imposta il colore del testo

        // Aggiungi la JLabel al pannello al centro
        add(label, BorderLayout.CENTER);
    }
}
