package unibo.citysimulation.model.business;

import java.util.List;

/**
 * Interface representing financial aspects of a business entity.
 * Provides methods for calculating income, personnel cost, profit, and checking if the business made a profit.
 */
public interface BusinessFinancial {
    
    /**
     * Calculates the total income of the business.
     *
     * @return the total income
     */
    double calculateIncome(List<Employee> employees, int income);

    
}