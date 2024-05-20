package unibo.citysimulation.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.model.business.Business;
import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class GraphicsModel {
    private List<XYSeriesCollection> datasets;
    private List<String> names = Arrays.asList("Person State", "Transport Congestion", "Business Occupation");
    private int counter = 0;
    private int columnCount = 0;

    public GraphicsModel(){
        createDatasets(List.of(3, 7, 1));
    }

    private void createDatasets(List<Integer> numCollections) {
        datasets =  IntStream.range(0, names.size())
                .<XYSeriesCollection>mapToObj(i -> createDataset(numCollections.get(i)))
                .collect(Collectors.toList());
    }

    private XYSeriesCollection createDataset(int numObjects) {
        XYSeriesCollection collection = new XYSeriesCollection();
    
        IntStream.range(0, numObjects)
                .mapToObj(i -> {
                    XYSeries series = new XYSeries("Object " + i, false);
                    series.add(0, 0); // Aggiungi la coppia di valori (0, 0)
                    return series;
                })
                .forEach(collection::addSeries);
    
        return collection;
    }

    public void clearDatasets() {
        synchronized (datasets) {
            columnCount = 0; // Resetta anche il contatore delle colonne
            for (XYSeriesCollection dataset : datasets) {
                for (int i = 0; i < dataset.getSeriesCount(); i++) {
                    dataset.getSeries(i).clear();
                }
            }
        }
    }

    public synchronized void updateDataset(List<Integer> states, List<Double> congestions, List<Business> businesses,
            double counter) {
        synchronized (datasets) {
            if (columnCount > ConstantAndResourceLoader.MAX_COLUMNS) {
                int columnsToRemove = columnCount - ConstantAndResourceLoader.MAX_COLUMNS;
                datasets.forEach(dataset -> {
                    synchronized (dataset) {
                        IntStream.range(0, columnsToRemove).forEach(i -> {
                            IntStream.range(0, dataset.getSeriesCount()).forEach(j -> {
                                XYSeries series = dataset.getSeries(j);
                                if (!series.isEmpty()) {
                                    series.remove(0);
                                }
                            });
                        });
                    }
                });
                columnCount = ConstantAndResourceLoader.MAX_COLUMNS;
            }

            columnCount++;

            updateSeries(datasets.get(0), states, counter);
            updateSeries(datasets.get(1), congestions, counter);
            datasets.get(2).getSeries(0).add(counter, business);
        }
    }

    private void updateSeries(XYSeriesCollection dataset, List<? extends Number> values, double counter) {
        IntStream.range(0, dataset.getSeriesCount()).forEach(i -> {
            dataset.getSeries(i).add(counter, values.get(i));
        });
    }

    public List<Integer> getPeopleStateCounts(List<DynamicPerson> list){
        return Arrays.asList(
                (int) list.stream().filter(person -> person.getState() == PersonState.AT_HOME).count() * 100 / list.size(),
                (int) list.stream().filter(person -> person.getState() == PersonState.MOVING).count() * 100 / list.size(),
                (int) list.stream().filter(person -> person.getState() == PersonState.WORKING).count() * 100 / list.size());
    }        

    public List<Double> getTransportLinesCongestion(List<TransportLine> list) {
        return list.stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());
    }

    public List<Business> getBusinessesOccupation(List<Business> list) { //
        return list; 
    }

    public List<XYSeriesCollection> getDatasets(){
        return datasets;
    }

    public int getCounter(){
        return counter++;
    }

    public int getColumnCount(){
        return columnCount;
    }

    public List<String> getNames(){
        return names;
    }
}
