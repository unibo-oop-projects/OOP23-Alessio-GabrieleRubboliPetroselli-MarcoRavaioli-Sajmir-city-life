package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
/**
 * Factory for creating Zone objects.
 * This factory creates a list of Zone objects with predefined information.
 */
public class ZoneFactory {
    /**
     * Creates a list of Zone objects with predefined information.
     *
     * @return a list of Zone objects
     */
    public static List<Zone> createZones() {
        List<Zone> zones = new ArrayList<>();

        // Aggiungi le informazioni di base per ciascuna zona utilizzando mappe
        List<Object> infos = new ArrayList<>();
        infos.add("Centro");
        infos.add(10f);
        infos.add(40f);
        infos.add(50f);
        infos.add(new Pair<>(1000, 1500));
        infos.add(new Pair<>(3, 99));
        zones.add(createZone(infos));

        infos.clear();

        infos.add("Industrial");
        infos.add(30f);
        infos.add(10f);
        infos.add(20f);
        infos.add(new Pair<>(800, 1300));
        infos.add(new Pair<>(3, 99));
        zones.add(createZone(infos));

        infos.clear();

        infos.add("Periferia");
        infos.add(20f);
        infos.add(30f);
        infos.add(10f);
        infos.add(new Pair<>(900, 1200));
        infos.add(new Pair<>(20, 99));
        zones.add(createZone(infos));
        
        // Aggiungi altre zone se necessario

        return zones;
    }
     /**
     * Creates a Zone object with the given information.
     *
     * @param infos a list of information for creating a Zone object
     * @return a Zone object
     */
    private static Zone createZone(List<Object> infos) {
        return new ZoneImpl((String) infos.get(0), (float) infos.get(1), (float) infos.get(2),
                (float) infos.get(3), (Pair<Integer,Integer>) infos.get(4), (Pair<Integer,Integer>) infos.get(5));
    }
}
