package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;

/**
 * Represents a medium-sized business in the city simulation.
 * Inherits from the Business class.
 */
public class MediumBusiness extends Business {

    /**
     * Constructs a new MediumBusiness object.
     * Sets the opening time, closing time, revenue, maximum number of employees,
     * maximum age, minimum age, and maximum tardiness based on the configuration
     * values defined in the BusinessConfig class.
     */
    public MediumBusiness(Zone zone) {
        super(zone);
        this.opLocalTime = BusinessConfig.MEDIUM_OPENING_TIME;
        this.clLocalTime = BusinessConfig.MEDIUM_CLOSING_TIME;
        this.revenue = BusinessConfig.MEDIUM_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_MEDIUM_BUSINESS;
        this.maxAge = BusinessConfig.MEDIUM_MAX_AGE;
        this.minAge = BusinessConfig.MEDIUM_MIN_AGE;
        this.maxTardiness = BusinessConfig.MEDIUM_MAX_TARDINESS;
    }
}
