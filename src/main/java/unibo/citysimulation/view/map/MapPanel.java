package unibo.citysimulation.view.map;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import unibo.citysimulation.utilities.Pair;

public interface MapPanel {

    int getWidth();

    int getHeight();

    void addMouseListener(MouseListener listener);

    void setSize(int width, int height);
    /**
     * Sets the lines information for the map.
     *
     * @param points the coordinates of the transport lines
     * @param names  the names of the transport lines
     */
    public void setLinesInfo(final List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points,
            final List<String> names);

    /**
     * Sets the colors of the transport lines based on congestion.
     *
     * @param colors the colors of the transport lines
     */
    public void setLinesColor(final List<Color> colors);

    /**
     * Sets the entities to be displayed on the map.
     *
     * @param peopleMap      the map of people with their coordinates and colors
     * @param businessPoints the map of businesses with their coordinates
     */
    void setEntities(final Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap,
            final List<Pair<Integer, Integer>> businessPoints);

    /**
     * Sets the image to be displayed on the map panel.
     *
     * @param image The BufferedImage to set.
     */
    void setImage(final BufferedImage image);
}
