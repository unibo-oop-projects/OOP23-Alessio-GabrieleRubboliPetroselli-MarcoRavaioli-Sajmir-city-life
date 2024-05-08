package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        // Create a JLabel with the desired text
        JLabel label = new JLabel("GRAPHICSPANEL", SwingConstants.CENTER); // Align the text to the center
        label.setForeground(Color.WHITE); // Set the text color

        // Add the JLabel to the panel at the center
        add(label, BorderLayout.CENTER);
    }
}
