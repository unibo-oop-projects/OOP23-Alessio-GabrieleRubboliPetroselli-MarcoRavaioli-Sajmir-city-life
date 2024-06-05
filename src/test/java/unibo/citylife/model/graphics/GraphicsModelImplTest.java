package unibo.citylife.model.graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.jfree.data.xy.XYSeriesCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.business.impl.Business;
import unibo.citysimulation.model.business.impl.BusinessFactoryImpl;
import unibo.citysimulation.model.business.utilities.EmploymentOfficeData;
import unibo.citysimulation.model.graphics.impl.GraphicsModelImpl;
import unibo.citysimulation.model.person.api.DynamicPerson;
import unibo.citysimulation.model.person.creation.PersonCreation;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.transport.creation.TransportCreation;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;

class GraphicsModelImplTest {

    private GraphicsModelImpl graphicsModel;
    private List<DynamicPerson> people;
    private List<TransportLine> lines;
    private final List<Business> businesses = new ArrayList<>();
    private EmploymentOfficeData employmentOfficeData;

    @BeforeEach
    public void setUp() {
        this.employmentOfficeData = new EmploymentOfficeData(new LinkedList<>());

        final List<Zone> zones = ZoneFactory.createZonesFromFile();
        lines = TransportCreation.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, lines);
        businesses.addAll(BusinessFactoryImpl.createMultipleBusiness(zones, 100));
        final List<List<DynamicPerson>> peopleGroup = PersonCreation.createAllPeople(100, zones, businesses);

        for (final List<DynamicPerson> group : peopleGroup) {
            for (final DynamicPerson person : group) {
                employmentOfficeData.disoccupied().add(person);
            }
        }

        people = peopleGroup.stream()
                .flatMap(List::stream)
                .peek(p -> employmentOfficeData.disoccupied().add(p))
                .collect(Collectors.toList());

        graphicsModel = new GraphicsModelImpl();
    }

    @Test
    void testConstructor() {
        assertNotNull(graphicsModel.getDatasets(), "Datasets should not be null");
        assertEquals(3, graphicsModel.getDatasets().size(), "There should be 3 datasets");
        assertEquals(List.of("Person State", "Transport Congestion", "Business Occupation"), graphicsModel.getNames(),
                "Dataset names should match the expected list");
        assertEquals(List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN),
                graphicsModel.getColors(), "Colors should match the expected list");
    }

    @Test
    void testClearDatasets() {
        graphicsModel.updateDataset(people, lines, businesses, 1);
        graphicsModel.clearDatasets();
        final List<XYSeriesCollection> datasets = graphicsModel.getDatasets();
        datasets.forEach(ds -> {
            final List<?> seriesList = ds.getSeries();
            seriesList.forEach(series -> {
                assertTrue(((org.jfree.data.xy.XYSeries) series).isEmpty(), "Series should be empty after clearing datasets");
            });
        });
    }

    @Test
    void testUpdateDataset() {
        graphicsModel.updateDataset(people, lines, businesses, 1);

        for (final XYSeriesCollection ds : graphicsModel.getDatasets()) {
            for (int i = 0; i < ds.getSeriesCount(); i++) {
                assertEquals(1, ds.getSeries(i).getItemCount(), "Series should have 1 item after one update");
            }
        }

        for (int i = 1; i < 3; i++) {
            graphicsModel.updateDataset(people, lines, businesses, 1);
        }

        for (final XYSeriesCollection ds : graphicsModel.getDatasets()) {
            for (int i = 0; i < ds.getSeriesCount(); i++) {
                assertEquals(2, ds.getSeries(i).getItemCount(), "Series should still have 2 items");
            }
        }
    }

    @Test
    void testGetDatasets() {
        assertNotNull(graphicsModel.getDatasets(), "Datasets should not be null");
        assertEquals(3, graphicsModel.getDatasets().size(), "There should be 3 datasets");
    }

    @Test
    void testGetNames() {
        assertEquals(List.of("Person State", "Transport Congestion", "Business Occupation"), graphicsModel.getNames(),
                "Dataset names should match the expected list");
    }

    @Test
    void testGetColors() {
        assertEquals(List.of(Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN, Color.YELLOW, Color.PINK, Color.CYAN),
                graphicsModel.getColors(), "Colors should match the expected list");
    }
}
