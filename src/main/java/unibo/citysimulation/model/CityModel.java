package unibo.citysimulation.model;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;

import java.util.List;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;

    public CityModel() {
        initializeZones();
        initializeTransports();
    }

    private void initializeZones() {
        zones = ZoneFactory.createZones();
    }

    private void initializeTransports() {
        transports = TransportFactory.createTransports(zones);
    }

    public List<Zone> getZones() {
        return zones;
    }

    public List<TransportLine> getTransports() {
        return transports;
    }
}
