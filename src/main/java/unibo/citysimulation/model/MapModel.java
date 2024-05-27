package unibo.citysimulation.model;

import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.image.BufferedImage;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.Pair;

/**
 * Model interface representing the map.
 */
public interface MapModel {

    /**
     * Starts the simulation.
     */
    void startSimulation();

    /**
     * Retrieves the names of transport lines.
     *
     * @return A list of transport line names.
     */
    List<String> getTransportNames();

    /**
     * Retrieves information about businesses.
     *
     * @param businesses The list of businesses.
     * @return A map where the key is the index of the business in the list and the value is the pair of denormalized coordinates.
     */
    Map<Integer, Pair<Integer, Integer>> getBusinessInfos(List<Business> businesses);

    /**
     * Retrieves information about dynamic people.
     *
     * @param people The list of dynamic people.
     * @return A map where the key is the person's name and the value is a pair of their denormalized coordinates and their color.
     */
    Map<String, Pair<Pair<Integer, Integer>, Color>> getPersonInfos(List<DynamicPerson> people);

    /**
     * Retrieves a list of colors based on the congestion percentages.
     *
     * @return A list of colors representing congestion levels.
     */
    List<Color> getColorList();

    /**
     * Retrieves the points coordinates of the transport lines.
     *
     * @return A list of pairs, each representing the start and end coordinates of a transport line.
     */
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getLinesPointsCoordinates();

    /**
     * Sets the transport information.
     *
     * @param lines The list of transport lines.
     */
    void setTransportInfo(List<TransportLine> lines);

    /**
     * Sets the transport congestion levels.
     *
     * @param lines The list of transport lines.
     */
    void setTransportCongestion(List<TransportLine> lines);

    /**
     * Sets the last clicked coordinates after normalization.
     *
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     */
    void setLastClickedCoordinates(int x, int y);

    /**
     * Sets the maximum coordinates of the map.
     *
     * @param x The maximum x-coordinate.
     * @param y The maximum y-coordinate.
     */
    void setMaxCoordinates(int x, int y);

    /**
     * Normalizes a coordinate based on the maximum value.
     *
     * @param c The coordinate to normalize.
     * @param max The maximum value of the coordinate.
     * @return The normalized coordinate.
     */
    int normalizeCoordinate(int c, int max);

    /**
     * Denormalizes a coordinate based on the maximum value.
     *
     * @param c The coordinate to denormalize.
     * @param max The maximum value of the coordinate.
     * @return The denormalized coordinate.
     */
    int denormalizeCoordinate(int c, int max);

    /**
     * Gets the normalized x-coordinate of the last click.
     *
     * @return The normalized x-coordinate.
     */
    int getNormX();

    /**
     * Gets the normalized y-coordinate of the last click.
     *
     * @return The normalized y-coordinate.
     */
    int getNormY();

    /**
     * Gets the maximum x-coordinate of the map.
     *
     * @return The maximum x-coordinate.
     */
    int getMaxX();

    /**
     * Gets the maximum y-coordinate of the map.
     *
     * @return The maximum y-coordinate.
     */
    int getMaxY();

    /**
     * Retrieves the image of the map.
     *
     * @return The BufferedImage object representing the map image.
     */
    BufferedImage getImage();
}
