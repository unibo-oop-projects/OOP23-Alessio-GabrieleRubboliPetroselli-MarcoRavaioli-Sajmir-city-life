package unibo.citysimulation.view.map;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 * Panel for displaying the map.
 */
public class MapPanelImpl extends StyledPanel implements MapPanel {
    private static final long serialVersionUID = 1L;
    private static final Integer BASIC_STROKE_SIZE = 6;
    private static final Pair<Integer, Integer> PEOPLE_SIZE = new Pair<>(5, 5);
    private transient BufferedImage mapImage;
    private transient List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections
            .emptyList();
    private transient List<Color> congestionsColorList = Collections.emptyList();
    private transient Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap = Collections.emptyMap();
    private transient List<Pair<Integer, Integer>> businessPoints = Collections.emptyList();
    private transient List<String> linesName = Collections.emptyList();

    /**
     * Constructs a MapPanel with the specified background color.
     *
     * @param bgColor the background color of the panel
     */
    public MapPanelImpl(final Color bgColor) {
        super(bgColor);
    }

    public void setSize(final int width, final int height) {
        this.setSize(new Dimension(width, height));
    }

    /**
     * Paints the map image on the panel.
     *
     * @param g The Graphics context.
     */
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

    private void drawBusinesses(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        businessPoints.forEach(point -> {
            final Color color = new Color(139, 69, 19);
            g2.setColor(color);
            g2.fillRect(point.getFirst(), point.getSecond(), 10, 10);
        });
    }

    public void setLinesInfo(final List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points,
            final List<String> names) {
        this.linesPointsCoordinates = new ArrayList<>(points);
        this.linesName = new ArrayList<>(names);
    }

    public void setLinesColor(final List<Color> colors) {
        this.congestionsColorList = new ArrayList<>(colors);
        this.congestionsColorList = new ArrayList<>(colors);
    }

    public void setEntities(final Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap,
            final List<Pair<Integer, Integer>> businessPoints) {
        this.peopleMap = new HashMap<>(peopleMap);
        this.businessPoints = new ArrayList<>(businessPoints);
        repaint();
    }

    public void setImage(final BufferedImage image) {
        mapImage = createImageDefensiveCopy(image);
        repaint();
    }

    private static BufferedImage createImageDefensiveCopy(final BufferedImage original) {
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

    @Override
    public void addMouseListener(MouseListener listener) {
        super.addMouseListener(listener);
    }

    @Override
    public int getWidth() {
        return this.getSize().width;
    }

    @Override
    public int getHeight() {
        return this.getSize().height;
    }
}
