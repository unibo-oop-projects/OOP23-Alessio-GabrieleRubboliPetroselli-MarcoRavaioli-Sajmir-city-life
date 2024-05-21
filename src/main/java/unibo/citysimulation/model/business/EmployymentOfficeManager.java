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
    private final List<Business> businesses;
    private final EmployymentOffice employymentOffice;
    private Random random = new Random();

    /**
     * Constructs an EmploymentOfficeManager with the given list of businesses and employment office.
     * 
     * @param businesses The list of businesses to manage.
     * @param employymentOffice The employment office to interact with.
     */
    public EmployymentOfficeManager(final List<Business> businesses, final EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employymentOffice = employymentOffice;
        this.random = new Random();
    }

    /**
     * Handles the firing of employees for all businesses.
     * For each employee that should be fired, adds the person to the employment office's disoccupied people list
     * and fires the employee from their business.
     */
    /**
     * Handles the firing of employees for all businesses.
     * For each employee that should be fired, adds the person to the employment office's disoccupied people list
     * and fires the employee from their business.
     */
    public final void handleEmployeeFiring() {
        List<Employee> employeesToFire = getEmployeesToFire();

        fireEmployees(employeesToFire);
    }

    /**
     * Handles the hiring of employees for all businesses.
     * Selects a random business that can hire, and hires a random number of employees
     * from the employment office's disoccupied people list until the maximum number of employees
     * is reached for the business.
     */
    public final void handleEmployeeHiring() {
        List<Business> businessesThatCanHire = getBusinessesThatCanHire();
        System.out.println("Businesses that can hire: " + businessesThatCanHire.size());

        
        if (!businessesThatCanHire.isEmpty()) {
            Business randomBusiness = selectRandomBusiness(businessesThatCanHire);
            List<DynamicPerson> peopleToHire = getPeopleToHire(randomBusiness);
            System.out.println("People to hire: " + peopleToHire.size() + " for business " + randomBusiness.getRevenue());

            hirePeople(randomBusiness, peopleToHire);
        }
    }

    /**
     * Checks if a business can hire more employees.
     * 
     * @param business The business to check.
     * @return true if the business can hire more employees, false otherwise.
     */
    private final boolean canHire(final Business business) {
        return business.getEmployees().size() < business.getMaxEmployees();
    }

    /**
     * Checks if an employee should be fired based on their delay count.
     * 
     * @param employee The employee to check.
     * @return true if the employee should be fired, false otherwise.
     */
    private final boolean shouldFire(final Employee employee) {
        return employee != null && employee.getCountDelay() > employee.getBusiness().getMaxTardiness();
    }

    /**
     * Retrieves the list of businesses that can hire more employees.
     * 
     * @return the list of businesses that can hire.
     */
    private List<Business> getBusinessesThatCanHire() {
        return businesses.stream()
            .filter(this::canHire)
            .collect(Collectors.toList());
    }

    /**
     * Selects a random business from the list of businesses that can hire.
     * 
     * @param businessesThatCanHire the list of businesses that can hire.
     * @return a randomly selected business.
     */
    private Business selectRandomBusiness(List<Business> businessesThatCanHire) {
        return businessesThatCanHire.get(random.nextInt(businessesThatCanHire.size()));
    }

    /**
     * Retrieves a random number of disoccupied people to be hired by the business.
     * 
     * @param business the business that will hire the people.
     * @return the list of people to be hired.
     */
    private List<DynamicPerson> getPeopleToHire(Business business) {
        int availableSpots = business.getMaxEmployees() - business.getEmployees().size();
        List<DynamicPerson> disoccupiedPeople = employymentOffice.getDisoccupiedPeople();
        int maxPeopleToHire = Math.min(availableSpots, disoccupiedPeople.size());

        if (maxPeopleToHire > 0) {
            int peopleToHireCount = random.nextInt(maxPeopleToHire) + 1;
            return disoccupiedPeople.stream()
                .limit(peopleToHireCount)
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Hires the specified people for the given business and removes them from the employment office.
     * 
     * @param business the business that will hire the people.
     * @param peopleToHire the list of people to be hired.
     */
    private void hirePeople(Business business, List<DynamicPerson> peopleToHire) {
        peopleToHire.forEach(person -> {
            business.hire(new Employee(person, business));
            System.out.println("Hiring person: " + person.getPersonData().name() + " to business: " + business.getRevenue());

            employymentOffice.removeDisoccupiedPerson(person);
        });
    }

    /**
     * Retrieves a list of employees that should be fired.
     * 
     * @return the list of employees to be fired.
     */
    private List<Employee> getEmployeesToFire() {
        return businesses.stream()
            .flatMap(business -> business.getEmployees().stream())
            .filter(this::shouldFire)
            .collect(Collectors.toList());
    }

    /**
     * Fires the specified employees and adds them to the employment office's disoccupied people list.
     * 
     * @param employeesToFire the list of employees to be fired.
     */
    private void fireEmployees(List<Employee> employeesToFire) {
        employeesToFire.forEach(employee -> {
            employymentOffice.addDisoccupiedPerson(employee.getPerson());
            employee.getBusiness().fire(employee);
        });
    }
}
