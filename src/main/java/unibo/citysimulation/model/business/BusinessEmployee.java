package unibo.citysimulation.model.business;

import java.util.List;

import unibo.citysimulation.model.person.Person;

/**
 * Interface representing employee-related aspects of a business entity.
 * Provides methods for hiring, firing, counting, and retrieving employees.
 */
public interface BusinessEmployee {
    
    /**
     * Hires a new employee.
     *
     * @param employee the employee to be hired
     */
    void hire(Person employee);

    /**
     * Fires an existing employee.
     *
     * @param employee the employee to be fired
     */
    void fire(Employee employee);

    /**
     * Checks if the business has any employees.
     *
     * @return true if the business has one or more employees, false otherwise
     */
    boolean hasEmployees();

    /**
     * Counts the number of employees in the business.
     *
     * @return the number of employees
     */
    int countEmployees();


   

   
    
}