package unibo.citysimulation.model.map.impl;

import java.awt.image.BufferedImage;

import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import java.util.Map;
import java.util.Collections;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.map.api.MapModel;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.api.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Model class representing the map.
 */
public class MapModelImpl implements MapModel {
    private static final int PERCENT_50 = 50;
    private static final int COLOR_MAX = 255;

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
    public List<Pair<Integer, Integer>> getBusinessInfos(final List<Business> businesses) {
        final int maxX = coordinateHandler.getMaxX();
        final int maxY = coordinateHandler.getMaxY();

        return businesses.stream()
            .map(business -> denormalizePosition(business.getPosition(), maxX, maxY))
            .collect(Collectors.toList());
    }

    /**
     * Gets the information of people, mapping each person's name to their
     * coordinates and color.
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
                                denormalizePosition(person.getPosition().get(), maxX, maxY),
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
            // Green component decreases from 255 to 0 as percentage increases from 0 to 50
            final int green = (int) (COLOR_MAX - (perc / PERCENT_50) * COLOR_MAX);
            return new Color(0, green, 0);
        } else {
            // Red component increases from 0 to 255 and green component decreases from 255
            // to 0 as percentage increases from 50 to 100
            final double adjustedPerc = (perc - PERCENT_50) / PERCENT_50;
            final int red = (int) (adjustedPerc * COLOR_MAX);
            final int green = (int) ((1 - adjustedPerc) * COLOR_MAX);
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
        final int maxX = coordinateHandler.getMaxX();
        final int maxY = coordinateHandler.getMaxY();
        return transportManager.getLinesPointsCoordinates().stream()
                .map(pair -> new Pair<>(
                        denormalizePosition(pair.getFirst(), maxX, maxY),
                        denormalizePosition(pair.getSecond(), maxX, maxY)))
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
     * Sets the transport congestion information with the given list of transport
     * lines.
     *
     * @param lines the list of transport lines
     */
    @Override
    public void setTransportCongestion(final List<TransportLine> lines) {
        transportManager.setTransportCongestion(lines);
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

    private int denormalizeCoordinate(final int c, final int max) {
        return coordinateHandler.denormalizeCoordinate(c, max);
    }

    private Pair<Integer, Integer> denormalizePosition(final Pair<Integer, Integer> position, final int maxX,
            final int maxY) {
        return new Pair<>(
                denormalizeCoordinate(position.getFirst(), maxX),
                denormalizeCoordinate(position.getSecond(), maxY));
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
