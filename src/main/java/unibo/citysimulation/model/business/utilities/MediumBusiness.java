package unibo.citysimulation.model.business.utilities;

import unibo.citysimulation.model.business.impl.Business;
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
     *
     * @param zone the zone where the medium-sized business is located
     */
    public MediumBusiness(final Zone zone) {
        super(zone);
        setOpLocalTime(BusinessConfig.MEDIUM_OPENING_TIME);
        setClLocalTime(BusinessConfig.MEDIUM_CLOSING_TIME);
        setRevenue(BusinessConfig.MEDIUM_REVENUE);
        setMaxEmployees(BusinessConfig.MAX_EMPLOYEES_MEDIUM_BUSINESS);
        setMaxAge(BusinessConfig.MEDIUM_MAX_AGE);
        setMinAge(BusinessConfig.MEDIUM_MIN_AGE);
        setMaxTardiness(BusinessConfig.MEDIUM_MAX_TARDINESS);
    }
}
