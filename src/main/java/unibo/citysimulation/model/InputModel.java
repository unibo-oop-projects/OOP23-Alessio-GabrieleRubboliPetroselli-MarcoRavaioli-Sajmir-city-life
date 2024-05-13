package unibo.citysimulation.model;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class InputModel {
    private int numberOfPeople = 0;
    private int numberOfBusiness = 0;
    private int capacity = 0;
    private int richness = 0;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople * (ConstantAndResourceLoader.MAX_PEOPLE - ConstantAndResourceLoader.MIN_PEOPLE) / 100 + ConstantAndResourceLoader.MIN_PEOPLE;
    }

    public int getNumberOfBusiness() {
        return numberOfBusiness;
    }

    public void setNumberOfBusiness(int numberOfBusiness) {
        this.numberOfBusiness = numberOfBusiness;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRichness() {
        return richness;
    }

    public void setRichness(int richness) {
        this.richness = richness;
    }
}
