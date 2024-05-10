package unibo.citysimulation.view.map;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;
    private List<Zone> zones; // List of zones

    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel() {
        super(bgColor);
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
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
        drawTransportLines(g);
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


     /**
     * Draws the transport lines between zones.
     *
     * @param g The Graphics context.
     */
    private void drawTransportLines(Graphics g) {
        for (int i = 0; i < zones.size() - 1; i++) {
            for (int j = i + 1; j < zones.size(); j++) {
                Zone zone1 = zones.get(i);
                Zone zone2 = zones.get(j);

                Pair<Integer, Integer> startPoint = zone1.getRandomPosition();
                Pair<Integer, Integer> endPoint = zone2.getRandomPosition();

                g.drawLine(startPoint.getFirst(), startPoint.getSecond(), endPoint.getFirst(), endPoint.getSecond());
            }
        }
    }
}
