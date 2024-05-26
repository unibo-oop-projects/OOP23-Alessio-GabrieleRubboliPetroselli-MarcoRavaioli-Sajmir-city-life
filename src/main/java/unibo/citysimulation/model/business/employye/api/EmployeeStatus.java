package unibo.citysimulation.model.business.employye.api;

import unibo.citysimulation.utilities.Pair;

/**
 * This interface represents an employee with a name and an ID.
 */
public interface EmployeeStatus {

    /**
     * Returns the count of delays for the employee.
     *
     * @return The count of delays for the employee.
     */
    int getCountDelay();

    /**
     * Sets the count of delays for the employee.
     *
     * @param countDelay The count of delays for the employee.
     */
    void setCountDelay(int countDelay);

    /**
     * Increments the count of delays for the employee by 1.
     */
    void incrementDelayCount();

    /**
     * Checks if the employee is late based on the given business position.
     *
     * @param businessPosition The business position to check against.
     * @return True if the employee is late, false otherwise.
     */
    boolean isLate(Pair<Integer, Integer> businessPosition);
}
