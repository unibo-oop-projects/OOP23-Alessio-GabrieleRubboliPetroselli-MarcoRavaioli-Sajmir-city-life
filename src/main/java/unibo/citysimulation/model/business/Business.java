package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;

public abstract class Business implements BusinessEmployee{

    //private Zone zone = Zone.getRandomZone();
    protected List<Employee> employees;
    protected LocalTime opLocalTime;
    protected LocalTime clLocalTime;
    protected double revenue;
    protected int maxEmployees;
    

    public Business() {
        this.employees = new ArrayList<>();
        
  
    }

    //public Zone getZone() {
        //return zone;
    //}

    public List<Employee> getEmployees() {
        return employees;
    }

    public LocalTime getOpLocalTime() {
        return opLocalTime;
    }

    public LocalTime getClLocalTime() {
        return clLocalTime;
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public double getRevenue() {
        return revenue;
    }

    

    
}

    

