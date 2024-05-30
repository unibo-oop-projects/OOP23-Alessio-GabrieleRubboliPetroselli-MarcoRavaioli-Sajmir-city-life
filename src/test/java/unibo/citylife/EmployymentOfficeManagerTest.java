/*package unibo.citylife;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;
import unibo.citysimulation.model.person.DynamicPersonImpl;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
public class EmployymentOfficeManagerTest {
    private Business business;
    private EmployymentOffice employymentOffice;
    private EmployymentOfficeManager employymentOfficeManager;
    private DynamicPersonImpl person;
    private Zone businessZone;
    private Zone differentZone;
    @BeforeEach
    public void setup() {
        // Create zones from file
        List<Zone> zones = ZoneFactory.createZonesFromFile();
        assertNotNull(zones);
        assertFalse(zones.isEmpty());
        businessZone = zones.get(0);
        differentZone = zones.get(1); // Ensure a different zone
        // Create a business of type SMALL using the factory
        business = BusinessFactory.createBusiness(BusinessType.SMALL, businessZone).orElseThrow();
        // Create the employment office
        employymentOffice = new EmployymentOffice();
        // Create a personData and a dynamicPerson
        PersonData personData = new PersonData("John Doe", 30, business, differentZone); // Set residence zone to differentZone
        person = new DynamicPersonImpl(personData, 100);
        // Add the unemployed person to the employment office
        employymentOffice.addDisoccupiedPerson(person);
        // Create a list of businesses and pass it to the EmployymentOfficeManager
        employymentOfficeManager = new EmployymentOfficeManager(employymentOffice);
    }
    @Test
    public void testHandleEmployeeHiring() {
        // Execute the method to be tested
        System.out.println("Starting testHandleEmployeeHiring...");
        employymentOfficeManager.handleEmployeeHiring(business);
        // Verify that the person was hired and removed from the employment office
        boolean isHired = business.getEmployees().stream().anyMatch(e -> e.getPerson().equals(person));
        boolean isStillUnemployed = employymentOffice.getDisoccupiedPeople().contains(person);
        System.out.println("Person hired: " + isHired);
        System.out.println("Person still unemployed: " + isStillUnemployed);
        assertTrue(isHired);
        assertFalse(isStillUnemployed);
    }
    @Test
    public void testHandleEmployeeHiringSameZone() {
        // Create a personData and a dynamicPerson living in the same zone as the business
        PersonData personDataSameZone = new PersonData("Jane Doe", 25, business, businessZone);
        DynamicPersonImpl personSameZone = new DynamicPersonImpl(personDataSameZone, 100);
        // Add the unemployed person to the employment office
        employymentOffice.addDisoccupiedPerson(personSameZone);
        // Execute the method to be tested
        System.out.println("Starting testHandleEmployeeHiringSameZone...");
        employymentOfficeManager.handleEmployeeHiring(business);
        // Verify that the person living in the same zone was not hired
        boolean isHired = business.getEmployees().stream().anyMatch(e -> e.getPerson().equals(personSameZone));
        boolean isStillUnemployed = employymentOffice.getDisoccupiedPeople().contains(personSameZone);
        System.out.println("Person living in same zone hired: " + isHired);
        System.out.println("Person living in same zone still unemployed: " + isStillUnemployed);
        assertFalse(isHired);
        assertTrue(isStillUnemployed);
    }
    @Test
    public void testHandleEmployeeFiring() {
        // Hire the person
        business.hire(new Employee(person, business));
        // Configure the person to be fired
        for (int i = 0; i <= business.getMaxTardiness(); i++) {
            person.incrementLastArrivingTime(1); // Simulate delays to reach the maximum allowed
        }
        // Execute the method to be tested
        System.out.println("Starting testHandleEmployeeFiring...");
        employymentOfficeManager.handleEmployeeFiring(business);
        // Verify that the person was fired and added to the employment office
        boolean isFired = employymentOffice.getDisoccupiedPeople().contains(person);
        boolean isStillEmployed = business.getEmployees().stream().anyMatch(e -> e.getPerson().equals(person));
        System.out.println("Person fired: " + isFired);
        System.out.println("Person still employed: " + isStillEmployed);
        assertTrue(isFired);
        assertFalse(isStillEmployed);
    }
}
*/

