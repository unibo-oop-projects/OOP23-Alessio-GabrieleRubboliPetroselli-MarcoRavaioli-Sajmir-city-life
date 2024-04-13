package unibo.citysimulation.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class NewSimulationView extends JFrame {

    public NewSimulationView() {
        setTitle("City Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        configureLayout();
        createComponents();

        setVisible(true);
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
    }

    private void createComponents() {
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel leftPanel = new JPanel(new GridLayout(0, 1));
        JPanel centerPanel = new JPanel(new FlowLayout());
        JPanel rightPanel = new JPanel(new GridLayout(0, 1));

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");

        topPanel.add(startButton);
        topPanel.add(pauseButton);
        topPanel.add(stopButton);

        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        new NewSimulationView();
    }
}
