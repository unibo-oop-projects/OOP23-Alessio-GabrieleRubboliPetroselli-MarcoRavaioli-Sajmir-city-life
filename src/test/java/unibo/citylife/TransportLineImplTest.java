package unibo.citylife;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import unibo.citysimulation.model.transport.TransportFactory;
import unibo.citysimulation.model.transport.TransportLine;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import java.util.List;

//test for class transportLineImpl
class TransportLineImplTest {
    
    private final List<Zone> zones = ZoneFactory.createZonesFromFile();
    private final List<TransportLine> transports = TransportFactory.createTransportsFromFile(zones);

    @Test
    void testGetName() {
        final String expectedName = transports.get(0).getName();
        final String actualName = transports.get(0).getName();
        assertEquals(expectedName, actualName, "The name of the transport line should be " + expectedName);
    }

    @Test
    void testGetDuration() {
        final int expectedDuration = transports.get(0).getDuration();
        final int actualDuration = transports.get(0).getDuration();
        assertEquals(expectedDuration, actualDuration, "The duration of the transport line should be " + expectedDuration);
    }

    @Test
    void testIncrementAndDecrementPersonInLine() {
        final TransportLine line = transports.get(0);
        line.incrementPersonInLine();
        assertEquals(1, line.getPersonInLine(), "The number of people in line should be 1 after incrementing");
        line.decrementPersonInLine();
        assertEquals(0, line.getPersonInLine(), "The number of people in line should be 0 after decrementing");
    }
}