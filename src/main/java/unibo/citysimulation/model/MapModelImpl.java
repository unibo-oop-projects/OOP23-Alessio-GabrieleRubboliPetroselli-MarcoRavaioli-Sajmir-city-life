package unibo.citysimulation.model;

import java.awt.image.BufferedImage;


import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import java.util.Map;
import java.util.Collections;
import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Model class representing the map.
 */
public class MapModelImpl implements MapModel {
    private static final int PERCENT_50 = 50;
    private static final int COLOR_MAX = 255;
    private static final int GREEN_BASE = 128;

    private final MapImageLoader imageLoader;
    private final MapCoordinateHandler coordinateHandler;
    private final TransportManager transportManager;

    /**
     * Constructs a MapModel object and loads the map image.
     */
    public MapModelImpl() {
        this.imageLoader = new MapImageLoader();
        this.coordinateHandler = new MapCoordinateHandler();
        this.transportManager = new TransportManager();
    }

    /**
     * Starts the simulation by setting the initial transport congestion.
     */
    @Override
    public void startSimulation() {
        transportManager.setTransportCongestion(Collections.emptyList());
    }

    /**
     * Gets the names of the transport lines.
     *
     * @return the list of transport line names
     */
    @Override
    public List<String> getTransportNames() {
        return transportManager.getTransportNames();
    }

    /**
     * Gets the business information, mapping each business to its coordinates.
     *
     * @param businesses the list of businesses
     * @return a map of business indices to their coordinates
     */
    @Override
    public Map<Integer, Pair<Integer, Integer>> getBusinessInfos(final List<Business> businesses) {
        final int maxX = coordinateHandler.getMaxX();
        final int maxY = coordinateHandler.getMaxY();
        return businesses.stream()
                .collect(Collectors.toMap(
                        businesses::indexOf,
                        business -> new Pair<>(
                                denormalizeCoordinate(business.getPosition().getFirst(), maxX),
                                denormalizeCoordinate(business.getPosition().getSecond(), maxY))));    
    }

    /**
     * Gets the information of people, mapping each person's name to their coordinates and color.
     *
     * @param people the list of dynamic people
     * @return a map of person names to their coordinates and color
     */
    @Override
    public Map<String, Pair<Pair<Integer, Integer>, Color>> getPersonInfos(final List<DynamicPerson> people) {
        final int maxX = coordinateHandler.getMaxX();
        final int maxY = coordinateHandler.getMaxY();
        return people.stream()
                .filter(person -> person.getPosition().isPresent())
                .collect(Collectors.toMap(
                        person -> person.getPersonData().name(),
                        person -> new Pair<>(
                                new Pair<>(denormalizeCoordinate(person.getPosition().get().getFirst(), maxX),
                                        denormalizeCoordinate(person.getPosition().get().getSecond(), maxY)),
                                getPersonColor(person))));
    }

    /**
     * Gets the color of a person based on their state.
     *
     * @param person the dynamic person
     * @return the color representing the person's state
     */
    private Color getPersonColor(final DynamicPerson person) {
        return person.getState() == PersonState.AT_HOME ? Color.BLUE : Color.RED;
    }

    /**
     * Gets the list of colors representing congestion levels.
     *
     * @return the list of colors
     */
    @Override
    public List<Color> getColorList() {
        return transportManager.getCongestionList().stream()
                .map(this::getColor)
                .collect(Collectors.toList());
    }

    /**
     * Gets the color representing the congestion percentage.
     *
     * @param perc the congestion percentage
     * @return the color representing the congestion level
     */
    private Color getColor(final Double perc) {
        if (!transportManager.isSimulationStarted()) {
            return Color.GRAY;
        }
        if (perc <= PERCENT_50) {
            int green = (int) (GREEN_BASE + ((GREEN_BASE - 1) * perc / PERCENT_50));
            green = Math.max(0, Math.min(COLOR_MAX, green));
            return new Color(0, green, 0);
        } else {
            final double adjustedPerc = (perc - PERCENT_50) / PERCENT_50;
            int red = (int) (COLOR_MAX * adjustedPerc);
            int green = (int) (COLOR_MAX * (1 - adjustedPerc));
            red = Math.max(0, Math.min(COLOR_MAX, red)); // Ensure red is within the range
            green = Math.max(0, Math.min(COLOR_MAX, green));
            return new Color(red, green, 0);
        }
    }

    /**
     * Gets the coordinates of line points for all transport lines.
     *
     * @return the list of pairs of start and end coordinates of the lines
     */
    @Override
    public List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates() {
        return transportManager.getLinesPointsCoordinates().stream()
        .map(pair -> new Pair<>(
                new Pair<>(denormalizeCoordinate(pair.getFirst().getFirst(), coordinateHandler.getMaxX()),
                        denormalizeCoordinate(pair.getFirst().getSecond(), coordinateHandler.getMaxY())),
                new Pair<>(denormalizeCoordinate(pair.getSecond().getFirst(), coordinateHandler.getMaxX()),
                        denormalizeCoordinate(pair.getSecond().getSecond(), coordinateHandler.getMaxY()))))
        .collect(Collectors.toList());
    }

    /**
     * Sets the transport information with the given list of transport lines.
     *
     * @param lines the list of transport lines
     */
    @Override
    public void setTransportInfo(final List<TransportLine> lines) {
    transportManager.setTransportInfo(lines);
    }

    /**
     * Sets the transport congestion information with the given list of transport lines.
     *
     * @param lines the list of transport lines
     */
    @Override
    public void setTransportCongestion(final List<TransportLine> lines) {
        transportManager.setTransportCongestion(lines);
    }

    /**
     * Sets the last clicked coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    @Override
    public void setLastClickedCoordinates(final int x, final int y) {
        coordinateHandler.setLastClickedCoordinates(x, y);    
    }

    /**
     * Sets the maximum coordinates.
     *
     * @param x the maximum x-coordinate
     * @param y the maximum y-coordinate
     */
    @Override
    public void setMaxCoordinates(final int x, final int y) {
        coordinateHandler.setMaxCoordinates(x, y);
    }

    /**
     * Normalizes the given coordinate based on the maximum value.
     *
     * @param c the coordinate to normalize
     * @param max the maximum value for normalization
     * @return the normalized coordinate
     */
    @Override
    public int normalizeCoordinate(final int c, final int max) {
        return coordinateHandler.normalizeCoordinate(c, max);
    }

    /**
     * Denormalizes the given coordinate based on the maximum value.
     *
     * @param c the coordinate to denormalize
     * @param max the maximum value for denormalization
     * @return the denormalized coordinate
     */
    @Override
    public int denormalizeCoordinate(final int c, final int max) {
        return coordinateHandler.denormalizeCoordinate(c, max);
    }

    /**
     * Gets the normalized x-coordinate.
     *
     * @return the normalized x-coordinate
     */
    @Override
    public int getNormX() {
        return coordinateHandler.getNormX();
    }

    /**
     * Gets the normalized y-coordinate.
     *
     * @return the normalized y-coordinate
     */
    @Override
    public int getNormY() {
        return coordinateHandler.getNormY();
    }

    /**
     * Gets the maximum x-coordinate.
     *
     * @return the maximum x-coordinate
     */
    @Override
    public int getMaxX() {
        return coordinateHandler.getMaxX();
    }

    /**
     * Gets the maximum y-coordinate.
     *
     * @return the maximum y-coordinate
     */
    @Override
    public int getMaxY() {
        return coordinateHandler.getMaxY();
    }

    /**
     * Gets the image of the map.
     *
     * @return the map image
     */
    @Override
    public BufferedImage getImage() {
        return imageLoader.getImage();
    }

}
