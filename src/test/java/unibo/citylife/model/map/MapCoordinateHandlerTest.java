package unibo.citylife.model.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.map.impl.MapCoordinateHandler;
import unibo.citysimulation.utilities.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

class MapCoordinateHandlerTest {
    private static final Integer HIGH_NUM = 150;

    private MapCoordinateHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new MapCoordinateHandler();
    }

    @Test
    void testConstructor() {
        assertEquals(-1, handler.getMaxX(), "Initial maxX should be -1");
        assertEquals(-1, handler.getMaxY(), "Initial maxY should be -1");
    }

    @Test
    void testSetMaxCoordinates() {
        final Pair<Integer, Integer> maxCoordinateTest = new Pair<>(100, 200);
        handler.setMaxCoordinates(maxCoordinateTest.getFirst(), maxCoordinateTest.getSecond());
        assertEquals(maxCoordinateTest.getFirst(), handler.getMaxX(), "maxX should be set to 100");
        assertEquals(maxCoordinateTest.getSecond(), handler.getMaxY(), "maxY should be set to 200");
    }

    @Test
    void testDenormalizeCoordinate() {
        final List<Integer> utilityNums = List.of(0, 100, 200, 500, 1000);
        handler.setMaxCoordinates(utilityNums.get(1), utilityNums.get(2));

        assertEquals(utilityNums.get(0), 
                    handler.denormalizeCoordinate(utilityNums.get(0), 
                    utilityNums.get(1)), "Denormalized 0 should be 0");
        assertEquals(utilityNums.get(1) / 2, 
                    handler.denormalizeCoordinate(utilityNums.get(3), 
                    utilityNums.get(1)), "Denormalized 500 should be 50 for max 100");
        assertEquals(utilityNums.get(1), 
                    handler.denormalizeCoordinate(utilityNums.get(4), 
                    utilityNums.get(1)), "Denormalized 1000 should be 100 for max 100");

        assertEquals(utilityNums.get(0), 
                    handler.denormalizeCoordinate(utilityNums.get(0), 
                    utilityNums.get(2)), "Denormalized 0 should be 0");
        assertEquals(utilityNums.get(1), 
                    handler.denormalizeCoordinate(utilityNums.get(3), 
                    utilityNums.get(2)), "Denormalized 500 should be 100 for max 200");
        assertEquals(utilityNums.get(2), 
                    handler.denormalizeCoordinate(utilityNums.get(4), 
                    utilityNums.get(2)), "Denormalized 1000 should be 200 for max 200");
    }

    @Test
    void testGetMaxX() {
        handler.setMaxCoordinates(HIGH_NUM, HIGH_NUM * 2);
        assertEquals(HIGH_NUM, handler.getMaxX(), "maxX should be set to 150");
    }

    @Test
    void testGetMaxY() {
        handler.setMaxCoordinates(HIGH_NUM, HIGH_NUM * 2);
        assertEquals(HIGH_NUM * 2, handler.getMaxY(), "maxY should be set to 300");
    }

    @Test
    void testDenormalizeCoordinateWithNegativeMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            handler.denormalizeCoordinate(HIGH_NUM, -HIGH_NUM);
        }, "Should throw IllegalArgumentException for negative max value");
    }
}
