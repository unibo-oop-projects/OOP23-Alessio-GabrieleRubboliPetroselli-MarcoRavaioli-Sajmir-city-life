package unibo.citylife.model.graphics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.graphics.impl.DatasetManager;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

public class DatasetManagerTest {
    private DatasetManager datasetManager;

    @BeforeEach
    public void setUp() {
        List<Integer> seriesCount = Arrays.asList(3, 7, 3);
        List<String> names = Arrays.asList("Dataset1", "Dataset2", "Dataset3");
        datasetManager = new DatasetManager(seriesCount, names);
    }

    @Test
    public void testConstructor() {
        List<XYSeriesCollection> datasets = datasetManager.getDatasets();
        assertNotNull(datasets, "Datasets should not be null");
        assertEquals(3, datasets.size(), "There should be three datasets");
        assertEquals(3, datasets.get(0).getSeriesCount(), "Each dataset should have three series");
        assertEquals(7, datasets.get(1).getSeriesCount(), "Each dataset should have three series");
    }

    @Test
public void testClearDatasets() {
    // Simula alcuni aggiornamenti ai dataset per assicurarsi che contengano dati
    List<Integer> peopleState = Arrays.asList(1, 2, 3);
    List<Double> linesCongestion = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7);
    List<Integer> businessesOccupation = Arrays.asList(10, 20, 30);
    datasetManager.updateDataset(peopleState, linesCongestion, businessesOccupation);

    // Pulisce i dataset
    datasetManager.clearDatasets();

    // Recupera i dataset aggiornati
    List<XYSeriesCollection> datasets = datasetManager.getDatasets();

    // Verifica che ogni serie in ogni dataset sia vuota
    datasets.forEach(ds -> {
        for (int i = 0; i < ds.getSeriesCount(); i++) {
            assertTrue(ds.getSeries(i).isEmpty(), "Series should be empty after clearing");
        }
    });
}

@Test
public void testUpdateDataset() {
    List<Integer> peopleState = Arrays.asList(1, 2, 3);
    List<Double> linesCongestion = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7);
    List<Integer> businessesOccupation = Arrays.asList(10, 20, 30);

    datasetManager.updateDataset(peopleState, linesCongestion, businessesOccupation);
    List<XYSeriesCollection> datasets = datasetManager.getDatasets();

    // Verifica che i valori di peopleState siano correttamente aggiunti
    XYSeriesCollection peopleDataset = datasets.get(0);
    for (int i = 0; i < peopleState.size(); i++) {
        XYSeries series = peopleDataset.getSeries(i);
        assertEquals(2, series.getItemCount(), "Series should have two item (one for the creation))");
        assertEquals(peopleState.get(i), series.getY(1).intValue(), "Value should be " + linesCongestion.get(i));
    }

    // Verifica che i valori di linesCongestion siano correttamente aggiunti
    XYSeriesCollection congestionDataset = datasets.get(1);
    for (int i = 0; i < linesCongestion.size(); i++) {
        XYSeries series = congestionDataset.getSeries(i);
        assertEquals(2, series.getItemCount(), "Series should have two item (one for the creation))");
        assertEquals(linesCongestion.get(i), series.getY(1).doubleValue(), "Value should be " + linesCongestion.get(i));
    }

    // Verifica che i valori di businessesOccupation siano correttamente aggiunti
    XYSeriesCollection businessDataset = datasets.get(2);
    for (int i = 0; i < businessesOccupation.size(); i++) {
        XYSeries series = businessDataset.getSeries(i);
        assertEquals(2, series.getItemCount(), "Series should have two item (one for the creation))");
        assertEquals(businessesOccupation.get(i).intValue(), series.getY(1).intValue(), "Value should be " + businessesOccupation.get(i));
    }
}

    @Test
public void testRemoveOldColumns() {
    List<Integer> peopleState = Arrays.asList(1, 2, 3);
    List<Double> linesCongestion = Arrays.asList(1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7);
    List<Integer> businessesOccupation = Arrays.asList(10, 20, 30);

    // Simula aggiornamenti per più colonne del massimo consentito
    for (int i = 0; i < ConstantAndResourceLoader.MAX_COLUMNS + 1; i++) {
        datasetManager.updateDataset(peopleState, linesCongestion, businessesOccupation);
    }

    List<XYSeriesCollection> datasets = datasetManager.getDatasets();
    datasets.forEach(ds -> {
        for (int i = 0; i < ds.getSeriesCount(); i++) {
            XYSeries series = ds.getSeries(i);
            assertEquals(ConstantAndResourceLoader.MAX_COLUMNS + 1, series.getItemCount(), "Series should have max columns");
        }
    });
}

    @Test
    public void testUpdateDatasetWithNullValues() {
        assertThrows(NullPointerException.class, () -> {
            datasetManager.updateDataset(null, null, null);
        }, "Should throw NullPointerException when passing null values");
    }

    @Test
    public void testUpdateDatasetWithDifferentSizes() {
        List<Integer> peopleState = Arrays.asList(1, 2);
        List<Double> linesCongestion = Arrays.asList(1.1, 2.2, 3.3);
        List<Integer> businessesOccupation = Arrays.asList(10);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            datasetManager.updateDataset(peopleState, linesCongestion, businessesOccupation);
        }, "Should throw IndexOutOfBoundsException when list sizes do not match series count");
    }

    @Test
    public void testGetDatasets() {
        List<XYSeriesCollection> datasets = datasetManager.getDatasets();
        assertNotNull(datasets, "Datasets should not be null");
        assertEquals(3, datasets.size(), "There should be three datasets");
        datasets.forEach(ds -> assertNotNull(ds, "Dataset should not be null"));
    }
}
