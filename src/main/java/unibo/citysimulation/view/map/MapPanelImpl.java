package unibo.citysimulation.view.map;

import unibo.citysimulation.model.map.impl.ImageHandler;
import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.view.StyledPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.Collections;

/**
 * The class that implements MapPanel interface, here there are all the methods
 * for the MapPanel management.
 */
public final class MapPanelImpl extends StyledPanel implements MapPanel {
    private static final long serialVersionUID = 1L;
    private static final Integer BASIC_STROKE_SIZE = 6;
    private static final Pair<Integer, Integer> PEOPLE_SIZE = new Pair<>(5, 5);

    private final ImageHandler imageHandler = new ImageHandler();
    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections.emptyList();
    private List<Color> congestionsColorList = Collections.emptyList();
    private Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap = Collections.emptyMap();
    private List<Pair<Integer, Integer>> businessPoints = Collections.emptyList();
    private List<String> linesName = Collections.emptyList();

    /**
     * Constructs a MapPanel with the specified background color.
     *
     * @param bgColor the background color of the panel
     */
    public MapPanelImpl(final Color bgColor) {
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
        imageHandler.getImage().ifPresent(image -> g.drawImage(image, 0, 0, getWidth(), getHeight(), this));

        if (!peopleMap.isEmpty()) {
            drawPeople(g);
        }

        if (!businessPoints.isEmpty()) {
            drawBusinesses(g);
        }

        if (!linesPointsCoordinates.isEmpty()) {
            drawTransportLines(g);
        }
    }

    private void drawTransportLines(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;

        IntStream.range(0, linesPointsCoordinates.size()).forEach(i -> {
            final Pair<Integer, Integer> start = linesPointsCoordinates.get(i).getFirst();
            final Pair<Integer, Integer> end = linesPointsCoordinates.get(i).getSecond();
            g2.setColor(congestionsColorList.get(i));
            g2.setStroke(new BasicStroke(BASIC_STROKE_SIZE));
            g2.drawLine(start.getFirst(), start.getSecond(), end.getFirst(), end.getSecond());

            final String lineName = linesName.get(i);
            final int midX = (start.getFirst() + end.getFirst()) / 2;
            final int midY = (start.getSecond() + end.getSecond()) / 2;

            drawCenteredString(g2, lineName, midX, midY);
        });
    }

    private void drawCenteredString(final Graphics2D g2, final String text, final int x, final int y) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        final Font originalFont = g2.getFont();
        final Font newFont = originalFont.deriveFont(originalFont.getSize() * 1.2F);
        g2.setFont(newFont);

        final FontMetrics fm = g2.getFontMetrics();
        final int textWidth = fm.stringWidth(text);
        g2.drawString(text, x - textWidth / 2, y);

        g2.setFont(originalFont);
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

    @Override
    public void setLinesInfo(final List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points,
            final List<String> names) {
        this.linesPointsCoordinates = new ArrayList<>(points);
        this.linesName = new ArrayList<>(names);
    }

    @Override
    public void setLinesColor(final List<Color> colors) {
        this.congestionsColorList = new ArrayList<>(colors);
    }

    @Override
    public void setEntities(final Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap,
            final List<Pair<Integer, Integer>> businessPoints) {
        this.peopleMap = new HashMap<>(peopleMap);
        this.businessPoints = new ArrayList<>(businessPoints);
        repaint();
    }

    @Override
    public void setImage(final BufferedImage image) {
        imageHandler.setImage(image);
        repaint();
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
