package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating TransportLine objects.
 * This factory creates a list of TransportLine objects based on a list of Zone objects.
 */
public class TransportFactory {
    /**
     * Creates a list of TransportLine objects based on a list of Zone objects.
     *
     * @param zones the list of Zone objects
     * @return a list of TransportLine objects
     */
    public static List<TransportLine> createTransports(List<Zone> zones) {
        List<TransportLine> transports = new ArrayList<>();

        List<Object> infos = new ArrayList<>();

        infos.add("Tangenziale");
        infos.add(100);
        infos.add(20);
        infos.add(new Pair<>(zones.get(0), zones.get(1)));
        transports.add(createTransportLine(infos));

        infos.clear();

        infos.add("Secante");
        infos.add(20);
        infos.add(5);
        infos.add(new Pair<>(zones.get(1), zones.get(2)));

        transports.add(createTransportLine(infos));

        infos.clear();

        infos.add("Circolare");
        infos.add(50);
        infos.add(10);
        infos.add(new Pair<>(zones.get(0), zones.get(2)));

        transports.add(createTransportLine(infos));
        infos.clear();

        return transports;
    }

    @SuppressWarnings("unchecked")
    private static TransportLine createTransportLine(List<Object> infos) {
        return new TransportLineImpl((String) infos.get(0), (int) infos.get(1), (int) infos.get(2), (Pair<Zone, Zone>) infos.get(3));
    }
}
