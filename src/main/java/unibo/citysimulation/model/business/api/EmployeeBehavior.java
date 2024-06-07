package unibo.citysimulation.model.business.api;

import java.util.Optional;

import unibo.citysimulation.model.business.impl.Employee;
import unibo.citysimulation.utilities.Pair;

/**
 * This interface represents employye behavior.
 */
public interface EmployeeBehavior {
    /**
     * Increments the count of delays for the employee by 1.
     *
     * @return The updated Employee object.
     */
    Employee incrementDelayCount();

    /**
     * Checks if the employee is late based on the given business position.
     *
     * @param businessPosition The business position to check against.
     * @return True if the employee is late, false otherwise.
     */
    boolean isLate(Optional<Pair<Integer, Integer>> businessPosition);
}
