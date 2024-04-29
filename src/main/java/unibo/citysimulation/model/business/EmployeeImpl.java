package unibo.citysimulation.model.business;

/**
 * EmployeeImpl is a class that implements the Employee interface.
 * It represents an employee with a name and an ID.
 */
public class EmployeeImpl implements Employee{
    
    private String name;
    private int id;

    /**
     * Constructs a new EmployeeImpl with the given name and ID.
     *
     * @param name the name of the employee
     * @param id the ID of the employee
     */
    public EmployeeImpl(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Returns the name of the employee.
     *
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the employee.
     *
     * @return the ID of the employee
     */
    @Override
    public int getId() {
        return id;
    }
}
