package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;

import java.time.Duration;

/**
 * This class represents a business entity with employees, income, wage rate, and operating hours.
 * It implements the Business interface.
 */
public class BusinessImpl implements Business {

    private static final int DEFAULT_THRESHOLD = 1000;

    private List<Employee> employees;
    private String name;
    private int income;
    private double wageRate;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private Zone zone;
    

    public BusinessImpl(String name, int income, double wageRate, LocalTime openingTime, LocalTime closingTime, Zone zone) {
        this.employees = new ArrayList<>();
        this.name = name;
        this.income = income;
        this.wageRate = wageRate;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.zone = zone;
    }

    /**
     * Hires a new employee.
     *
     * @param employee the employee to hire
     */
    @Override
    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    /**
     * Fires an employee.
     *
     * @param employee the employee to fire
     */
    @Override
    public void fire(Employee employee) {
        this.employees.remove(employee);
    }

    /**
     * Checks if the business has any employees.
     *
     * @return true if the business has employees, false otherwise
     */
    @Override
    public boolean hasEmployees() {
        return !this.employees.isEmpty();
    }

    /**
     * Returns the number of employees in the business.
     *
     * @return the number of employees
     */
    @Override
    public int countEmployees() {
        return this.employees.size();
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee
     * @return the employee with the given ID, or null if not found
     */
    @Override
    public Employee getEmployeeById(int id) {
        for (Employee employee : this.employees) {
            if (employee.getId() == id) {
                return (EmployeeImpl) employee;
            }
        }
        return null;
    }

    /**
     * Updates an employee's information.
     *
     * @param employee the updated employee
     */
    @Override
    public void updateEmployee(Employee employee) {
        for (Employee e : this.employees) {
            if (e.getId() == employee.getId()) {
                e = employee;
            }
        }
    }

    /**
     * Calculates the income of the business.
     *
     * @return the income of the business
     */
    @Override
    public double calculateIncome() {
        int rate = this.employees.size() / DEFAULT_THRESHOLD;
        return this.income * Math.pow(2, rate);
    }

    /**
     * Calculates the total personnel cost of the business.
     *
     * @return the total personnel cost
     */
    @Override
    public double calculatePersonnelCost() {
        double totalCost = 0;
        for (Employee employee : this.employees) {
            totalCost += this.wageRate * getBusinessHours();
        }
        return totalCost;
    }

    /**
     * Calculates the profit of the business.
     *
     * @return the profit of the business
     */
    @Override
    public double calculateProfit() {
        return calculateIncome() - calculatePersonnelCost();
    }

    /**
     * Checks if the business is profitable.
     *
     * @return true if the business is profitable, false otherwise
     */
    @Override
    public boolean isProfit() {
        return calculateProfit() > DEFAULT_INCOME;
    }

    /**
     * Returns the income of the business.
     *
     * @return the income of the business
     */
    @Override
    public int getIncome() {
        return this.income;
    }

    /**
     * Retrieves the list of employees.
     *
     * @return a list of employees
     */
    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees);
    }

    /**
     * Returns the name of the business.
     *
     * @return the name of the business
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the duration of business hours.
     *
     * @return the duration of business hours
     */
    @Override
    public Long getBusinessHours() {
        Duration duration = Duration.between(this.openingTime, this.closingTime);
        return duration.toHours();
    }

    /**
     * Returns the opening time of the business.
     *
     * @return the opening time
     */
    public LocalTime getOpeningTime() {
        return this.openingTime;
    }

    /**
     * Returns the closing time of the business.
     *
     * @return the closing time
     */
    public LocalTime getClosingTime() {
        return this.closingTime;
    }

    public Zone getZone() {
        return this.zone;
    }

}
