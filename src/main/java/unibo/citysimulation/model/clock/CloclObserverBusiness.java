package unibo.citysimulation.model.clock;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;
import unibo.citysimulation.model.person.Person;

import java.util.List;
import java.time.LocalTime;

public class CloclObserverBusiness implements ClockObserver{
    
    private static final int DEFAULT_DAY = 0;
    private static final int WEEK = 7;
    private List<Business> businesses;
    private int lastKnownDay = DEFAULT_DAY;

    private EmployymentOfficeManager employmentManager;
    
    public CloclObserverBusiness(List<Business> businesses, EmployymentOffice employymentOffice) {
        this.businesses = businesses;
        this.employmentManager = new EmployymentOfficeManager(businesses, employymentOffice);
    }
    
    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        if (currentDay != lastKnownDay) {
            employmentManager.handleEmployeeFiring();
            lastKnownDay = currentDay;
        }
        if (currentDay % WEEK == 0) {
            employmentManager.handleEmployeeHiring();
        }
        }
    }

