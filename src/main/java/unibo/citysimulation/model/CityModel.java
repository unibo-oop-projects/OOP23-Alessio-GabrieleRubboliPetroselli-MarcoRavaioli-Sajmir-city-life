package unibo.citysimulation.model;

import unibo.citysimulation.model.zone.ZoneImpl;
import unibo.citysimulation.model.zone.Zone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityModel {
    private List<Zone> zones;

    public CityModel() {
        this.zones = new ArrayList<>();
        // Inizializza e aggiungi le zone alla città
        initializeZones();
    }

    private void initializeZones() {
        Map<String, List<Object>> zonesInfo = getZoneInfoList();

        // Itera sulla lista di mappe di informazioni sulle zone e crea le zone corrispondenti
        for (Map.Entry<String,List<Object>> entry : zonesInfo.entrySet()) {
            Zone zone = new ZoneImpl(entry.getKey());

            zone.setPersonPercents((float) entry.getValue().get(0));
            zone.setBusinessPercents((float) entry.getValue().get(1));
            zone.setWellfare((float) entry.getValue().get(2));

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
}
