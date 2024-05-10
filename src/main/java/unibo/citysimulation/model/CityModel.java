package unibo.citysimulation.model;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserverPerson;
import unibo.citysimulation.model.person.Person;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model of the city simulation, containing zones, transports, businesses, and people.
 */
public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
    private ZoneTable zoneTable;
    private List<Business> businesses;
    private List<List<Person>> people;
    private MapModel mapModel;
    private ClockModel clockModel;

    /**
     * Constructs a CityModel object with default settings.
     */
    public CityModel() {
        this.mapModel = new MapModel();
        this.clockModel = new ClockModel(365);
    }

    /**
     * Creates entities such as zones, transports, businesses, and people.
     * @param numberOfPeople The number of people to create in the simulation.
     */
    public void createEntities(int numberOfPeople) {
        // Create zones
        this.zones = ZoneFactory.createZonesFromFile();
        System.out.println("Zones created. " + zones.size());

        // Create transports
        this.transports = TransportFactory.createTransportsFromFile(zones);
        System.out.println("Transports created. " + transports.size());

        // Create zone table
        this.zoneTable = new ZoneTable();
        zoneTable.addPair(zones.get(0), zones.get(1), transports.get(0));
        zoneTable.addPair(zones.get(1), zones.get(2), transports.get(1));
        zoneTable.addPair(zones.get(0), zones.get(2),transports.get(2));

        // Create businesses
        this.businesses = BusinessFactory.createBusinesses(zones);
        System.out.println("Businesses created. " + businesses.size());

        // Create people
        this.people = new ArrayList<>();
        for (var zone : zones) {
            this.people.add(PersonFactory.createGroupOfPeople((int) (numberOfPeople * (zone.getBusinessPercents()/100)),
            zone.getWellfareMinMax(), businesses, zone, zoneTable));
        }

        // Add people as observers to clock model
        clockModel.addObserver(new ClockObserverPerson(people));

        System.out.println("People groups created. " + people.size());
        for (var group : people) {
            System.out.println("Group size: " + group.size());
        }

        // Print details of each person
        for (var group : people) {
            for (var person : group) {
                System.out.println(person.getName() + ", " + person.getAge() + ", " + person.getMoney() + ", " +
                person.getBusiness().getName() + ", " + person.getBusiness().getZone().getName() + ", " + person.getResidenceZone().getName());
            }
        }
    }

    /**
     * Gets the map model associated with this city model.
     * @return The map model.
     */
    public MapModel getMapModel() {
        return this.mapModel;
    }

    /**
     * Gets the clock model associated with this city model.
     * @return The clock model.
     */
    public ClockModel getClockModel() {
        return this.clockModel;
    }

    /**
     * Gets the list of zones in the city model.
     * @return The list of zones.
     */
    public List<Zone> getZones() {
        return this.zones;
    }

    /**
     * Gets the list of transport lines in the city model.
     * @return The list of transport lines.
     */
    public List<TransportLine> getTransportLines() {
        return this.transports;
    }
    public List<Person> getAllPeople() {
        List<Person> allPeople = new ArrayList<>();
        for (List<Person> group : this.people) {
            allPeople.addAll(group);
        }
        return allPeople;
    }
}
