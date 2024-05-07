package unibo.citysimulation.model;

import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import java.util.List;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
    private Integer numPersons;

    public CityModel() {
        this.zones = ZoneFactory.createZones();
        System.out.println("Zones created. " + zones.size());
        this.transports = TransportFactory.createTransports(zones);
        System.out.println("Transports created. " + transports.size());
    }
}
