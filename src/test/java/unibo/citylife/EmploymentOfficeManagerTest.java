package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.business.impl.Employee;
import unibo.citysimulation.model.business.impl.EmploymentOfficeManager;
import unibo.citysimulation.model.business.utilities.BusinessType;
import unibo.citysimulation.model.business.utilities.EmploymentOfficeData;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.api.PersonData;
import unibo.citysimulation.model.person.impl.DynamicPersonImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmploymentOfficeManagerTest {

    private EmploymentOfficeManager employmentOfficeManager;
    private EmploymentOfficeData employmentOfficeData;
    private Optional<Business> business;
    private List<DynamicPerson> disoccupiedPeople;
    private Zone zone;
    private Zone zone2;

    @BeforeEach
    void setUp() {
        zone = ZoneCreation.createZonesFromFile().get(0);
        zone2 = ZoneCreation.createZonesFromFile().get(1);
        disoccupiedPeople = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            disoccupiedPeople.add(new DynamicPersonImpl(
                    new PersonData("Person" + i, 25, zone2),
                    100,
                    Optional.empty()));
        }
        employmentOfficeData = new EmploymentOfficeData(disoccupiedPeople);
        employmentOfficeManager = new EmploymentOfficeManager(employmentOfficeData);
        business = BusinessFactoryImpl.createBusiness(BusinessType.BIG, zone);
    }
    @Test
    void testHandleEmployeeFiring() {
        // First, hire some people
        if(business.isPresent()) {
            business.get().hire(new Employee(disoccupiedPeople.get(0), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(1), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(2), business.get().getBusinessData()));
            business.get().getBusinessData().employees().get(0).incrementDelayCount();
            business.get().getBusinessData().employees().get(0).incrementDelayCount();
            business.get().getBusinessData().employees().get(0).incrementDelayCount();            
        }
        int hiredCount = business.get().getBusinessData().employees().size();
        employmentOfficeManager.handleEmployeeFiring(business.get());
        assertTrue(business.get().getBusinessData().employees().size() < hiredCount);
        assertTrue(employmentOfficeData.disoccupied().size() > 0);
    }

    @Test
    void testHandleEmployyePay() {
        if(business.isPresent()) {
            business.get().hire(new Employee(disoccupiedPeople.get(0), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(1), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(2), business.get().getBusinessData()));            
        }
        List<Double> initialMoney = new ArrayList<>();
        for (Employee employee : business.get().getBusinessData().employees()) {
            initialMoney.add(employee.person().getMoney());
        }
        employmentOfficeManager.handleEmployyePay(business.get());
        for (int i = 0; i < business.get().getBusinessData().employees().size(); i++) {
            Employee employee = business.get().getBusinessData().employees().get(i);
            assertTrue(employee.person().getMoney() > initialMoney.get(i));
        }
    }
}
