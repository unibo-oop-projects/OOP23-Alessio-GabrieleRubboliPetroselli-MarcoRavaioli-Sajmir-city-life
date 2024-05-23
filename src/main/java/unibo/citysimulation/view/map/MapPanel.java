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
import java.awt.BasicStroke;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * Panel for displaying the map.
 */
public class MapPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;
    
    private BufferedImage mapImage;
    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections.emptyList();
    private List<Color> congestionsColorList = Collections.emptyList();
    private Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap = Collections.emptyMap();
    private Map<Integer, Pair<Integer, Integer>> businessMap = Collections.emptyMap();
    private List<String> linesName = Collections.emptyList();

    /**
     * Constructs a MapPanel with the specified background color.
     */
    public MapPanel(final Color bgColor){
        super(bgColor);
    }

    private void drawTransportLines(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        
        for(int i = 0; i < linesPointsCoordinates.size(); i++) {
            final int x1 = linesPointsCoordinates.get(i).getFirst().getFirst();
            final int y1 = linesPointsCoordinates.get(i).getFirst().getSecond();
            final int x2 = linesPointsCoordinates.get(i).getSecond().getFirst();
            final int y2 = linesPointsCoordinates.get(i).getSecond().getSecond();
            g2.setColor(congestionsColorList.get(i));
            g2.setStroke(new BasicStroke(6));
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
            g2.fillOval(point.getFirst(), point.getSecond(), 5, 5);
        });
    }

    private void drawBusinesses(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
    
        businessMap.forEach((name, point) -> {
            final Color color = new Color(139, 69, 19);
            g2.setColor(color);
            g2.fillRect(point.getFirst(), point.getSecond(), 10, 10);
        });
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

        if (businessMap != null) {
            drawBusinesses(g);
        }

        if (linesPointsCoordinates != null) {
            drawTransportLines(g);
        }
    }

    public void setLinesInfo(final List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points, final List<String> names){
        this.linesPointsCoordinates = points;
        this.linesName = names;
    }

    public void setLinesColor(final List<Color> colors){
        this.congestionsColorList = colors;
    }

    public void setEntities(final Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap, final Map<Integer, Pair<Integer, Integer>> businessMap){
        this.peopleMap = peopleMap;
        this.businessMap = businessMap;
        repaint();
    }

    public void setBusinessPoints(final Map<Integer, Pair<Integer, Integer>> businessMap){
        this.businessMap = businessMap;
    }
        


    /**
     * Sets the image to be displayed on the map panel.
     *
     * @param image The BufferedImage to set.
     */
    public void setImage(final BufferedImage image) {
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
