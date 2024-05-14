package unibo.citysimulation.view.map;

import unibo.citysimulation.model.MapModel;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingUtilities;
/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;
    private int originalWidth;
    private int originalHeight;

    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates;
    private List<Color> congestionsColorList;
    private List<String> transportLines;
    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel() {
        super(bgColor);

        // Add a ComponentListener to listen for resize events
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("MapPanel size: " + getWidth() + "x" + getHeight());

                Window window = SwingUtilities.getWindowAncestor(MapPanel.this);
                if (window != null) {
                    System.out.println("Window size: " + window.getWidth() + "x" + window.getHeight());
                }
            }
        });
    }

    public void setLinesPoints(List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points) {
        this.linesPointsCoordinates = points;
        repaint();
    }
    public void SetTransportNames(List<String> lines){
        transportLines = lines;      
    }

    public void setCongestionsList(List<Color> ColorList) {
        this.congestionsColorList = ColorList;
        repaint();
    }

    private void drawTransportLines(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
    
        for(int i = 0; i < linesPointsCoordinates.size(); i++) {
            int x1 = linesPointsCoordinates.get(i).getFirst().getFirst();
            int y1 = linesPointsCoordinates.get(i).getFirst().getSecond();
            int x2 = linesPointsCoordinates.get(i).getSecond().getFirst();
            int y2 = linesPointsCoordinates.get(i).getSecond().getSecond();
            g2.setColor(congestionsColorList.get(i));
            g2.drawLine(x1, y1, x2, y2);
    
            String linename = transportLines.get(i);
    
            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2;
    
            // Set color to black before drawing the string
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            Font currentFont = g2.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F); // 2 times the current size
            g2.setFont(newFont);

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(linename);
            int textX = midX - textWidth / 2;
    
            g2.drawString(linename, textX, midY);
    
            // Reset the font size for the next line
            g2.setFont(currentFont);
        }
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
