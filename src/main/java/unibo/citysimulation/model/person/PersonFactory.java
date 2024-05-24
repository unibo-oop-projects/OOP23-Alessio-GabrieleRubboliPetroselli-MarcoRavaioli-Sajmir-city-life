package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.Employee;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class PersonFactory {
    private static final Random random = new Random();

    public static List<List<DynamicPerson>> createAllPeople(int numberOfPeople, List<Zone> zones, List<Business> businesses) {
        return zones.stream()
                .map(zone -> createGroupOfPeople(
                        zones.indexOf(zone),
                        (int) (numberOfPeople * (zone.businessPercents() / 100.0)),
                        zone.wellfareMinMax(),
                        businesses,
                        zone))
                .collect(Collectors.toList());
    }

    private static List<DynamicPerson> createGroupOfPeople(int groupCounter, int numberOfPeople,
                                                       Pair<Integer, Integer> moneyMinMax, List<Business> businesses, Zone residenceZone) {
    List<DynamicPerson> people = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {

            List<Business> eligibleBusinesses = businesses.stream()
                    .filter(b -> !b.getZone().equals(residenceZone))
                    .collect(Collectors.toList());

            if (eligibleBusinesses.isEmpty()) {
                throw new IllegalStateException("No eligible businesses found for zone: " + residenceZone.name());
            }

            Business business = eligibleBusinesses.get(random.nextInt(eligibleBusinesses.size()));

            DynamicPerson person = createPerson("Person" + groupCounter + i, random.nextInt(60) + 18, business, residenceZone,
                    random.nextInt(moneyMinMax.getSecond() - moneyMinMax.getFirst()) + moneyMinMax.getFirst());
            people.add(person);

            business.hire(new Employee(person, business));
        }
        return people;
    }


    private static DynamicPerson createPerson(String name, int age, Business business, Zone residenceZone, int money) {
        Optional<Business> businessOptional = Optional.ofNullable(business);
        return new DynamicPersonImpl(new PersonData(name, age, businessOptional, residenceZone), money);
    }
}