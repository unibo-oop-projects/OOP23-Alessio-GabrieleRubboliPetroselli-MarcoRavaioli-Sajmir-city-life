package unibo.citysimulation.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * Represents a styled panel with a background color.
 */
public class StyledPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static Color bgColor;

    /**
     * Constructs a StyledPanel object with the specified background color.
     * 
     * @param bgColor The background color.
     */
    public StyledPanel(final Color bgColor) {
        setLayout(new BorderLayout());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        setBorder(compoundBorder);
        setBackground(bgColor);
    }

    /**
     * Returns the background color of the panel.
     * 
     * @return The background color.
     */
    public Color getBgColor() {
        return bgColor;
    }
}
