package unibo.citylife;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.person.StaticPerson;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;

public class EmployymentOfficeManagerTest {
    
    private EmployymentOffice employymentOffice;
    private List<Business> businesses;
    private EmployymentOfficeManager employymentOfficeManager;
    List<Zone> zones = new ArrayList<>();
    List<List<DynamicPerson>> people;
    Zone zone = ZoneFactory.createZonesFromFile().get(1);
    Zone zone2 = ZoneFactory.createZonesFromFile().get(2);
    Optional<Business> business = BusinessFactory.createBusiness(BusinessType.BIG, zone2);

    @BeforeEach
    public void setup() {
        employymentOffice = new EmployymentOffice();
        businesses = new ArrayList<>();
        zones.add(zone);
        employymentOfficeManager = new EmployymentOfficeManager(businesses, employymentOffice);
        businesses.add(business.get());
        people = new ArrayList<>();
        people = PersonFactory.createAllPeople(20, zones, businesses);
        // Aggiungiamo persone disoccupate
        for (int i = 0; i < 10; i++) {
            employymentOffice.addDisoccupiedPerson(people.get(0).get(0));
        }
    }

    @Test
    public void testHandleEmployeeHiring() {
        employymentOfficeManager.handleEmployeeHiring();
        Business business = businesses.get(0);
        assertFalse(business.getEmployees().isEmpty(), "Business should have hired employees");
        assertTrue(employymentOffice.getDisoccupiedPeople().size() < 10, "Employment office should have fewer disoccupied people");
    }

    @Test
    public void testHandleEmployeeFiring() {
        employymentOfficeManager.handleEmployeeHiring();
        Business business = businesses.get(0);
        business.getEmployees().forEach(employee -> employee.setCountDelay(5)); // Simuliamo ritardi
        employymentOfficeManager.handleEmployeeFiring();
        assertTrue(business.getEmployees().isEmpty(), "Business should have fired all employees due to delays");
    }
}
