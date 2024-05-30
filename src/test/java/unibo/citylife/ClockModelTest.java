package unibo.citylife;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
 
import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.clock.impl.ClockModelImpl;
 
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
 
class ClockModelTest {
 
    private ClockModel clockModel;
    private static final int TOTAL_DAYS = 5; // Numero arbitrario di giorni per il test
 
    @BeforeEach
    void setUp() {
        clockModel = new ClockModelImpl(TOTAL_DAYS);
    }
 
    @Test
    void testClockModel() throws InterruptedException, TimeoutException {
        // Aggiungi un observer per monitorare gli aggiornamenti del tempo
        final TestClockObserver observer = new TestClockObserver();
        clockModel.addObserver(observer);
 
        // Avvia la simulazione
        clockModel.restartSimulation(); // Durata di un'ora simulata in millisecondi
 
        final int maxWaitTime = 5;
        observer.awaitInitialization(maxWaitTime, TimeUnit.SECONDS);
 
        // Verifica che il tempo corrente non sia null
        assertNotNull(clockModel.getCurrentTime());
 
        // Ferma la simulazione
        clockModel.stopSimulation();
    }
 
    // Classe observer di test per monitorare gli aggiornamenti del tempo
    private static final class TestClockObserver implements ClockObserver {
        private final CountDownLatch latch = new CountDownLatch(1);
 
        @Override
        public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
            // Una volta ricevuto un aggiornamento del tempo, controlla che il tempo corrente non sia null
            if (currentTime != null) {
                latch.countDown(); // Sblocca il latch per indicare che il tempo corrente è stato inizializzato
            }
        }
 
        // Attende fino a quando il tempo corrente non è stato inizializzato o finché non scade il timeout
        public void awaitInitialization(final long timeout, final TimeUnit unit) throws InterruptedException, TimeoutException {
            final boolean success = latch.await(timeout, unit);
            if (!success) {
                throw new TimeoutException("Initialization did not complete within the specified timeout");
            }
        }
    }
}

