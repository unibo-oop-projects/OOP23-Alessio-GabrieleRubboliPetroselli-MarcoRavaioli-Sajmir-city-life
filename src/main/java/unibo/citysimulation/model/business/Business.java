package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;

import unibo.citysimulation.model.zone.Zone;

public abstract class Business implements BusinessEmployee{

   // protected Hash code; // per il nome
    protected Zone zone;
    protected List<Employee> employees;

    public Business() {
        this.employees = new ArrayList<>();
     //   this.code = hashCode();
        this.zone = getZone();
    }

  /*   public String getName() {
        return name;
    }
    */

    public Zone getZone() {
        return zone;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    
}

    

