package unibo.citysimulation.model;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class InputModel {
    private int numberOfPeople;
    private int numberOfBusiness;
    private int capacity;
    private int richness;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(final int numberOfPeople) {
        this.numberOfPeople = numberOfPeople * (ConstantAndResourceLoader.MAX_PEOPLE - ConstantAndResourceLoader.MIN_PEOPLE) / 100 + ConstantAndResourceLoader.MIN_PEOPLE;
        //System.out.println(numberOfPeople);
    }

    public int getNumberOfBusiness() {
        return numberOfBusiness;
        
    }

    public void setNumberOfBusiness(final int numberOfBusiness) {
        this.numberOfBusiness = numberOfBusiness;
        //System.out.println(numberOfBusiness);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(final int capacity) {
        this.capacity = capacity;
        //System.out.println(capacity);
    }

    public int getRichness() {
        return richness;
    }

    public void setRichness(final int richness) {
        this.richness = richness;
        //System.out.println(richness);
    }
}
