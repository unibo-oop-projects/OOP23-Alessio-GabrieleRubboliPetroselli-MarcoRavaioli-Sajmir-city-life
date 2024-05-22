package unibo.citylife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessConfig;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.DynamicPersonImpl;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.person.StaticPerson;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;

public class EmployymentOfficeManagerTest {
    
    private Business business;
    private EmployymentOffice employymentOffice;
    private EmployymentOfficeManager employymentOfficeManager;
    private DynamicPersonImpl person;
    private Zone zone;

    @BeforeEach
    public void setup() {
        // Creiamo le zone dal file
        List<Zone> zones = ZoneFactory.createZonesFromFile();
        assertNotNull(zones);
        assertFalse(zones.isEmpty());
        zone = zones.get(0);

        // Creiamo un business di tipo SMALL utilizzando la factory
        business = BusinessFactory.createBusiness(BusinessType.SMALL, zone).orElseThrow();

        // Creiamo l'ufficio di impiego
        employymentOffice = new EmployymentOffice();

        // Creiamo un personData e un dynamicPerson
        PersonData personData = new PersonData("John Doe", 30, business, zone);
        person = new DynamicPersonImpl(personData, 100);

        // Aggiungiamo la persona disoccupata all'ufficio di impiego
        employymentOffice.addDisoccupiedPerson(person);

        // Creiamo una lista di businesses e passiamola all'EmployymentOfficeManager
        List<Business> businesses = Arrays.asList(business);
        employymentOfficeManager = new EmployymentOfficeManager(businesses, employymentOffice);
    }

    @Test
    public void testHandleEmployeeHiring() {
        // Eseguiamo il metodo da testare
        employymentOfficeManager.handleEmployeeHiring();

        // Verifichiamo che la persona sia stata assunta e rimossa dall'ufficio di impiego
        assertTrue(business.getEmployees().stream().anyMatch(e -> e.getPerson().equals(person)));
        assertFalse(employymentOffice.getDisoccupiedPeople().contains(person));
    }

    @Test
    public void testHandleEmployeeFiring() {
        // Assumiamo la persona
        business.hire(new Employee(person, business));

        // Configuriamo la persona per essere licenziata
        for (int i = 0; i <= business.getMaxTardiness(); i++) {
            person.incrementLastArrivingTime(1); // Simula ritardi per raggiungere il massimo consentito
        }

        // Eseguiamo il metodo da testare
        employymentOfficeManager.handleEmployeeFiring();

        // Verifichiamo che la persona sia stata licenziata e aggiunta all'ufficio di impiego
        assertTrue(employymentOffice.getDisoccupiedPeople().contains(person));
        assertFalse(business.getEmployees().stream().anyMatch(e -> e.getPerson().equals(person)));
    }
}
