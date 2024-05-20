package unibo.citysimulation.model.business;

import java.util.List;

/**
 * The EmploymentOfficeManager class manages the hiring and firing of employees for businesses.
 */
public class EmployymentOfficeManager {
    private final List<Business> businesses;
    private final EmployymentOffice employymentOffice;

    /**
     * Constructs an EmploymentOfficeManager with the given list of businesses and employment office.
     * 
     * @param businesses The list of businesses to manage.
     * @param employymentOffice The employment office to interact with.
     */
    public EmployymentOfficeManager(final List<Business> businesses, final EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employymentOffice = employymentOffice;
    }

    /**
     * Handles the firing of employees for all businesses.
     * For each employee that should be fired, adds the person to the employment office's disoccupied people list
     * and fires the employee from their business.
     */
    public final void handleEmployeeFiring() {
        businesses.stream()
            .flatMap(business -> business.getEmployees().stream())
            .filter(this::shouldFire)
            .forEach(employee -> {
                employymentOffice.addDisoccupiedPerson(employee.getPerson());
                employee.getBusiness().fire(employee);
            });
    }

    /**
     * Handles the hiring of employees for all businesses.
     * For each business that can hire, hires employees from the employment office's disoccupied people list
     * until the maximum number of employees is reached for the business.
     */
    public final void handleEmployeeHiring() {
        businesses.stream()
            .filter(this::canHire)
            .forEach(business -> {
                employymentOffice.getDisoccupiedPeople().stream()
                    .limit(business.getMaxEmployees() - business.getEmployees().size())
                    .forEach(person -> {
                        business.hire(new Employee(person, business));
                        employymentOffice.removeDisoccupiedPerson(person);
                    });
            });
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
        return employee.getCountDelay() > employee.getBusiness().getMaxTardiness();
    }
}
