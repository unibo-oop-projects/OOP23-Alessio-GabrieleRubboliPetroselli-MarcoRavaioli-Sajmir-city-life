package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

public interface Zone {
    String getName();

    float getPersonPercents();

    float getBusinessPercents();

    float getWellfare();

    Pair<Integer, Integer> getWellfareMinMax();

    /**
     * Returns the boundary of the zone.
     *
     * @return the boundary of the zone
     */
    public Boundary getBoundary();
}
