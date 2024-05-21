package unibo.citysimulation.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
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
public class MapModel {
    private BufferedImage image;
    private int normClickedX;
    private int normClickedY;
    private int maxX;
    private int maxY;
    private boolean simulationStarted = false;
    private List<String> linesName = Collections.emptyList();
    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = Collections.emptyList();
    private List<Double> congestionsList = Collections.emptyList();

    /**
     * Constructs a MapModel object and loads the map image.
     */
    public MapModel() {
        normClickedX = -1;
        normClickedY = -1;
        maxX = -1;
        maxY = -1;
        loadMapImage();
    }

    public void startSimulation() {
        simulationStarted = true;
    }

    public List<String> getTransportNames() {
        return linesName;
    }

    public Map<Integer,Pair<Integer,Integer>> getBusinessInfos(List<Business> businesses) {
        return businesses.stream()
            .collect(Collectors.toMap(
                businesses::indexOf,
                business -> new Pair<>(
                    denormalizeCoordinate(business.getPosition().getFirst(), maxX),
                    denormalizeCoordinate(business.getPosition().getSecond(), maxY))));
            
            
    }

    public Map<String, Pair<Pair<Integer, Integer>, Color>> getPersonInfos(List<DynamicPerson> people) {
        return people.stream()
            .filter(person -> person.getPosition().isPresent())
            .collect(Collectors.toMap(
                person -> person.getPersonData().name(),
                person -> new Pair<>(
                    new Pair<>(denormalizeCoordinate(person.getPosition().get().getFirst(), maxX),
                        denormalizeCoordinate(person.getPosition().get().getSecond(), maxY)),
                    getPersonColor(person))));
    }

    private Color getPersonColor(DynamicPerson person) {
        return person.getState() == PersonState.AT_HOME ? Color.BLUE : Color.RED;
    }

    public List<Color> getColorList() {
        return congestionsList.stream()
            .map(this::getColor)
            .collect(Collectors.toList());
    }

    private Color getColor(Double perc) {
        if (!simulationStarted) {
            return Color.GRAY;
        }
        if (perc <= 50) {
            int green = (int) (128 + (127 * perc / 50));
            return new Color(0, green, 0);
        } else {
            double adjustedPerc = (perc - 50) / 50;
            int red = (int) (255 * adjustedPerc);
            int green = (int) (255 * (1 - adjustedPerc));
            return new Color(red, green, 0);
        }
    }

    public List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates() {
        return linesPointsCoordinates.stream()
            .map(pair -> new Pair<>(
                new Pair<>(denormalizeCoordinate(pair.getFirst().getFirst(), maxX),
                    denormalizeCoordinate(pair.getFirst().getSecond(), maxY)),
                new Pair<>(denormalizeCoordinate(pair.getSecond().getFirst(), maxX),
                    denormalizeCoordinate(pair.getSecond().getSecond(), maxY))))
            .collect(Collectors.toList());
    }

    public void setTransportInfo(List<TransportLine> lines) {
        linesPointsCoordinates = lines.stream()
            .map(line -> {
                Pair<Integer, Integer> startPoint = line.getLinkedZones().getFirst().boundary().getCenter();
                Pair<Integer, Integer> endPoint = line.getLinkedZones().getSecond().boundary().getCenter();
                return new Pair<>(startPoint, endPoint);
            })
            .collect(Collectors.toList());

        linesName = lines.stream()
            .map(TransportLine::getName)
            .collect(Collectors.toList());
    }

    public void setTransportCongestion(List<TransportLine> lines) {
        congestionsList = lines.stream()
            .map(TransportLine::getCongestion)
            .collect(Collectors.toList());
    }

    /**
     * Sets the last clicked coordinates after normalization.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     */
    public void setLastClickedCoordinates(int x, int y) {
        normClickedX = x;
        normClickedY = y;
    }

    /**
     * Sets the maximum coordinates of the map.
     *
     * @param x The maximum x-coordinate.
     * @param y The maximum y-coordinate.
     */
    public void setMaxCoordinates(int x, int y) {
        maxX = x;
        maxY = y;
    }

    /**
     * Normalizes a coordinate based on the maximum value.
     *
     * @param c   The coordinate to normalize.
     * @param max The maximum value of the coordinate.
     * @return The normalized coordinate.
     */
    public int normalizeCoordinate(int c, int max) {
        return (int) (c / (double) max * 1000);
    }

    public int denormalizeCoordinate(int c, int max) {
        return (int) (c / 1000.0 * max);
    }

    public int getNormX() {
        return normClickedX;
    }

    public int getNormY() {
        return normClickedY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    /**
     * Retrieves the image of the map.
     *
     * @return The BufferedImage object representing the map image.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Loads the map image from the resources.
     */
    private void loadMapImage() {
        try {
            URL imageUrl = getClass().getResource("/unibo/citysimulation/image3.png");
            if (imageUrl != null) {
                image = ImageIO.read(imageUrl);
            } else {
                throw new IOException("Image file not found");
            }
        } catch (IOException e) {
            handleImageLoadError(e);
        }
    }

    /**
     * Handles errors that occur during image loading.
     *
     * @param e The IOException instance representing the error.
     */
    private void handleImageLoadError(IOException e) {
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}
