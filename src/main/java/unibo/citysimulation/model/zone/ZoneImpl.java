package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

/**
 * A class representing a zone in the city.
 */
public class ZoneImpl implements Zone {
    private String name;
    private Boundary boundary;
    private float personPercents;
    private float businessPercents;
    private float wellfare;
    private Pair<Integer, Integer> wellfareMinMax;
    private Pair<Integer, Integer> ageMinMax;

    /**
     * Constructs a ZoneImpl object with the specified parameters.
     *
     * @param name              the name of the zone
     * @param personPercents    the percentage of people in the zone
     * @param businessPercents  the percentage of businesses in the zone
     * @param wellfare          the wellfare value of the zone
     * @param wellfareMinMax    the minimum and maximum wellfare values of the zone
     * @param ageMinMax         the minimum and maximum age values of the zone
     */
    public ZoneImpl(String name, Boundary boundary, float personPercents, float businessPercents, float wellfare, Pair<Integer, Integer> wellfareMinMax, Pair<Integer,Integer> ageMinMax) {
        this.name = name;
        this.boundary = boundary;
        this.personPercents = personPercents;
        this.businessPercents = businessPercents;
        this.wellfare = wellfare;
        this.wellfareMinMax = wellfareMinMax;
        this.ageMinMax = ageMinMax;
    }

    /**
     * Sets the name of the zone.
     *
     * @param name the name of the zone
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the zone.
     *
     * @return the name of the zone
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the percentage of people in the zone.
     *
     * @param personPercents the percentage of people in the zone
     */
    public void setPersonPercents(float personPercents) {
        this.personPercents = personPercents;
    }

    /**
     * Returns the percentage of people in the zone.
     *
     * @return the percentage of people in the zone
     */
    public float getPersonPercents() {
        return personPercents;
    }

    /**
     * Sets the percentage of businesses in the zone.
     *
     * @param businessPercents the percentage of businesses in the zone
     */
    public void setBusinessPercents(float businessPercents) {
        this.businessPercents = businessPercents;
    }

    /**
     * Returns the percentage of businesses in the zone.
     *
     * @return the percentage of businesses in the zone
     */
    public float getBusinessPercents() {
        return businessPercents;
    }

    /**
     * Sets the wellfare value of the zone.
     *
     * @param wellfare the wellfare value of the zone
     */
    public void setWellfare(float wellfare) {
        this.wellfare = wellfare;
    }

    /**
     * Returns the wellfare value of the zone.
     *
     * @return the wellfare value of the zone
     */
    public float getWellfare() {
        return wellfare;
    }

    /**
     * Returns the minimum and maximum wellfare values of the zone.
     *
     * @return the minimum and maximum wellfare values of the zone
     */
    public Pair<Integer, Integer> getWellfareMinMax() {
        return wellfareMinMax;
    }

    @Override
    public Boundary getBoundary() {
        return boundary;
    }
}