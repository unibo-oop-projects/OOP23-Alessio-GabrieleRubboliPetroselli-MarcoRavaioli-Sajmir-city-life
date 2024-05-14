package unibo.citysimulation.model.business;

import unibo.citysimulation.model.zone.Zone;

public class BigBusiness extends Business {
    private static final int MAX_TARDINESS = 3; // Example maximum allowed tardiness for employees

    public BigBusiness(Zone zone) {
        super(zone);
    }

    @Override
    public void hire(Employee employee) {
        // Implement hire method for big business
    }

    @Override
    public void fire(Employee employee) {
        // Fire employee if they exceed maximum tardiness
        if (employee.getcountDelay() > MAX_TARDINESS) {
            employees.remove(employee);
        }
    }
}