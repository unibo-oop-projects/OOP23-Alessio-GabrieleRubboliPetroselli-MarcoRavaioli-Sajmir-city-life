package unibo.citysimulation.model.business;

/**
 * Interface representing employee-related aspects of a business entity.
 * Provides methods for hiring, firing, counting, and retrieving employees.
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
}