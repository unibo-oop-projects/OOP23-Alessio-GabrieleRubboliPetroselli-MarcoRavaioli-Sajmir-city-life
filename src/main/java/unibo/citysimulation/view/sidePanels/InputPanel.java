package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for user input controls.
 */
public class InputPanel extends StyledPanel {
    private JButton startButton;
    private JSlider peopleSlider;
    private JLabel sliderLabel;
    private JLabel sliderValueLabel;

    /**
     * Constructs an InputPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public InputPanel(Color bgColor) {
        super(bgColor);

        // Set the layout manager to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a JLabel with the desired text
        JLabel label = new JLabel("INPUTPANEL", SwingConstants.CENTER); // Align the text to the center
        label.setForeground(Color.WHITE); // Set the text color

        // Add the JLabel to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(label, gbc);

        // Create a new panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create the start button
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size

        // Add the start button to the button panel
        buttonPanel.add(startButton);

        // Add the button panel to the main panel
        gbc.gridy = 1;
        add(buttonPanel, gbc);

        // Create a new panel for the second row
        JPanel secondRowPanel = new JPanel(new FlowLayout());

        // Create the JSlider for input
        peopleSlider = new JSlider(0, 100); // Adjust range as needed
        peopleSlider.setMajorTickSpacing(20);
        peopleSlider.setMinorTickSpacing(5);
        peopleSlider.setPaintTicks(true);
        peopleSlider.setPaintLabels(true);

        // Create a label for the slider
        sliderLabel = new JLabel("Number of People: ");
        sliderLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        add(sliderLabel, gbc);

        // Add the slider
        gbc.gridy = 3;
        add(peopleSlider, gbc);

        // Create a label to display the current slider value
        sliderValueLabel = new JLabel("Current value: " + peopleSlider.getValue());
        sliderValueLabel.setForeground(Color.WHITE);
        gbc.gridy = 4;
        add(sliderValueLabel, gbc);
    }

    /**
     * Retrieves the start button.
     *
     * @return The start button.
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * Retrieves the slider for controlling the number of people.
     *
     * @return The people slider.
     */
    public JSlider getPeopleSlider() {
        return peopleSlider;
    }
}
