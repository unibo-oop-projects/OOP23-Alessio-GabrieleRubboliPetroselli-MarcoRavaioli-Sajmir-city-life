package unibo.citysimulation.model.zone;

public interface Zone {
    String getName();
    void setName(String name);

    float getPersonPercents();
    void setPersonPercents(float personPercents);

    float getBusinessPercents();
    void setBusinessPercents(float businessPercents);

    float getWellfare();
    void setWellfare(float wellfare);
}
