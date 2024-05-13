package unibo.citysimulation.view.map;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.zone.Boundary;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;
    private int originalWidth;
    private int originalHeight;

    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates;
    private List<Double> congestionsList;

    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel() {
        super(bgColor);
    }

    public void setLinesPoints(List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points) {
        this.linesPointsCoordinates = points;
        repaint();
    }

    private void drawTransportLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(4));

        linesPointsCoordinates.forEach(line -> {
            int x1 = line.getFirst().getFirst();
            int y1 = line.getFirst().getSecond();
            int x2 = line.getSecond().getFirst();
            int y2 = line.getSecond().getSecond();

            g2.drawLine(x1, y1, x2, y2);
        });

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

        if (linesPointsCoordinates != null) {
            drawTransportLines(g);
        }
    }

    /**
     * Sets the image to be displayed on the map panel.
     *
     * @param image The BufferedImage to set.
     */
    public void setImage(BufferedImage image) {
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
    /*
     * private void drawTransportLines(Graphics g) {
     * Graphics2D g2 = (Graphics2D) g;
     * g2.setStroke(new BasicStroke(4));
     * 
     * for (TransportLine line : transportLines) { // invece che le linee di
     * trasporto io qui posso avere direttamente la congestione
     * Zone zone1 = line.getLink().getFirst();
     * Zone zone2 = line.getLink().getSecond();
     * 
     * Pair<Integer, Integer> startPoint = getCenterOfZone(zone1);
     * Pair<Integer, Integer> endPoint = getCenterOfZone(zone2);
     * 
     * // Determine the color based on the capacity
     * double capacity = line.getCongestion();
     * System.out.println("congestion: "+capacity);
     * if (capacity < 10) {
     * g2.setColor(Color.GREEN);
     * } else if (capacity < 30) {
     * g2.setColor(Color.YELLOW);
     * } else {
     * g2.setColor(Color.ORANGE);
     * }
     * 
     * g2.drawLine(startPoint.getFirst(), startPoint.getSecond(),
     * endPoint.getFirst(), endPoint.getSecond());
     * }
     * }
     */
}
