package unibo.citysimulation.model.business;
  
public class MediumBusiness extends Business{

    public MediumBusiness() {
        this.opLocalTime = BusinessConfig.MEDIUM_OPENING_TIME;
        this.clLocalTime = BusinessConfig.MEDIUM_CLOSING_TIME;
        this.revenue = BusinessConfig.MEDIUM_REVENUE;
        this.maxEmployees = BusinessConfig.MAX_EMPLOYEES_MEDIUM_BUSINESS;
    }

    @Override
    public void hire(Employee employee) {
        if(employee.getPerson().getAge() >= BusinessConfig.MEDIUM_MIN_AGE && employee.getPerson().getAge() <= BusinessConfig.MEDIUM_MAX_AGE) {
            if(employees.size() < getMaxEmployees()) {
                employees.add(employee);
            }
        }
    }

    @Override
    public void fire(Employee employee) {
        if (employee.getCountDelay(employee) > BusinessConfig.MEDIUM_MAX_TARDINESS) {
            employees.remove(employee);
        }
    }
    
}
