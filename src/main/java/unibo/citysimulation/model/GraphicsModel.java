package unibo.citysimulation.model;

import java.util.Arrays;
import java.util.List;
import java.awt.Color;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.api.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

/**
 * Manages datasets for graphical representation of various simulation data.
 */
public class GraphicsModel {
    private List<XYSeriesCollection> datasets;
    private final List<String> names = Arrays.asList("Person State", "Transport Congestion", "Business Occupation");
    private final List<Integer> seriesCount = List.of(3, 7, 1);
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.CYAN);
    private int counter;
    private int columnCount;

    /**
     * Constructs a GraphicsModel and initializes datasets.
     */
    public GraphicsModel() {
        counter = 0;
        columnCount = 0;
        createDatasets(seriesCount);
    }

    private void createDatasets(final List<Integer> numCollections) {
        datasets = IntStream.range(0, names.size())
                .<XYSeriesCollection>mapToObj(i -> createDataset(numCollections.get(i)))
                .collect(Collectors.toList());
    }

    private XYSeriesCollection createDataset(final int numObjects) {
        final XYSeriesCollection collection = new XYSeriesCollection();

        IntStream.range(0, numObjects)
                .mapToObj(i -> {
                    final XYSeries series = new XYSeries("Object " + i, false);
                    series.add(0, 0);
                    return series;
                })
                .forEach(collection::addSeries);

        return collection;
    }

    /**
     * Clears all datasets.
     */
    public void clearDatasets() {
        columnCount = 0; 
        datasets.forEach(ds -> {
            for (int i = 0; i < ds.getSeriesCount(); i++) {
                ds.getSeries(i).clear();
            }
        });
    }

    /**
 * Updates the datasets with new values.
 *
 * @param people List of dynamic person objects representing the population.
 * @param lines List of transport line objects representing the transportation network.
 * @param businesses List of business objects representing the businesses.
 */
    public void updateDataset(final List<DynamicPerson> people, final List<TransportLine> lines, 
                final List<Business> businesses) {
        final List<Integer> states = getPeopleStateCounts(people);
        final List<Double> congestions = getTransportLinesCongestion(lines);
        final List<Integer> businessOccupations = getBusinessesOccupation(businesses);
        counter++;

        final List<XYSeriesCollection> datasetsCopy = datasets;

        updateSeries(datasetsCopy.get(0), states, counter);
        updateSeries(datasetsCopy.get(1), congestions, counter);
        updateSeries(datasetsCopy.get(2), businessOccupations, counter);

        columnCount++;

        if (columnCount > ConstantAndResourceLoader.MAX_COLUMNS) {
            final int columnsToRemove = columnCount - ConstantAndResourceLoader.MAX_COLUMNS;
            datasetsCopy.forEach(ds -> removeOldColumns(ds, columnsToRemove));

            columnCount = ConstantAndResourceLoader.MAX_COLUMNS;
        }

        datasets = datasetsCopy;
    }

    private void removeOldColumns(final XYSeriesCollection dataset, final int columnsToRemove) {
        for (int i = 0; i < columnsToRemove; i++) {
            for (int j = 0; j < dataset.getSeriesCount(); j++) {
                final XYSeries series = dataset.getSeries(j);
                if (!series.isEmpty()) {
                    series.remove(0);
                }
            }
        }
    }

    private void updateSeries(final XYSeriesCollection dataset, final List<? extends Number> values, final double counter) {
        IntStream.range(0, dataset.getSeriesCount()).forEach(i -> {
            dataset.getSeries(i).add(counter, values.get(i));
        });
    }

    private List<Integer> getPeopleStateCounts(final List<DynamicPerson> list) {
        return Arrays.asList(
                calculatePercentage(list, PersonState.AT_HOME),
                calculatePercentage(list, PersonState.MOVING),
                calculatePercentage(list, PersonState.WORKING)
        );
    }

    private int calculatePercentage(final List<DynamicPerson> list, final PersonState state) {
        return (int) (list.stream().filter(person -> person.getState() == state).count() * 100.0 / list.size());
    }

    private List<Double> getTransportLinesCongestion(final List<TransportLine> list) {
        return list.stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());
    }

    /**
     * Calculates the occupation percentage for each business in the given list.
     *
     * @param list the list of businesses
     * @return a list of integers representing the occupation percentage for each business
     */
    public List<Integer> getBusinessesOccupation(final List<Business> list) { //
        return list.stream()
            .map(business -> {
                final int maxEmployees = business.getMaxEmployees();
                final int currentEmployees = business.getEmployees().size();
                return maxEmployees > 0 ? (int) ((double) currentEmployees / maxEmployees * 100) : 0;
            })
            .collect(Collectors.toList());
    }

    /**
 * Retrieves the datasets.
 *
 * @return The list of XYSeriesCollection datasets.
 */
    public List<XYSeriesCollection> getDatasets() {
        return datasets;
    }

    /**
     * Returns the number of columns in the graphics model.
     *
     * @return the number of columns
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * Returns the list of colors for rendering the datasets.
     *
     * @return the list of colors
     */
    public List<Color> getColors() {
        return colors;
    }
}
