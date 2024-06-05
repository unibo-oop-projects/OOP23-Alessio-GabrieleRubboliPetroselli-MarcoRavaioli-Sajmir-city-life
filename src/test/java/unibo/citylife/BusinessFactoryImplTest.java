package unibo.citylife;

import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.business.utilities.BigBusiness;
import unibo.citysimulation.model.business.utilities.BusinessConfig;
import unibo.citysimulation.model.business.utilities.BusinessType;
import unibo.citysimulation.model.business.utilities.MediumBusiness;
import unibo.citysimulation.model.business.utilities.SmallBusiness;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneCreation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessFactoryImplTest {

    private List<Zone> zones = ZoneCreation.createZonesFromFile();


    @Test
    void testCreateBusiness_Big() {
        Optional<Business> business = BusinessFactoryImpl.createBusiness(BusinessType.BIG, zones.get(0));
        assertTrue(business.isPresent());
        assertTrue(business.get() instanceof BigBusiness);
        assertEquals(zones.get(0), business.get().getBusinessData().zone());
    }

    @Test
    void testCreateBusiness_Medium() {
        Optional<Business> business = BusinessFactoryImpl.createBusiness(BusinessType.MEDIUM, zones.get(0));
        assertTrue(business.isPresent());
        assertTrue(business.get() instanceof MediumBusiness);
        assertEquals(zones.get(0), business.get().getBusinessData().zone());
    }

    @Test
    void testCreateBusiness_Small() {
        Optional<Business> business = BusinessFactoryImpl.createBusiness(BusinessType.SMALL, zones.get(0));
        assertTrue(business.isPresent());
        assertTrue(business.get() instanceof SmallBusiness);
        assertEquals(zones.get(0), business.get().getBusinessData().zone());
    }

    @Test
    void testCreateBusiness_Invalid() {
        Optional<Business> business = BusinessFactoryImpl.createBusiness(null, zones.get(0));
        assertFalse(business.isPresent());
    }

    @Test
    void testCreateRandomBusiness() {
        Optional<Business> business = BusinessFactoryImpl.createRandomBusiness(zones);
        assertTrue(business.isPresent());
        assertNotNull(business.get().getBusinessData().zone());
    }

    @Test
    void testCreateMultipleBusiness() {
        int numberOfPeople = 100;
        Collection<Business> businesses = BusinessFactoryImpl.createMultipleBusiness(zones, numberOfPeople);
        assertEquals(numberOfPeople / BusinessConfig.BUSINESS_PERCENTAGE, businesses.size());
    }

    @Test
    void testCreateMultipleBusiness_NoZones() {
        List<Zone> emptyZones = new ArrayList<>();
        int numberOfPeople = 100;
        Collection<Business> businesses = BusinessFactoryImpl.createMultipleBusiness(emptyZones, numberOfPeople);
        assertTrue(businesses.isEmpty());
    }
}
