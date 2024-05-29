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
    void hire(Employee employee);
    /**
     * Fires an employee from the business.
     * 
     * @param employee the employee to be fired
     */
    void fire(Employee employee);
    /**
     * Checks the delays of the employees.
     * 
     * @param currentTime the current time
     */
    void checkEmployeeDelays(LocalTime currentTime);
    /**
     * Pays the employees.
     * @return the total amount of money paid to the employees
     */
    double calculatePay();
}
