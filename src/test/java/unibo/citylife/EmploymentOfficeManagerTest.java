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

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the EmploymentOfficeManager class.
 */
public class EmploymentOfficeManagerTest {

    private static final int AGE = 25;
    private EmploymentOfficeManager employmentOfficeManager;
    private EmploymentOfficeData employmentOfficeData;
    private Optional<Business> business;
    private List<DynamicPerson> disoccupiedPeople;

    @BeforeEach
    final void setUp() {
        Zone zone;
        Zone zone2;
        zone = ZoneCreation.createZonesFromFile().get(0);
        zone2 = ZoneCreation.createZonesFromFile().get(1);
        disoccupiedPeople = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            disoccupiedPeople.add(new DynamicPersonImpl(
                    new PersonData("Person" + i, AGE, zone2),
                    100,
                    Optional.empty()));
        }
        employmentOfficeData = new EmploymentOfficeData(disoccupiedPeople);
        employmentOfficeManager = new EmploymentOfficeManager(employmentOfficeData);
        business = BusinessFactoryImpl.createBusiness(BusinessType.BIG, zone);
    }

    @Test
    void testHandleEmployeeFiring() {
        if (business.isPresent()) {
            business.get().hire(new Employee(disoccupiedPeople.get(0), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(1), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(2), business.get().getBusinessData()));
            business.get().getBusinessData().employees().get(0).incrementDelayCount();
            business.get().getBusinessData().employees().get(0).incrementDelayCount();
            business.get().getBusinessData().employees().get(0).incrementDelayCount();
        }
        final int hiredCount = business.get().getBusinessData().employees().size();
        employmentOfficeManager.handleEmployeeFiring(business.get());
        assertTrue(business.get().getBusinessData().employees().size() < hiredCount);
        assertTrue(employmentOfficeData.disoccupied().size() > 0);
    }

    @Test
    void testHandleEmployyePay() {
        if (business.isPresent()) {
            business.get().hire(new Employee(disoccupiedPeople.get(0), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(1), business.get().getBusinessData()));
            business.get().hire(new Employee(disoccupiedPeople.get(2), business.get().getBusinessData()));
        }
        final List<Double> initialMoney = new ArrayList<>();
        for (final Employee employee : business.get().getBusinessData().employees()) {
            initialMoney.add(employee.person().getMoney());
        }
        employmentOfficeManager.handleEmployyePay(business.get());
        for (int i = 0; i < business.get().getBusinessData().employees().size(); i++) {
            final Employee employee = business.get().getBusinessData().employees().get(i);
            assertTrue(employee.person().getMoney() > initialMoney.get(i));
        }
    }
}
