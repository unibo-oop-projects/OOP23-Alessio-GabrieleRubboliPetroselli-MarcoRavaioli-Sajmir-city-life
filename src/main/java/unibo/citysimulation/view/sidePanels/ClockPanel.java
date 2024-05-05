package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.controller.ClockController;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockPanel extends StyledPanel {
    private JLabel timeDay = new JLabel("", SwingConstants.CENTER);

    public ClockPanel(Color bgColor) {
        super(bgColor);

        // Crea una JLabel con il testo desiderato
        JLabel label = new JLabel("CLOCkPANEL", SwingConstants.CENTER); // Allinea il testo al centro
        label.setForeground(Color.WHITE); // Imposta il colore del testo

        // Aggiungi la JLabel al pannello al centro
        add(label, BorderLayout.CENTER);

        add(timeDay, BorderLayout.SOUTH);
    }

    public void setClockText(String text){
        timeDay.setText(text);
    }
}