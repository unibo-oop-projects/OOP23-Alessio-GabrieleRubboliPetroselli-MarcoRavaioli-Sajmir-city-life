package unibo.citysimulation.view.map;

import unibo.citysimulation.view.StyledPanel;
import unibo.citysimulation.model.MapModel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private MapModel mapModel;
    private BufferedImage mapImage;


    public MapPanel() {
        super(bgColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the image on the JPanel
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void setImage(BufferedImage image){
        mapImage = image;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        BufferedImage image = mapModel.getImage();
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }
}
