package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
/**
 * This class represents the input panel.
 */
public class InputPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;

    private final JButton startButton;
    private final JButton stopButton;
    private final JSlider peopleSlider;
    private final JSlider capacitySlider;
    private final JSlider businessSlider;
    private static final int BUTTON_PANEL_GRID_Y = 5;
    private static final int FONT_SIZE = 14;
    private static final int MAJOR_TICK_SPACING = 20;
    private static final int MINOR_TICK_SPACING = 5;
    private static final int BACKGROUND_COLOR_VALUE = 50;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_FONT_SIZE = 14;


    /**
     * Constructs an InputPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
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
        businessSlider = createSlider("Number of Businesses", 0, 100); // Initialize businessSlider here
        gbc.gridy = 2;
        add(businessSlider, gbc);
        // Create and add slider for the transport line capacities
        capacitySlider = createSlider("Transports' Capacity", 0, 100);
        gbc.gridy = 3;
        add(capacitySlider, gbc);
        // Create and add slider for the general wealth of people
        //richnessSlider = createSlider("People's Richness", 0, 100);
        gbc.gridy = 4;
        //add(richnessSlider, gbc);
        final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // Padding
        startButton = createButton("Start", Color.GREEN);
        buttonPanel.add(startButton);
        stopButton = createButton("Stop", Color.RED);
        stopButton.setEnabled(false);
        buttonPanel.add(stopButton);
        gbc.gridy = BUTTON_PANEL_GRID_Y;
        gbc.gridwidth = 2; // Occupies two columns
        add(buttonPanel, gbc);
    }
     /**
     * Returns the start button.
     *
     * @return the start button
     */
    public JButton getStartButton() {
        return startButton;
    }
    /**
     * Returns the stop button.
     *
     * @return the stop button
     */
    public JButton getStopButton() {
        return stopButton;
    }
    /**
     * Returns the value of the people slider.
     *
     * @return the value of the people slider
     */
    public int getPeopleSliderValue() {
        return peopleSlider.getValue();
    }
    /**
     * Returns the value of the capacity slider.
     *
     * @return the value of the capacity slider
     */
    public int getCapacitySliderValue() {
        return capacitySlider.getValue();
    }
    /**
     * Returns the people slider.
     *
     * @return the people slider
     */
    public JSlider getPeopleSlider() {
        return peopleSlider;
    }
    /**
     * Returns the capacity slider.
     *
     * @return the capacity slider
     */
    public JSlider getCapacitySlider() {
        return capacitySlider;
    }
    /**
     * Returns the value of the business slider.
     *
     * @return the value of the business slider
     */
    public JSlider getBusinessSlider() {
        return businessSlider;
    }
    
    private JSlider createSlider(final String title, final int min, final int max) {
        final JSlider slider = new JSlider(min, max);
        final TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleColor(Color.WHITE);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        slider.setBorder(border);
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setForeground(Color.WHITE);
        slider.setBackground(new Color(
            BACKGROUND_COLOR_VALUE, 
            BACKGROUND_COLOR_VALUE, 
            BACKGROUND_COLOR_VALUE
        ));
        return slider;
    }

    private JButton createButton(final String text, final Color color) {
        final JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, BUTTON_FONT_SIZE));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        return button;
    }
}
    
