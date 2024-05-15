package unibo.citylife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import unibo.citysimulation.model.clock.ClockModel;
import unibo.citysimulation.model.clock.ClockObserver;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClockModelTest {

    private ClockModel clockModel;
    private int totalDays = 5; // Numero arbitrario di giorni per il test

    @BeforeEach
    void setUp() {
        clockModel = new ClockModel(totalDays);
    }

    @Test
    void testClockModel() throws InterruptedException {
        // Aggiungi un observer per monitorare gli aggiornamenti del tempo
        TestClockObserver observer = new TestClockObserver();
        clockModel.addObserver(observer);

        // Avvia la simulazione
        clockModel.restartSimulation(); // Durata di un'ora simulata in millisecondi

        // Attendi finché il tempo corrente non è diverso da null (massimo 5 secondi)
        observer.awaitInitialization(5, TimeUnit.SECONDS);

        // Verifica che il tempo corrente non sia null
        assertNotNull(clockModel.getCurrentTime());

        // Ferma la simulazione
        clockModel.stopSimulation();
    }

    // Classe observer di test per monitorare gli aggiornamenti del tempo
    private static class TestClockObserver implements ClockObserver {
        private CountDownLatch latch = new CountDownLatch(1);

        @Override
        public void onTimeUpdate(LocalTime currentTime, int currentDay) {
            // Una volta ricevuto un aggiornamento del tempo, controlla che il tempo corrente non sia null
            if (currentTime != null) {
                latch.countDown(); // Sblocca il latch per indicare che il tempo corrente è stato inizializzato
            }
        }

        // Attende fino a quando il tempo corrente non è stato inizializzato o finché non scade il timeout
        public void awaitInitialization(long timeout, TimeUnit unit) throws InterruptedException {
            latch.await(timeout, unit);
        }
    }
}

