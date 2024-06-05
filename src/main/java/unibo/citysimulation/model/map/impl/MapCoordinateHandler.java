package unibo.citysimulation.model.map.impl;

/**
 * Utility class for handling coordinate normalization and denormalization.
 */
public class MapCoordinateHandler {
    private int maxX;
    private int maxY;

    /**
     * Construct ana handler for coordinate normalization.
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
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
        maxX = x;
        maxY = y;
    }

    /**
     * Denormalizes a coordinate based on the maximum value.
     *
     * @param c   The coordinate to denormalize.
     * @param max The maximum value of the coordinate.
     * @return The denormalized coordinate.
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
