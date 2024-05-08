package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

/**
 * The Zone interface represents a zone in a city simulation.
 */
public interface Zone {
    /**
     * Gets the name of the zone.
     *
     * @return the name of the zone
     */
    String getName();

    /**
     * Sets the name of the zone.
     *
     * @param name the name of the zone
     */
    void setName(String name);

    /**
     * Gets the percentage of people in the zone.
     *
     * @return the percentage of people in the zone
     */
    float getPersonPercents();

    /**
     * Sets the percentage of people in the zone.
     *
     * @param personPercents the percentage of people in the zone
     */
    void setPersonPercents(float personPercents);

    /**
     * Gets the percentage of businesses in the zone.
     *
     * @return the percentage of businesses in the zone
     */
    float getBusinessPercents();

    /**
     * Sets the percentage of businesses in the zone.
     *
     * @param businessPercents the percentage of businesses in the zone
     */
    void setBusinessPercents(float businessPercents);

    /**
     * Gets the welfare level of the zone.
     *
     * @return the welfare level of the zone
     */
    float getWellfare();

    /**
     * Sets the welfare level of the zone.
     *
     * @param wellfare the welfare level of the zone
     */
    void setWellfare(float wellfare);

    /**
     * Gets the minimum and maximum welfare levels of the zone.
     *
     * @return a Pair object containing the minimum and maximum welfare levels of the zone
     */
    Pair<Integer, Integer> getWellfareMinMax();

    Boundary getBoundary();
}