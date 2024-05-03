package unibo.citysimulation.model.zone;

import java.util.List;

import unibo.citysimulation.model.transport.TransportLine;

public interface ZoneCreation {
    void createZone(String name, List<Pair> boundary);
    void createPairs(String name1, String name2, TransportLine transportLine);
    
}

