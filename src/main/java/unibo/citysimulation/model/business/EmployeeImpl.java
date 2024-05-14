package unibo.citysimulation.model.business;
 
/**
 * EmployeeImpl is a class that implements the Employee interface.
 * It represents an employee with a name and an ID.
 */
public class EmployeeImpl implements Employee extends Person{
    
    
    private int countDelay;

    public EmployeeImpl(String name, int experiece) { 
        super(name, experience);
        this.countDelay = 0;
        
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getExperience() {
        return super.getExperience();
    }

    @Override
    public int getcountDelay() {
        return countDelay;
    }

    

}