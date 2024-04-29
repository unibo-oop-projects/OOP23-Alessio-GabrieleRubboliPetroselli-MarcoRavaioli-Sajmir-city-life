package unibo.citysimulation.model.business;

import java.util.List;

/**
 * Interface representing a business entity.
 */
public interface Business extends BusinessEmployee, BusinessFinancial, BusinessGraphic{

    public static final int DEFAULT_INCOME = 0;

    /**
     * Returns the income of the business.
     *
     * @return The income of the business.
     */
    int getIncome();

    /**
     * Retrieves the list of all employees.
     *
     * @return a list of all employees
     */
    List<Employee> getEmployees();

    /**
     * Returns the name of the business.
     *
     * @return The name of the business.
     */
    String getName();

    /**
     * Returns the hours of the business.
     *
     * @return The hours of the business.
     */
    Long getBusinessHours();

}

    
