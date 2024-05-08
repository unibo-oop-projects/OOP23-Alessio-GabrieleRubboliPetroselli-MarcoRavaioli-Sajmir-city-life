package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonFactory {
    private static Random random = new Random();

    public static List<Person> createGroupOfPeople(int numberOfPeople, Pair<Integer, Integer> moneyMinMax,
    List<Business> businesses, Zone residenceZone, ZoneTable zoneTable) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            people.add(createPerson("Person" + i, random.nextInt(62) + 18,
            random.nextInt(moneyMinMax.getSecond() - moneyMinMax.getFirst()) + moneyMinMax.getFirst(),
            businesses.get(new Random().nextInt(businesses.size())), residenceZone, zoneTable));
        }
        return people;
    }

    private static Person createPerson(String name, int age, int money, Business business, Zone residenceZone, ZoneTable zoneTable) {
        return new PersonImpl(name, age, money, business, residenceZone, zoneTable);
    }
}

