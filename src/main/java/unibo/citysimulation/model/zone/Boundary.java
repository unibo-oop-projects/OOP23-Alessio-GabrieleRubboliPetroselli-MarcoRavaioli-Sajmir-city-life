package unibo.citysimulation.model.zone;

/**
 * The Boundary class represents a rectangular boundary in a city simulation.
 * It defines the coordinates of the top-left corner (x1, y1) and the bottom-right corner (x2, y2).
 */
public class Boundary {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     * Constructs a Boundary object with the specified coordinates.
     *
     * @param x1 the x-coordinate of the top-left corner
     * @param y1 the y-coordinate of the top-left corner
     * @param x2 the x-coordinate of the bottom-right corner
     * @param y2 the y-coordinate of the bottom-right corner
     */
    public Boundary(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Checks if the given coordinates (x, y) are inside the boundary.
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if the coordinates are inside the boundary, false otherwise
     */
    public boolean isInside(int x, int y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }
}
