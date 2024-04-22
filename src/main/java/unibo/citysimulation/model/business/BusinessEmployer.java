package unibo.citysimulation.model.business;

/**
 * Interface representing employee-related aspects of a business entity.
 */
public interface BusinessEmployer {
    // Method to hire an employee
    void hire(Employee employee);

    // Method to fire an employee
    void fire(Employee employee);
}
