package unibo.citysimulation.model.business;

import unibo.citysimulation.model.person.Person;



/**
 * EmployeeImpl is a class that implements the Employee interface.
 * It represents an employee with a name and an ID.
 */
public class Employee implements EmployeeStatus{
    
    private int countDelay;
    private Person person;

    public Employee(Person person){
        this.person = person;
        this.countDelay = 0;

    }

    public Person getPerson() {
        return person;
    }

    @Override
    public int getCountDelay(Employee employee) {
        return employee.countDelay;
    }

    @Override
    public void setCountDelay(int countDelay) {
        this.countDelay = countDelay;
    }

}