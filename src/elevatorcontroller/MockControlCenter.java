package elevatorcontroller;

import common.SoftwareBusWrapper;
import common.SoftwareBusApi;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A development mock acts like a Control Center to drive the Elevator Controller during development.
 * The Elevator Controller responds to commands from the Control Center, so to test out our Elevator Controller we need
 * something that acts like a Control Center.
 * <p />
 * Not yet implemented. The skeleton is from the motion mechanics repo.
 */
public class MockControlCenter {

    static void main() {
        SoftwareBusApi bus = new SoftwareBusWrapper();

        // We don't want the timer to shut down when we hit the end of main(), so don't wrap
        // in a try-with-resource.
        //noinspection resource
        ScheduledExecutorService timer  = Executors.newSingleThreadScheduledExecutor();
        AtomicLong last = new AtomicLong(System.nanoTime());
        timer.scheduleAtFixedRate(() -> {
           try {
               long now = System.nanoTime();
               // TODO: THIS IS WHERE WE WOULD DRIVE THE ELEVATOR CONTROLLER!
           } catch (Throwable e) {
               e.printStackTrace();
           }
        }, 0, 16, TimeUnit.MILLISECONDS);
    }
}
