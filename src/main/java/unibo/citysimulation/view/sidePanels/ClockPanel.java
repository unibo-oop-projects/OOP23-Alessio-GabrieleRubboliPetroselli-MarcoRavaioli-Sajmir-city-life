package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockPanel extends StyledPanel {
    private JLabel clockLabel;
    private Timer timer;
    private long startTime;

    public ClockPanel(Color bgColor) {
        super(bgColor);

        clockLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        clockLabel.setForeground(Color.WHITE);
        add(clockLabel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                clockLabel.setText(dateFormat.format(new Date(elapsedTime)));
            }
        });
    }

    public void startClock() {
        startTime = System.currentTimeMillis();
        timer.start();
    }

    public void stopClock() {
        timer.stop();
    }
}