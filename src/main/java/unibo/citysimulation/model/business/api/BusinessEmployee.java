package unibo.citysimulation.model.business.api;

import java.time.LocalTime;

import unibo.citysimulation.model.business.employye.impl.Employee;

/**
 * The interface representing a business employee.
 */
public interface BusinessEmployee {
    /**
     * Hires an employee for the business.
     * 
     * @param employee the employee to be hired
     */
    void hire(final Employee employee);
    /**
     * Fires an employee from the business.
     * 
     * @param employee the employee to be fired
     */
    void fire(final Employee employee);

    void checkEmployeeDelays(final LocalTime currentTime);

    double calculatePay();
}
