package unibo.citysimulation.view.sidePanels;

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

import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
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

        // Initialize datasets
        congestionDataset = createDataset();
        line1dataset = createDataset();
        businessDataset = createDataset();
        stateDataset = createStateDataset();

        // Initialize charts
        JFreeChart peopleChart = createChart("Transport Congestion", "", "", congestionDataset);
        JFreeChart transportChart = createChart("People State", "", "", stateDataset);
        JFreeChart businessChart = createChart("Business", "", "", businessDataset);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        XYPlot plot = peopleChart.getXYPlot();
        plot.setRenderer(renderer);

        // Create chart panels
        ChartPanel peopleChartPanel = new ChartPanel(peopleChart);
        ChartPanel transportChartPanel = new ChartPanel(transportChart);
        ChartPanel businessChartPanel = new ChartPanel(businessChart);

        // Add chart panels to the panel
        add(transportChartPanel);
        add(businessChartPanel);
        add(peopleChartPanel);

        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(3, 1));
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

    private XYSeriesCollection createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1", false);
        XYSeries series2 = new XYSeries("Object 2", false);

        dataset.addSeries(series1);
        dataset.addSeries(series2);

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

        congestionDataset.getSeries(0).add(counter, (double) series0);
        congestionDataset.getSeries(1).add(counter, (double) series1);

        columnCount++;

        if (columnCount > 200) {

            int columnsToRemove = columnCount - 200;

            // Rimuovi le colonne in eccesso dal dataset
            for (int i = 0; i < columnsToRemove; i++) {
                for (int j = 0; j < congestionDataset.getSeriesCount(); j++) {
                    congestionDataset.getSeries(j).remove(0);
                }
            }

            columnCount = 200;
        }
    }
    public void updateStateDataset(int movingCount, int workingCount, int atHomeCount, double counter) {
        stateDataset.getSeries("Moving").add(counter, movingCount);
        stateDataset.getSeries("Working").add(counter, workingCount);
        stateDataset.getSeries("At Home").add(counter, atHomeCount);
    }
}