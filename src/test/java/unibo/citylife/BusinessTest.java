package unibo.citylife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.Employee;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.api.PersonData;
import unibo.citysimulation.model.person.impl.DynamicPersonImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneCreation;

public class BusinessTest {
    private Business business;
    private Employee employee1;
    private Employee employee2;
    private Zone zone;
    private Business.BusinessData businessData;

    @BeforeEach
    void setUp() {
        zone = ZoneCreation.createZonesFromFile().get(0);
        businessData = new Business.BusinessData(
                1,
                new ArrayList<>(),
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                100.0,
                5,
                zone.getRandomPosition(),
                18,
                65,
                3,
                zone
        );

        business = new Business(businessData) {};
        
        DynamicPerson person1 = new DynamicPersonImpl(
                new PersonData("Person1", 25, zone),
                100,
                Optional.of(business)
        );

        DynamicPerson person2 = new DynamicPersonImpl(
                new PersonData("Person2", 30, zone),
                100,
                Optional.of(business)
        );

        employee1 = new Employee(person1, businessData);
        employee2 = new Employee(person2, businessData);
    }

    @Test
    void testHire() {
        assertTrue(business.hire(employee1));
        assertTrue(business.hire(employee2));
        assertEquals(2, business.getBusinessData().employees().size());

        DynamicPerson person3 = new DynamicPersonImpl(
                new PersonData("Person3", 70, zone), 100, Optional.empty()
        );
        Employee employee3 = new Employee(person3, businessData);
        assertFalse(business.hire(employee3)); // Should fail due to age
    }

    @Test
    void testFire() {
        business.hire(employee1);
        business.hire(employee2);
        assertEquals(2, business.getBusinessData().employees().size());

        employee1.incrementDelayCount();
        employee1.incrementDelayCount();
        employee1.incrementDelayCount(); // Exceeds max tardiness
        business.fire(employee1);

        assertEquals(1, business.getBusinessData().employees().size());
        assertFalse(business.getBusinessData().employees().contains(employee1));
    }

    

    @Test
    void testCalculatePay() {
        double pay = business.calculatePay();
        assertEquals(800.0, pay); // 8 hours * 100.0 revenue
    }
}
