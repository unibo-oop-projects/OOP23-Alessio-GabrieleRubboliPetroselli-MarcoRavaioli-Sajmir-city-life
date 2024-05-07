package unibo.citysimulation.model;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;

import java.util.List;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
<<<<<<< HEAD
    private Integer numPersons;
    private MapModel mapModel;
=======
>>>>>>> a455cfbc93136e3dd957f0a47d3fa2eeeb7c8cb1

    public CityModel() {
        this.mapModel = new MapModel();
        this.zones = ZoneFactory.createZones();
        System.out.println("Zones created. " + zones.size());
        this.transports = TransportFactory.createTransports(zones);
        System.out.println("Transports created. " + transports.size());
    }
}
