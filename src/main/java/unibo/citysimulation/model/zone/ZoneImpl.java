package unibo.citysimulation.model.zone;

/**
 * A class representing a zone in the city.
 */
public class ZoneImpl implements Zone {
    private String name;
    private float personPercents;
    private float businessPercents;
    private float wellfare;
    private Pair<Integer, Integer> wellfareMinMax;

    public ZoneImpl(String name) {
        this.name = name;
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

    // Getter and setter for 'wellfare'
    public void setWellfare(float wellfare) {
        this.wellfare = wellfare;
    }

    public float getWellfare() {
        return wellfare;
    }
}