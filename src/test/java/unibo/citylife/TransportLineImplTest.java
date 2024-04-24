package unibo.citylife;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.transport.TransportLineImpl;
import unibo.citysimulation.model.transport.Zone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransportLineImplTest {

    @Test
    public void testAddPredefinedLine() {
        // Creazione delle zone di origine e destinazione
        Zone originZone1 = new Zone("Origin 1");
        Zone destinationZone1 = new Zone("Destination 1");
        Zone originZone2 = new Zone("Origin 2");
        Zone destinationZone2 = new Zone("Destination 2");
        Zone originZone3 = new Zone("Origin 3");
        Zone destinationZone3 = new Zone("Destination 3");

        // Creazione della linea di trasporto
        TransportLineImpl transportLine = new TransportLineImpl("Line 1", 8, 20, 100, 60);

        // Aggiunta di più linee predefinite
        
        transportLine.addPredefinedLine(originZone1, destinationZone1, 30);
        transportLine.addPredefinedLine(originZone2, destinationZone2, 45);
        transportLine.addPredefinedLine(originZone3, destinationZone3, 20);

        // Verifica che tutte le linee predefinite siano state aggiunte correttamente
        assertTrue(transportLine.getPredefinedLines().containsKey(originZone1));
        assertTrue(transportLine.getPredefinedLines().containsKey(originZone2));
        assertTrue(transportLine.getPredefinedLines().containsKey(originZone3));
        assertEquals(destinationZone1, transportLine.getPredefinedLines().get(originZone1));
        assertEquals(destinationZone2, transportLine.getPredefinedLines().get(originZone2));
        assertEquals(destinationZone3, transportLine.getPredefinedLines().get(originZone3));

        // Verifica che tutte le durate dei percorsi siano state memorizzate correttamente
        assertTrue(transportLine.getPredefinedDurations().containsKey(originZone1));
        assertTrue(transportLine.getPredefinedDurations().containsKey(originZone2));
        assertTrue(transportLine.getPredefinedDurations().containsKey(originZone3));
        assertEquals(30, transportLine.getPredefinedDurations().get(originZone1));
        assertEquals(45, transportLine.getPredefinedDurations().get(originZone2));
        assertEquals(20, transportLine.getPredefinedDurations().get(originZone3));
    }
}