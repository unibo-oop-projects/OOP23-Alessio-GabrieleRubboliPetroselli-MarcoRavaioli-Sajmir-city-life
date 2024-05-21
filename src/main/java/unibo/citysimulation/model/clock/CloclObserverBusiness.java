package unibo.citysimulation.model.clock;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;

import java.util.List;
import java.time.LocalTime;

/**
 * A ClockObserver implementation that handles business-related operations based on time updates.
 */
public class CloclObserverBusiness implements ClockObserver{
    
    private static final int DEFAULT_DAY = 0;
    private static final int WEEK = 7;
    private final List<Business> businesses;
    private int lastKnownDay = DEFAULT_DAY;

    private EmployymentOfficeManager employmentManager;
    
    /**
     * Constructs a CloclObserverBusiness object with the given list of businesses and employment office.
     * 
     * @param businesses the list of businesses
     * @param employymentOffice the employment office
     */
    public CloclObserverBusiness(final List<Business> businesses, final EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employmentManager = new EmployymentOfficeManager(businesses, employymentOffice);

        // Assumi dipendenti iniziali
        this.employmentManager.handleEmployeeHiring();
    }
    
    /**
     * Handles business operations based on the current time and day.
     * 
     * @param currentTime the current time
     * @param currentDay the current day
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime,final int currentDay) {
        if (currentDay != lastKnownDay) {
            employmentManager.handleEmployeeFiring();
            lastKnownDay = currentDay;
        }
        if (currentDay % WEEK == 0) {
            employmentManager.handleEmployeeHiring();
        }
        for (Business business : businesses) {
            business.checkEmployeeDelays(currentTime);
        }
    }
}

