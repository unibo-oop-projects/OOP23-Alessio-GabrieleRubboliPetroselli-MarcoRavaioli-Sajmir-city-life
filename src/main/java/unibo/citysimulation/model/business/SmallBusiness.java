
package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;


public class SmallBusiness extends Business{

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 30;
    private static final int MAX_TARDINESS = 8;

    public SmallBusiness(Zone zone) {
        super(zone);
    }

    @Override
    public void hire(Employee employee) {
        if (employee.getPerson().getAge() >= MIN_AGE && employee.getPerson().getAge() <= MAX_AGE){
            employees.add(employee);
        }
    }

    @Override
    public void fire(Employee employee) {
        if (employee.getcountDelay(employee) > MAX_TARDINESS){
            employees.remove(employee);
        }
    }
    
}
