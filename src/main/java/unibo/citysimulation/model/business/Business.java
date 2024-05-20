package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.zone.Zone;

/**
 * The abstract class representing a business in the city simulation.
 */
public abstract class Business implements BusinessEmployee{

    protected final List<Employee> employees;
    protected LocalTime opLocalTime;
    protected LocalTime clLocalTime;
    protected double revenue;
    protected int maxEmployees;
    private final Pair<Integer, Integer> position;
    protected int minAge;
    protected int maxAge;
    protected int maxTardiness;

    /**
     * Constructs a new Business object.
     */
    public Business() {
        this.employees = new ArrayList<>();
        this.position = Zone.getRandomZone().getRandomPosition();
    }

    /**
     * Hires an employee for the business.
     * 
     * @param employee the employee to hire
     */
    @Override
    public final void hire(final Employee employee) {
        if(employee.getPerson().getPersonData().age() >= this.minAge && employee.getPerson().getPersonData().age() <= this.maxAge) {
            if(employees.size() < getMaxEmployees()) {
                employees.add(employee);
            }
        }
    }
    
    /**
     * Fires an employee from the business.
     * 
     * @param employee the employee to fire
     */
    @Override
    public final void fire(final Employee employee) {
        if (employee.getCountDelay() > this.maxTardiness) {
            employees.remove(employee);
        }
    }

    /**
     * Checks the delays of all employees at the current time.
     * 
     * @param currentTime the current time
     */
    public void checkEmployeeDelays(LocalTime currentTime) {
        if (currentTime.equals(opLocalTime)) {
            for (Employee employee : employees) {
                if (employee.isLate(this.position)) {
                    employee.incrementDelayCount();
                }
            }
        }
    }

    /**
     * Returns the position of the business.
     * 
     * @return the position of the business
     */
    public final Pair<Integer, Integer> getPosition() {
        return position;
    }

    /**
     * Returns the list of employees in the business.
     * 
     * @return the list of employees
     */
    public final List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Returns the opening time of the business.
     * 
     * @return the opening time
     */
    public final LocalTime getOpLocalTime() {
        return opLocalTime;
    }

    /**
     * Returns the closing time of the business.
     * 
     * @return the closing time
     */
    public final LocalTime getClLocalTime() {
        return clLocalTime;
    }

    /**
     * Returns the maximum number of employees allowed in the business.
     * 
     * @return the maximum number of employees
     */
    public final int getMaxEmployees() {
        return maxEmployees;
    }

    /**
     * Returns the revenue of the business.
     * 
     * @return the revenue
     */
    public final double getRevenue() {
        return revenue;
    }

    /**
     * Returns the minimum age requirement for employees in the business.
     * 
     * @return the minimum age requirement
     */
    public final int getMinAge() {
        return minAge;
    }

    /**
     * Returns the maximum age requirement for employees in the business.
     * 
     * @return the maximum age requirement
     */
    public final int getMaxAge() {
        return maxAge;
    }

    /**
     * Returns the maximum tardiness allowed for employees in the business.
     * 
     * @return the maximum tardiness
     */
    public final int getMaxTardiness() {
        return maxTardiness;
    }
    
}

    

