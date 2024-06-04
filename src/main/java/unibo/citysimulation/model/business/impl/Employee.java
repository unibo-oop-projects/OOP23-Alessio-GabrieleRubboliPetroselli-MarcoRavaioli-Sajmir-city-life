package unibo.citysimulation.model.business.impl;

import java.util.Optional;

import unibo.citysimulation.model.business.api.EmployeeBehavior;
import unibo.citysimulation.model.business.impl.Business.BusinessData;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.utilities.Pair;


/**
 * Employee
 */
public record Employee(DynamicPerson person, BusinessData businessData, int count) implements EmployeeBehavior{

    public Employee(DynamicPerson person, BusinessData businessData) {
        this(person, businessData, 0);
    }
    
    @Override
    public Employee incrementDelayCount() {
        return new Employee(person, businessData, count + 1);
    }

    @Override
    public boolean isLate(Optional<Pair<Integer, Integer>> businessPosition) {
        return person.getPosition().equals(businessPosition);
    }


}
