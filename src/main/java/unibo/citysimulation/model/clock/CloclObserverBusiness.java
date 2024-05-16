package unibo.citysimulation.model.clock;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.person.Person;

import java.util.List;
import java.time.LocalTime;

public class CloclObserverBusiness implements ClockObserver{
    
    private static final int DEFAULT_DAY = 0;
    private static final int WEEK = 7;
    private List<Business> businesses;
    private int lastKnownDay = DEFAULT_DAY;
    
    public CloclObserverBusiness(List<Business> businesses) {
        this.businesses = businesses;
    }
    
    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        if(currentDay != lastKnownDay){
        for (Business business : businesses) {
            for (Employee employee : business.getEmployees()) {
                business.fire(employee, business);
            }
        }
        }
    lastKnownDay = currentDay;
        if (currentDay % WEEK == 0) {
        for (Business business : businesses) {
            if(business.getEmployees().size() < business.getMaxEmployees()){
            for (Person person : disoccupiedPeople){
                business.hire(new Employee(person), business);
            }
            }
        }
        }
        }
    }

