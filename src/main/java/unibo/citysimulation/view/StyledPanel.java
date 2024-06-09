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
    private final Color bgColor;

    /**
     * Constructs a StyledPanel object with the specified background color.
     * 
     * @param bgColor The background color.
     */
    public StyledPanel(final Color bgColor) {
        this.bgColor = bgColor;
        setLayout(new BorderLayout());
        final Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        final Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        final Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
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
