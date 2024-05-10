package unibo.citysimulation.model.business;

import java.util.List;

import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.time.LocalTime;

/**
 * Interface representing a business entity.
 */
public interface Business extends BusinessEmployee, BusinessFinancial, BusinessGraphic {

    /**
     * Default income value for a business.
     */
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
     * @return A list of all employees.
     */
    List<Person> getEmployees();

    /**
     * Returns the name of the business.
     *
     * @return The name of the business.
     */
    String getName();

    /**
     * Returns the business hours of the business.
     *
     * @return The business hours of the business.
     */
    Long getBusinessHours();

    /**
     * Returns the opening time of the business.
     *
     * @return The opening time of the business.
     */
    LocalTime getOpeningTime();

    /**
     * Returns the closing time of the business.
     *
     * @return The closing time of the business.
     */
    LocalTime getClosingTime();

    /**
     * Returns the zone where the business is located.
     *
     * @return The zone where the business is located.
     */
    Zone getZone();

    /**
     * Returns the position of the business.
     *
     * @return The position of the business.
     */
    Pair<Integer, Integer> getPosition();

}
