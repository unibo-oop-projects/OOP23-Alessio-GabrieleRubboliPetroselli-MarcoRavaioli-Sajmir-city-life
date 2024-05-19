package unibo.citysimulation.model.business;

public class BigBusiness extends Business{

    public BigBusiness() {
        this.opLocalTime = BusinessConfig.BIG_OPENING_TIME;
        this.clLocalTime = BusinessConfig.BIG_CLOSING_TIME;
        this.revenue = BusinessConfig.BIG_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_BIG_BUSINESS;
    }

    @Override
    public void hire(Employee employee) {
        if(employee.getPerson().getAge() >= BusinessConfig.BIG_MIN_AGE && employee.getPerson().getAge() <= BusinessConfig.BIG_MAX_AGE) {
            if(employees.size() < getMaxEmployees()) {
                employees.add(employee);
            }
        }
    }

    @Override
    public void fire(Employee employee) {
        if (employee.getCountDelay(employee) > BusinessConfig.BIG_MAX_TARDINESS) {
            employees.remove(employee);
        }
    }
    
}
