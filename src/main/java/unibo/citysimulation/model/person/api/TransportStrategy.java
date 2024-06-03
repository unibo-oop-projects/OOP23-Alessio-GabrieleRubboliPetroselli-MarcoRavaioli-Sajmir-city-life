package unibo.citysimulation.model.person.api;

import unibo.citysimulation.model.transport.api.TransportLine;

import java.util.List;

public interface TransportStrategy {
    boolean isCongested(List<TransportLine> lines);
    int calculateArrivalTime(int currentTime, int tripDuration);
    void incrementPersonsInLine(List<TransportLine> lines);
    void decrementPersonsInLine(List<TransportLine> lines);
}
