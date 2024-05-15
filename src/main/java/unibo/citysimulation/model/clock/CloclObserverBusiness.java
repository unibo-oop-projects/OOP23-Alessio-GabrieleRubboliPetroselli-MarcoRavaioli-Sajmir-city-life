package unibo.citysimulation.model.clock;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.Employee;

import java.util.List;
import java.time.LocalTime;

public class CloclObserverBusiness implements ClockObserver{
    
    private List<Business> businesses;
    
    public CloclObserverBusiness(List<Business> businesses) {
        this.businesses = businesses;
    }
    
    @Override
    public void onTimeUpdate(LocalTime currentTime, int currentDay) {
        for (Business business : businesses) {
            for (Employee employee : business.getEmployees()) {
                business.fire(employee, business);
            }
        }
        
    }
}
