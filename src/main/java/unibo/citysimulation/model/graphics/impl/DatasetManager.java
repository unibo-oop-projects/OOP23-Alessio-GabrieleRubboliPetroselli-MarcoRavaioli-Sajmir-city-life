package unibo.citysimulation.model.graphics.impl;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

/**
 * Manages the datasets used for graphical representation in the city
 * simulation.
 */
public class DatasetManager {
    private List<XYSeriesCollection> datasets;
    private int counter;
    private int columnCount;

    /**
     * Constructs a DatasetManager and initializes the datasets.
     *
     * @param seriesCount a list of integers representing the number of series for
     *                    each dataset
     * @param names       a list of strings representing the names of each dataset
     */
    public DatasetManager(final List<Integer> seriesCount, final List<String> names) {
        counter = 0;
        columnCount = 0;
        createDatasets(seriesCount, names);
    }

    private void createDatasets(final List<Integer> numCollections, final List<String> names) {
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
     * @param peopleState          List of integers representing the state counts of
     *                             people.
     * @param linesCongestion      List of doubles representing the congestion
     *                             levels of transport lines.
     * @param businessesOccupation List of integers representing the occupation
     *                             levels of businesses.
     */
    public void updateDataset(final List<Integer> peopleState, final List<Double> linesCongestion,
            final List<Integer> businessesOccupation) {
        counter++;

        updateSeries(datasets.get(0), peopleState, counter);
        updateSeries(datasets.get(1), linesCongestion, counter);
        updateSeries(datasets.get(2), businessesOccupation, counter);

        columnCount++;

        if (columnCount > ConstantAndResourceLoader.MAX_COLUMNS) {
            final int columnsToRemove = columnCount - ConstantAndResourceLoader.MAX_COLUMNS;
            datasets.forEach(ds -> removeOldColumns(ds, columnsToRemove));

            columnCount = ConstantAndResourceLoader.MAX_COLUMNS;
        }
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

    private void updateSeries(final XYSeriesCollection dataset, final List<? extends Number> values,
            final double counter) {
        IntStream.range(0, dataset.getSeriesCount()).forEach(i -> {
            dataset.getSeries(i).add(counter, values.get(i));
        });
    }

    /**
     * Retrieves the datasets.
     *
     * @return The list of XYSeriesCollection datasets.
     */
    public List<XYSeriesCollection> getDatasets() {
        return datasets;
    }
}
