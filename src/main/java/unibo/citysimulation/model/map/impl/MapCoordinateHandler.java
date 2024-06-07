package unibo.citysimulation.model.map.impl;

/**

 * Utility class for handling coordinate normalization and denormalization.
 * This class manages the maximum coordinates of the map and provides methods
 * to denormalize coordinates based on these maximum values.
 */
public class MapCoordinateHandler {
    private int maxX;
    private int maxY;

    /**
     * Constructs a handler for coordinate normalization with initial values.
     * Initially sets the maximum coordinates to -1 to indicate they are not set.
     */
    public MapCoordinateHandler() {
        maxX = -1;
        maxY = -1;
    }

    /**
     * Sets the maximum coordinates of the map.
     *
     * @param x The maximum x-coordinate.
     * @param y The maximum y-coordinate.
     */
    public void setMaxCoordinates(final int x, final int y) {
        maxX = x;
        maxY = y;
    }

    /**
     * Denormalizes a coordinate based on the maximum value.
     * This converts a normalized coordinate (in the range [0, 1000]) to its actual value
     * based on the maximum coordinate value.
     *
     * @param c   The normalized coordinate to denormalize.
     * @param max The maximum value of the coordinate.
     * @return The denormalized coordinate.
     * @throws IllegalArgumentException if the maximum value is negative.
     */
    public int denormalizeCoordinate(final int c, final int max) {
        if (max < 0) {
            throw new IllegalArgumentException("Max value must be non-negative");
        }
        return (int) (c / 1000.0 * max);
    }

    /**
     * Gets the maximum x-coordinate of the map.
     *
     * @return The maximum x-coordinate.
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * Gets the maximum y-coordinate of the map.
     *
     * @return The maximum y-coordinate.
     */
    public int getMaxY() {
        return maxY;
    }
}
