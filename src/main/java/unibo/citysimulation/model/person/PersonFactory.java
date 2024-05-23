package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PersonFactory {
    private static Random random = new Random();

    public static List<List<DynamicPerson>> createAllPeople(final int numberOfPeople, final List<Zone> zones,
            final List<Business> businesses) {
        return zones.stream()
                .map(zone -> PersonFactory.createGroupOfPeople(
                        zones.indexOf(zone),
                        (int) (numberOfPeople * (zone.businessPercents() / 100)),
                        zone.wellfareMinMax(),
                        businesses,
                        zone))
                .collect(Collectors.toList());
    }

    private static List<DynamicPerson> createGroupOfPeople(final int groupCounter, final int numberOfPeople,
            final Pair<Integer, Integer> moneyMinMax,
            final List<Business> businesses, final Zone residenceZone) {
        final List<DynamicPerson> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            // Filtra i business per escludere quelli nella stessa zona della residenza
            final List<Business> eligibleBusinesses = businesses.stream()
                    .filter(b -> !b.getZone().equals(residenceZone)) ///
                    .collect(Collectors.toList());

            if (eligibleBusinesses.isEmpty()) {
                throw new IllegalStateException("No eligible businesses found for zone: " + residenceZone.name());
            }

            final Business business = eligibleBusinesses.get(random.nextInt(eligibleBusinesses.size()));
            people.add(createPerson("Person" + groupCounter + i, random.nextInt(62) + 18, business, residenceZone,
                    random.nextInt(moneyMinMax.getSecond() - moneyMinMax.getFirst()) + moneyMinMax.getFirst()));
        }
        return people;
    }

    private static DynamicPerson createPerson(final String name, final int age, final Business business,
            final Zone residenceZone, final int money) {
        return new DynamicPersonImpl(new PersonData(name, age, business, residenceZone), money);
    }
}
