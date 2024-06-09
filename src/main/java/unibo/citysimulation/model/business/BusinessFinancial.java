package unibo.citysimulation.model.business;

/**
 * Interface representing financial aspects of a business entity.
 */
public interface BusinessFinancial {
    // Method to calculate the total income
    double calculateIncome();

    // Method to calculate the cost of personnel
    double calculatePersonnelCost();

    // Method to calculate profit
    double calculateProfit();

    // Method to check if the business made a profit or a loss
    boolean isProfit();
}