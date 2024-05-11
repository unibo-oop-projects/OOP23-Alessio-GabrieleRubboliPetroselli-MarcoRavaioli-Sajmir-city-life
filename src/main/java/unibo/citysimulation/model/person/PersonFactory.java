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

    public static List<DynamicPerson> createGroupOfPeople(int numberOfPeople, Pair<Integer, Integer> moneyMinMax,
    List<Business> businesses, Zone residenceZone, ZoneTable zoneTable) {
        List<DynamicPerson> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            people.add(createPerson("Person" + i, random.nextInt(62) + 18,
            businesses.get(new Random().nextInt(businesses.size())), residenceZone,
            random.nextInt(moneyMinMax.getSecond() - moneyMinMax.getFirst()) + moneyMinMax.getFirst()));
            //people.get(i).getPersonData().business().hire(people.get(i));
        }
        return people;
    }

    private static DynamicPerson createPerson(String name, int age, Business business, Zone residenceZone, int money) {
        return new DynamicPersonImpl(new PersonData(name, age, business, residenceZone), money);
    }
}

