package unibo.citysimulation.model.business;

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
    double calculateIncome();

    /**
     * Calculates the cost of personnel in the business.
     *
     * @return the personnel cost
     */
    double calculatePersonnelCost();

    /**
     * Calculates the profit of the business.
     * This is typically calculated as total income minus personnel cost.
     *
     * @return the profit
     */
    double calculateProfit();

    /**
     * Checks if the business made a profit.
     *
     * @return true if the business made a profit, false otherwise
     */
    boolean isProfit();
}