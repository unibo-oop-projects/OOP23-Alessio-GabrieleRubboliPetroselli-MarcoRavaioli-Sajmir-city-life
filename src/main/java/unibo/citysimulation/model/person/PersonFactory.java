package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonFactory {
    private static Random random = new Random();

    public static List<List<DynamicPerson>> createAllPeople(int numberOfPeople, List<Zone> zones, List<Business> businesses) {
        int groupsCounter = 0;
        List<List<DynamicPerson>> people = new ArrayList<>();
        for (var zone : zones) {
            people.add(PersonFactory.createGroupOfPeople(groupsCounter, (int) (numberOfPeople * (zone.businessPercents()/100)),
            zone.wellfareMinMax(), businesses, zone));
            groupsCounter++;
        }
        return people;
    }

    private static List<DynamicPerson> createGroupOfPeople(int groupCounter, int numberOfPeople, Pair<Integer, Integer> moneyMinMax,
    List<Business> businesses, Zone residenceZone) {
        List<DynamicPerson> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            people.add(createPerson("Person" + groupCounter + i, random.nextInt(62) + 18,
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

