package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.time.Duration;


/**
 * Implementation of the {@link Business} interface representing a business entity.
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
    private Pair<Integer,Integer> position;
    

    /**
     * Constructor for the BusinessImpl class.
     *
     * @param name The name of the business.
     * @param income The income of the business.
     * @param wageRate The wage rate of the business.
     * @param openingTime The opening time of the business.
     * @param closingTime The closing time of the business.
     * @param zone The zone where the business is located.
     * @param position The position of the business.
     */
    public BusinessImpl(String name, int income, double wageRate, LocalTime openingTime, LocalTime closingTime, Zone zone, Pair<Integer,Integer> position) {
        this.employees = new ArrayList<>();
        this.name = name;
        this.income = income;
        this.wageRate = wageRate;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.zone = zone;

        // This would be randomly assigned within the boundaries
        this.position = position;

    }

    @Override
    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public void fire(Employee employee) {
       this.employees.remove(employee);
    }

    @Override
    public boolean hasEmployees() {
        return !this.employees.isEmpty();
    }

    @Override
    public int countEmployees() {
        return this.employees.size();
    }

    @Override
    public Employee getEmployeeById(int id) {
        for (Employee employee : this.employees) {
            if (employee.getId() == id) {
                return (EmployeeImpl) employee;
            }
        }
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        for (Employee e : this.employees) {
            if (e.getId() == employee.getId()) {
                e = employee;
            }
        }
    }

    @Override
    public double calculateIncome() {
        int rate = this.employees.size() / DEFAULT_THRESHOLD;
        return this.income * Math.pow(2, rate);

    }

    @Override
    public double calculatePersonnelCost() {
        double totalCost = 0;
        for (Employee employee : this.employees) {
            totalCost += this.wageRate * getBusinessHours();
        }
        return totalCost;
    }

    @Override
    public double calculateProfit() {
        return calculateIncome() - calculatePersonnelCost();
    }

    @Override
    public boolean isProfit() {
        return calculateProfit() > DEFAULT_INCOME;
    }

    @Override
    public int getIncome() {
        return this.income;
    }

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(this.employees);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getBusinessHours() {
        Duration duration = Duration.between(this.openingTime, this.closingTime);
        return duration.toHours();
    }

    public LocalTime getOpeningTime() {
        return this.openingTime;
    }

    public LocalTime getClosingTime() {
        return this.closingTime;
    }

    public Zone getZone() {
        return this.zone;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return position;
    }

}
