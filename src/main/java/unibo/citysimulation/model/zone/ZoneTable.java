package unibo.citysimulation.model.zone;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZoneTable {
    private static ZoneTable instance;
    private static Map<Pair<Zone, Zone>, TransportLine[]> zonePairs;

    private ZoneTable() {
        zonePairs = new HashMap<>();
    }

    public static ZoneTable getInstance() {
        if (instance == null) {
            instance = new ZoneTable();
        }
        return instance;
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine[] transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
        zonePairs.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
        System.out.println("Added pair: " + zone1.name() + " - " + zone2.name() + " with lines: " + Arrays.toString(transportLine));
    }

    public TransportLine[] getTransportLine(Zone zone1, Zone zone2) {
        System.out.println("Getting pair: " + zone1.name() + " - " + zone2.name());

        //System.out.println("\n"+zonePairs);
        
        System.out.println(zonePairs.get(new Pair<Zone,Zone>(zone1, zone2))[0].getName());
        return zonePairs.get(new Pair<Zone,Zone>(zone1, zone2));
    }

    public int getTripDuration(TransportLine[] transportLines) {
        return Arrays.stream(transportLines)
                             .mapToInt(transportLine -> transportLine.getDuration())
                             .map(duration -> duration * 60)
                             .sum();
    }
}

