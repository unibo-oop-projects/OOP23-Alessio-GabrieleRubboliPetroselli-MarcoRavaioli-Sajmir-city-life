package unibo.citysimulation.model;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.business.BusinessFactory;
import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserverPerson;
import unibo.citysimulation.model.person.DynamicPerson;


import unibo.citysimulation.model.business.BusinessType;
import unibo.citysimulation.model.business.EmployymentOffice;
import unibo.citysimulation.model.person.PersonFactory;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;
import unibo.citysimulation.utilities.Pair;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


        int numberOfBusinesses = 30; //
        for (int i = 0; i < numberOfBusinesses; i++) {
            BusinessFactory businessFactory = new BusinessFactory();
            Business business = businessFactory.createBusiness(BusinessFactory.getRandomBusinessType()).get();
            businesses.add(business);
        }

        // Create people
        this.people = new ArrayList<>();
        people = PersonFactory.createAllPeople(getInputModel().getNumberOfPeople(), zones, businesses);

        for (int i =  0; i < people.size(); i++) { //
            employymentOffice.addDisoccupiedPerson(people.get(i).get(i)); 
        }

        // Add people as observers to clock model
        clockModel.addObserver(new ClockObserverPerson(people));

        System.out.println("People groups created. " + people.size());
        for (var group : people) {
            System.out.println("Group size: " + group.size());
            
        }

        
        ////////////////////////////////////////////////////////////////
        // Print details of each person
        
        ////////////////////////////////////////////////////////////////
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
        System.out.println("Businesses present: " + res);
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
