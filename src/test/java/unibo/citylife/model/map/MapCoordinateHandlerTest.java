package unibo.citylife.model.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.map.impl.MapCoordinateHandler;

import static org.junit.jupiter.api.Assertions.*;

public class MapCoordinateHandlerTest {

    private MapCoordinateHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new MapCoordinateHandler();
    }

    @Test
    public void testConstructor() {
        assertEquals(-1, handler.getMaxX(), "Initial maxX should be -1");
        assertEquals(-1, handler.getMaxY(), "Initial maxY should be -1");
    }

    @Test
    public void testSetMaxCoordinates() {
        handler.setMaxCoordinates(100, 200);
        assertEquals(100, handler.getMaxX(), "maxX should be set to 100");
        assertEquals(200, handler.getMaxY(), "maxY should be set to 200");
    }

    @Test
    public void testDenormalizeCoordinate() {
        handler.setMaxCoordinates(100, 200);

        assertEquals(0, handler.denormalizeCoordinate(0, 100), "Denormalized 0 should be 0");
        assertEquals(50, handler.denormalizeCoordinate(500, 100), "Denormalized 500 should be 50 for max 100");
        assertEquals(100, handler.denormalizeCoordinate(1000, 100), "Denormalized 1000 should be 100 for max 100");

        assertEquals(0, handler.denormalizeCoordinate(0, 200), "Denormalized 0 should be 0");
        assertEquals(100, handler.denormalizeCoordinate(500, 200), "Denormalized 500 should be 100 for max 200");
        assertEquals(200, handler.denormalizeCoordinate(1000, 200), "Denormalized 1000 should be 200 for max 200");
    }

    @Test
    public void testGetMaxX() {
        handler.setMaxCoordinates(150, 300);
        assertEquals(150, handler.getMaxX(), "maxX should be set to 150");
    }

    @Test
    public void testGetMaxY() {
        handler.setMaxCoordinates(150, 300);
        assertEquals(300, handler.getMaxY(), "maxY should be set to 300");
    }

    @Test
    public void testDenormalizeCoordinateWithNegativeMax() {
        assertThrows(IllegalArgumentException.class, () -> {
            handler.denormalizeCoordinate(500, -100);
        }, "Should throw IllegalArgumentException for negative max value");
    }
}
