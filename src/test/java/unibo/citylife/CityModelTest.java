package unibo.citylife;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class CityModelTest {
    private CityModel cityModel;

    @BeforeEach
    void setUp() {
        cityModel = new CityModel();
    }

    @Test
    void testCalculateTotalBusinesses() {
        final int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        assertEquals(10, cityModel.getTotalBusinesses());
    }

    @Test
    void testCreateBusinesses() {
        final int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        cityModel.createBusinesses();
        assertEquals(10, cityModel.getBusinesses().size());
    }

    @Test
    void testBusinessesDistributedAccordingToZones() {
        final int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        cityModel.createBusinesses();
        final List<Zone> zones = cityModel.getZones();
        final int totalBusinesses = cityModel.getBusinesses().size();
        final int sumBusinessesByZone = zones.stream()
            .mapToInt(zone -> (int) cityModel.getBusinesses().stream()
                .filter(business -> business.getZone().equals(zone))
                .count())
            .sum();
        assertEquals(totalBusinesses, sumBusinessesByZone);
    }

    @Test
    void testGetRandomZone() {
        final Zone randomZone = cityModel.getRandomZone();
        assertNotNull(randomZone);
    }

    @Test
    void testGetZoneByPosition() {
        final Pair<Integer, Integer> position = new Pair<>(50, 50); // Adjust according to your zone boundaries
        final Optional<Zone> zone = cityModel.getZoneByPosition(position);
        assertTrue(zone.isPresent());
    }

    @Test
    void testFrameSizeCalculation() {
        cityModel.takeFrameSize();
        final Pair<Integer, Integer> frameSize = new Pair<>(cityModel.getFrameWidth(), cityModel.getFrameHeight());
        assertNotNull(frameSize);
        assertTrue(frameSize.getFirst() > 0);
        assertTrue(frameSize.getSecond() > 0);
    }
}
