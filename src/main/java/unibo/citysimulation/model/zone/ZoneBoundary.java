package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

/**
 * The Boundary class represents a rectangular boundary in a city simulation.
 * It defines the coordinates of the top-left corner (x1, y1) and the bottom-right corner (x2, y2).
 */
public class ZoneBoundary implements Boundary{

    private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> Boundary;

    public ZoneBoundary(int x1, int y1, int x2, int y2) {
        this.Boundary = new Pair<>(new Pair<>(x1, y1), new Pair<>(x2, y2));
    }

    @Override
    public int getWidth() {
        return Boundary.getSecond().getFirst() - Boundary.getFirst().getFirst();
    }

    @Override
    public int getHeight() {
        return Boundary.getSecond().getSecond() - Boundary.getFirst().getSecond();
    }

    @Override
    public boolean contains(Pair<Integer, Integer> point) {
        return point.getFirst() >= Boundary.getFirst().getFirst() && point.getFirst() <= Boundary.getSecond().getFirst()
                && point.getSecond() >= Boundary.getFirst().getSecond() && point.getSecond() <= Boundary.getSecond().getSecond();
    }
    

   
}
