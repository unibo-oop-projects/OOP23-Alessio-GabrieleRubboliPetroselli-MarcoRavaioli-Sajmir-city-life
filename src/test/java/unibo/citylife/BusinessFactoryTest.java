package unibo.citylife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.person.PersonData;
import unibo.citysimulation.model.person.PersonImpl;

public class BusinessFactoryTest {
    
    @Test
    public void testCreateBigBusiness() {
        //arrange
        BusinessFactory factory = new BusinessFactory();
        PersonImpl person = new PersonImpl("bho", 0);
        PersonImpl person2 = new PersonImpl("ciao", 40);

        Employee employee = new Employee(person2);
        employee.setcountDelay(10);
        
        //act
        BusinessType type = BusinessType.BIG;

        Business business = factory.createBusiness(type).get();

        // Verifica che il numero massimo di dipendenti sia corretto
        assertEquals(25.0, business.getRevenue());
        
        // Verifica che il business sia stato creato correttamente
        assertNotNull(business);

        //Verifica che il numero massimo di dipendenti sia corretto
        assertEquals(300, business.getMaxEmployees());

        //Verifica che l'orario di apertura sia corretto    
        assertEquals(12, business.getOpLocalTime().getHour());
        assertEquals(0, business.getOpLocalTime().getMinute());

        //Verifica che l'orario di chiusura sia corretto
        assertEquals(21, business.getClLocalTime().getHour());
        assertEquals(0, business.getClLocalTime().getMinute());

        //Verifica assunzione di dipendenti
        assertEquals(0, business.getEmployees().size());

        //Verifica HIRE di dipendenti
        business.hire(new Employee(person), business);

        //Verifica che il numero di dipendenti sia corretto
        assertEquals(0, business.getEmployees().size());

        //Verifica HIRE di dipendenti
        business.hire(employee, business);

        //Verifica che il numero di dipendenti sia corretto
        assertEquals(1, business.getEmployees().size());

        //Verifica FIRE di dipendenti
        business.fire(employee, business);

        //Verifica che il numero di dipendenti sia corretto
        assertEquals(0, business.getEmployees().size());

    }
}
