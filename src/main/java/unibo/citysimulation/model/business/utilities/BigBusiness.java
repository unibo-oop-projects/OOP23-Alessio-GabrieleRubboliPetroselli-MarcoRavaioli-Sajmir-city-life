package unibo.citysimulation.model.business.utilities;

import java.util.LinkedList;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.zone.Zone;

public class BigBusiness extends Business {

    
    public BigBusiness(final Zone zone) {
        super(new BusinessData(
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
