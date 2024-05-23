package unibo.citysimulation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the welcome screen of the application.
 */
public class WelcomeScreen extends JFrame {
    /**
     * Constructs a WelcomeScreen object.
     */
    public WelcomeScreen() {
    // Set the title of the window
    setTitle("Welcome to city-simulation");

    // Set the layout manager to BorderLayout
    setLayout(new BorderLayout());

    setMinimumSize(new Dimension(400, 200));

    // Create a JLabel with the welcome message
    final JLabel welcomeLabel = new JLabel("Welcome to city-simulation", SwingConstants.CENTER);
    add(welcomeLabel, BorderLayout.CENTER);

    // Create a panel for the buttons
    final JPanel buttonPanel = new JPanel(new FlowLayout());

    // Create the START button
    final JButton startButton = new JButton("START");
    startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
            // Close the welcome screen
            dispose();

            // Start the simulation
            final SimulationLauncher simulationLauncher = new SimulationLauncher();
            try {
                final java.lang.reflect.Method method = SimulationLauncher.class.getMethod("start");
                if (method != null) {
                    method.invoke(simulationLauncher);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    });
    buttonPanel.add(startButton);

    // Create the EXIT button
    final JButton exitButton = new JButton("EXIT");
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
        // Exit the program
        System.exit(0);
        }
    });
    buttonPanel.add(exitButton);

    // Add the button panel to the window
    add(buttonPanel, BorderLayout.SOUTH);

    // Set the default close operation
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set the size of the window
    setSize(400, 200);

    // Center the window on the screen
    setLocationRelativeTo(null);
    }

    public static void main(final String[] args) {

    // Create and show the welcome screen
    final WelcomeScreen welcomeScreen = new WelcomeScreen();
    welcomeScreen.setVisible(true);
    }
}
