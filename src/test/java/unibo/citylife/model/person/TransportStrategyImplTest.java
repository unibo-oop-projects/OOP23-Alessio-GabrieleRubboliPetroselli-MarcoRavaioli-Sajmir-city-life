package unibo.citylife.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibo.citysimulation.model.person.api.TransportStrategy;
import unibo.citysimulation.model.person.impl.TransportStrategyImpl;
import unibo.citysimulation.model.transport.api.TransportFactory;
import unibo.citysimulation.model.transport.api.TransportLine;
import unibo.citysimulation.model.transport.impl.TransportFactoryImpl;
import unibo.citysimulation.model.zone.Zone;
import unibo.citysimulation.model.zone.ZoneFactory;
import unibo.citysimulation.model.zone.ZoneTableCreation;

class TransportStrategyImplTest {

    private TransportStrategy transportStrategy;
    private List<TransportLine> allLines;

    @BeforeEach
    public void setUp() {
        TransportFactory transportFactory = new TransportFactoryImpl();
        final List<Zone> zones = ZoneFactory.createZonesFromFile();
        allLines = transportFactory.createTransportsFromFile(zones);
        ZoneTableCreation.createAndAddPairs(zones, allLines);

        transportStrategy = new TransportStrategyImpl();
    }

    @Test
    void testIsCongested() {
        final List<TransportLine> lines = List.of(allLines.get(0), allLines.get(1));

        assertFalse(transportStrategy.isCongested(lines));

        lines.forEach(l -> IntStream.range(0, l.getCapacity()).forEach(n -> l.incrementPersonInLine()));

        assertTrue(transportStrategy.isCongested(lines));
    }

        @Test
    void testCalculateArrivalTime() {
        final int exampleTime = 1800;

        final int arrivalTime = transportStrategy.calculateArrivalTime(exampleTime, exampleTime);
        assertEquals(exampleTime * 2, arrivalTime);
    }

    @Test
    void testIncrementAndDecrementPersonsInLine() {
        // Prendiamo una linea di trasporto dalla lista
        final TransportLine line = allLines.get(0);

        // Verifichiamo che all'inizio non ci siano persone nella linea
        assertEquals(0, line.getPersonInLine());

        // Incrementiamo il numero di persone nella linea
        transportStrategy.incrementPersonsInLine(List.of(line));

        // Ora dovremmo avere una persona nella linea
        assertEquals(1, line.getPersonInLine());

        // Decrementiamo il numero di persone nella linea
        transportStrategy.decrementPersonsInLine(List.of(line));

        // Ora dovremmo tornare a zero persone nella linea
        assertEquals(0, line.getPersonInLine());
    }
}
