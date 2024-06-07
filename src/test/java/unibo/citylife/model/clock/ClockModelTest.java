package unibo.citylife.model.clock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import unibo.citysimulation.model.clock.api.ClockModel;
import unibo.citysimulation.model.clock.api.ClockObserver;
import unibo.citysimulation.model.clock.impl.ClockModelImpl;
import unibo.citysimulation.utilities.ConstantAndResourceLoader;

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

        // Try to stop the simulation, nothing should happen
        clockModel.stopSimulation();

        // Start the simulation
        clockModel.restartSimulation();

        assertEquals(clockModel.getCurrentDay(), 1);

        assertEquals((Double) clockModel.getDoubleCurrentTime(), clockModel.getDoubleCurrentTime());

        assertNotNull(clockModel.getTimer());

        final int maxWaitTime = 5;
        observer.awaitInitialization(maxWaitTime, TimeUnit.SECONDS);

        clockModel.pauseSimulation();

        assertTrue(clockModel.isPaused());

        clockModel.pauseSimulation();

        assertFalse(clockModel.isPaused());

        // Verify that the current time is not null
        assertNotNull(clockModel.getCurrentTime());

        // Stop the simulation
        clockModel.stopSimulation();

        clockModel.setUpdateRate(ConstantAndResourceLoader.TIME_UPDATE_RATE / 10);

        assertTrue(clockModel.getUpdateRate() == ConstantAndResourceLoader.TIME_UPDATE_RATE / 10);

        // Restart the simulation
        clockModel.restartSimulation();

        clockModel.removeObserver(observer);
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
        public void awaitInitialization(final long timeout, final TimeUnit unit)
                throws InterruptedException, TimeoutException {
            final boolean success = latch.await(timeout, unit);
            if (!success) {
                throw new TimeoutException("Initialization did not complete within the specified timeout");
            }
        }
    }
}
