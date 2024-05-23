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
public class MapModelImpl implements MapModel{
    final private MapImageLoader imageLoader;
    final private MapCoordinateHandler coordinateHandler;
    final private TransportManager transportManager;

    /**
     * Constructs a MapModel object and loads the map image.
     */
    public MapModelImpl() {
        this.imageLoader = new MapImageLoader();
        this.coordinateHandler = new MapCoordinateHandler();
        this.transportManager = new TransportManager();
    }

    @Override
    public void startSimulation() {
        transportManager.setTransportCongestion(Collections.emptyList());
    }

    @Override
    public List<String> getTransportNames() {
        return transportManager.getTransportNames();
    }

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

    private Color getPersonColor(final DynamicPerson person) {
        return person.getState() == PersonState.AT_HOME ? Color.BLUE : Color.RED;
    }

    @Override
    public List<Color> getColorList() {
        return transportManager.getCongestionList().stream()
                .map(this::getColor)
                .collect(Collectors.toList());
    }

    private Color getColor(final Double perc) {
        if (!transportManager.isSimulationStarted()) {
            return Color.GRAY;
        }
        if (perc <= 50) {
            int green = (int) (128 + (127 * perc / 50));
            green = Math.max(0, Math.min(255, green));
            return new Color(0, green, 0);
        } else {
            final double adjustedPerc = (perc - 50) / 50;
            int red = (int) (255 * adjustedPerc);
            int green = (int) (255 * (1 - adjustedPerc));
            red = Math.max(0, Math.min(255, red)); // Ensure red is within the range
            green = Math.max(0, Math.min(255, green));
            return new Color(red, green, 0);
        }
    }

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

    @Override
    public void setTransportInfo(List<TransportLine> lines) {
    transportManager.setTransportInfo(lines);
    }

    @Override
    public void setTransportCongestion(final List<TransportLine> lines) {
        transportManager.setTransportCongestion(lines);
    }

    @Override
    public void setLastClickedCoordinates(final int x, final int y) {
        coordinateHandler.setLastClickedCoordinates(x, y);    
    }

    @Override
    public void setMaxCoordinates(final int x, final int y) {
        coordinateHandler.setMaxCoordinates(x, y);
    }

    @Override
    public int normalizeCoordinate(final int c, final int max) {
        return coordinateHandler.normalizeCoordinate(c, max);
    }

    @Override
    public int denormalizeCoordinate(final int c, final int max) {
        return coordinateHandler.denormalizeCoordinate(c, max);
    }

    @Override
    public int getNormX() {
        return coordinateHandler.getNormX();
    }

    @Override
    public int getNormY() {
        return coordinateHandler.getNormY();
    }

    @Override
    public int getMaxX() {
        return coordinateHandler.getMaxX();
    }

    @Override
    public int getMaxY() {
        return coordinateHandler.getMaxY();
    }

    @Override
    public BufferedImage getImage() {
        return imageLoader.getImage();
    }

}
