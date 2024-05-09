package unibo.citysimulation.model.zone;

import java.util.Random;

import unibo.citysimulation.utilities.Pair;

/**
 * A class representing a zone in the city.
 */
public class ZoneImpl implements Zone {
    private String name;
    private float personPercents;
    private float businessPercents;
    private Pair<Integer, Integer> wellfareMinMax;
    private Pair<Integer, Integer> ageMinMax;
    private Random random = new Random();
    private Boundary boundary;

    public ZoneImpl(String name, float personPercents, float businessPercents, Pair<Integer,
    Integer> wellfareMinMax, Pair<Integer,Integer> ageMinMax, Boundary boundary) {
        this.name = name;
        this.personPercents = personPercents;
        this.businessPercents = businessPercents;
        this.wellfareMinMax = wellfareMinMax;
        this.ageMinMax = ageMinMax;
        this.boundary = boundary;
    }

    // Getter and setter for 'name'
    public void setName(String name) {
        this.name = name;
    }

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

    public Pair<Integer, Integer> getWellfareMinMax() {
        return wellfareMinMax;
    }

    @Override
    public Boundary getBoundary() {
        return boundary;
    }

    @Override
    public Pair<Integer, Integer> getRandomPosition() {
        int x = random.nextInt(boundary.getWidth());
        int y = random.nextInt(boundary.getHeight());
        return new Pair<>(x, y);
    }
}