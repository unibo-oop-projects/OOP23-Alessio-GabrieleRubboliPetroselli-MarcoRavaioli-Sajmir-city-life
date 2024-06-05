package unibo.citysimulation.model.business.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Optional;

import unibo.citysimulation.model.business.api.EmploymentOfficeBehavior;
import unibo.citysimulation.model.business.utilities.EmploymentOfficeData;
import unibo.citysimulation.model.person.api.DynamicPerson;

/**
 * The EmploymentOfficeManager class manages the hiring and firing of employees for businesses.
 */
public class EmploymentOfficeManager implements EmploymentOfficeBehavior {

    private final EmploymentOfficeData employmentOffice;
    private final Random random;
    private static final int ZERO = 0;
    private static final LocalTime TIME_ZERO = LocalTime.of(ZERO, ZERO);

    /**
     * Constructs an EmploymentOfficeManager with the given employment office.
     * 
     * @param employymentOffice The employment office to interact with.
     */
    public EmploymentOfficeManager(final EmploymentOfficeData employymentOffice) {
        this.employmentOffice = employymentOffice;
        this.random = new Random();
    }

    /**
     * Handles the firing of employees for the specified business.
     * Randomly selects a number of employees to be fired, ensuring it is less than the number of people hired,
     * or up to 10% of the total personnel if no hires were made, adds the person to the employment office's disoccupied
     * people list and fires the employee from their business.
     * 
     * @param business The business for which to handle employee firing.
     * @param hiredCount The number of people hired previously.
     */
    @Override
    public final void handleEmployeeFiring(final Business business) {
        final List<Employee> employeesToFire = getEmployeesToFire(business);
        fireEmployees(business, employeesToFire);
    }
    /**
     * Handles the hiring of employees for the specified business.
     * Randomly selects a number of employees to hire from the employment office's disoccupied people list 
     * or up to the maximum number of employees allowed for the business.
     * 
     * @param business The business for which to handle employee hiring.
     * @return The number of people hired.
     */
    @Override
    public final int handleEmployeeHiring(final Business business) {
        if (canHire(business)) {
            final Optional<List<DynamicPerson>> peopleToHire = getPeopleToHire(business);
            hirePeople(business, peopleToHire);
        }
        return ZERO;
    }

    /**
     * Checks if a business can hire more employees.
     * 
     * @param business The business to check.
     * @return true if the business can hire more employees, false otherwise.
     */
    private boolean canHire(final Business business) {
        return business.getBusinessData().employees().size() < business.getBusinessData().maxEmployees();
    }

    /**
     * Checks if an employee should be fired based on their delay count.
     * 
     * @param employee The employee to check.
     * @return true if the employee should be fired, false otherwise.
     */
    private boolean shouldFire(final Employee employee) {
        return employee != null && employee.count() > employee.businessData().maxTardiness();
    }

    /**
     * Retrieves a random number of disoccupied people to be hired by the business.
     * Filters out people who live in the same zone as the business.
     * 
     * @param business The business that will hire the people.
     * @return The list of people to be hired.
     */
    private Optional<List<DynamicPerson>> getPeopleToHire(final Business business) {
        final int availableSpots = business.getBusinessData().maxEmployees() - business.getBusinessData().employees().size();
        if (availableSpots > 0) {
            final List<DynamicPerson> disoccupiedPeople = employmentOffice.disoccupied();
            final List<DynamicPerson> eligiblePeople = disoccupiedPeople.stream()
                .filter(person -> !person.getPersonData().residenceZone().equals(business.getBusinessData().zone()))
                .collect(Collectors.toList());
            final int maxPeopleToHire = Math.min(availableSpots, eligiblePeople.size());
            if (maxPeopleToHire > 0) {
                final int peopleToHireCount = random.nextInt(maxPeopleToHire) + 1; 
                return Optional.of(eligiblePeople.stream()
                    .limit(peopleToHireCount)
                    .collect(Collectors.toList()));
            }
        }
        return Optional.empty();
    }

    /**
     * Hires the specified people for the given business and removes them from the employment office.
     * 
     * @param business The business that will hire the people.
     * @param peopleToHire The list of people to be hired.
     * @return The number of people hired.
     */
    private int hirePeople(final Business business, final Optional<List<DynamicPerson>> peopleToHire) {
        if (peopleToHire.isPresent()) {
            final List<DynamicPerson> people = peopleToHire.get();
            people.forEach(person -> {
                final Employee employee = new Employee(person, business.getBusinessData());
                business.hire(employee);
                employmentOffice.disoccupied().remove(person);
                person.setBusiness(Optional.of(business));
            });
            return people.size();
        }
        return 0;
    }
    
    /**
     * Retrieves a list of employees that should be fired from the specified business.
     * 
     * @param business The business to check for employees to fire.
     * @return The list of employees to be fired.
     */
    private List<Employee> getEmployeesToFire(final Business business) {
        return business.getBusinessData().employees().stream()
            .filter(this::shouldFire)
            .collect(Collectors.toList());
    }

    /**
     * Fires the specified employees and adds them to the employment office's disoccupied people list.
     * 
     * @param employeesToFire The list of employees to be fired.
     * @param business The business that will fire the employees.
     */
    private void fireEmployees(final Business business, final List<Employee> employeesToFire) {
        employeesToFire.forEach(employee -> {
            employmentOffice.disoccupied().add(employee.person());
            business.fire(employee);
            employee.person().setBusiness(Optional.empty());
            employee.person().setBusinessBegin(TIME_ZERO);
            employee.person().setBusinessEnd(TIME_ZERO);
        });
    }
    /**
     * Handles the payment for all employees in the given business.
     * Calculates the pay for each employee and adds it to their personal account.
     *
     * @param business the business for which the payment is being handled
     */
    @Override
    public final void handleEmployyePay(final Business business) {
        business.getBusinessData().employees().forEach(employee -> {
            final double pay = business.calculatePay();
            employee.person().addMoney(pay);
        });
    }
}
