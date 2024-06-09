package unibo.citysimulation.view.map;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import unibo.citysimulation.utilities.Pair;

/**
 * Panel for displaying the map.
 */
public interface MapPanel {

        /**
         * Gets the width of the map panel.
         *
         * @return the width of the map panel
         */
        int getWidth();

        /**
         * Gets the height of the map panel.
         *
         * @return the height of the map panel
         */
        int getHeight();

        /**
         * Adds a mouse listener to the map panel.
         *
         * @param listener the mouse listener to add
         */
        void addMouseListener(MouseListener listener);

        /**
         * Sets the lines information for the map.
         *
         * @param points the coordinates of the transport lines
         * @param names  the names of the transport lines
         */
        void setLinesInfo(List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> points,
                        List<String> names);

        /**
         * Sets the colors of the transport lines based on congestion.
         *
         * @param colors the colors of the transport lines
         */
        void setLinesColor(List<Color> colors);

        /**
         * Sets the entities to be displayed on the map.
         *
         * @param peopleMap      the map of people with their coordinates and colors
         * @param businessPoints the map of businesses with their coordinates
         */
        void setEntities(Map<String, Pair<Pair<Integer, Integer>, Color>> peopleMap,
                        List<Pair<Integer, Integer>> businessPoints);

        /**
         * Sets the image to be displayed on the map panel.
         *
         * @param image The BufferedImage to set.
         */
        void setImage(BufferedImage image);
}
