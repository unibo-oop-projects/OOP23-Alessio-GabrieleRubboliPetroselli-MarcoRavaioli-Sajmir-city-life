package unibo.citysimulation.model.person.impl;

import java.util.List;

import unibo.citysimulation.model.person.api.TransportStrategy;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

/**
 * A strategy class to help client DynamicPerson to handle transport updates.
 */
public final class TransportStrategyImpl implements TransportStrategy {

    @Override
    public boolean isCongested(final List<TransportLine> lines) {
        return lines.stream()
            .anyMatch(line -> line.getCongestion() > ConstantAndResourceLoader.CONGESTION_VALUE);
    }

    @Override
    public int calculateArrivalTime(final int currentTime, final int tripDuration) {
        return (currentTime + tripDuration) % ConstantAndResourceLoader.SECONDS_IN_A_DAY;
    }

    @Override
    public void incrementPersonsInLine(final List<TransportLine> lines) {
        lines.forEach(TransportLine::incrementPersonInLine);
    }

    @Override
    public void decrementPersonsInLine(final List<TransportLine> lines) {
        lines.forEach(TransportLine::decrementPersonInLine);
    }
}
