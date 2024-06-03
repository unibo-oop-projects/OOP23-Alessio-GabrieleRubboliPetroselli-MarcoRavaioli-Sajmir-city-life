package unibo.citysimulation.model.person.api;

import java.util.List;

import unibo.citysimulation.model.transport.TransportLine;

public interface TransportStrategy {
    boolean isCongested(List<TransportLine> lines);
    int calculateArrivalTime(int currentTime, int tripDuration);
    void incrementPersonsInLine(List<TransportLine> lines);
    void decrementPersonsInLine(List<TransportLine> lines);
}
