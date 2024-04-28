package unibo.citysimulation.model.transport;
import java.util.HashMap;
import java.util.Map;


public class ZoneTable {
    private Map<Pair<Zone, Zone>, Integer> zonePairs;

    public ZoneTable() {
        this.zonePairs = new HashMap<>();
    }

    public void addPair(Zone zone1, Zone zone2, int minutes) {
        this.zonePairs.put(new Pair<>(zone1, zone2), minutes);
        this.zonePairs.put(new Pair<>(zone2, zone1), minutes); // to ensure the table works both ways
    }

    public int getMinutesForPair(Zone zone1, Zone zone2) {
        return this.zonePairs.getOrDefault(new Pair<>(zone1, zone2), 0);
    }
}

