package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;

public class BigBusiness extends Business {
    private static final int MAX_TARDINESS = 3; // Example maximum allowed tardiness for employees
    private static final int MIN_AGE = 25;

    public BigBusiness(Zone zone) {
        super(zone);
    }

    @Override
    public void hire(Employee employee) {
        // Implement hire method for big business
       if(employee.getPerson().getAge() > MIN_AGE) {
           employees.add(employee);
       }
        
    }

    @Override
    public void fire(Employee employee) {
        // Fire employee if they exceed maximum tardiness
        if (employee.getcountDelay(employee) > MAX_TARDINESS) {
            employees.remove(employee);
        }
    }
}