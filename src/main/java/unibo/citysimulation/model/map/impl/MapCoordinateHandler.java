package unibo.citysimulation.model.map.impl;

/**
 * Utility class for handling coordinate normalization and denormalization.
 */
public class MapCoordinateHandler {
    private int normClickedX;
    private int normClickedY;
    private int maxX;
    private int maxY;

    /**
     * Construct ana handler for coordinate normalization
     */
    public MapCoordinateHandler() {
        normClickedX = -1;
        normClickedY = -1;
        maxX = -1;
        maxY = -1;
    }

    /**
     * Sets the last clicked coordinates after normalization.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     */
    public void setLastClickedCoordinates(final int x, final int y) {
        normClickedX = x;
        normClickedY = y;
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
     * Normalizes a coordinate based on the maximum value.
     *
     * @param c   The coordinate to normalize.
     * @param max The maximum value of the coordinate.
     * @return The normalized coordinate.
     */
    public int normalizeCoordinate(final int c, final int max) {
        return (int) (c / (double) max * 1000);
    }

    /**
     * Denormalizes a coordinate based on the maximum value.
     *
     * @param c   The coordinate to denormalize.
     * @param max The maximum value of the coordinate.
     * @return The denormalized coordinate.
     */
    public int denormalizeCoordinate(final int c, final int max) {
        return (int) (c / 1000.0 * max);
    }

    /**
     * Gets the normalized x-coordinate of the last click.
     *
     * @return The normalized x-coordinate.
     */
    public int getNormX() {
        return normClickedX;
    }

    /**
     * Gets the normalized y-coordinate of the last click.
     *
     * @return The normalized y-coordinate.
     */
    public int getNormY() {
        return normClickedY;
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
