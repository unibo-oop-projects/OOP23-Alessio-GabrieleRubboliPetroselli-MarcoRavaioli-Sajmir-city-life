package unibo.citylife;

import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.person.api.PersonData;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;

import java.util.List;
import java.util.Random;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonDataTest {
    private final List<Zone> zones = ZoneFactory.createZonesFromFile();
    private final Random random = new Random();
    @Test
    void testPersonDataConstructor() {
        final String name = "John";
        final int age = 30;
        final Zone residenceZone = zones.get(random.nextInt(zones.size()));
        final Business business = BusinessFactoryImpl.createRandomBusiness(zones).get();
        final PersonData personData = new PersonData(name, age, Optional.of(business), residenceZone);

        assertNotNull(personData);
        assertEquals(name, personData.name());
        assertEquals(age, personData.age());
        assertEquals(business, personData.business().get());
        assertEquals(residenceZone, personData.residenceZone());
    }
}
