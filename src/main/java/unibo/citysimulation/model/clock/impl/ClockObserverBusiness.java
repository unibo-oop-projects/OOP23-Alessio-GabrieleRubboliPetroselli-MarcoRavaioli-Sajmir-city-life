package unibo.citysimulation.model.clock.impl;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.EmploymentOfficeManager;
import unibo.citysimulation.model.business.utilities.EmploymentOfficeData;
import unibo.citysimulation.model.clock.api.ClockObserver;

import java.util.List;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A ClockObserver implementation that handles business-related operations.
 */
public class ClockObserverBusiness implements ClockObserver {
    private final List<Business> businesses;
    private final EmploymentOfficeManager employmentManager;
    private final Map<Business, Integer> businessHiredCountMap;

    /**
     * Constructs a CloclObserverBusiness object with the given list of businesses and employment office.
     * 
     * @param businesses the list of businesses
     * @param employmentOffice the employment office
     */
    public ClockObserverBusiness(final List<Business> businesses, final EmploymentOfficeData employmentOffice) {
        this.businesses = Collections.unmodifiableList(businesses);
        this.employmentManager = new EmploymentOfficeManager(employmentOffice);
        this.businessHiredCountMap = new HashMap<>();
    }

    /**
     * Handles business operations based on the current time and day.
     * 
     * @param currentTime the current time
     * @param currentDay the current day
     */
    @Override
    public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
        for (final Business business : businesses) {
            business.checkEmployeeDelays(currentTime);
            if (currentTime.equals(business.getBusinessData().opLocalTime())) {
                final int hiredCount = employmentManager.handleEmployeeHiring(business);
                businessHiredCountMap.put(business, hiredCount);
            }
            if (currentTime.equals(business.getBusinessData().clLocalTime())) {
                employmentManager.handleEmployeeFiring(business);
                employmentManager.handleEmployyePay(business);
            }
        }
    }
}
