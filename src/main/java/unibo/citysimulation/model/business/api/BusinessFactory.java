package unibo.citysimulation.model.business.api;

import java.util.List;
import java.util.Optional;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.utilities.BusinessType;
import unibo.citysimulation.model.zone.Zone;

public interface BusinessFactory {
    Optional<Business> createBusiness(final BusinessType type, final Zone zone);
    Optional<Business> createRandomBusiness(final List<Zone> zones);
    Business createMultipleBusiness(final List<Zone> zones, final int numberOfPeople);
}
