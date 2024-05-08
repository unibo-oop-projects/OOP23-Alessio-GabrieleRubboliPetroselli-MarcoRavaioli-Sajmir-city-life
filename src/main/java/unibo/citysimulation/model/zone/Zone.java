package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

public interface Zone {
    String getName();
    void setName(String name);

    float getPersonPercents();
    void setPersonPercents(float personPercents);

    float getBusinessPercents();
    void setBusinessPercents(float businessPercents);

    float getWellfare();
    void setWellfare(float wellfare);

    Pair<Integer, Integer> getWellfareMinMax();
}
