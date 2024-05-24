package unibo.citysimulation.model.business;

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
}
