package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.utilities.Pair;

public interface Zone {
    String getName();

    float getPersonPercents();

    float getBusinessPercents();

    Pair<Integer, Integer> getWellfareMinMax();

    /**
     * Returns the boundary of the zone.
     *
     * @return the boundary of the zone
     */
    public Boundary getBoundary();

    public Pair<Integer, Integer> getRandomPosition();

}
