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
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.*;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private final List<Color> colors = List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW,
            Color.PINK, Color.CYAN);
    private List<XYPlot> plots;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(Color bgColor) {
        super(bgColor);
    }

    public void createGraphics(List<String> names, List<XYSeriesCollection> datasets) {
        plots = createCharts(names, datasets).stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(createRenderer(plot.getSeriesCount())))
                .collect(Collectors.toList());

        synchronized (this) {
            plots.forEach(plot -> add(new ChartPanel(plot.getChart())));
        }

        setLayout(new GridLayout(names.size(), 1));
    }

    public List<JFreeChart> createCharts(List<String> names, List<XYSeriesCollection> datasets) {
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
        rangeAxis.setRange(0, 105);

        return chart;
    }
}