package unibo.citylife;

import org.junit.jupiter.api.Test;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonDataTest {
    private List<Zone> zones = ZoneFactory.createZonesFromFile();
    private List<Business> businesses = BusinessFactory.createBusinesses(zones);
    private Random random = new Random();
    

    @Test
    void testPersonDataConstructor() {
        String name = "John";
        int age = 30;
        Zone residenceZone = zones.get(random.nextInt(zones.size()));
        Business business = businesses.get(random.nextInt(businesses.size()));

        PersonData personData = new PersonData(name, age, business, residenceZone);

        assertNotNull(personData);
        assertEquals(name, personData.name());
        assertEquals(age, personData.age());
        assertEquals(business, personData.business());
        assertEquals(residenceZone, personData.residenceZone());
    }
}
