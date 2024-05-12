package unibo.citysimulation.view.map;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.zone.Boundary;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;
    private List<Zone> zones; // List of zones
    private int originalWidth; 
    private int originalHeight; 
    private List<TransportLine> transportLines;

    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel() {
        super(bgColor);
        this.transportLines=new ArrayList<>();
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
    public void setTransportLines(List<TransportLine> transportLines) {
    this.transportLines = transportLines;
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
        originalWidth = image.getWidth();
        originalHeight = image.getHeight();
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
    
        for (TransportLine line : transportLines) {
            Zone zone1 = line.getLink().getFirst();
            Zone zone2 = line.getLink().getSecond();
    
            Pair<Integer, Integer> startPoint = getCenterOfZone(zone1);
            Pair<Integer, Integer> endPoint = getCenterOfZone(zone2);
    
            // Set the color based on the color of the line
            g2.setColor(line.getColor());
    
            g2.drawLine(startPoint.getFirst(), startPoint.getSecond(), endPoint.getFirst(), endPoint.getSecond());
        }
    }
    

    
    private Pair<Integer, Integer> getCenterOfZone(Zone zone) {
        Boundary bounds = zone.getBoundary();
    
        int centerX = bounds.getX() + bounds.getWidth() / 2;
        int centerY = bounds.getY() + bounds.getHeight() / 2;
    
        return new Pair<>(centerX, centerY);
    }
}
