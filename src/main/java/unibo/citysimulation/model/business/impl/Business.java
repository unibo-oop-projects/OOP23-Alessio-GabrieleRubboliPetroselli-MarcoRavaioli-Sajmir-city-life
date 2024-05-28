package unibo.citysimulation.model.business.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.business.api.BusinessEmployee;
import unibo.citysimulation.model.business.employye.impl.Employee;
import unibo.citysimulation.model.zone.Zone;
/**
 * The abstract class representing a business in the city simulation.
 */
public abstract class Business implements BusinessEmployee {

    private final List<Employee> employees;
    private LocalTime opLocalTime;
    private LocalTime clLocalTime;
    private double revenue;
    private int maxEmployees;
    private final Pair<Integer, Integer> position;
    private int minAge;
    private int maxAge;
    private int maxTardiness;
    private Zone zone;

    /**
     * Constructs a new Business object.
     * 
     * @param zone the zone in which the business is located
     */
    public Business(final Zone zone) {
        this.employees = new ArrayList<>();
        this.zone = zone;
        this.position = zone.getRandomPosition();
    }

    /**
     * Hires an employee for the business.
     * 
     * @param employee the employee to hire
     */
    @Override
    public final void hire(final Employee employee) {
        if (employee.getPerson().getPersonData().age() >= this.minAge 
           && employee.getPerson().getPersonData().age() <= this.maxAge 
           && employees.size() < getMaxEmployees()) {
            employees.add(employee);
        }
    }

    /**
     * Fires an employee from the business.
     * 
     * @param employee the employee to fire
     */
    @Override
    public final void fire(final Employee employee) {
        if (employee != null && employee.getCountDelay() > this.maxTardiness) {
            employees.remove(employee);
        }
    }

    /**
     * Checks the delays of all employees at the current time.
     * 
     * @param currentTime the current time
     */
    @Override
    public void checkEmployeeDelays(final LocalTime currentTime) {
        if (currentTime.equals(opLocalTime)) {
            for (final Employee employee : employees) {
                if (employee.isLate(this.position)) {
                    employee.incrementDelayCount();
                }
            }
        }
    }
    @Override
    public final double calculatePay(){
        final double hoursworked = clLocalTime.getHour() - opLocalTime.getHour();
        return hoursworked * revenue;
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
        return employees != null ? employees : new ArrayList<>();
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
     * Sets the opening time of the business.
     * 
     * @param opLocalTime the opening time to set
     */
    public final void setOpLocalTime(final LocalTime opLocalTime) {
        this.opLocalTime = opLocalTime;
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
     * Sets the closing time of the business.
     * 
     * @param clLocalTime the closing time to set
     */
    public final void setClLocalTime(final LocalTime clLocalTime) {
        this.clLocalTime = clLocalTime;
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
     * Sets the maximum number of employees allowed in the business.
     * 
     * @param maxEmployees the maximum number of employees to set
     */
    public final void setMaxEmployees(final int maxEmployees) {
        this.maxEmployees = maxEmployees;
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
     * Sets the revenue of the business.
     * 
     * @param revenue the revenue to set
     */
    public final void setRevenue(final double revenue) {
        this.revenue = revenue;
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
     * Sets the minimum age requirement for employees in the business.
     * 
     * @param minAge the minimum age requirement to set
     */
    public final void setMinAge(final int minAge) {
        this.minAge = minAge;
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
     * Sets the maximum age requirement for employees in the business.
     * 
     * @param maxAge the maximum age requirement to set
     */
    public final void setMaxAge(final int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * Returns the maximum tardiness allowed for employees in the business.
     * 
     * @return the maximum tardiness
     */
    public final int getMaxTardiness() {
        return maxTardiness;
    }

    /**
     * Sets the maximum tardiness allowed for employees in the business.
     * 
     * @param maxTardiness the maximum tardiness to set
     */
    public final void setMaxTardiness(final int maxTardiness) {
        this.maxTardiness = maxTardiness;
    }

    /**
     * Returns the zone in which the business is located.
     * 
     * @return the zone
     */
    public final Zone getZone() {
        return zone;
    }

    /**
     * Sets the zone in which the business is located.
     * 
     * @param zone the zone to set
     */
    public final void setZone(final Zone zone) {
        this.zone = zone;
    }    

}
