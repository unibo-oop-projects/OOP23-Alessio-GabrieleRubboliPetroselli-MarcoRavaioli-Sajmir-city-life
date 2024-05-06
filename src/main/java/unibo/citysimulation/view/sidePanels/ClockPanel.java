package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.controller.ClockSpeedController;
import unibo.citysimulation.view.StyledPanel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class ClockPanel extends StyledPanel {
    private JLabel timeDay = new JLabel("Giorno: 1 ora: 00:00", SwingConstants.CENTER);
    private JSlider speedSlider;

    public ClockPanel(Color bgColor, ClockSpeedController clockSpeedController) {
        super(bgColor);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 50, 1000, 500);
        speedSlider.setMajorTickSpacing(4500);
        speedSlider.setPaintTicks(true); // Visualizza le etichette
        speedSlider.setPaintLabels(true); // Visualizza le etichette dei valori
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int speed = speedSlider.getValue();
                clockSpeedController.setClockSpeed(speed);
            }
        });

        // Set up layout
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.add(speedSlider, BorderLayout.CENTER);

        add(timeDay, BorderLayout.SOUTH);

        add(sliderPanel);
    }

    public void setClockText(String text){
        timeDay.setText(text);
    }
    
}