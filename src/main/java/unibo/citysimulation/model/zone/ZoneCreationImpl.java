package unibo.citysimulation.model.zone;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Implementation of the ZoneCreation interface that provides methods to create and manage zones.
 */
public class ZoneCreationImpl implements ZoneCreation {

    private ZoneTable zoneTable;
    private Map<String, Zone> zoneMap;
    private List<Zone> zonesInfo;

    /**
     * Constructs a new ZoneCreationImpl object.
     */
    public ZoneCreationImpl() {
        this.zoneTable = new ZoneTable();
        this.zoneMap = new HashMap<>();
        this.zonesInfo = new LinkedList<>();
        };

    /**
     * Creates a new boundary with the given coordinates.
     * 
     * @param x1 the x-coordinate of the first boundary point
     * @param y1 the y-coordinate of the first boundary point
     * @param x2 the x-coordinate of the second boundary point
     * @param y2 the y-coordinate of the second boundary point
     * @return the boundary object
     */
    @Override
    public Boundary createBoundary(int x1, int y1, int x2, int y2) {
        return new Boundary(x1, y1, x2, y2);
    }
    

    /**
     * Creates a new zone with the given name and boundary coordinates.
     *
     * @param name the name of the zone
     * @param x1   the x-coordinate of the first boundary point
     * @param y1   the y-coordinate of the first boundary point
     * @param x2   the x-coordinate of the second boundary point
     * @param y2   the y-coordinate of the second boundary point
     */
    @Override
    public Zone createZone(List<Object> infos) {
        String name = (String) infos.get(0);
        Boundary boundary = (Boundary) infos.get(1);
        float personPercents = (Float) infos.get(2);
        float businessPercents = (Float) infos.get(3);
        float wellfare = (Float) infos.get(4);
        Pair<Integer, Integer> wellfareMinMax = (Pair<Integer, Integer>) infos.get(5);
        Pair<Integer, Integer> ageMinMax = (Pair<Integer, Integer>) infos.get(6);

        Zone zone = new ZoneImpl(name, boundary, personPercents, businessPercents, wellfare, wellfareMinMax, ageMinMax);
        zoneMap.put(name, zone);
        zonesInfo.add(zone);

        return zone;
}

    /**
     * Creates a pair of zones and associates them with the given transport line.
     *
     * @param name1         the name of the first zone
     * @param name2         the name of the second zone
     * @param transportLine the transport line to associate with the zones
     */
    @Override
    public void createPairs(String name1, String name2, TransportLine transportLine) {
        Zone zone1 = zoneMap.get(name1); // Changed Zone to ZoneImpl
        Zone zone2 = zoneMap.get(name2); // Changed Zone to ZoneImpl
        if (zone1 != null && zone2 != null) {
            zoneTable.addPair(zone1, zone2, transportLine);
    }
}

    /**
     * Checks if a given point is inside the specified zone.
     *
     * @param name the name of the zone
     * @param x    the x-coordinate of the point
     * @param y    the y-coordinate of the point
     * @return true if the point is inside the zone, false otherwise
     */
    public boolean isPointInZone(String name, int x, int y) {
        Zone zone = zoneMap.get(name);
        if (zone != null) {
            return zone.getBoundary().isInside(x, y);
        } else {
            return false;
        }
    }
}
