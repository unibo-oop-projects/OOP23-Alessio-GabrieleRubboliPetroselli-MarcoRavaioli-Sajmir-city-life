package unibo.citysimulation.model.clock.impl;

import unibo.citysimulation.model.business.employye.impl.EmployymentOffice;
import unibo.citysimulation.model.business.employye.impl.EmployymentOfficeManager;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.clock.api.ClockObserver;

import java.util.List;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * A ClockObserver implementation that handles business-related operations based on time updates.
 */
public class CloclObserverBusiness implements ClockObserver {
    private final List<Business> businesses;
    private final EmployymentOfficeManager employmentManager;
    private final Map<Business, Integer> businessHiredCountMap;

    /**
     * Constructs a CloclObserverBusiness object with the given list of businesses and employment office.
     * 
     * @param businesses the list of businesses
     * @param employymentOffice the employment office
     */
    public CloclObserverBusiness(final List<Business> businesses, final EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employmentManager = new EmployymentOfficeManager(employymentOffice);
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
            if (currentTime.equals(business.getOpLocalTime())) {
                final int hiredCount = employmentManager.handleEmployeeHiring(business);
                businessHiredCountMap.put(business, hiredCount);
            }
            if (currentTime.equals(business.getClLocalTime())) {
                final int hiredCount = businessHiredCountMap.getOrDefault(business, 0);
                employmentManager.handleEmployeeFiring(business, hiredCount);
                employmentManager.handleEmployyePay(business);
            }
        }
    }
}
