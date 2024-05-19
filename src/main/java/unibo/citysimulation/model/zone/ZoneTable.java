package unibo.citysimulation.model.zone;

import java.util.*;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

public class ZoneTable {
    private static ZoneTable instance;
    private static Map<Pair<Zone, Zone>, TransportLine> zonePairs;

    private ZoneTable() {
        zonePairs = new HashMap<>();
    }

    public static ZoneTable getInstance() {
        if (instance == null) {
            instance = new ZoneTable();
        }
        return instance;
    }

    public void addPair(Zone zone1, Zone zone2, TransportLine transportLine) {
        zonePairs.put(new Pair<>(zone1, zone2), transportLine);
        zonePairs.put(new Pair<>(zone2, zone1), transportLine); // to ensure the table works both ways
        System.out.println("Added pair: " + zone1.name() + " - " + zone2.name() + " with lines: " + Arrays.toString(transportLine));
    }

    public TransportLine[] getTransportLine(Zone zone1, Zone zone2) {
        System.out.println("Getting pair: " + zone1.name() + " - " + zone2.name());
        System.out.println(zonePairs.get(new Pair<Zone,Zone>(zone1, zone2))[0].getName());
        return zonePairs.get(new Pair<Zone,Zone>(zone1, zone2));
    }

    public static int getTripDuration(TransportLine transportLines) {
        return transportLines.getDuration();
    }

    public Map<Pair<Zone, Zone>, TransportLine> getZonePairs(){
        return zonePairs;
    }
}

/*
 * zoneTable ci da i collegamenti nodo - arco
 * 
 * una volta istanziati trasporti e zone PathFinder calcola la tabella dei
 * percorsi da una zona a tutte le altre
 * 
 * la tabella si applica ad ogni zona
 * 
 * INIZIA LA SIMULAZIONE
 * 
 * person1234 deve andare da ZoneA a ZoneZ interroga la lista della zona in cui
 * è
 * 
 * persona1234 scorre cercando il primo percorso con tutte le linee libere
 * 
 * se non trova linee riprova il ciclo di clock successivo
 */
