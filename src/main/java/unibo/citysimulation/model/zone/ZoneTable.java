package unibo.citysimulation.model.zone;


import java.util.Arrays;


import java.util.HashMap;

import java.util.Map;





import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

public final class ZoneTable {
    private static final Map<Pair<Zone, Zone>, TransportLine[]> ZONE_PAIRS = new HashMap<>();

    private ZoneTable() {
    }

    private static class Holder {
        private static final ZoneTable INSTANCE = new ZoneTable();
    }

    public static ZoneTable getInstance() {
        return Holder.INSTANCE;
    }

    public void addPair(final Zone zone1, final Zone zone2, final TransportLine[] transportLine) {
        ZONE_PAIRS.put(new Pair<>(zone1, zone2), transportLine);
        ZONE_PAIRS.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
        //System.out.println("Added pair: " + zone1.name() + " - " + zone2.name() + " with lines: " + Arrays.toString(transportLine));
    }

    public TransportLine[] getTransportLine(final Zone zone1, final Zone zone2) {
        //System.out.println("Getting pair: " + zone1.name() + " - " + zone2.name());

        //System.out.println("\n"+zonePairs);
        
        //System.out.println(zonePairs.get(new Pair<Zone,Zone>(zone1, zone2))[0].getName());
        return ZONE_PAIRS.get(new Pair<Zone,Zone>(zone1, zone2));
    }

    public int getTripDuration(final TransportLine[] transportLines) {
        return Arrays.stream(transportLines)
                             .mapToInt(TransportLine::getDuration)
                             .map(duration -> duration * 60)
                             .sum();
    }
}
