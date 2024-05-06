/*package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.person.PersonImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private ClockModel clock;
    private ZoneTable zoneTable;

    public PersonFactory(ClockModel clock, ZoneTable zoneTable) {
        this.clock = clock;
        this.zoneTable = zoneTable;
    }

    public List<Person> createGroupOfPeople(int numberOfPeople, int initialMoney, Business business, Zone residenceZone) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            people.add(createPerson(initialMoney, business, residenceZone));
        }
        return people;
    }

    public List<Person> createPersonsZone1(int numberOfPeople) {
        return createGroupOfPeople(numberOfPeople, initialMoney, Business.ZONE1, Zone.ZONE1);
    }

    private Person createPerson(int money, Business business, Zone residenceZone) {
        return new PersonImpl(money, business, residenceZone, clock, zoneTable);
    }
}*/

