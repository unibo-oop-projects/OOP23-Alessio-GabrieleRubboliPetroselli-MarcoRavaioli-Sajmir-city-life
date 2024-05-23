package unibo.citysimulation.model;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserverPerson;
import unibo.citysimulation.model.clock.CloclObserverBusiness;
import unibo.citysimulation.model.person.DynamicPerson;


import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.business.EmployymentOfficeManager;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Boundary;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTable;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.utilities.Pair;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import java.util.Random;


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
    private GraphicsModel graphicsModel;

    private EmployymentOffice employymentOffice;
    

    private int frameWidth;
    private int frameHeight;

    private static final Random random = new Random();
    private int totalBusinesses;




    /**
     * Constructs a CityModel object with default settings.
     */
    public CityModel() {
        this.mapModel = new MapModel();
        this.clockModel = new ClockModel(365);
        this.inputModel = new InputModel();
        this.graphicsModel = new GraphicsModel();

        this.zones = ZoneFactory.createZonesFromFile();
        this.transports = TransportFactory.createTransportsFromFile(zones);
        this.businesses = new ArrayList<>();

        this.employymentOffice = new EmployymentOffice();
        

    
    }

    public Zone getRandomZone() {
        if (zones.isEmpty()) {
            throw new IllegalStateException("No zones available.");
        }
        return zones.get(random.nextInt(zones.size()));
    }

    public Optional<Zone> getZoneByPosition(Pair<Integer, Integer> position) {
        return zones.stream()
                .filter(zone -> isPositionInZone(position, zone))
                .findFirst();
    }

    private boolean isPositionInZone(Pair<Integer, Integer> position, Zone zone) {
        int x = position.getFirst();
        int y = position.getSecond();
        Boundary boundary = zone.boundary();
        return x >= boundary.getX() && x <= (boundary.getX() + boundary.getWidth())
                && y >= boundary.getY() && y <= (boundary.getY() + boundary.getHeight());
    }

    //istanzo businessfactory chiamo la create e le meto random

    /**
     * Creates entities such as zones, transports, businesses, and people.
     * @param numberOfPeople The number of people to create in the simulation.
     */
    

        // Create businesses
        
    

        // Create zones
        //this.zones = ZoneFactory.createZonesFromFile();
    public void createEntities() {
        graphicsModel.clearDatasets();

        transports.forEach(t -> t.setCapacity(t.getCapacity() * inputModel.getCapacity() / 100));

        // Create zone table
        ZoneTableCreation.createAndAddPairs(zones, transports);


        int numberOfPeople = getInputModel().getNumberOfPeople();
        calculateTotalBusinesses(numberOfPeople);
        // Create businesses
        createBusinesses();


        // Create people
        this.people = new ArrayList<>();
        people = PersonFactory.createAllPeople(getInputModel().getNumberOfPeople(), zones, businesses);

        for (List<DynamicPerson> group : people) {
            for (DynamicPerson person : group) {
                employymentOffice.addDisoccupiedPerson(person);
            }
        }

        // Add people as observers to clock model
        clockModel.addObserver(new ClockObserverPerson(people));

        clockModel.addObserver(new CloclObserverBusiness(businesses, employymentOffice));

        

        //System.out.println("People groups created. " + people.size());
        for (var group : people) {
            //System.out.println("Group size: " + group.size());
            
        }

        EmployymentOfficeManager employmentManager = new EmployymentOfficeManager(employymentOffice);

        
        
    }

    public final void createBusinesses() {
        int remainingBusinesses = totalBusinesses;

    for (Zone zone : zones) {
        int zoneBusinessCount = (int) (totalBusinesses * zone.businessPercents() / 100.0);
        remainingBusinesses -= zoneBusinessCount;

        for (int i = 0; i < zoneBusinessCount; i++) {
            BusinessFactory.getRandomBusiness(List.of(zone)).ifPresent(business -> {
                businesses.add(business);
            });
        }
    }

    for (int i = 0; remainingBusinesses > 0 && i < zones.size(); i++) {
        Zone zone = zones.get(i);
        BusinessFactory.getRandomBusiness(List.of(zone)).ifPresent(business -> {
            businesses.add(business);
        });
        remainingBusinesses--;
    }
    }

    public void calculateTotalBusinesses(int numberOfPeople) {
        this.totalBusinesses = numberOfPeople / 10;
    }

    public int getTotalBusinesses() {
        return this.totalBusinesses;
    }
    

    

    

    public Pair<Integer,Integer> getFrameSize(){
        // Get the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calculate the maximum dimensions based on the screen size and a constant percentage
        int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        // Calculate the frame dimensions based on the maximum dimensions
        int frameHeight = maxHeight > (maxWidth / 2) ? maxWidth / 2 : maxHeight;
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

    public GraphicsModel getGraphicsModel() {
        return this.graphicsModel;
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

    /**
     * Gets the list of businesses in the city model.
     * @return The list of businesses.
     */
    public List<Business> getBusinesses() {
        return this.businesses;
    }

    public List<DynamicPerson> getAllPeople() {
        return people.stream()              // Stream<List<DynamicPerson>>
                     .flatMap(List::stream) // Stream<DynamicPerson>
                     .collect(Collectors.toList()); // Converti in List<DynamicPerson>
    }
    

    public boolean isPeoplePresent() {
        return this.people != null;                                                         // questo null è da togliere (come tutti gli altri)
    }
    

    public boolean isBusinessesPresent() {
        boolean res = this.businesses != null;
        //System.out.println("Businesses present: " + res);
        return res;
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

    
}
