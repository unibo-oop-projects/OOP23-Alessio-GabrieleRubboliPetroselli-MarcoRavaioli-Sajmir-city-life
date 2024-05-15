package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;

public abstract class Business {
    
    protected List<Employee> employees;
    protected Zone zone;

    public Business(Zone zone) {
        this.zone = zone;
        this.employees = new ArrayList<>();
    }

    public abstract void hire(Employee employee);

    public abstract void fire(Employee employee);
}
    
