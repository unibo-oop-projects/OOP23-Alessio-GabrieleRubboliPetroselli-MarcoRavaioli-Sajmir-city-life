package unibo.citysimulation.model.business.employye.api;

import unibo.citysimulation.model.person.api.DynamicPerson;

/**
 * The HandleDisoccupiedPerson interface represents an employment office that handles disoccupied persons.
 * It provides methods to add and remove disoccupied persons from the employment office.
 */
public interface HandleDisoccupiedPerson {

    /**
     * Adds a disoccupied person to the employment office.
     * 
     * @param person the person to be added
     */
    void addDisoccupiedPerson(DynamicPerson person);

    /**
     * Removes a disoccupied person from the employment office.
     * 
     * @param person the person to be removed
     */
    void removeDisoccupiedPerson(DynamicPerson person);

}
