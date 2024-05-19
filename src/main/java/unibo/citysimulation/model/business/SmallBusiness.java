package unibo.citysimulation.model.business;

public class SmallBusiness extends Business{

    public SmallBusiness() {
        this.opLocalTime = BusinessConfig.SMALL_OPENING_TIME;
        this.clLocalTime = BusinessConfig.SMALL_CLOSING_TIME;
        this.revenue = BusinessConfig.SMALL_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_SMALL_BUSINESS;
    }

    @Override
    public void hire(Employee employee) {
        if(employee.getPerson().getAge() >= BusinessConfig.SMALL_MIN_AGE && employee.getPerson().getAge() <= BusinessConfig.SMALL_MAX_AGE) {
            if(employees.size() < maxEmployees) {
                employees.add(employee);
            }
        }
    }

    @Override
    public void fire(Employee employee) {
        if (employee.getCountDelay(employee) > BusinessConfig.SMALL_MAX_TARDINESS) {
            employees.remove(employee);
        }
    }
    
}
