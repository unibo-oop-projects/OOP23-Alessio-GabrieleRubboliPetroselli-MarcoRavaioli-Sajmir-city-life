package unibo.citysimulation.view.sidePanels;


import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
public class InfoPanel extends StyledPanel{
    private JLabel coordinates = new JLabel("Coordinates: (x, y)", SwingConstants.CENTER);

    public InfoPanel(Color bgColor) { 
        super(bgColor);

        // Crea una JLabel con il testo desiderato
        JLabel label = new JLabel("INFOPANEL", SwingConstants.CENTER); // Allinea il testo al centro
        label.setForeground(Color.WHITE); // Imposta il colore del testo
        
        // Aggiungi la JLabel al pannello al centro
        add(label, BorderLayout.CENTER);

        add(coordinates, BorderLayout.SOUTH);
    }

    public void setCoordinates(int x, int y){
        coordinates.setText("Coordinates: (" + x + ", " + y + ")");
    }
}
