package unibo.citysimulation.view.sidePanels;

import unibo.citysimulation.view.StyledPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JPanel;

import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private CategoryAxis domainAxis;
    private NumberAxis rangeAxis;
    private XYSeriesCollection line0dataset; // Make dataset a class member
    private XYSeriesCollection line1dataset;
    private XYSeriesCollection businessDataset;

    private int columnCount = 0;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);

        // Initialize datasets
        line0dataset = createDataset();
        line1dataset = createDataset();
        businessDataset = createDataset();

        // Initialize charts
        JFreeChart peopleChart = createChart("People Selected", "", "", line0dataset);
        JFreeChart transportChart = createChart("Transport", "", "", null);
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
        add(peopleChartPanel);
        add(transportChartPanel);
        add(businessChartPanel);

        // Set layout to arrange charts horizontally
        setLayout(new GridLayout(3, 1));
    }

    private JPanel createChartPanel() {
        String chartTitle = "PROVA";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                null, null, dataset);

        return new ChartPanel(chart);
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

    public void updateDataset(int cong0, int cong1, double counter) {

        line0dataset.getSeries(0).add(counter, (double) cong0);
        line0dataset.getSeries(1).add(counter, (double) cong1);

        // line0dataset.addValue(cong0, "Cong0", time);
        // line1dataset.addValue(cong1, "Cong1", time);

        columnCount++;

        if (columnCount > 200) {

            int columnsToRemove = columnCount - 200;

            System.out.println(columnsToRemove + " columns to remove");
            // Rimuovi le colonne in eccesso dal dataset
            for (int i = 0; i < columnsToRemove; i++) {
                for (int j = 0; j < line0dataset.getSeriesCount(); j++) {
                    line0dataset.getSeries(j).remove(0);
                }
            }

            columnCount = 200;
        }

    }
}