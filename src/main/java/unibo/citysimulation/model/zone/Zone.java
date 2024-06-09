package unibo.citysimulation.model.zone;

import java.util.Random;

import unibo.citysimulation.utilities.Pair;
/**
 * Represents a zone in the city simulation.
 * 
 * @param name            the name of the zone
 * @param personPercents  the percentage of people in the zone
 * @param businessPercents the percentage of businesses in the zone
 * @param wellfareMinMax  the minimum and maximum wellfare values in the zone
 * @param ageMinMax       the minimum and maximum age values in the zone
 * @param boundary        the boundary of the zone
 */
public record Zone(String name, float personPercents, float businessPercents, Pair<Integer, Integer> wellfareMinMax,
        Pair<Integer, Integer> ageMinMax, Boundary boundary) {
    static final Random RANDOM = new Random();

    /**
     * Generates a random position within the zone's boundary.
     * 
     * @return a Pair object representing the random position (x, y)
     */
    public Pair<Integer, Integer> getRandomPosition() {
        final int x = RANDOM.nextInt(boundary.getWidth()) + boundary.getX();
        final int y = RANDOM.nextInt(boundary.getHeight()) + boundary.getY();
        return new Pair<>(x, y);
    }
}
