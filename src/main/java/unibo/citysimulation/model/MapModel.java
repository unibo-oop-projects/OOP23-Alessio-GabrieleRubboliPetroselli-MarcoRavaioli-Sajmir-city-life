package unibo.citysimulation.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Model class representing the map.
 */
public class MapModel {
    private BufferedImage image;
    private int normClickedX = -1;
    private int normClickedY = -1;
    private int maxX = -1;
    private int maxY = -1;

    private List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> linesPointsCoordinates = new ArrayList<>();
    private List<Double> congestionsList = new ArrayList<>();

    /**
     * Constructs a MapModel object and loads the map image.
     */
    public MapModel() {
        loadMapImage();
    }

    public void setTransportInfos(List<TransportLine> lines) {
        linesPointsCoordinates = lines.stream()
                .map(line -> {
                    Pair<Integer, Integer> startPoint = line.getLinkedZones().getFirst().boundary().getCenter();
                    Pair<Integer, Integer> endPoint = line.getLinkedZones().getSecond().boundary().getCenter();
                    return new Pair<>(startPoint, endPoint);
                })
                .collect(Collectors.toList());

        congestionsList = lines.stream()
                .map(line -> line.getCongestion())
                .collect(Collectors.toList());
    }

    public Map<String, Pair<Pair<Integer, Integer>, Color>> getPersonInfos(List<DynamicPerson> people) {
        return people.stream()
                .filter(person -> person.getPosition().isPresent())
                .collect(Collectors.toMap(
                        person -> person.getPersonData().name(),
                        person -> new Pair<>(person.getPosition().get(), this.getPersonColor(person))));
    }

    public List<Color> getColorList() {
        return congestionsList.stream()
                .map(this::getLineColor)
                .collect(Collectors.toList());
    }

    public Color getLineColor(Double perc) {
        // Calcola il valore interpolato tra il verde e il rosso in base alla
        // percentuale
        int red = (int) (255 * perc);
        int green = (int) (255 * (1 - perc));
        int blue = 0; // Nessun blu, altrimenti otterremmo un colore viola

        // Assicurati che i valori siano compresi tra 0 e 255
        red = Math.min(Math.max(red, 0), 255);
        green = Math.min(Math.max(green, 0), 255);
        blue = Math.min(Math.max(blue, 0), 255);

        // Restituisci il colore RGB
        return new Color(red, green, blue);
    }

    public Color getPersonColor(DynamicPerson person) {
        if (person.getState() == PersonState.AT_HOME) {
            return Color.BLUE;
        } else {
            return Color.RED;
        }
    }

    public List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates() {
        return linesPointsCoordinates;
    }

    public void setCongestionsList(List<Double> congestionList) {
        this.congestionsList = congestionList;
    }

    public List<Double> getCongestionsList() {
        return congestionsList;
    }

    /**
     * Sets the last clicked coordinates after normalization.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     */
    public void setLastClickedCoordinates(int x, int y) {
        normClickedX = normalizeCoordinate(x, maxX);
        normClickedY = normalizeCoordinate(y, maxY);
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
    private int normalizeCoordinate(int c, int max) {
        return (int) (c / (double) maxX * 1000);
    }

    public int getNormX() {
        return normClickedX;
    }

    public int getNormY() {
        return normClickedY;
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
            // Load the image using a relative path within the classpath
            URL imageUrl = getClass().getResource("/unibo/citysimulation/image2.jpg");
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
        // Handle the error in a meaningful way, such as showing an error message to the
        // user
        JOptionPane.showMessageDialog(null, "Error loading map image: " + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
