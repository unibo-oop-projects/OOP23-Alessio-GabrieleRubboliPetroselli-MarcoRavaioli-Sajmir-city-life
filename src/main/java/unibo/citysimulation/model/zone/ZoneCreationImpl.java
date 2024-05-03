package unibo.citysimulation.model.zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unibo.citysimulation.model.transport.TransportLine;

public class ZoneCreationImpl implements ZoneCreation{

    private ZoneTable zoneTable;
    private Map<String, Zone> zoneMap;

    public ZoneCreationImpl() {
        this.zoneTable = new ZoneTable();
        this.zoneMap = new HashMap<>();
    }

    @Override
    public void createZone(String name, List<Pair> boundary) {
        Zone zone = new Zone(name, boundary);
        zoneMap.put(name, zone);
    }

    @Override
    public void createPairs(String name1, String name2, TransportLine transportLine) {
        Zone zone1 = zoneMap.get(name1);
        Zone zone2 = zoneMap.get(name2);
        if (zone1 != null && zone2 != null) {
            zoneTable.addPair(zone1, zone2, transportLine);
        }
    }

    

    
}
