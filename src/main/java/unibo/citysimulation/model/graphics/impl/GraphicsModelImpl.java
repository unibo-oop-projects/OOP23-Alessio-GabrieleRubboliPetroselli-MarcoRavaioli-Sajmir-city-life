package unibo.citysimulation.model.graphics.impl;

import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.graphics.api.GraphicsModel;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.transport.TransportLine;

/**
 * Manages datasets for graphical representation of various simulation data.
 */
public class GraphicsModelImpl implements GraphicsModel {
    private final DatasetManager datasetManager;
    private final List<String> names = Arrays.asList("Person State", "Transport Congestion", "Business Occupation");
    private final List<Integer> seriesCount = List.of(3, 7, 1);
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.CYAN);

    /**
     * Constructs a GraphicsModel and initializes datasets.
     */
    public GraphicsModelImpl() {
        this.datasetManager = new DatasetManager(seriesCount, names);
    }

    /**
     * Clears all datasets.
     */
    @Override
    public void clearDatasets() {
        datasetManager.clearDatasets();
    }

    /**
     * Updates the datasets with new values.
     *
     * @param people     List of dynamic person objects representing the population.
     * @param lines      List of transport line objects representing the
     *                   transportation network.
     * @param businesses List of business objects representing the businesses.
     */
    @Override
    public void updateDataset(final List<DynamicPerson> people, final List<TransportLine> lines,
            final List<Business> businesses) {
        datasetManager.updateDataset(StatisticCalculator.getPeopleStateCounts(people),
                StatisticCalculator.getTransportLinesCongestion(lines),
                StatisticCalculator.getBusinessesOccupation(businesses));
    }

    /**
     * Retrieves the datasets.
     *
     * @return The list of XYSeriesCollection datasets.
     */
    @Override
    public List<XYSeriesCollection> getDatasets() {
        return datasetManager.getDatasets();
    }

    /**
     * Returns the number of columns in the graphics model.
     *
     * @return the number of columns
     */
    @Override
    public List<String> getNames() {
        return names;
    }

    /**
     * Returns the list of colors for rendering the datasets.
     *
     * @return the list of colors
     */
    @Override
    public List<Color> getColors() {
        return colors;
    }
}
