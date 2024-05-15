package unibo.citysimulation.model.business;

import java.util.ArrayList;
import java.util.List;
import unibo.citysimulation.model.person.Person;

public class EmployymentOffice {

    List<Person> disoccupiedPeople;

    public EmployymentOffice() {
        this.disoccupiedPeople = new ArrayList<>();
    }

    public List<Person> getDisoccupiedPeople() {
        return disoccupiedPeople;
    }

    public void addDisoccupiedPerson(Person person) {
        this.disoccupiedPeople.add(person);
    }

    public void removeDisoccupiedPerson(Person person) {
        this.disoccupiedPeople.remove(person);
    }
    
}
