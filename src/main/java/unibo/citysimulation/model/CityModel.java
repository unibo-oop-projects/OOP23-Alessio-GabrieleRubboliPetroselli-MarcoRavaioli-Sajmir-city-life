package unibo.citysimulation.model;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;

import java.util.List;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
    private Integer numPersons;
    private MapModel mapModel;
    private ClockModel clockModel;

    public CityModel() {
        this.mapModel = new MapModel();
        this.clockModel = new ClockModel(365);
        this.zones = ZoneFactory.createZones();
        System.out.println("Zones created. " + zones.size());
        this.transports = TransportFactory.createTransports(zones);
        System.out.println("Transports created. " + transports.size());
    }

    public MapModel getMapModel() {
        return this.mapModel;
    }

    public ClockModel getClockModel() {
        return this.clockModel;
    }
}