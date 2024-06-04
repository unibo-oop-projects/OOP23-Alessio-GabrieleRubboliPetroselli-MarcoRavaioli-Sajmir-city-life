package unibo.citysimulation.model.person.api;

import unibo.citysimulation.model.transport.api.TransportLine;

import java.util.List;

/**
 * Interface for strategy handling the transport changes caused by the person.
 */
public interface TransportStrategy {
    /**
     * Checks if the transport has an high level of congestion.
     * 
     * @param lines the transport lines that we consider.
     * @return true if the transport has an high level of congestion.
     */
    boolean isCongested(List<TransportLine> lines);

    /**
     * Calculates the arrival time based on the current time and trip duration for the next trip.
     * 
     * @param currentTime the actual time of the simulation.
     * @param tripDuration the time that is necessary to do the trip.
     * @return the arrival time for each person.
     */
    int calculateArrivalTime(int currentTime, int tripDuration);

    /**
     * Increments by one the number of person in all the lines that are travelled.
     * @param lines the lines that are travelled.
     */
    void incrementPersonsInLine(List<TransportLine> lines);

    /**
     * Decrements by one the number of person in all the lines that are travelled.
     * @param lines the lines that are travelled.
     */
    void decrementPersonsInLine(List<TransportLine> lines);
}
