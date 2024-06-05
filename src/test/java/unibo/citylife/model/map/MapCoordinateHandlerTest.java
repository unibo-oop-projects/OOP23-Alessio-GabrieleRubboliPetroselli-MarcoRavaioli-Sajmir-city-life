package unibo.citylife.model.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.map.impl.MapCoordinateHandler;

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
        handler.setMaxCoordinates(100, 200);
        assertEquals(100, handler.getMaxX(), "maxX should be set to 100");
        assertEquals(200, handler.getMaxY(), "maxY should be set to 200");
    }

    @Test
    void testDenormalizeCoordinate() {
        final List<Integer> utility_nums = List.of(0, 100, 200, 500, 1000);
        handler.setMaxCoordinates(utility_nums.get(1), utility_nums.get(2));
        
        assertEquals(utility_nums.get(0), 
                    handler.denormalizeCoordinate(utility_nums.get(0), 
                    utility_nums.get(1)), "Denormalized 0 should be 0");
        assertEquals(utility_nums.get(1) / 2, 
                    handler.denormalizeCoordinate(utility_nums.get(3), 
                    utility_nums.get(1)), "Denormalized 500 should be 50 for max 100");
        assertEquals(utility_nums.get(1), 
                    handler.denormalizeCoordinate(utility_nums.get(4), 
                    utility_nums.get(1)), "Denormalized 1000 should be 100 for max 100");

        assertEquals(utility_nums.get(0), 
                    handler.denormalizeCoordinate(utility_nums.get(0), 
                    utility_nums.get(2)), "Denormalized 0 should be 0");
        assertEquals(utility_nums.get(1), 
                    handler.denormalizeCoordinate(utility_nums.get(3), 
                    utility_nums.get(2)), "Denormalized 500 should be 100 for max 200");
        assertEquals(utility_nums.get(2), 
                    handler.denormalizeCoordinate(utility_nums.get(4), 
                    utility_nums.get(2)), "Denormalized 1000 should be 200 for max 200");
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
