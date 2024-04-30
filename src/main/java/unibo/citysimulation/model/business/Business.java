package unibo.citysimulation.model.business;


import java.time.LocalTime;
import java.util.List;

/**
 * Interface representing a business entity.
 */
public interface Business{
    /**
     * Updates the list of employees with the given list.
     *
     * @param employees the new list of employees
     * @return a new instance of Business with updated employee list
     */
    Business updateEmployees(List<Employee> employees);

    /**
     * Returns the list of employees.
     *
     * @return the list of employees
     */
    List<Employee> getEmployees();

    /**
     * Returns the opening time of the business.
     *
     * @return the opening time
     */
    LocalTime getOpeningTime();
    
    /**
     * Returns the closing time of the business.
     *
     * @return the closing time
     */
    LocalTime getClosingTime();

    /**
     * Returns the wage rate of the business.
     *
     * @return the wage rate
     */
    double getWageRate();

    /**
     * Returns the income of the business.
     *
     * @return the income
     */
    double getIncome();
}

    
