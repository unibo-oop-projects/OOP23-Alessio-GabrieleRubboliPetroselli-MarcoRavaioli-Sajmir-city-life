package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;

public abstract class Business implements BusinessEmployee{

    protected Zone zone;
    protected List<Employee> employees;
    LocalTime opLocalTime;
    LocalTime clLocalTime;

    public Business() {
        this.employees = new ArrayList<>();
        this.zone = zone.getRandomZone();
    }

    public Zone getZone() {
        return zone;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public LocalTime getOpLocalTime() {
        return opLocalTime;
    }

    public LocalTime getClLocalTime() {
        return clLocalTime;
    }

    

    
}

    

