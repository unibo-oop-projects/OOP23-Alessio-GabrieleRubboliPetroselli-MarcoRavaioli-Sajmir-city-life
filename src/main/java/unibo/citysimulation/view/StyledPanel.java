package unibo.citysimulation.view;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class StyledPanel extends JPanel {
    protected static Color bgColor;

    public StyledPanel(Color bgColor) {
        setLayout(new BorderLayout());

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);

        setBorder(compoundBorder);
        setBackground(bgColor);
    
    }
}
