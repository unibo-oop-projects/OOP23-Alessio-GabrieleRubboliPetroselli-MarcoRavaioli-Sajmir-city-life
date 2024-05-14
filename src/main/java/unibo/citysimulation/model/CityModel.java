package unibo.citysimulation.model;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserverPerson;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.utilities.Pair;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model of the city simulation, containing zones, transports, businesses, and people.
 */
public class CityModel {
    private List<Zone> zones;
    private List<TransportLine> transports;
    private List<Business> businesses;
    private List<List<DynamicPerson>> people;
    private MapModel mapModel;
    private ClockModel clockModel;
    private InputModel inputModel;

    private int frameWidth;
    private int frameHeight;

    /**
     * Constructs a CityModel object with default settings.
     */
    public CityModel() {
        this.mapModel = new MapModel();
        this.clockModel = new ClockModel(365);
        this.inputModel = new InputModel();

        this.zones = ZoneFactory.createZonesFromFile();
        this.transports = TransportFactory.createTransportsFromFile(zones);

    }

    /**
     * Creates entities such as zones, transports, businesses, and people.
     * @param numberOfPeople The number of people to create in the simulation.
     */
    public void createEntities() {

        transports.forEach(t -> t.setCapacity(t.getCapacity() * inputModel.getCapacity() / 100));

        // Create zone table
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(1), transports.get(0));
        ZoneTable.getInstance().addPair(zones.get(1), zones.get(2), transports.get(1));
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(2),transports.get(2));
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(3), transports.get(3));
        ZoneTable.getInstance().addPair(zones.get(0), zones.get(4),transports.get(4));
        ZoneTable.getInstance().addPair(zones.get(1), zones.get(3), transports.get(5));
        ZoneTable.getInstance().addPair(zones.get(1), zones.get(4),transports.get(6));
        ZoneTable.getInstance().addPair(zones.get(2), zones.get(3), transports.get(7));
        ZoneTable.getInstance().addPair(zones.get(2), zones.get(4),transports.get(8));
        ZoneTable.getInstance().addPair(zones.get(3), zones.get(4), transports.get(9));


        // Create businesses
        this.businesses = BusinessFactory.createBusinesses(zones);
        System.out.println("Businesses created. " + businesses.size());


        // Create people
        this.people = new ArrayList<>();
        people = PersonFactory.createAllPeople(getInputModel().getNumberOfPeople(), zones, businesses);

        // Add people as observers to clock model
        clockModel.addObserver(new ClockObserverPerson(people));

        System.out.println("People groups created. " + people.size());
        for (var group : people) {
            System.out.println("Group size: " + group.size());
        }
        ////////////////////////////////////////////////////////////////
        // Print details of each person
        for (var group : people) {
            for (var person : group) {
                System.out.println(person.getPersonData().name() + ", " + person.getPersonData().age() + ", " + person.getMoney() + ", " +
                person.getPersonData().business().getName() + ", " + person.getPersonData().business().getZone().name() + ", " + person.getPersonData().residenceZone().name()
                + ", " + person.getTripDuration());
            }
        }
        ////////////////////////////////////////////////////////////////
    }

    public Pair<Integer,Integer> getFrameSize(){
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the maximum dimensions based on the screen size and a constant percentage
        int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        // Calculate the frame dimensions based on the maximum dimensions
        int frameHeight = maxHeight > maxWidth / 2 ? maxWidth / 2 : maxHeight;
        int frameWidth = frameHeight * 2;

        // Create and return the window model with the calculated dimensions
        return new Pair<>(frameWidth, frameHeight);
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

    public InputModel getInputModel() {
        return this.inputModel;
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

    public List<DynamicPerson> getAllPeople() {
        List<DynamicPerson> allPeople = new ArrayList<>();
        for (var group : this.people) {
            allPeople.addAll(group);
        }
        return allPeople;
    }

    public void setFrameSize(Pair<Integer, Integer> frameSize) {
        this.frameWidth = frameSize.getFirst();
        this.frameHeight = frameSize.getSecond();
    }

    public int getFrameWidth(){
        return this.frameWidth;
    }

    public int getFrameHeight(){
        return this.frameHeight;
    }

    public Pair<Integer, Integer> getScreenSize() {
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the maximum dimensions based on the screen size and a constant percentage
        int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        // Calculate the frame dimensions based on the maximum dimensions
        int frameHeight = maxHeight > maxWidth / 2 ? maxWidth / 2 : maxHeight;
        int frameWidth = frameHeight * 2;

        // Create and return the window model with the calculated dimensions
        return new Pair<>(frameWidth, frameHeight);
    }
}
