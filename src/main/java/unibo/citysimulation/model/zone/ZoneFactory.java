package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

public class ZoneFactory {

    public static List<Zone> createZones() {
        List<Zone> zones = new ArrayList<>();

        // Aggiungi le informazioni di base per ciascuna zona utilizzando mappe
        List<Object> infos = new ArrayList<>();
        infos.add("Centro");
        infos.add(10f);
        infos.add(40f);
        infos.add(50);
        infos.add(new Pair<>(1000, 1500));
        infos.add(new Pair<>(3, 99));
        Zone centerZone = createZone(infos);
        zones.add(centerZone);

        infos.clear();

        infos.add("Industrial");
        infos.add(30f);
        infos.add(10f);
        infos.add(20);
        infos.add(new Pair<>(800, 1300));
        infos.add(new Pair<>(3, 99));
        Zone industrialZone = createZone(infos);
        zones.add(industrialZone);
        
        // Aggiungi altre zone se necessario

        return zones;
    }

    @SuppressWarnings("unchecked")
    private static Zone createZone(List<Object> infos) {
        return new ZoneImpl((String) infos.get(0), (float) infos.get(1), (float) infos.get(2),
                (float) infos.get(3), (Pair<Integer,Integer>) infos.get(4), (Pair<Integer,Integer>) infos.get(5));
    }
}
