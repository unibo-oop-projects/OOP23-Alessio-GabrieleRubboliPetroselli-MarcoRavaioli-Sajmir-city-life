package unibo.citysimulation.model.business;

/**
 * Represents a big business in the city simulation.
 * Extends the Business class.
 */
public class BigBusiness extends Business{

    /**
     * Constructs a new instance of the BigBusiness class.
     * Initializes the opening and closing times, revenue, maximum number of employees,
     * maximum and minimum age requirements, and maximum tardiness for the big business.
     */
    public BigBusiness() {
        this.opLocalTime = BusinessConfig.BIG_OPENING_TIME;
        this.clLocalTime = BusinessConfig.BIG_CLOSING_TIME;
        this.revenue = BusinessConfig.BIG_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_BIG_BUSINESS;
        this.maxAge = BusinessConfig.BIG_MAX_AGE;
        this.minAge = BusinessConfig.BIG_MIN_AGE;
        this.maxTardiness = BusinessConfig.BIG_MAX_TARDINESS;
    }
}
