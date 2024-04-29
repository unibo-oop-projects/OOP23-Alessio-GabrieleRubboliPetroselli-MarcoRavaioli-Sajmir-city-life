package unibo.citysimulation.model.business;
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
    void hire(Employee employee);

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


    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee
     * @return the employee with the given ID, or null if no such employee exists
     */
    Employee getEmployeeById(int id);

    /**
     * Updates an employee's information.
     *
     * @param employee the employee to be updated
     */
    void updateEmployee(Employee employee);
    
}