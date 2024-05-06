package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.List;

public class TransportFactory {

    public static List<TransportLine> createTransports(List<Zone> zones) {
        List<TransportLine> transports = new ArrayList<>();

        List<List<Object>> transportsInfo = getTransportInfoList(zones);

        // Itera sulla lista di mappe di informazioni sulle zone e crea le zone corrispondenti
        for (var entry : transportsInfo) {
            TransportLine transport = new TransportLineImpl((String)entry.get(0), (Integer)entry.get(1), (Integer)entry.get(2), (Pair<Zone,Zone>)entry.get(3));
            transports.add(transport);
        }

        return transports;
    }

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
