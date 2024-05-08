package unibo.citysimulation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    public WelcomeScreen() {
    // Set the title of the window
    setTitle("Welcome to city-simulation");

    // Set the layout manager to BorderLayout
    setLayout(new BorderLayout());

    // Create a JLabel with the welcome message
    JLabel welcomeLabel = new JLabel("Welcome to city-simulation", SwingConstants.CENTER);
    add(welcomeLabel, BorderLayout.CENTER);

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel(new FlowLayout());

    // Create the START button
    JButton startButton = new JButton("START");
    startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Close the welcome screen
            dispose();

            // Start the simulation
            SimulationLauncher simulationLauncher = new SimulationLauncher();
            try {
                java.lang.reflect.Method method = SimulationLauncher.class.getMethod("start");
                if (method != null) {
                    method.invoke(simulationLauncher);
                }
            } catch (NoSuchMethodException noSuchMethodException) {
                System.out.println("The start() method does not exist in SimulationLauncher.");
            } catch (Exception exception) {
                System.out.println("An error occurred while trying to start the simulation.");
                exception.printStackTrace();
            }
        }
    });
    buttonPanel.add(startButton);

    // Create the EXIT button
    JButton exitButton = new JButton("EXIT");
    exitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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

    public static void main(String[] args) {
    // Create and show the welcome screen
    WelcomeScreen welcomeScreen = new WelcomeScreen();
    welcomeScreen.setVisible(true);
    }
}
