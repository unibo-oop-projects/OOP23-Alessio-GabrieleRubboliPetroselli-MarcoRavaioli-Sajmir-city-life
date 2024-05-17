package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;
import unibo.citysimulation.model.person.DynamicPerson;

public class EmployymentOffice {

    List<DynamicPerson> disoccupiedPeople;

    public EmployymentOffice() {
        this.disoccupiedPeople = new ArrayList<>();
    }

    public List<DynamicPerson> getDisoccupiedPeople() {
        return disoccupiedPeople;
    }

    public void addDisoccupiedPerson(DynamicPerson person) {
        this.disoccupiedPeople.add(person);
    }

    public void removeDisoccupiedPerson(DynamicPerson person) {
        this.disoccupiedPeople.remove(person);
    }
    
}
