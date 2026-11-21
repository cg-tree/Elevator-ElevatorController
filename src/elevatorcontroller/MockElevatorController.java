package elevatorcontroller;

import common.SoftwareBusWrapper;
import common.SoftwareBusApi;
import motionmechanics.MockMotionController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A development mock that drives the motion mechanics using the software bus.
 * <p/>
 * In the real system, the ElevatorController will listen for messages from the manager on the bus and translate those
 * into commands sent to the motion subsystem.
 */
public class MockElevatorController {

    static void main() {
        SoftwareBusApi bus = new SoftwareBusWrapper();
        MockMotionController mockMotion = new MockMotionController(bus, 1);

        // We don't want the timer to shut down when we hit the end of main(), so don't wrap
        // in a try-with-resource.
        //noinspection resource
        ScheduledExecutorService timer  = Executors.newSingleThreadScheduledExecutor();
        AtomicLong last = new AtomicLong(System.nanoTime());
        timer.scheduleAtFixedRate(() -> {
           try {
               long now = System.nanoTime();
               mockMotion.tick((now - last.getAndSet(now)) / 1e9);
           } catch (Throwable e) {
               e.printStackTrace();
           }
        }, 0, 16, TimeUnit.MILLISECONDS);
    }
}
