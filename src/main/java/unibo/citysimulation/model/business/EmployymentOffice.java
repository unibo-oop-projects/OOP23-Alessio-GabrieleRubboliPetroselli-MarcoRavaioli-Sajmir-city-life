package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;
import unibo.citysimulation.model.person.DynamicPerson;

/**
 * The EmploymentOffice class represents an employment office in a city simulation.
 * It keeps track of disoccupied people and provides methods to add and remove them.
 */
public class EmployymentOffice {

    /**
     * A list of disoccupied people registered at the employment office.
     */
    private final List<DynamicPerson> disoccupiedPeople;

    /**
     * Constructs a new EmploymentOffice object with an empty list of disoccupied people.
     */
    public EmployymentOffice() {
        this.disoccupiedPeople = new ArrayList<>();
    }

    /**
     * Returns the list of disoccupied people registered at the employment office.
     * 
     * @return the list of disoccupied people
     */
    public final List<DynamicPerson> getDisoccupiedPeople() {
        return disoccupiedPeople;
    }

    /**
     * Adds a disoccupied person to the employment office.
     * 
     * @param person the person to be added
     */
    public final void addDisoccupiedPerson(final DynamicPerson person) {
        this.disoccupiedPeople.add(person);
    }

    /**
     * Removes a disoccupied person from the employment office.
     * 
     * @param person the person to be removed
     */
    public final void removeDisoccupiedPerson(final DynamicPerson person) {
        this.disoccupiedPeople.remove(person);
    }
    
}
