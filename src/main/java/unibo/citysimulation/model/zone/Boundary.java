package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

/**
 * The Boundary class represents a rectangular boundary in a city simulation.
 * It defines the coordinates of the top-left corner (x1, y1) and the bottom-right corner (x2, y2).
 */
public class Boundary {
    
    final Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coordinates;

    /**
     * Constructs a Boundary object with the specified coordinates.
     *
     * @param x1 the x-coordinate of the top-left corner
     * @param y1 the y-coordinate of the top-left corner
     * @param x2 the x-coordinate of the bottom-right corner
     * @param y2 the y-coordinate of the bottom-right corner
     */
    public Boundary(final int x1, final int y1, final int x2, final int y2) {
        coordinates = new Pair<>(new Pair<>(x1, y1), new Pair<>(x2, y2));
    }

    /**
     * Checks if the given coordinates (x, y) are inside the boundary.
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if the coordinates are inside the boundary, false otherwise
     */
    public boolean isInside(final int x,final int y) {
        return x >= coordinates.getFirst().getFirst() && x <= coordinates.getSecond().getFirst() &&
                y >= coordinates.getFirst().getSecond() && y <= coordinates.getSecond().getSecond();
    }

    /**
     * Returns the height of the boundary.
     *
     * @return the height of the boundary
     */
    public int getHeight() {
        return coordinates.getSecond().getSecond() - coordinates.getFirst().getSecond();
    }

    /**
     * Returns the width of the boundary.
     *
     * @return the width of the boundary
     */
    public int getWidth() {
        return coordinates.getSecond().getFirst() - coordinates.getFirst().getFirst();
    }

    /**
     * Returns the x-coordinate of the top-left corner of the boundary.
     *
     * @return the x-coordinate of the top-left corner
     */
    public int getX() {
        return coordinates.getFirst().getFirst();
    }

    /**
     * Returns the y-coordinate of the top-left corner of the boundary.
     *
     * @return the y-coordinate of the top-left corner
     */
    public int getY() {
        return coordinates.getFirst().getSecond();
    }

    /**
     * Returns the center coordinates of the boundary.
     *
     * @return the center coordinates as a Pair object
     */
    public Pair<Integer, Integer> getCenter(){
        return new Pair<Integer, Integer>(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    /**
     * Checks if the given position is inside the boundary.
     *
     * @param position the position to check
     * @return true if the position is inside the boundary, false otherwise
     */
    public boolean contains(final Pair<Integer, Integer> position) {
        return isInside(position.getFirst(), position.getSecond());
    }
}
