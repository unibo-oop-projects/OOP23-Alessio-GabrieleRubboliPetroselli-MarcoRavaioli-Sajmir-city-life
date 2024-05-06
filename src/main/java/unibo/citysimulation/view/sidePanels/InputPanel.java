package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends StyledPanel {
    private JLabel numberOfPersonLabel;
    private JTextField numberOfPersonTextField;
    private JButton confirmButton;
    private ClockModel clockModel;

    public InputPanel(Color bgColor, ClockModel clockModel) {
        super(bgColor);
        this.clockModel = clockModel;

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
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockModel.startSimulation();
            }
        });

        // Add the start button to the button panel
        buttonPanel.add(startButton);

        // Create the pause button
        JButton pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(100, 50)); // Set the preferred size
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockModel.pauseSimulation();
            }
        });
        buttonPanel.add(pauseButton);


        // Add the button panel to the main panel
        gbc.gridy = 1;
        add(buttonPanel, gbc);

        // Create a new panel for the second row
        JPanel secondRowPanel = new JPanel(new FlowLayout());

        // Create the label for the number of persons
        numberOfPersonLabel = new JLabel("Number of People:");
        numberOfPersonLabel.setForeground(Color.BLACK);
        secondRowPanel.add(numberOfPersonLabel);

        // Create the JTextField for input
        numberOfPersonTextField = new JTextField(10);
        secondRowPanel.add(numberOfPersonTextField);

        // Create the confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = numberOfPersonTextField.getText();
                try {
                    int numberOfPersons = Integer.parseInt(input);
                    // TODO: Handle the number of persons (e.g., pass it to a listener)
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(InputPanel.this, "Invalid input! Please enter a valid number.");
                }
            }
        });
        secondRowPanel.add(confirmButton);

        // Add the second row panel to the panel
        gbc.gridy = 2;
        add(secondRowPanel, gbc);
    }
}
