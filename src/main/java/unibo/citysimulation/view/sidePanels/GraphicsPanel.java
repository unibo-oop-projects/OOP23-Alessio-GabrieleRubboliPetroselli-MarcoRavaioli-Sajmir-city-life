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
    private XYSeriesCollection congestionDataset; // Make dataset a class member
    private XYSeriesCollection peopleStateDataset;
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

        // Initialize datasets
        congestionDataset = createDataset();
        line1dataset = createDataset();
        businessDataset = createDataset();
        stateDataset = createStateDataset();

        // Initialize charts
        JFreeChart peopleChart = createChart("Transport Congestion", "", "", congestionDataset);
        JFreeChart transportChart = createChart("People State", "", "", stateDataset);
        JFreeChart businessChart = createChart("Business", "", "", businessDataset);

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

    private XYSeriesCollection createStateDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries movingSeries = new XYSeries("Moving", false);
        XYSeries workingSeries = new XYSeries("Working", false);
        XYSeries atHomeSeries = new XYSeries("At Home", false);

        dataset.addSeries(movingSeries);
        dataset.addSeries(workingSeries);
        dataset.addSeries(atHomeSeries);

        return dataset;
    }

    public void updateDataset(int series0, int series1, double counter) {

        if (columnCount > 150) {

        graphicDimensionControl(congestionDataset);
    }
    public void updateStateDataset(int movingCount, int workingCount, int atHomeCount, double counter) {
        stateDataset.getSeries("Moving").add(counter, movingCount);
        stateDataset.getSeries("Working").add(counter, workingCount);
        stateDataset.getSeries("At Home").add(counter, atHomeCount);
        graphicDimensionControl(stateDataset);
    }

    private void graphicDimensionControl(XYSeriesCollection dataset) {
        columnCount++;
    
        if (columnCount > 200) {
            int columnsToRemove = columnCount - 200;
    
            // Rimuovi le colonne in eccesso dal dataset
            for (int i = 0; i < columnsToRemove && dataset.getSeries(0).getItemCount() > 0; i++) {
                for (int j = 0; j < dataset.getSeriesCount(); j++) {
                    dataset.getSeries(j).remove(0);
                }
            }
    
            columnCount -= columnsToRemove; // Aggiorna il conteggio delle colonne
        }
    }
}