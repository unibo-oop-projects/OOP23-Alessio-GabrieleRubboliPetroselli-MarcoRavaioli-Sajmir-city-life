package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private List<XYSeriesCollection> datasets;
    private List<String> names;
    private Random random = new Random();
    private XYSeriesCollection congestionDataset; // Make dataset a class member
    private XYSeriesCollection line1dataset;
    private XYSeriesCollection businessDataset;
    private XYSeriesCollection stateDataset;

    private int columnCount = 0;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);
        names = new ArrayList<>();
        names.add("Transport Congestion");
        names.add("Transport");
        names.add("Business");

        datasets = new ArrayList<>();

        datasets = createDatasets(names);

        List<JFreeChart> charts = createCharts(names, datasets);


        List<XYPlot> plots = new ArrayList<>();

        for (int i = 0; i < charts.size(); i++) {
            XYPlot plot = charts.get(i).getXYPlot();
            plot.setRenderer(createRenderer(plot.getSeriesCount()));
            plots.add(plot);

            add(new ChartPanel(charts.get(i)));
        }
        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(names.size(), 1));
    }

    private List<XYSeriesCollection> createDatasets(List<String> names) {
        List<XYSeriesCollection> datasets = new ArrayList<XYSeriesCollection>();

        for (String name : names) {
            XYSeriesCollection dataset = createDataset(3);

            datasets.add(dataset);
        }

        return datasets;
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

        for(int i = 0; i < num; i++){
            Color color = new Color(random.nextInt(4) * 64, random.nextInt(4) * 64, random.nextInt(4) * 64);
            renderer.setSeriesPaint(i, color);
            renderer.setSeriesShapesVisible(i, false);
        }

        return renderer;
    }

    public void clearDatasets() {
        columnCount = 0; // Resetta anche il contatore delle colonne
        for (XYSeriesCollection dataset : datasets) {
            for (int i = 0; i < dataset.getSeriesCount(); i++) {
                dataset.getSeries(i).clear();
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
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, 100);

        return chart;
    }

    private XYSeriesCollection createDataset(int numObjects) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = 0; i < numObjects; i++) {
            XYSeries series = new XYSeries("Object " + String.valueOf(i), false);

            dataset.addSeries(series);
        }

        return dataset;
    }

    public void updateDataset(List<TransportLine> lines, int people, int business, double counter) {
        int columnsToRemove = columnCount - 150;

        if (columnsToRemove > 0) {

            // Rimuovi le colonne in eccesso dal dataset
            for (int k = 0; k < datasets.size(); k++) {
                for (int i = 0; i < columnsToRemove; i++) {
                    for (int j = 0; j < datasets.get(k).getSeriesCount(); j++) {
                        datasets.get(k).getSeries(j).remove(0);
                    }
                }
            }

            columnCount = 150;
        }

        for (int i = 0; i < lines.size(); i++) {
            datasets.get(0).getSeries(i).add(counter, lines.get(i).getCongestion());
            datasets.get(1).getSeries(i).add(counter, people);
            datasets.get(2).getSeries(i).add(counter, business);
        }

        columnCount++;
    }
}