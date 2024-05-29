package unibo.citysimulation.model.business.employye.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Optional;

import unibo.citysimulation.model.business.employye.api.HandleEmployye;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.api.DynamicPerson;

/**
 * The EmploymentOfficeManager class manages the hiring and firing of employees for businesses.
 */
public class EmployymentOfficeManager implements HandleEmployye {

    private final EmployymentOffice employymentOffice;
    private final Random random;
    private static final double FIRING_RATE = 0.1;

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
     * Randomly selects a number of employees to be fired, ensuring it is less than the number of people hired,
     * or up to 10% of the total personnel if no hires were made, adds the person to the employment office's disoccupied
     * people list and fires the employee from their business.
     * 
     * @param business The business for which to handle employee firing.
     * @param hiredCount The number of people hired previously.
     */
    @Override
    public final void handleEmployeeFiring(final Business business, final int hiredCount) {
        final List<Employee> employeesToFire = getEmployeesToFire(business);
        int maxToFire;
        if (hiredCount > 0) {
            maxToFire = Math.min(employeesToFire.size(), hiredCount - 1);
        } else {
            maxToFire = Math.max(1, (int) Math.floor(business.getEmployees().size() * FIRING_RATE));
        }
        if (maxToFire > 0) {
            final int numberToFire = random.nextInt(maxToFire) + 1;
            final List<Employee> selectedToFire = employeesToFire.stream()
                .limit(numberToFire)
                .collect(Collectors.toList());
            fireEmployees(selectedToFire);
        }
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
            return hirePeople(business, peopleToHire);
        }
        return 0;
    }

    /**
     * Checks if a business can hire more employees.
     * 
     * @param business The business to check.
     * @return true if the business can hire more employees, false otherwise.
     */
    private boolean canHire(final Business business) {
        return business.getEmployees().size() < business.getMaxEmployees();
    }

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
    private Optional<List<DynamicPerson>> getPeopleToHire(final Business business) {
        final int availableSpots = business.getMaxEmployees() - business.getEmployees().size();
        if (availableSpots > 0) {
            final List<DynamicPerson> disoccupiedPeople = employymentOffice.getDisoccupiedPeople();
            final List<DynamicPerson> eligiblePeople = disoccupiedPeople.stream()
                .filter(person -> !person.getPersonData().residenceZone().equals(business.getZone()))
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
                final Employee employee = new Employee(person, business);
                business.hire(employee);
                employymentOffice.removeDisoccupiedPerson(person);
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
    /**
     * Handles the payment for all employees in the given business.
     * Calculates the pay for each employee and adds it to their personal account.
     *
     * @param business the business for which the payment is being handled
     */
    @Override
    public final void handleEmployyePay(final Business business) {
        business.getEmployees().forEach(employee -> {
            final double pay = business.calculatePay();
            employee.getPerson().addMoney(pay);
        });
    }
}
