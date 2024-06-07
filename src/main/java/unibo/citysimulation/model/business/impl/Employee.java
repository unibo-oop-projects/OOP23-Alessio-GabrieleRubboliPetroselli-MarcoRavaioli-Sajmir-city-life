package unibo.citysimulation.model.business.impl;

import java.util.Optional;

import unibo.citysimulation.model.business.api.EmployeeBehavior;
import unibo.citysimulation.model.business.impl.Business.BusinessData;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.utilities.Pair;


/**
 * Represents an employee in the city simulation.
 */
public record Employee(DynamicPerson person, BusinessData businessData, int count) implements EmployeeBehavior{

    /**
     * Constructs a new Employee object with the given person and business data.
     * @param person The dynamic person associated with the employee.
     * @param businessData The business data associated with the employee.
     */
    public Employee(final DynamicPerson person, final BusinessData businessData) {
        this(person, businessData, 0);
    }
    
    /**
     * Increments the delay count of the employee.
     * @return A new Employee object with the incremented delay count.
     */
    @Override
    public Employee incrementDelayCount() {
        return new Employee(person, businessData, count + 1);
    }

    /**
     * Checks if the employee is late based on the business position.
     * @param businessPosition The position of the business.
     * @return true if the employee is late, false otherwise.
     */
    @Override
    public boolean isLate(final Optional<Pair<Integer, Integer>> businessPosition) {
        return person.getPosition().equals(businessPosition);
    }
}
