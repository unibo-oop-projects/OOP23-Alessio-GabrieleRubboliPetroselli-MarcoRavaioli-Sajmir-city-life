package unibo.citysimulation.model.business;

import unibo.citysimulation.model.person.DynamicPerson;



/**
 * EmployeeImpl is a class that implements the Employee interface.
 * It represents an employee with a name and an ID.
 */
public class Employee implements EmployeeStatus{
    
    private int countDelay;
    private DynamicPerson person;

    public Employee(DynamicPerson person){
        this.person = person;
        this.countDelay = 0;

    }

    public DynamicPerson getPerson() {
        return person;
    }

    @Override
    public int getcountDelay(Employee employee) {
        return employee.countDelay;
    }

    @Override
    public void setcountDelay(int countDelay) {
        this.countDelay = countDelay;
    }

}