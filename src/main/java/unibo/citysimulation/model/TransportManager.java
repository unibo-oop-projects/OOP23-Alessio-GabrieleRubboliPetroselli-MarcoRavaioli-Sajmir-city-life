package unibo.citysimulation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

public class TransportManager {
    private boolean simulationStarted;
    private List<String> linesName = Collections.emptyList();
    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections.emptyList();
    private List<Double> congestionsList = Collections.emptyList();

        protected List<String> getTransportNames() {
        simulationStarted = false;
        return new ArrayList<>(linesName);
    }

    public List<Double> getCongestionList(){
        return new ArrayList<>(congestionsList);
    }

    public List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates() {
        return new ArrayList<>(linesPointsCoordinates);
    }

    public void setTransportInfo(List<TransportLine> lines) {
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

    public void setTransportCongestion(final List<TransportLine> lines) {
        congestionsList = lines.stream()
            .map(TransportLine::getCongestion)
            .collect(Collectors.toList());

        if (!simulationStarted) {
            simulationStarted = true;
        }
    }

    public boolean isSimulationStarted() {
        return simulationStarted;
    }
}
