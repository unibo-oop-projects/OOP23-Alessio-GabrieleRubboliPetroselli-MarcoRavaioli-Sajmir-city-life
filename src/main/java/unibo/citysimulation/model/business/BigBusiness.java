package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;

/**
 * Represents a big business in the city simulation.
 * Extends the Business class.
 */
public class BigBusiness extends Business{

    /**
     * Constructs a new instance of the BigBusiness class.
     * Initializes the opening and closing times, revenue, maximum number of employees,
     * maximum and minimum age requirements, and maximum tardiness for the big business.
     *
     * @param zone the zone where the big business is located
     */
    public BigBusiness(final Zone zone) {
        super(zone);
        setOpLocalTime(BusinessConfig.BIG_OPENING_TIME);
        setClLocalTime(BusinessConfig.BIG_CLOSING_TIME);
        setRevenue(BusinessConfig.BIG_REVENUE);
        setMaxEmployees(BusinessConfig.MAX_EMPLOYEES_BIG_BUSINESS);
        setMaxAge(BusinessConfig.BIG_MAX_AGE);
        setMinAge(BusinessConfig.BIG_MIN_AGE);
        setMaxTardiness(BusinessConfig.BIG_MAX_TARDINESS);
    }
}
