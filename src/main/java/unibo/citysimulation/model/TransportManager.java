package unibo.citysimulation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Utility class for MapModel.
 * Manages transport lines and their related data within the city simulation.
 */
public class TransportManager {
    private boolean simulationStarted;
    private List<String> linesName = Collections.emptyList();
    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections.emptyList();
    private List<Double> congestionsList = Collections.emptyList();

    /**
     * Gets the names of the transport lines.
     *
     * @return A list of transport line names.
     */
    protected List<String> getTransportNames() {
        simulationStarted = false;
        return new ArrayList<>(linesName);
    }

    /**
     * Gets the list of congestion levels for each transport line.
     *
     * @return A list of congestion levels.
     */
    protected List<Double> getCongestionList() {
        return new ArrayList<>(congestionsList);
    }

    /**
     * Gets the coordinates of the points defining each transport line.
     *
     * @return A list of pairs of coordinates for each transport line.
     */
    protected List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates() {
        return new ArrayList<>(linesPointsCoordinates);
    }

    /**
     * Sets the transport line information, including names and point coordinates.
     *
     * @param lines A list of transport lines.
     */
    protected void setTransportInfo(final List<TransportLine> lines) {
        linesPointsCoordinates = lines.stream()
            .map(line -> {
                final Pair<Integer, Integer> startPoint = line.getLinkedZones().getFirst().boundary().getCenter();
                final Pair<Integer, Integer> endPoint = line.getLinkedZones().getSecond().boundary().getCenter();
                return new Pair<>(startPoint, endPoint);
            })
            .collect(Collectors.toList());

        linesName = lines.stream()
            .map(TransportLine::getName)
            .collect(Collectors.toList());
    }

    /**
     * Sets the congestion levels for each transport line.
     *
     * @param lines A list of transport lines.
     */
    protected void setTransportCongestion(final List<TransportLine> lines) {
        congestionsList = lines.stream()
            .map(TransportLine::getCongestion)
            .collect(Collectors.toList());

        if (!simulationStarted) {
            simulationStarted = true;
        }
    }
    /**
     * Checks if the simulation has started.
     *
     * @return True if the simulation has started, false otherwise.
     */
    protected boolean isSimulationStarted() {
        return simulationStarted;
    }
}
