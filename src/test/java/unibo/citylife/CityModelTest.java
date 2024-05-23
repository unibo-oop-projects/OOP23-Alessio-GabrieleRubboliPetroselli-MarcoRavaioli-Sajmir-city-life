package unibo.citylife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.CityModel;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.utilities.Pair;

public class CityModelTest {
    private CityModel cityModel;

    @BeforeEach
    void setUp() {
        cityModel = new CityModel();
    }

    @Test
    void testCalculateTotalBusinesses() {
        int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        assertEquals(10, cityModel.getTotalBusinesses());
    }

    @Test
    void testCreateBusinesses() {
        int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        cityModel.createBusinesses();
        assertEquals(10, cityModel.getBusinesses().size());
    }

    @Test
    void testBusinessesDistributedAccordingToZones() {
        int numberOfPeople = 100;
        cityModel.calculateTotalBusinesses(numberOfPeople);
        cityModel.createBusinesses();
        List<Zone> zones = cityModel.getZones();
        int totalBusinesses = cityModel.getBusinesses().size();
        
        int sumBusinessesByZone = zones.stream()
            .mapToInt(zone -> (int) cityModel.getBusinesses().stream()
                .filter(business -> business.getZone().equals(zone))
                .count())
            .sum();
        
        assertEquals(totalBusinesses, sumBusinessesByZone);
    }

    @Test
    void testGetRandomZone() {
        Zone randomZone = cityModel.getRandomZone();
        assertNotNull(randomZone);
    }

    @Test
    void testGetZoneByPosition() {
        Pair<Integer, Integer> position = new Pair<>(50, 50); // Adjust according to your zone boundaries
        Optional<Zone> zone = cityModel.getZoneByPosition(position);
        assertTrue(zone.isPresent());
    }

    @Test
    void testFrameSizeCalculation() {
        Pair<Integer, Integer> frameSize = cityModel.getFrameSize();
        assertNotNull(frameSize);
        assertTrue(frameSize.getFirst() > 0);
        assertTrue(frameSize.getSecond() > 0);
    }
}
