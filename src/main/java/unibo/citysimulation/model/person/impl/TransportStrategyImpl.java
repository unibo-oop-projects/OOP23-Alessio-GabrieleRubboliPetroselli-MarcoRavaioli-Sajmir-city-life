package unibo.citysimulation.model.person.impl;

import java.util.List;

import unibo.citysimulation.model.person.api.TransportStrategy;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class TransportStrategyImpl implements TransportStrategy {

    @Override
    public boolean isCongested(List<TransportLine> lines) {
        return lines.stream()
            .anyMatch(line -> line.getCongestion() > ConstantAndResourceLoader.CONGESTION_VALUE);
    }

    @Override
    public int calculateArrivalTime(int currentTime, int tripDuration) {
        return (currentTime + tripDuration) % ConstantAndResourceLoader.SECONDS_IN_A_DAY;
    }

    @Override
    public void incrementPersonsInLine(List<TransportLine> lines) {
        lines.forEach(TransportLine::incrementPersonInLine);
    }

    @Override
    public void decrementPersonsInLine(List<TransportLine> lines) {
        lines.forEach(TransportLine::decrementPersonInLine);
    }
}
