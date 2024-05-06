package unibo.citysimulation.model.transport;

import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportFactory {

    public static List<TransportLine> createTransports(List<Zone> zones) {
        List<TransportLine> transports = new ArrayList<>();

        Map<String, List<Object>> transportsInfo = getTransportInfoList(zones);

        // Itera sulla lista di mappe di informazioni sulle zone e crea le zone corrispondenti
        for (var entry : transportsInfo.entrySet()) {
            TransportLine transport = createTransport(entry.getKey(), entry.getValue());
            transports.add(transport);
        }

        return transports;
    }

    private static Map<String, List<Object>> getTransportInfoList(List<Zone> zones) {
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

    private static TransportLine createTransport(String name, List<Object> infos) {
        return new TransportLineImpl(name, (int)(infos.get(0)), (int)(infos.get(1)), (Pair<Zone,Zone>)(infos.get(2)));
    }
}
