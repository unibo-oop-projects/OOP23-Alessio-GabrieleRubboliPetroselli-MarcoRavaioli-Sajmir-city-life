package unibo.citysimulation.view.sidepanels;

import unibo.citysimulation.utilities.Pair;
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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.BasicStroke;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel for displaying graphics.
 */
public class GraphicsPanel extends StyledPanel {
    private static final long serialVersionUID = 1L;
    private final JButton legendButton;

    private static final Pair<Integer, Integer> BUTTON_DIMENSIONS = new Pair<>(70, 40);
    private static final Double GRAPH_MARGIN = 0.01;
    private static final Integer GRAPH_RANGE = 105;

    /**
     * Constructs a GraphicsPanel with the specified background color.
     *
     * @param bgColor The background color of the panel.
     */
    public GraphicsPanel(final Color bgColor) {
        super(bgColor);

        this.legendButton = new JButton("?");
        this.legendButton.setPreferredSize(new Dimension(BUTTON_DIMENSIONS.getFirst(), BUTTON_DIMENSIONS.getSecond()));

        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Create a new panel for the button
        bottomPanel.setBackground(bgColor);
        bottomPanel.add(this.legendButton); // Add the button to the panel

        // Add the bottom panel to this panel
        this.setLayout(new BorderLayout()); // Set layout of GraphicsPanel
        this.add(bottomPanel, BorderLayout.SOUTH); // Add the panel to the SOUTH position
    }

    /**
     * Returns the legend button used in the graphics panel.
     *
     * @return the JButton that opens the legend panel
     */
    public JButton getLegendButton() {
        return this.legendButton;
    }

    /**
     * Creates and displays a series of XY charts based on the provided names,
     * datasets, and colors.
     * Each chart is added to a vertically arranged grid layout within a panel.
     *
     * @param names    the list of names for each chart
     * @param datasets the list of datasets for each chart, where each dataset
     *                 corresponds to a chart
     * @param colors   the list of colors for rendering the datasets in the charts
     */
    public void createGraphics(final List<String> names, final List<XYSeriesCollection> datasets,
            final List<Color> colors) {
        final List<XYPlot> plots = createCharts(names, datasets).stream()
                .map(JFreeChart::getXYPlot)
                .peek(plot -> plot.setRenderer(createRenderer(plot.getSeriesCount(), colors)))
                .collect(Collectors.toList());

        final JPanel chartsPanel = new JPanel();
        chartsPanel.setBackground(super.getBgColor());
        chartsPanel.setLayout(new GridLayout(plots.size(), 1)); // 1 colonna, tante righe quante sono i grafici

        plots.forEach(plot -> chartsPanel.add(new ChartPanel(plot.getChart())));

        this.add(chartsPanel, BorderLayout.CENTER);
    }


    private List<JFreeChart> createCharts(final List<String> names, final List<XYSeriesCollection> datasets) {
        final List<JFreeChart> charts = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            charts.add(createChart(names.get(i), datasets.get(i)));
        }
        return charts;
    }

    private JFreeChart createChart(final String title, final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);

        final XYPlot plot = chart.getXYPlot();
        final ValueAxis domainAxis = plot.getDomainAxis();
        final ValueAxis rangeAxis = plot.getRangeAxis();

        domainAxis.setTickLabelsVisible(false);
        domainAxis.setLowerMargin(GRAPH_MARGIN);
        domainAxis.setUpperMargin(GRAPH_MARGIN);
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRange(0, GRAPH_RANGE);

        return chart;
    }

    private XYLineAndShapeRenderer createRenderer(final int num, final List<Color> colors) {
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        for (int i = 0; i < num; i++) {
            renderer.setSeriesPaint(i, colors.get(i));
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
        return renderer;
    }
}
