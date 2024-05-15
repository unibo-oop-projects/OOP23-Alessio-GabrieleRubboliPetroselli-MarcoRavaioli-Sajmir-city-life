package unibo.citysimulation.model;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import unibo.citysimulation.model.person.DynamicPerson;
import unibo.citysimulation.model.person.StaticPerson.PersonState;
import unibo.citysimulation.model.transport.TransportLine;

public class GraphicsModel {
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.CYAN);
    private List<XYSeriesCollection> datasets;
    private List<String> names = Arrays.asList("Person State", "Transport Congestion", "Business Occupation");
    private int counter = 0;

    private int columnCount = 0;

    public GraphicsModel(){
        createDatasets(List.of(3, 5, 1));
        System.out.println("in teoria qua ha già creato i datasets");
    }

    public void createDatasets(List<Integer> numCollections) {
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

    public synchronized void updateDataset(List<Integer> states, List<Double> congestions, int business,
            double counter) {
        synchronized (datasets) {
            if (columnCount > 150) {
                int columnsToRemove = columnCount - 150;
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
                columnCount = 150;
            }

            columnCount++;

            for (int i = 0; i < datasets.get(0).getSeriesCount(); i++) {
                synchronized (datasets.get(0)) {
                    datasets.get(0).getSeries(i).add(counter, states.get(i));
                    //maxStateHeight = states.get(i) > maxStateHeight ? states.get(i) : maxStateHeight;
                }
            }

            for (int i = 0; i < datasets.get(1).getSeriesCount(); i++) {
                synchronized (datasets.get(1)) {
                    datasets.get(1).getSeries(i).add(counter, congestions.get(i));
                    //maxCongestionHeight = congestions.get(i) > maxCongestionHeight ? congestions.get(i)
                            //: maxCongestionHeight;
                }
            }

            synchronized (datasets.get(2)) { // Sincronizza l'accesso al dataset corrente
                datasets.get(2).getSeries(0).add(counter, business);
            }
        }
    }

    public List<Integer> getPeopleStateCounts(List<DynamicPerson> list){
        return Arrays.asList(
                (int) list.stream().filter(person -> person.getState() == PersonState.AT_HOME).count(),
                (int) list.stream().filter(person -> person.getState() == PersonState.MOVING).count(),
                (int) list.stream().filter(person -> person.getState() == PersonState.WORKING).count());
    }        

    public List<Double> getTransportLinesCongestion(List<TransportLine> list) {
        return list.stream()
                .map(TransportLine::getCongestion)
                .collect(Collectors.toList());
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
