package unibo.citysimulation.model.business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.utilities.Pair;
import unibo.citysimulation.model.zone.Zone;

public abstract class Business implements BusinessEmployee{

    
    protected List<Employee> employees;
    protected LocalTime opLocalTime;
    protected LocalTime clLocalTime;
    protected double revenue;
    protected int maxEmployees;
    private Pair<Integer, Integer> position;
    

    public Business() {
        this.employees = new ArrayList<>();
        position = Zone.getRandomZone().getRandomPosition();
        
  
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
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

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public double getRevenue() {
        return revenue;
    }

    

    
}

    

