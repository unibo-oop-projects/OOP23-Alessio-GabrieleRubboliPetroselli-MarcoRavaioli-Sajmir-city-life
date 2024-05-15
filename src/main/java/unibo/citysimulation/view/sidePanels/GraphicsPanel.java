package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN);
    private List<XYSeriesCollection> datasets;
    private List<String> names;
    private List<XYPlot> plots;
    private int columnCount = 0;

    private int maxStateHeight = 1;
    private double maxCongestionHeight = 1;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        names = Arrays.asList("Person State", "Transport Congestion", "Business");

        datasets = createDatasets(names.size());
        List<JFreeChart> charts = createCharts(names, datasets);

        plots = charts.stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(createRenderer(plot.getSeriesCount())))
                .collect(Collectors.toList());

        synchronized (this) {
            plots.forEach(plot -> add(new ChartPanel(plot.getChart())));
        }


        plots.get(0).getRangeAxis().setTickLabelsVisible(false);
        plots.get(1).getRangeAxis().setTickLabelsVisible(false);


        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(names.size(), 1));
    }

    private List<XYSeriesCollection> createDatasets(int num) {
        return IntStream.range(0, num)
                .mapToObj(i -> createDataset(3))
                .collect(Collectors.toList());
    }

    private XYSeriesCollection createDataset(int numObjects) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        IntStream.range(0, numObjects)
                .mapToObj(i -> new XYSeries("Object " + i, false))
                .forEach(dataset::addSeries);

        return dataset;
    }

    private List<JFreeChart> createCharts(List<String> names, List<XYSeriesCollection> datasets) {
        List<JFreeChart> charts = new ArrayList<JFreeChart>();

        for (int i = 0; i < names.size(); i++) {
            XYSeriesCollection dataset = datasets.get(i);
            String name = names.get(i);
            JFreeChart chart = createChart(name, null, null, dataset);
            charts.add(chart);
        }

        return charts;
    }

    private XYLineAndShapeRenderer createRenderer(int num) {
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < num; i++) {
            renderer.setSeriesPaint(i, colors.get(i));
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        return renderer;
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

    // Method to create a chart
    private JFreeChart createChart(String title, String domainLabel, String rangeLabel,
            XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                domainLabel,
                rangeLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        ValueAxis domainAxis = chart.getXYPlot().getDomainAxis();
        ValueAxis rangeAxis = chart.getXYPlot().getRangeAxis();

        domainAxis.setTickLabelsVisible(false);
        domainAxis.setLowerMargin(0.01);
        domainAxis.setUpperMargin(0.01);
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 1);

        return chart;
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
                    maxStateHeight = states.get(i) > maxStateHeight ? states.get(i) : maxStateHeight;
                }
            }

            for (int i = 0; i < datasets.get(1).getSeriesCount(); i++) {
                synchronized (datasets.get(1)) {
                    datasets.get(1).getSeries(i).add(counter, congestions.get(i));
                    maxCongestionHeight = congestions.get(i) > maxCongestionHeight ? congestions.get(i)
                            : maxCongestionHeight;
                }
            }

            synchronized (datasets.get(2)) { // Sincronizza l'accesso al dataset corrente
                datasets.get(2).getSeries(0).add(counter, business);
            }

            NumberAxis stateAxis = new NumberAxis();
            stateAxis.setRange(0, (maxStateHeight * 1.15 < 100 ? maxStateHeight * 1.15 : 100));
            plots.get(0).setRangeAxis(stateAxis);

            NumberAxis congestionAxis = new NumberAxis();
            congestionAxis.setRange(0, (maxCongestionHeight * 1.15 < 100 ? maxCongestionHeight * 1.15 : 100));
            plots.get(1).setRangeAxis(congestionAxis);
        }
    }

}