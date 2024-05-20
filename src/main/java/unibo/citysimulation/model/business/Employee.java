package unibo.citysimulation.model.business;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.utilities.Pair;

/**
 * The Employee class represents an employee with a name and an ID.
 * It implements the EmployeeStatus interface.
 */
public class Employee implements EmployeeStatus{
    
    private int countDelay;
    private final DynamicPerson person;
    private final Business business;

    /**
     * Constructs an Employee object with the specified person and business.
     * @param person The person associated with the employee.
     * @param business The business associated with the employee.
     */
    public Employee(final DynamicPerson person, final Business business){
        this.person = person;
        this.countDelay = 0;
        this.business = business;
    }

    public DynamicPerson getPerson() {
        return person;
    }

    /**
     * Returns the business associated with the employee.
     * @return The business associated with the employee.
     */
    public final Business getBusiness() {
        return business;
    }

    /**
     * Returns the count of delays for the employee.
     * @return The count of delays for the employee.
     */
    @Override
    public final int getCountDelay() {
        return countDelay;
    }

    /**
     * Sets the count of delays for the employee.
     * @param countDelay The count of delays for the employee.
     */
    @Override
    public final void setCountDelay(int countDelay) {
        this.countDelay = countDelay;
    }

    /**
     * Increments the count of delays for the employee by 1.
     */
    @Override
    public final void incrementDelayCount() {
        countDelay++;
    }

    /**
     * Checks if the employee is late based on the business position.
     * @param businessPosition The position of the business.
     * @return true if the employee is late, false otherwise.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Override
    public final boolean isLate(Pair<Integer, Integer> businessPosition) {
        return !this.person.getPosition().equals(businessPosition);
    }
}