package unibo.citysimulation.model.business.utilities;

import java.util.LinkedList;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.zone.Zone;

/**
 * Represents a big business in the city simulation.
 * Extends the Business class.
 */
public class BigBusiness extends Business {

   /**
     * Creates a big business in the city simulation.
     * @param zone the zone where the big business is located
     */
    public BigBusiness(final int id, final Zone zone) {
        super(new BusinessData(
            id,
            new LinkedList<>(),
            BusinessConfig.BIG_OPENING_TIME,
            BusinessConfig.BIG_CLOSING_TIME,
            BusinessConfig.BIG_REVENUE,
            BusinessConfig.MAX_EMPLOYEES_BIG_BUSINESS,
            zone.getRandomPosition(),
            BusinessConfig.BIG_MIN_AGE,
            BusinessConfig.BIG_MAX_AGE,
            BusinessConfig.BIG_MAX_TARDINESS,
            zone));
    }
}
