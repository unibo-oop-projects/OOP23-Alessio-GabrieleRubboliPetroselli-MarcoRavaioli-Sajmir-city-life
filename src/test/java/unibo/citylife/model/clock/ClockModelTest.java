package unibo.citylife.model.clock;
 
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
    private static final int TOTAL_DAYS = 5; // Number of days to simulate
 
    @BeforeEach
    void setUp() {
        clockModel = new ClockModelImpl(TOTAL_DAYS);
    }
 
    @Test
    void testClockModel() throws InterruptedException, TimeoutException {
        // Add a test observer to monitor time updates
        final TestClockObserver observer = new TestClockObserver();
        clockModel.addObserver(observer);
 
        // Start the simulation
        clockModel.restartSimulation();
 
        final int maxWaitTime = 5;
        observer.awaitInitialization(maxWaitTime, TimeUnit.SECONDS);
 
        // Verify that the current time is not null
        assertNotNull(clockModel.getCurrentTime());
 
        // Stop the simulation
        clockModel.stopSimulation();
    }
 
    // Observer test class
    private static final class TestClockObserver implements ClockObserver {
        private final CountDownLatch latch = new CountDownLatch(1);
 
        @Override
        public void onTimeUpdate(final LocalTime currentTime, final int currentDay) {
            // Verify that the current time is not null
            if (currentTime != null) {
                latch.countDown();
            }
        }
 
        // Wait for the observer to be initialized
        public void awaitInitialization(final long timeout, final TimeUnit unit) throws InterruptedException, TimeoutException {
            final boolean success = latch.await(timeout, unit);
            if (!success) {
                throw new TimeoutException("Initialization did not complete within the specified timeout");
            }
        }
    }
}

