package unibo.citysimulation.model.business;

import java.util.List;

/**
 * Interface representing financial aspects of a business entity.
 * Provides methods for calculating income, personnel cost, profit, and checking if the business made a profit.
 */
public interface BusinessFinancial {
    

    /**
     * Calculates the total personnel cost of the business.
     *
     * @param wageRate the hourly wage rate for each employee
     * @return the total personnel cost
     */
    double calculatePersonnelCost(Business business);

    /**
     * Calculates the total income of the business.
     *
     * @return the total income
     */
    double calculateIncome(Business business);

    /**
     * Calculates the profit of the business.
     *
     * @param business the business entity
     * @return the profit
     */
    double calculateProfit(Business business);
    
    


    
}