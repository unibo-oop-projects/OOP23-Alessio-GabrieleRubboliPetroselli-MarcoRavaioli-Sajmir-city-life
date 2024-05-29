package unibo.citysimulation.model.zone;

import java.util.Random;

import unibo.citysimulation.utilities.Pair;

/**
 * Represents a zone in a city simulation.
 *
 * @param name The name of the zone.
 * @param personPercents The percentage of people in the zone.
 * @param businessPercents The percentage of businesses in the zone.
 * @param wellfareMinMax The minimum and maximum welfare values in the zone.
 * @param ageMinMax The minimum and maximum age values in the zone.
 * @param boundary The boundary of the zone.
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
