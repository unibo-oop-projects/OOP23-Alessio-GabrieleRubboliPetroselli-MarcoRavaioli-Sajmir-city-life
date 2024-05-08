package unibo.citysimulation.model;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
    private ZoneTable zoneTable;
    private List<Business> businesses;
    private List<List<Person>> people;
    private MapModel mapModel;
    private ClockModel clockModel;

    public CityModel() {
        this.mapModel = new MapModel();
        this.clockModel = new ClockModel(365);
    }

    public void createEntities(int numberOfPeople) {
        this.zones = ZoneFactory.createZones();
        System.out.println("Zones created. " + zones.size());
        this.transports = TransportFactory.createTransports(zones);
        System.out.println("Transports created. " + transports.size());
        this.zoneTable = new ZoneTable();
        this.businesses = BusinessFactory.createBusinesses(zones);
        System.out.println("Businesses created. " + businesses.size());

        this.people = new ArrayList<>();
        for (var zone : zones) {
            this.people.add(PersonFactory.createGroupOfPeople((int) (numberOfPeople * (zone.getBusinessPercents()/100)),
            zone.getWellfareMinMax(), businesses.get(new Random().nextInt(businesses.size())), zone, zoneTable));
        }

        System.out.println("People groups created. " + people.size());
        for (var group : people) {
            System.out.println("Group size: " + group.size());
        }
    }

    public MapModel getMapModel() {
        return this.mapModel;
    }

    public ClockModel getClockModel() {
        return this.clockModel;
    }

    public List<Zone> getZones() {
        return this.zones;
    }

    public List<TransportLine> getTransportLines() {
        return this.transports;
    }
}