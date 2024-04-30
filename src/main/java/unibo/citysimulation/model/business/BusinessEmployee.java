package unibo.citysimulation.model.business;


/**
 * This interface represents the employee-related aspects of a business entity.
 * It provides methods for hiring, firing, counting, and retrieving employees.
 */
public interface BusinessEmployee {
    
    /**
     * Hires a new employee to the business.
     *
     * @param business The business where the employee will be hired.
     * @param employee The employee to be hired.
     * @return The updated business after hiring the employee.
     */
    Business hire(Business business, Employee employee);

    /**
     * Fires an employee from the business.
     *
     * @param business The business from where the employee will be fired.
     * @param employee The employee to be fired.
     * @return The updated business after firing the employee.
     */
    Business fire(Business business, Employee employee);

    /**
     * Counts the number of employees in the business.
     *
     * @param business The business whose employees will be counted.
     * @return The number of employees in the business.
     */
    int countEmployees(Business business);

    /**
     * Checks if the business has any employees.
     *
     * @param business The business to be checked.
     * @return True if the business has at least one employee, false otherwise.
     */
    boolean hasEmployees(Business business);

    /**
     * Retrieves an employee from the business by their ID.
     *
     * @param business The business where the employee will be retrieved from.
     * @param id The ID of the employee to be retrieved.
     * @return The employee with the given ID, or null if no such employee exists.
     */
    Employee getEmployeeById(Business business, int id);
}