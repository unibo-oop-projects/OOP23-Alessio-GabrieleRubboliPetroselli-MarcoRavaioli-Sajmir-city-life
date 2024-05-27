package unibo.citysimulation.model;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticCalculator {

    protected static List<Integer> getPeopleStateCounts(final List<DynamicPerson> people) {
        return Arrays.asList(
                calculatePercentage(people, PersonState.AT_HOME),
                calculatePercentage(people, PersonState.MOVING),
                calculatePercentage(people, PersonState.WORKING)
        );
    }

    private static int calculatePercentage(final List<DynamicPerson> people, final PersonState state) {
        return (int) (people.stream().filter(person -> person.getState() == state).count() * 100.0 / people.size());
    }

    protected static List<Double> getTransportLinesCongestion(final List<TransportLine> lines) {
        return lines.stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the occupation percentage for each business in the given list.
     *
     * @param list the list of businesses
     * @return a list of integers representing the occupation percentage for each business
     */
    protected static List<Integer> getBusinessesOccupation(final List<Business> businesses) {
        return businesses.stream()
                .map(business -> (int) ((double) business.getEmployees().size() / business.getMaxEmployees() * 100))
                .collect(Collectors.toList());
    }
}