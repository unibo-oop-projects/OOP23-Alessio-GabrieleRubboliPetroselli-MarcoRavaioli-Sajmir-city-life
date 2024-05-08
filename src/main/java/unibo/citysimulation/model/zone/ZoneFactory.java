package unibo.citysimulation.model.zone;

import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;
 // Add this import statement

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
        infos.add(new Boundary(0, 0, 100, 100));
        infos.add(0.5f);
        infos.add(0.5f);
        infos.add(0.5f);
        infos.add(new Pair<>(0, 10));
        infos.add(new Pair<>(20, 30));
        zones.add(createZone(infos));

        infos.clear();

        infos.add("Periferia");
        infos.add(new Boundary(100, 100, 200, 200));
        infos.add(0.3f);
        infos.add(0.7f);
        infos.add(0.3f);
        infos.add(new Pair<>(10, 20));
        infos.add(new Pair<>(30, 40));
        zones.add(createZone(infos));

        return zones;

        
}

    private static Zone createZone(List<Object> infos) {
        String name = (String) infos.get(0);
        Boundary boundary = (Boundary) infos.get(1);
        float personPercents = (float) infos.get(2);
        float businessPercents = (float) infos.get(3);
        float wellfare = (float) infos.get(4);
        Pair<Integer, Integer> wellfareMinMax = (Pair<Integer, Integer>) infos.get(5);
        Pair<Integer, Integer> ageMinMax = (Pair<Integer, Integer>) infos.get(6);
        return new ZoneImpl(name, boundary, personPercents, businessPercents, wellfare, wellfareMinMax, ageMinMax);
    }
}
