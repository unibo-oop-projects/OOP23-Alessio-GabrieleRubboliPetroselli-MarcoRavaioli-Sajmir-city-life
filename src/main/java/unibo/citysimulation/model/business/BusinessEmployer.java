package unibo.citysimulation.model.business;

/**
 * Interface representing employee-related aspects of a business entity.
 */
public interface BusinessEmployer {
    // Method to hire an employee
    void hire(Employee employee);

    // Method to fire an employee
    void fire(Employee employee);

    // Method to check if the business has employees
    boolean hasEmployees();

    // Method to count the number of employees
    int countEmployees();

    // Method to get the list of employees
    List<Employee> getEmployees();

    // Method to get an employee by ID
    Employee getEmployeeById(int id);
    
}
