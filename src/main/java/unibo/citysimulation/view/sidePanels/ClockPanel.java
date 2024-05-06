package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.controller.ClockUserController;
import unibo.citysimulation.view.StyledPanel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class ClockPanel extends StyledPanel {
    private JLabel timeDay = new JLabel("Giorno: 1 ora: 00:00", SwingConstants.CENTER);
    private JButton speedButton;
    private int[] speeds = {1, 2, 10};
    private int currentSpeedIndex = 0;

    public ClockPanel(Color bgColor, ClockUserController clockUserController) {
        super(bgColor);

        speedButton = new JButton("1x");
        speedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Incrementa l'indice della velocità e riportalo a 0 se arrivi all'ultimo valore
                currentSpeedIndex = (currentSpeedIndex + 1) % speeds.length;
                int newSpeed = speeds[currentSpeedIndex];
                speedButton.setText(newSpeed + "x");
                clockUserController.setClockSpeed(newSpeed);
            }
        });

        JButton pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockUserController.pauseSimulation();
            }
        });

        // Set up layout
        JPanel speedPanel = new JPanel(new BorderLayout());
        speedPanel.add(speedButton,BorderLayout.WEST);
        speedPanel.add(pauseButton,BorderLayout.EAST);

        add(timeDay, BorderLayout.SOUTH);

        add(speedPanel);
    }

    public void setClockText(String text){
        timeDay.setText(text);
    }
    
}