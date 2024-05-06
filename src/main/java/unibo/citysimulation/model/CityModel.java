package unibo.citysimulation.model;

import unibo.citysimulation.model.zone.ZoneImpl;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;

    public CityModel() {
        this.zones = new ArrayList<>();
        this.transports = new ArrayList<>();
        // Inizializza e aggiungi le zone alla città
        initializeZones();
        initializeTransport();
    }

    private void initializeZones() {
        Map<String, List<Object>> zonesInfo = getZoneInfoList();

        // Itera sulla lista di mappe di informazioni sulle zone e crea le zone corrispondenti
        for (var entry : zonesInfo.entrySet()) {
            Zone zone = new ZoneImpl(entry.getKey(), (float) entry.getValue().get(0), (float) entry.getValue().get(1), (float) entry.getValue().get(2));

            zones.add(zone);
        }
    }


    // Definisci le informazioni di base per ogni zona utilizzando una lista di mappe
    private Map<String, List<Object>> getZoneInfoList() {
        Map<String, List<Object>> zoneInfoList = new HashMap<>();
        // Aggiungi le informazioni di base per ciascuna zona utilizzando mappe
        List<Object> infos = new ArrayList<>();
        infos.add(10f);
        infos.add(40f);
        infos.add(50);
        zoneInfoList.put("Centro", infos);

        infos.clear();

        infos.add(30f);
        infos.add(10f);
        infos.add(20);
        zoneInfoList.put("Industrial", infos);
        
        // Aggiungi altre zone se necessario
        return zoneInfoList;
    }

    public List<Zone> getZones() {
        return this.zones;
    }

    public Zone getZoneByName(String name) {
        for (Zone zone : zones) {
            if (zone.getName().equals(name)) {
                return zone;
            }
        }
        return null;
    }

    private void initializeTransport() {
        Map<String, List<Object>> transportsInfo = getTransportInfoList();

        // Itera sulla lista di mappe di informazioni sulle zone e crea le zone corrispondenti
        for (var entry : transportsInfo.entrySet()) {
            TransportLine transport = new TransportLineImpl(entry.getKey(), (int)(entry.getValue().get(0)), (int)(entry.getValue().get(1)), (Pair<Zone,Zone>)(entry.getValue().get(2)));

            transports.add(transport);
        }
    }

    private Map<String, List<Object>> getTransportInfoList() {
        Map<String, List<Object>> transportsInfo = new HashMap<>();
        List<Object> infos = new ArrayList<>();
        infos.add(100);
        infos.add(20);
        infos.add(new Pair<>(zones.get(0), zones.get(1)));
        
        transportsInfo.put("Tangenziale", infos);
        infos.clear();

        infos.add(20);
        infos.add(5);
        infos.add(new Pair<>(zones.get(1), zones.get(2)));

        transportsInfo.put("Secante", infos);
        infos.clear();

        return transportsInfo;
    }
}
