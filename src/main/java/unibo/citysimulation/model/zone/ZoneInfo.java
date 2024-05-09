package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

public interface ZoneInfo {
    
    String getName();

    float getPersonPercents();

    float getBusinessPercents();

    float getWellfare();
    
    Pair<Integer, Integer> getWellfareMinMax();

    public ZoneBoundary getZoneBoundary();

    public Pair<Integer, Integer> getRandomPosition();
}