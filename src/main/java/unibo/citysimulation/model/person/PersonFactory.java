package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.person.PersonImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonFactory {
    private ClockModel clock;
    private ZoneTable zoneTable;
    private Random random;

    public PersonFactory(ClockModel clock, ZoneTable zoneTable) {
        this.clock = clock;
        this.zoneTable = zoneTable;
        random = new Random();
    }

    public List<Person> createGroupOfPeople(int numberOfPeople, Pair<Integer, Integer> moneyMinMax, Business business, Zone residenceZone) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            people.add(createPerson("Person" + i, random.nextInt(100), random.nextInt(moneyMinMax.getSecond()) + moneyMinMax.getFirst(), business, residenceZone, clock, zoneTable));
        }
        return people;
    }

    private Person createPerson(String name, int age, int money, Business business, Zone residenceZone, ClockModel clock, ZoneTable zoneTable) {
        return new PersonImpl(name, age, money, business, residenceZone, clock, zoneTable);
    }
}

