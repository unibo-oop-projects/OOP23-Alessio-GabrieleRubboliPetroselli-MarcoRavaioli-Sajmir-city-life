package unibo.citysimulation.model.business;

import java.util.List;

import unibo.citysimulation.model.person.Person;

public class EmployymentOfficeManager {
    private List<Business> businesses;
    private EmployymentOffice employymentOffice;

    public EmployymentOfficeManager(List<Business> businesses, EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employymentOffice = employymentOffice;
    }

    public void handleEmployeeFiring() {
        for (Business business : businesses) {
            for (Employee employee : business.getEmployees()) {
                business.fire(employee, business);
                employymentOffice.addDisoccupiedPerson(employee.getPerson());
            }
        }
    }

    public void handleEmployeeHiring() {
        for (Business business : businesses) {
            if (business.getEmployees().size() < business.getMaxEmployees()) {
                for (Person person : employymentOffice.getDisoccupiedPeople()) {
                    business.hire(new Employee(person), business);
                    employymentOffice.removeDisoccupiedPerson(person);
                }
            }
        }
    }
}
