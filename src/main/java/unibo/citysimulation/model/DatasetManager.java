package unibo.citysimulation.model;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.utilities.ConstantAndResourceLoader;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class DatasetManager {
    private List<XYSeriesCollection> datasets;
    private int counter;
    private int columnCount;

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
     * @param people     List of dynamic person objects representing the population.
     * @param lines      List of transport line objects representing the
     *                   transportation network.
     * @param businesses List of business objects representing the businesses.
     */
    public void updateDataset(final List<Integer> peopleState, final List<Double> linesCongestion,
            final List<Integer> businessesOccupation) {
        counter++;

        final List<XYSeriesCollection> datasetsCopy = datasets;

        updateSeries(datasetsCopy.get(0), peopleState, counter);
        updateSeries(datasetsCopy.get(1), linesCongestion, counter);
        updateSeries(datasetsCopy.get(2), businessesOccupation, counter);

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
    protected List<XYSeriesCollection> getDatasets() {
        return datasets;
    }
}