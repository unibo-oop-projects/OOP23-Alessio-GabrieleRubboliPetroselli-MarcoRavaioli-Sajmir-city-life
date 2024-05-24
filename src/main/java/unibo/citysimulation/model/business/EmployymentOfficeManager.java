package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import unibo.citysimulation.model.person.DynamicPerson;

/**
 * The EmploymentOfficeManager class manages the hiring and firing of employees for businesses.
 */
public class EmployymentOfficeManager {

    private final EmployymentOffice employymentOffice;
    private final Random random;

    /**
     * Constructs an EmploymentOfficeManager with the given employment office.
     * 
     * @param employymentOffice The employment office to interact with.
     */
    public EmployymentOfficeManager(final EmployymentOffice employymentOffice) {
        this.employymentOffice = employymentOffice;
        this.random = new Random();
    }

    /**
     * Handles the firing of employees for the specified business.
     * For each employee that should be fired, adds the person to the employment office's disoccupied people list
     * and fires the employee from their business.
     * 
     * @param business The business for which to handle employee firing.
     */
    public final void handleEmployeeFiring(final Business business) {
        final List<Employee> employeesToFire = getEmployeesToFire(business);
        fireEmployees(employeesToFire);
    }

    /**
     * Handles the hiring of employees for the specified business.
     * Hires a minimum of 4 employees from the employment office's disoccupied people list 
     * or up to the maximum number of employees allowed for the business.
     * 
     * @param business The business for which to handle employee hiring.
     */
    public final void handleEmployeeHiring(final Business business) {
        if (canHire(business)) {
            final List<DynamicPerson> peopleToHire = getPeopleToHire(business);
            hirePeople(business, peopleToHire);
        }
    }

    /**
     * Checks if a business can hire more employees.
     * 
     * @param business The business to check.
     * @return true if the business can hire more employees, false otherwise.
     */
    private boolean canHire(final Business business) {
        
        boolean canHire = business.getEmployees().size() < business.getMaxEmployees();
        return canHire;    }

    /**
     * Checks if an employee should be fired based on their delay count.
     * 
     * @param employee The employee to check.
     * @return true if the employee should be fired, false otherwise.
     */
    private boolean shouldFire(final Employee employee) {
        return employee != null && employee.getCountDelay() > employee.getBusiness().getMaxTardiness();
    }

    /**
     * Retrieves a random number of disoccupied people to be hired by the business.
     * Filters out people who live in the same zone as the business.
     * 
     * @param business The business that will hire the people.
     * @return The list of people to be hired.
     */
    private List<DynamicPerson> getPeopleToHire(final Business business) {
        final int availableSpots = business.getMaxEmployees() - business.getEmployees().size();
        final List<DynamicPerson> disoccupiedPeople = employymentOffice.getDisoccupiedPeople();

        final List<DynamicPerson> eligiblePeople = disoccupiedPeople.stream()
            .filter(person -> !person.getPersonData().residenceZone().equals(business.getZone()))
            .collect(Collectors.toList());
        final int minPeopleToHire = Math.min(4, availableSpots); 
        final int maxPeopleToHire = Math.min(availableSpots, eligiblePeople.size());

        if (maxPeopleToHire >= minPeopleToHire) {
            final int peopleToHireCount = random.nextInt(maxPeopleToHire - minPeopleToHire + 1) + minPeopleToHire;
            return eligiblePeople.stream()
                .limit(peopleToHireCount)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Hires the specified people for the given business and removes them from the employment office.
     * 
     * @param business The business that will hire the people.
     * @param peopleToHire The list of people to be hired.
     */
    private void hirePeople(final Business business, final List<DynamicPerson> peopleToHire) {
        peopleToHire.forEach(person -> {
            business.hire(new Employee(person, business));
            employymentOffice.removeDisoccupiedPerson(person);
        });
    }

    /**
     * Retrieves a list of employees that should be fired from the specified business.
     * 
     * @param business The business to check for employees to fire.
     * @return The list of employees to be fired.
     */
    private List<Employee> getEmployeesToFire(final Business business) {
        return business.getEmployees().stream()
            .filter(this::shouldFire)
            .collect(Collectors.toList());
    }

    /**
     * Fires the specified employees and adds them to the employment office's disoccupied people list.
     * 
     * @param employeesToFire The list of employees to be fired.
     */
    private void fireEmployees(final List<Employee> employeesToFire) {
        employeesToFire.forEach(employee -> {
            employymentOffice.addDisoccupiedPerson(employee.getPerson());
            employee.getBusiness().fire(employee);
        });
    }
}
