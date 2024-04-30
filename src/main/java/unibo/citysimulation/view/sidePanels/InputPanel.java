package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends StyledPanel {
    private JLabel numberOfPersonLabel;
    private JTextField numberOfPersonTextField;
    private JButton confirmButton;

    public InputPanel(Color bgColor) {
        super(bgColor);

        // Set the layout manager to BorderLayout
        setLayout(new BorderLayout());

        // Create a JLabel with the desired text
        JLabel label = new JLabel("INPUTPANEL", SwingConstants.CENTER); // Align the text to the center
        label.setForeground(Color.WHITE); // Set the text color

        // Add the JLabel to the SOUTH region of the panel
        add(label, BorderLayout.SOUTH);

        // Create a new panel for the buttons and the second row with FlowLayout
        JPanel flowPanel = new JPanel(new FlowLayout());

        // Create the start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the start button click
            }
        });

        // Create the stop button
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the stop button click
            }
        });

        // Add the start and stop buttons to the flowPanel
        flowPanel.add(startButton);
        flowPanel.add(stopButton);

        // Create a new panel for the second row
        JPanel secondRowPanel = new JPanel(new FlowLayout());

        // Create the label for the number of persons
        numberOfPersonLabel = new JLabel("Number of Persons:");
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

        // Add the second row panel to the flowPanel
        flowPanel.add(secondRowPanel);

        // Add the flowPanel to the CENTER region of the inputPanel
        add(flowPanel, BorderLayout.CENTER);
    }
}