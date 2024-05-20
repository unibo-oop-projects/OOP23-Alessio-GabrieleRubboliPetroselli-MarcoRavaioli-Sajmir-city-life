package unibo.citysimulation.model.business;

/**
 * Represents a small business in the city simulation.
 * Inherits from the Business class.
 */
public class SmallBusiness extends Business{

    /**
     * Constructs a SmallBusiness object.
     * Initializes the opening time, closing time, revenue, maximum number of employees,
     * maximum age, minimum age, and maximum tardiness based on the configuration values
     * defined in the BusinessConfig class.
     */
    public SmallBusiness() {
        this.opLocalTime = BusinessConfig.SMALL_OPENING_TIME;
        this.clLocalTime = BusinessConfig.SMALL_CLOSING_TIME;
        this.revenue = BusinessConfig.SMALL_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_SMALL_BUSINESS;
        this.maxAge = BusinessConfig.SMALL_MAX_AGE;
        this.minAge = BusinessConfig.SMALL_MIN_AGE;
        this.maxTardiness = BusinessConfig.SMALL_MAX_TARDINESS;
    }
}
