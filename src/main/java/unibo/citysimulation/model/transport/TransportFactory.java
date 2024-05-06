package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.clock.ClockModel;
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
     * Constructs a TransportFactory with the specified ClockModel.
     *
     * @param clock the ClockModel to be used
     */

    /**
     * Creates a list of TransportLine objects based on a list of Zone objects.
     *
     * @param zones the list of Zone objects
     * @return a list of TransportLine objects
     */
    public static List<TransportLine> createTransports(List<Zone> zones) {
        List<TransportLine> transports = new ArrayList<>();

        List<List<Object>> transportsInfo = getTransportInfoList(zones);

        // Iterates over the list of transport information lists and creates the corresponding transport lines
        for (var entry : transportsInfo) {
            TransportLine transport = new TransportLineImpl((String)entry.get(0), (Integer)entry.get(1), (Integer)entry.get(2), (Pair<Zone,Zone>)entry.get(3));
            transports.add(transport);
        }

        return transports;
    }

    /**
     * Returns a list of transport information lists.
     * Each transport information list contains the name, capacity, duration, and a pair of origin and destination zones of a transport line.
     *
     * @param zones the list of Zone objects
     * @return a list of transport information lists
     */
    private static List<List<Object>> getTransportInfoList(List<Zone> zones) {
        List<List<Object>> transportsInfo = new ArrayList<>();
        List<Object> infos = new ArrayList<>();
        infos.add("Tangenziale");
        infos.add(100);
        infos.add(20);
        infos.add(new Pair<>(zones.get(0), zones.get(1)));

        transportsInfo.add(infos);
        infos.clear();

        infos.add("Secante");
        infos.add(20);
        infos.add(5);
        infos.add(new Pair<>(zones.get(1), zones.get(2)));

        transportsInfo.add(infos);
        infos.clear();

        return transportsInfo;
    }
}
