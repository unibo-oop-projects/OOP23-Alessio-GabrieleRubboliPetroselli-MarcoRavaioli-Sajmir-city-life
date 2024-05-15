package unibo.citysimulation.view.map;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.SwingUtilities;

import org.w3c.dom.css.RGBColor;
/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private BufferedImage mapImage;

    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates;
    private List<Color> congestionsColorList;
    private Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap;
    private Map<String, Pair<Integer, Integer>> businessMap;

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
    }

    public void setCongestionsList(List<Color> ColorList) {
        this.congestionsColorList = ColorList;
    }

    public void setPeopleMap(Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap) {
        this.peopleMap = peopleMap;
    }

    public void setBusinessPoints(Map<String, Pair<Integer, Integer>> businessMap) {
        this.businessMap = businessMap;
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
        }

    }

    private void drawPeople(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        for (var entry : peopleMap.entrySet()) {
            Pair<Integer, Integer> point = entry.getValue().getFirst();
            Color color = entry.getValue().getSecond();

            g2.setColor(color);
            g2.fillOval(point.getFirst(), point.getSecond(), 5, 5);
        }
    }

    private void drawBusinesses(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
    
        for (var entry : businessMap.entrySet()) {
            Pair<Integer, Integer> point = entry.getValue();
            Color color = new Color(139, 69, 19);
            g2.setColor(color);
            // Disegna un quadrato (rettangolo con altezza e larghezza uguali)
            g2.fillRect(point.getFirst(), point.getSecond(), 10, 10);
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

        if (peopleMap != null) {
            drawPeople(g);
        }

        if (businessMap != null) {
            drawBusinesses(g);
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
