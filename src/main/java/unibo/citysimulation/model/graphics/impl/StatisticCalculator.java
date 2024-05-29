package unibo.citysimulation.model.graphics.impl;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.api.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for calculating various statistics related to the city simulation.
 * This includes calculations for people states, transport line congestion levels, and business occupation percentages.
 */
public final class StatisticCalculator {

    private StatisticCalculator() { }

    /**
     * Calculates the percentage of people in each state (AT_HOME, MOVING, WORKING)
     * from the given list of people.
     *
     * @param people the list of dynamic person objects representing the population
     * @return a list of integers representing the percentage of people in each
     *         state: AT_HOME, MOVING, and WORKING
     */
    static List<Integer> getPeopleStateCounts(final List<DynamicPerson> people) {
        return Arrays.asList(
                calculatePercentage(people, PersonState.AT_HOME),
                calculatePercentage(people, PersonState.MOVING),
                calculatePercentage(people, PersonState.WORKING));
    }

    static int calculatePercentage(final List<DynamicPerson> people, final PersonState state) {
        return (int) (people.stream().filter(person -> person.getState() == state).count() * 100.0 / people.size());
    }

    /**
     * Calculates the congestion levels for each transport line in the given list.
     *
     * @param lines the list of transport line objects
     * @return a list of doubles representing the congestion level for each
     *         transport line
     */
    static List<Double> getTransportLinesCongestion(final List<TransportLine> lines) {
        return lines.stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the occupation percentage for each business in the given list.
     *
     * @param businesses the list of business objects
     * @return a list of integers representing the occupation percentage for each
     *         business
     */
    static List<Integer> getBusinessesOccupation(final List<Business> businesses) {
        return businesses.stream()
                .map(business -> (int) ((double) business.getEmployees().size() / business.getMaxEmployees() * 100))
                .collect(Collectors.toList());
    }
}
