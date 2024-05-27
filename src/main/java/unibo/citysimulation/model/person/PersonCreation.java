package unibo.citysimulation.model.person;

import unibo.citysimulation.model.business.employye.impl.Employee;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Optional;


/**
 * The PersonCreation class is responsible for creating instances of
 * DynamicPerson objects.
 */
public final class PersonCreation {
    private static Random random = new Random();

    private PersonCreation() {
    }

    /**
     * Creates a list of people based on the specified parameters.
     *
     * @param numberOfPeople The total number of people to create, given in input.
     * @param zones          The list of available zones.
     * @param businesses     The list of available businesses.
     * @return A list of lists of DynamicPerson objects for every zone.
     */
    public static List<List<DynamicPerson>> createAllPeople(final int numberOfPeople, final List<Zone> zones,
            final List<Business> businesses) {
        List<List<DynamicPerson>> allPeople = new ArrayList<>();
        for (Zone zone : zones) {
            int zoneIndex = zones.indexOf(zone);
            int peopleInZone = (int) (numberOfPeople * (zone.personPercents() / 100.0));
            List<DynamicPerson> peopleInCurrentZone = createGroupOfPeople(
                    zoneIndex,
                    peopleInZone,
                    zone.wellfareMinMax(),
                    businesses,
                    zone);
            allPeople.add(peopleInCurrentZone);
        }
        return allPeople;
    }

    private static List<DynamicPerson> createGroupOfPeople(final int groupCounter, final int numberOfPeople,
            final Pair<Integer, Integer> moneyMinMax,
            final List<Business> businesses, final Zone residenceZone) {
        List<DynamicPerson> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            List<Business> eligibleBusinesses = new ArrayList<>();
            for (Business business : businesses) {
                if (!business.getZone().equals(residenceZone)) {
                    eligibleBusinesses.add(business);
                }
            }

            if (eligibleBusinesses.isEmpty()) {
                throw new IllegalStateException("No eligible businesses found for zone: " + residenceZone.name());
            }

            Optional<Business> optionalBusiness = Optional.ofNullable(
                    eligibleBusinesses.get(random.nextInt(eligibleBusinesses.size())));
            DynamicPerson person = createPerson("Person" + groupCounter + i,
                    random.nextInt(ConstantAndResourceLoader.MAX_RANDOM_AGE) + ConstantAndResourceLoader.MIN_AGE,
                    optionalBusiness, residenceZone,
                    random.nextInt(moneyMinMax.getSecond() - moneyMinMax.getFirst()) + moneyMinMax.getFirst());

            if (optionalBusiness.isPresent()) {
                Business business = optionalBusiness.get();
                int initialEmployeeCount = business.getEmployees().size();
                Employee employee = new Employee(person, business);
                business.hire(employee);

                // Verifica se l'assunzione è avvenuta controllando l'incremento del numero di dipendenti
                if (business.getEmployees().size() > initialEmployeeCount) {
                    people.add(person);
                } else {
                    person.getPersonData().business();
                    Optional.empty();
                    people.add(person);
                }
            } else {
                people.add(person);
            }
        }
        return people;
    }

    private static DynamicPerson createPerson(final String name, final int age, final Optional<Business> business,
            final Zone residenceZone, final int money) {
        return new DynamicPersonImpl(new PersonData(name, age, business, residenceZone), money);
    }
}
