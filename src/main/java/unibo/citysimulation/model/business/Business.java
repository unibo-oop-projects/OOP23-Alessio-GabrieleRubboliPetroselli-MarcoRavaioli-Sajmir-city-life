package unibo.citysimulation.model.business;

/**
 * Interface representing a business entity.
 */
public interface Business extends BusinessEmployee, BusinessFinancial, BusinessGraphic{

    /**
     * Returns the income of the business.
     *
     * @return The income of the business.
     */
    int getIncome();

}

    
