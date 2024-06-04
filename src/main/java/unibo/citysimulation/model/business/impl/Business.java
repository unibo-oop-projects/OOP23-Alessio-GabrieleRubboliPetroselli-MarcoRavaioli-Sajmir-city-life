
package unibo.citysimulation.model.business.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.business.api.BusinessBehavior;
import unibo.citysimulation.model.zone.Zone;
/**
 * The abstract class representing a business in the city simulation.
 */
public abstract class Business implements BusinessBehavior {

    private final BusinessData businessData;

    public Business(BusinessData businessData) {
        this.businessData = Objects.requireNonNull(businessData);
    }

    public static record BusinessData( 
    List<Employee> employees,
    LocalTime opLocalTime,
    LocalTime clLocalTime,
    double revenue,
    int maxEmployees,
    Pair<Integer, Integer> position,
    int minAge,
    int maxAge,
    int maxTardiness,
    Zone zone) {
    }

    public BusinessData getBusinessData() {
        return businessData;
    }
    
    /**
     * Hires an employee for the business.
     * 
     * @param employee the employee to hire
     */
    @Override
    public final boolean hire(final Employee employee) {
        if (employee.person().getPersonData().age() >= businessData.minAge()
        && employee.person().getPersonData().age() <= businessData.maxAge() 
        && businessData.employees().size() < businessData.maxEmployees()) {
        businessData.employees().add(employee);
        return true;
    }
    return false;
    }

    /**
     * Fires an employee from the business.
     * 
     * @param employee the employee to fire
     */
    @Override
    public final void fire(final Employee employee) {
        if (employee != null && employee.count() > businessData.maxTardiness()) {
            businessData.employees.remove(employee);
        }
    }

    /**
     * Checks the delays of all employees at the current time.
     * 
     * @param currentTime the current time
     */
    @Override
    public void checkEmployeeDelays(final LocalTime currentTime) {
        if (currentTime.equals(businessData.opLocalTime())) {
        for (int i = 0; i < businessData.employees().size(); i++) {
            Employee employee = businessData.employees().get(i);
            if (employee.isLate(Optional.of(businessData.position()))) {
                Employee updatedEmployee = employee.incrementDelayCount();
                businessData.employees().set(i, updatedEmployee); // Replace the old employee with the updated one
            }
        }
    }
    }
    @Override
    public final double calculatePay() {
        final double hoursworked = businessData.clLocalTime().getHour() - businessData.opLocalTime.getHour();
        return hoursworked * businessData.revenue;
    }

    
}
