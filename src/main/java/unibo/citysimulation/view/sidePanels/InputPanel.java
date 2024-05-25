package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InputPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;

    private final JButton startButton;
    private final JButton stopButton;
    private final JSlider peopleSlider;
    private final JSlider businessSlider;
    private final JSlider capacitySlider;
    private final JSlider richnessSlider;

    public InputPanel(final Color bgColor) {
        super(bgColor);
    
        setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2; // Occupies two columns
        gbc.fill = GridBagConstraints.BOTH;
    
        gbc.gridwidth = 1; // Resets to one column for the subsequent elements
    
        // Create and add slider for the number of people
        peopleSlider = createSlider("Number of People %", 0, 100);
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        add(peopleSlider, gbc);
    
        // Create and add slider for the number of businesses
        businessSlider = createSlider("Number of Businesses", 0, 100);
        gbc.gridy = 2;
        add(businessSlider, gbc);

        // Create and add slider for the transport line capacities
        capacitySlider = createSlider("Transports' Capacity", 0, 100);
        gbc.gridy = 3;
        add(capacitySlider, gbc);
    
        // Create and add slider for the general wealth of people
        richnessSlider = createSlider("People's Richness", 0, 100);
        gbc.gridy = 4;
        add(richnessSlider, gbc);
    
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Padding
    
        startButton = createButton("Start", Color.GREEN);
        buttonPanel.add(startButton);

        stopButton = createButton("Stop", Color.RED);
        stopButton.setEnabled(false);
        buttonPanel.add(stopButton);
    
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Occupies two columns
        add(buttonPanel, gbc);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public int getPeopleSliderValue() {
        return peopleSlider.getValue();
    }

    public int getBusinessSliderValue() {
        return businessSlider.getValue();
    }

    public int getCapacitySliderValue() {
        return capacitySlider.getValue();
    }

    public int getRichnessSliderValue() {
        return richnessSlider.getValue();
    }

    public JSlider getPeopleSlider() {
        return peopleSlider;
    }

    public JSlider getBusinessSlider() {
        return businessSlider;
    }

    public JSlider getCapacitySlider() {
        return capacitySlider;
    }

    public JSlider getRichnessSlider() {
        return richnessSlider;
    }

    private JSlider createSlider(final String title, final int min, final int max) {
        final JSlider slider = new JSlider(min, max);
        final TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 14));
        slider.setBorder(border);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.WHITE);
        slider.setBackground(new Color(50, 50, 50)); // Darker background for better contrast
        return slider;
    }

    private JButton createButton(final String text, final Color color) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 50));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }
}
