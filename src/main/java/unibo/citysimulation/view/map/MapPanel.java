package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;

    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel() {
        super(bgColor);
    }

    /**
     * Paints the map image on the panel.
     *
     * @param g The Graphics context.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the image on the JPanel
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Sets the image to be displayed on the map panel.
     *
     * @param image The BufferedImage to set.
     */
    public void setImage(BufferedImage image){
        mapImage = image;
        repaint();
    }

    /**
     * Retrieves the preferred size of the map panel.
     *
     * @return The preferred size as a Dimension.
     */
    @Override
    public Dimension getPreferredSize() {
        if (mapImage != null) {
            return new Dimension(mapImage.getWidth(), mapImage.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
