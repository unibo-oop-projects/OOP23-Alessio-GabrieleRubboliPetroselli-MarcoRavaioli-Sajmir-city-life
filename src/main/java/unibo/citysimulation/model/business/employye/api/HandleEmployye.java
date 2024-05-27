package unibo.citysimulation.model.business.employye.api;

import unibo.citysimulation.model.business.impl.Business;

public interface HandleEmployye {
    /**
     * Handles the firing of employees for the specified business.
     * For each employee that should be fired, adds the person to the employment office's disoccupied people list
     * and fires the employee from their business.
     * 
     * @param business The business for which to handle employee firing.
     */
    void handleEmployeeFiring(final Business business);
    /**
     * Handles the hiring of employees for the specified business.
     * Hires a minimum of 4 employees from the employment office's disoccupied people list 
     * or up to the maximum number of employees allowed for the business.
     * 
     * @param business The business for which to handle employee hiring.
     */
    void handleEmployeeHiring(final Business business);
    /**
     * Handles the payment of employees for the specified business.
     * Pays each employee in the business the amount of money they are owed.
     * 
     * @param business The business for which to handle employee payment.
     */
    void handleEmployyePay(final Business business);

}