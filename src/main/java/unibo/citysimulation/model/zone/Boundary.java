package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;


/**
 * The Boundary class represents a rectangular boundary in a city simulation.
 * It defines the coordinates of the top-left corner (x1, y1) and the bottom-right corner (x2, y2).
 */
public class Boundary {
    
    private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coordinates;

    /**
     * Constructs a Boundary object with the specified coordinates.
     *
     * @param x1 the x-coordinate of the top-left corner
     * @param y1 the y-coordinate of the top-left corner
     * @param x2 the x-coordinate of the bottom-right corner
     * @param y2 the y-coordinate of the bottom-right corner
     */
    public Boundary(int x1, int y1, int x2, int y2) {
        coordinates = new Pair<>(new Pair<>(x1, y1), new Pair<>(x2, y2));
    }

    /**
     * Checks if the given coordinates (x, y) are inside the boundary.
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if the coordinates are inside the boundary, false otherwise
     */
    public boolean isInside(int x, int y) {
        return x >= coordinates.getFirst().getFirst() && x <= coordinates.getSecond().getFirst() &&
                y >= coordinates.getFirst().getSecond() && y <= coordinates.getSecond().getSecond();
    }

    public int getHeight() {
        return coordinates.getSecond().getSecond() - coordinates.getFirst().getSecond();
    }

    public int getWidth() {
        return coordinates.getSecond().getFirst() - coordinates.getFirst().getFirst();
    }

    public int getX() {
        return coordinates.getFirst().getFirst();
    }
    public int getY() {
        return coordinates.getFirst().getSecond();
    }

    
}
