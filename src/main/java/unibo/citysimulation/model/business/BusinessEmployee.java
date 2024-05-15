package unibo.citysimulation.model.business;



/**
 * Interface representing employee-related aspects of a business entity.
 * Provides methods for hiring, firing, counting, and retrieving employees.
 * Provides methods for hiring, firing, counting, and retrieving employees.
 */
public interface BusinessEmployee {
    
    void hire(Employee employee, Business business);
    void fire(Employee employee, Business business);

    
}