package unibo.citysimulation.model.zone;


import unibo.citysimulation.utilities.Pair;

/**
 * A class representing a zone in the city.
 */
public class ZoneImpl  {
    private String name;
    private float personPercents;
    private float businessPercents;
    private float wellfare;
    final private Pair<Integer, Integer> wellfareMinMax;


    public ZoneImpl(final String name, final float personPercents, final float businessPercents, final float wellfare, final Pair<Integer, Integer> wellfareMinMax) {
        this.name = name;
        this.personPercents = personPercents;
        this.businessPercents = businessPercents;
        this.wellfare = wellfare;
        this.wellfareMinMax = wellfareMinMax;

    }

    // Getter and setter for 'name'
    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Getter and setter for 'personPercents'
    public void setPersonPercents(final float personPercents) {
        this.personPercents = personPercents;
    }

    public float getPersonPercents() {
        return personPercents;
    }

    // Getter and setter for 'businessPercents'
    public void setBusinessPercents(final float businessPercents) {
        this.businessPercents = businessPercents;
    }

    public float getBusinessPercents() {
        return businessPercents;
    }

    // Getter and setter for 'wellfare'
    public void setWellfare(final float wellfare) {
        this.wellfare = wellfare;
    }

    public float getWellfare() {
        return wellfare;
    }

    public Pair<Integer, Integer> getWellfareMinMax() {
        return wellfareMinMax;
    }
}