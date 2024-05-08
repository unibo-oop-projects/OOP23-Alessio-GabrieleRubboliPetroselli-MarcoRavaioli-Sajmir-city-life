package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

public interface Zone {
    String getName();

    float getPersonPercents();

    /**
     * Gets the percentage of businesses in the zone.
     *
     * @return the percentage of businesses in the zone
     */
    float getBusinessPercents();

    /**
     * Gets the welfare level of the zone.
     *
     * @return the welfare level of the zone
     */
    float getWellfare();

    /**
     * Gets the minimum and maximum welfare levels of the zone.
     *
     * @return a Pair object containing the minimum and maximum welfare levels of the zone
     */
    Pair<Integer, Integer> getWellfareMinMax();

    /**
     * Returns the boundary of the zone.
     *
     * @return the boundary of the zone
     */
    public Boundary getBoundary();

    public Pair<Integer, Integer> getRandomPosition();
}