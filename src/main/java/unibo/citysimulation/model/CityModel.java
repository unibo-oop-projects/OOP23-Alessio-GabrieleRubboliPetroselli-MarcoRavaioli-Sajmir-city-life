/**
 * Represents the model of the city simulation, containing zones, transports,
 * businesses, and people.
 */
package unibo.citysimulation.model;

import unibo.citysimulation.model.business.employye.impl.EmployymentOffice;
import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactory;
import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.model.clock.impl.ClockModelImpl;
import unibo.citysimulation.model.clock.impl.ClockObserverPerson;
import unibo.citysimulation.model.clock.impl.CloclObserverBusiness;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.creation.PersonCreation;
import unibo.citysimulation.model.transport.TransportCreation;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Boundary;
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
import java.util.Optional;
import java.util.Random;
import java.util.Collections;

/**
 * Represents the model of the city simulation, containing zones, transports,
 * businesses, and people.
 */
public final class CityModel {
    private final List<Zone> zones;
    private List<TransportLine> transports;
    private final List<Business> businesses;
    private List<List<DynamicPerson>> people;
    private final MapModelImpl mapModel;
    private final ClockModel clockModel;
    private final InputModel inputModel;
    private final GraphicsModel graphicsModel;
    private final EmployymentOffice employymentOffice;
    private int frameWidth;
    private int frameHeight;
    private static final Random RANDOM = new Random();
    private int totalBusinesses;

    /**
     * Constructs a CityModel object with default settings.
     */
    public CityModel() {
        takeFrameSize();

        this.mapModel = new MapModelImpl();
        this.clockModel = new ClockModelImpl(ConstantAndResourceLoader.SIMULATION_TOTAL_DAYS);
        this.inputModel = new InputModel();
        this.graphicsModel = new GraphicsModel();
        this.zones = ZoneFactory.createZonesFromFile();
        this.transports = TransportCreation.createTransportsFromFile(zones);
        this.businesses = new ArrayList<>();
        this.employymentOffice = new EmployymentOffice();
    }

    /**
     * @return a random zone from the list of zones.
     */
    public Zone getRandomZone() {
        if (zones.isEmpty()) {
            throw new IllegalStateException("No zones available.");
        }
        return zones.get(RANDOM.nextInt(zones.size()));
    }

    /**
     * @return the zone from a position, if present.
     * 
     * @param position the position to check.
     */
    public Optional<Zone> getZoneByPosition(final Pair<Integer, Integer> position) {
        return zones.stream()
                .filter(zone -> isPositionInZone(position, zone))
                .findFirst();
    }

    /**
     * @return if a position is inside a certain zone or not.
     * 
     * @param position the position to check.
     * @param zone     the zone to check.
     */
    private boolean isPositionInZone(final Pair<Integer, Integer> position, final Zone zone) {
        final int x = position.getFirst();
        final int y = position.getSecond();
        final Boundary boundary = zone.boundary();
        return x >= boundary.getX() && x <= (boundary.getX() + boundary.getWidth())
                && y >= boundary.getY() && y <= (boundary.getY() + boundary.getHeight());
    }

    /**
     * Creates the remaining entities missing in the simulation start process.
     */
    public void createEntities() {
        graphicsModel.clearDatasets();

        transports = TransportCreation.createTransportsFromFile(zones);
        transports.forEach(t -> t.setCapacity(t.getCapacity() * inputModel.getCapacity() / 100));

        // Create zone table
        ZoneTableCreation.createAndAddPairs(zones, transports);
        final int numberOfPeople = getInputModel().getNumberOfPeople();

        final int numberOfBusinesses = inputModel.getNumberOfBusiness();
        calculateTotalBusinesses(numberOfPeople, numberOfBusinesses);

        createBusinesses();

        // Create people
        this.people = new ArrayList<>();
        people = PersonCreation.createAllPeople(getInputModel().getNumberOfPeople(), zones, businesses);

        for (final List<DynamicPerson> group : people) {
            for (final DynamicPerson person : group) {
                employymentOffice.addDisoccupiedPerson(person);
            }
        }
        clockModel.addObserver(new ClockObserverPerson(people));

        clockModel.addObserver(new CloclObserverBusiness(businesses, employymentOffice));
    }

    /**
     * Creates businesses in the city model based on the zones and their business percentages.
     * The total number of businesses is distributed among the zones according to their business percentages.
     * If there are remaining businesses after distributing among the zones, they are randomly assigned to the zones.
     */
    public void createBusinesses() {
        int remainingBusinesses = totalBusinesses;

        for (final Zone zone : zones) {
            final int zoneBusinessCount = (int) (totalBusinesses * zone.businessPercents() / 100.0);
            remainingBusinesses -= zoneBusinessCount;
            for (int i = 0; i < zoneBusinessCount; i++) {
                BusinessFactory.getRandomBusiness(List.of(zone)).ifPresent(businesses::add);
            }
        }
        for (int i = 0; remainingBusinesses > 0 && i < zones.size(); i++) {
            final Zone zone = zones.get(i);
            BusinessFactory.getRandomBusiness(List.of(zone)).ifPresent(businesses::add);
            remainingBusinesses--;
        }
    }

    /**
     * Calculates the total number of businesses in the city model.
     * @param numberOfPeople
     * @param numberOfBusinesses
     */
    public void calculateTotalBusinesses(final int numberOfPeople, final int numberOfBusinesses) {
        this.totalBusinesses = numberOfPeople / 10 + numberOfBusinesses;
    }

    /**
     * Returns the total number of businesses in the city model.
     *
     * @return the total number of businesses
     */
    public int getTotalBusinesses() {
        return this.totalBusinesses;
    }
    /**
     * Returns the number of people in the city model.
     * @param zone
     * @return
     */
    public double avaragePayZone(final Zone zone) {
        double avarage = 0;
        for (final Business business : businesses) {
            if (business.getZone().name().equals(zone.name())) {
                avarage += business.getEmployees().size() * business.calculatePay();
            }
        }
        return avarage;
    }
    /**
     * Returns the number of direct lines from a zone.
     *
     * @param zone the zone to check
     * @return the number of direct lines from the zone
     */
    public int getNumberOfDirectLinesFromZone(final Zone zone) {
        int numberOfDirectLines = 0;
        for (final TransportLine transportLine : transports) {
            if (transportLine.getLink().getFirst().equals(zone) || transportLine.getLink().getSecond().equals(zone)) {
                numberOfDirectLines++;
            }
        }
        return numberOfDirectLines;
    }
    /**
 * Adjusts the frame size based on the screen dimensions.
 */
    public void takeFrameSize() {
        // Get the screen size
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int maxWidth = (int) (screenSize.getWidth() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);
        final int maxHeight = (int) (screenSize.getHeight() * ConstantAndResourceLoader.SCREEN_SIZE_PERCENTAGE);

        // Calculate the frame dimensions based on the maximum dimensions
        final int frameHeight = maxHeight > (maxWidth / 2) ? maxWidth / 2 : maxHeight;
        final int frameWidth = frameHeight * 2;

        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
    }

    /**
     * Gets the map model associated with this city model.
     * 
     * @return The map model.
     */
    public MapModelImpl getMapModel() {
        return this.mapModel;
    }

    /**
     * Gets the clock model associated with this city model.
     * 
     * @return The clock model.
     */
    public ClockModel getClockModel() {
        return this.clockModel;
    }

    /**
     * @return the input model.
     */
    public InputModel getInputModel() {
        return this.inputModel;
    }

    /**
     * @return the graphics model.
     */
    public GraphicsModel getGraphicsModel() {
        return this.graphicsModel;
    }

    /**
     * Gets the list of zones in the city model.
     * 
     * @return The list of zones.
     */
    public List<Zone> getZones() {
        return Collections.unmodifiableList(this.zones);
    }

    /**
     * Gets the list of transport lines in the city model.
     * 
     * @return The list of transport lines.
     */
    public List<TransportLine> getTransportLines() {
        return Collections.unmodifiableList(this.transports);
    }

    /**
     * Gets the list of businesses in the city model.
     * 
     * @return The list of businesses.
     */
    public List<Business> getBusinesses() {
        return Collections.unmodifiableList(this.businesses);
    }

    /**
     * Gets the list of all the people in the city model.
     * 
     * @return a list with all the people from every zone of the map.
     */
    public List<DynamicPerson> getAllPeople() {
        return people.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
 * Checks if people are present in the city model.
 *
 * @return True if people are present, false otherwise.
 */
    public boolean isPeoplePresent() {
        return this.people != null;
    }

    /**
     * Checks if there are businesses present in the city model.
     * 
     * @return true if there are businesses present, false otherwise.
     */
    public boolean isBusinessesPresent() {
        return this.businesses != null;
    }

    /**
 * Sets the frame size for the city model.
 *
 * @param frameSize A pair containing the width and height of the frame.
 */
    public void setFrameSize(final Pair<Integer, Integer> frameSize) {
        this.frameWidth = frameSize.getFirst();
        this.frameHeight = frameSize.getSecond();
    }

    /**
 * Gets the frame width of the city model.
 *
 * @return The frame width.
 */
    public int getFrameWidth() {
        return this.frameWidth;
    }

    /**
 * Gets the frame height of the city model.
 *
 * @return The frame height.
 */
    public int getFrameHeight() {
        return this.frameHeight;
    }

    /**
 * Gets the number of people residing in a specific zone.
 *
 * @param zoneName The name of the zone.
 * @return The number of people residing in the specified zone.
 */
    public int getPeopleInZone(final String zoneName) {
        return (int) people.stream()
                .flatMap(List::stream)
                .filter(p -> p.getPersonData().residenceZone().name().equals(zoneName))
                .count();
    }

    /**
 * Gets the number of businesses in a specific zone.
 *
 * @param zoneName The name of the zone.
 * @return The number of businesses in the specified zone.
 */
    public int getBusinessesInZone(final String zoneName) {
        return (int) businesses.stream()
                .filter(b -> b.getZone().name().equals(zoneName))
                .count();
    }
}
