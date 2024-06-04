package unibo.citysimulation.view.sidepanels.graphics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class that manages chart and render creation.
 */
public final class ChartManagerImpl implements Serializable, ChartManager {
    private static final long serialVersionUID = 1L;
    private static final Double GRAPH_MARGIN = 0.01;
    private static final Integer GRAPH_RANGE = 105;

    @Override
    public List<JFreeChart> createCharts(final List<String> names, final List<XYSeriesCollection> datasets) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> createChart(names.get(i), datasets.get(i)))
                .collect(Collectors.toList());
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

    @Override
    public XYLineAndShapeRenderer createRenderer(final int num, final List<Color> colors) {
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        IntStream.range(0, num).forEach(i -> {
            renderer.setSeriesPaint(i, colors.get(i));
            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        });
        return renderer;
    }


}
