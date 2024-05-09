package unibo.citysimulation.model.zone;

import java.util.Random;

import unibo.citysimulation.utilities.Pair;

/**
 * A class representing a zone in the city.
 */
public class Zone implements ZoneInfo {
    private String name;
    private float personPercents;
    private float businessPercents;
    private float wellfare;
    private Pair<Integer, Integer> wellfareMinMax;
    private Pair<Integer, Integer> ageMinMax;
    private Random random = new Random();
    private ZoneBoundary boundary;

    public Zone(String name, float personPercents, float businessPercents, float wellfare, Pair<Integer,
    Integer> wellfareMinMax, Pair<Integer,Integer> ageMinMax, ZoneBoundary boundary) {
        this.name = name;
        this.personPercents = personPercents;
        this.businessPercents = businessPercents;
        this.wellfare = wellfare;
        this.wellfareMinMax = wellfareMinMax;
        this.ageMinMax = ageMinMax;
        this.boundary = boundary;
    }

    // Getter and setter for 'name'
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

    // Getter and setter for 'personPercents'
    public void setPersonPercents(float personPercents) {
        this.personPercents = personPercents;
    }

    public float getPersonPercents() {
        return personPercents;
    }

    // Getter and setter for 'businessPercents'
    public void setBusinessPercents(float businessPercents) {
        this.businessPercents = businessPercents;
    }

    public float getBusinessPercents() {
        return businessPercents;
    }

    // Getter and setter for 'wellfare'
    public void setWellfare(float wellfare) {
        this.wellfare = wellfare;
    }

    public float getWellfare() {
        return wellfare;
    }

    public Pair<Integer, Integer> getWellfareMinMax() {
        return wellfareMinMax;
    }

    @Override
    public ZoneBoundary getZoneBoundary() {
        return null;
    }

    @Override
    public Pair<Integer, Integer> getRandomPosition() {
        return null;
    }
}