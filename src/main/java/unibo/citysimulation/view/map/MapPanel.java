package unibo.citysimulation.view.map;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.BasicStroke;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;
    private static final Integer BASIC_STROKE_SIZE = 6;
    private static final Pair<Integer, Integer> PEOPLE_SIZE = new Pair<>(5, 5);
    private transient BufferedImage mapImage;
    private transient List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = new ArrayList<>();
    private transient List<Color> congestionsColorList = new ArrayList<>();
    private transient Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap = new HashMap<>();
    private transient List<Pair<Integer, Integer>> businessPoints = new ArrayList<>();
    private transient List<String> linesName = new ArrayList<>();

    /**
     * Constructs a MapPanel with the specified background color.
     *
     * @param bgColor the background color of the panel
     */
    public MapPanel(final Color bgColor) {
        super(bgColor);
    }

    /**
     * Paints the map image on the panel.
     *
     * @param g The Graphics context.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        // Draws the image on the JPanel
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (peopleMap != null) {
            drawPeople(g);
        }

        if (businessPoints != null) {
            drawBusinesses(g);
        }

        if (linesPointsCoordinates != null) {
            drawTransportLines(g);
        }
    }
    /**
     * Custom serialization logic for the MapPanel class.
     *
     * @param ois the ObjectInputStream
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        congestionsColorList = new ArrayList<>();
        linesPointsCoordinates = new ArrayList<>();
        peopleMap = new HashMap<>();
        businessPoints = new ArrayList<>();
        linesName = new ArrayList<>();
    }
    /**
     * Draws the transport lines on the map.
     *
     * @param g the Graphics context
     */
    private void drawTransportLines(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < linesPointsCoordinates.size(); i++) {
            final int x1 = linesPointsCoordinates.get(i).getFirst().getFirst();
            final int y1 = linesPointsCoordinates.get(i).getFirst().getSecond();
            final int x2 = linesPointsCoordinates.get(i).getSecond().getFirst();
            final int y2 = linesPointsCoordinates.get(i).getSecond().getSecond();
            g2.setColor(congestionsColorList.get(i));
            g2.setStroke(new BasicStroke(BASIC_STROKE_SIZE));
            g2.drawLine(x1, y1, x2, y2);

            final String linename = linesName.get(i);

            final int midX = (x1 + x2) / 2;
            final int midY = (y1 + y2) / 2;

            // Set color to black before drawing the string
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            final Font currentFont = g2.getFont();
            final Font newFont = currentFont.deriveFont(currentFont.getSize() * 1.2F);
            g2.setFont(newFont);

            final FontMetrics fm = g2.getFontMetrics();
            final int textWidth = fm.stringWidth(linename);
            final int textX = midX - textWidth / 2;

            g2.drawString(linename, textX, midY);

            // Reset the font size for the next line
            g2.setFont(currentFont);
        }
    }

    /**
     * Draws the people on the map.
     *
     * @param g the Graphics context
     */
    private void drawPeople(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        peopleMap.forEach((name, info) -> {
            final Pair<Integer, Integer> point = info.getFirst();
            final Color color = info.getSecond();
            g2.setColor(color);
            g2.fillOval(point.getFirst(), point.getSecond(), PEOPLE_SIZE.getFirst(), PEOPLE_SIZE.getSecond());
        });
    }

    /**
     * Draws the businesses on the map.
     *
     * @param g the Graphics context
     */
    private void drawBusinesses(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        businessPoints.forEach(point -> {
            final Color color = new Color(139, 69, 19);
            g2.setColor(color);
            g2.fillRect(point.getFirst(), point.getSecond(), 10, 10);
        });
    }

    /**
     * Sets the lines information for the map.
     *
     * @param points the coordinates of the transport lines
     * @param names  the names of the transport lines
     */
    public void setLinesInfo(final List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points,
            final List<String> names) {
        this.linesPointsCoordinates = new ArrayList<>(points);
        this.linesName = new ArrayList<>(names);
    }

    /**
     * Sets the colors of the transport lines based on congestion.
     *
     * @param colors the colors of the transport lines
     */
    public void setLinesColor(final List<Color> colors) {
        this.congestionsColorList = new ArrayList<>(colors);
        this.congestionsColorList = new ArrayList<>(colors);
    }

    /**
     * Sets the entities to be displayed on the map.
     *
     * @param peopleMap      the map of people with their coordinates and colors
     * @param businessPoints the map of businesses with their coordinates
     */
    public void setEntities(final Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap,
            final List<Pair<Integer, Integer>> businessPoints) {
        this.peopleMap = new HashMap<>(peopleMap);
        this.businessPoints = new ArrayList<>(businessPoints);
        repaint();
    }

    /**
     * Sets the image to be displayed on the map panel.
     *
     * @param image The BufferedImage to set.
     */
    public void setImage(final BufferedImage image) {
        mapImage = createImageDefensiveCopy(image);
        repaint();
    }

    /**
     * Creates a defensive copy of the specified BufferedImage.
     *
     * @param original The original BufferedImage.
     * @return A new BufferedImage with the same dimensions and type as the original.
     */
    public static BufferedImage createImageDefensiveCopy(final BufferedImage original) {
        if (original == null) {
            throw new IllegalArgumentException("The original image cannot be null");
        }
        // Create a new BufferedImage with the same dimensions and type as the original
        final BufferedImage copy = new BufferedImage(
            original.getWidth(),
            original.getHeight(),
            original.getType()
        );
        // Draw the original image onto the copy
        final Graphics2D g = copy.createGraphics();
        g.drawImage(original, 0, 0, null);
        g.dispose();
        return copy;
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
