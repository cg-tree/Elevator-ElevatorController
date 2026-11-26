package elevatorcontroller;

import common.SoftwareBusWrapper;
import common.SoftwareBusApi;
import messages.GoToMessage;
import messages.ServiceMessage;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A development mock acts like a Control Center to drive the Elevator Controller during development.
 * The Elevator Controller responds to commands from the Control Center, so to test out our Elevator Controller we need
 * something that acts like a Control Center.
 */
public class MockControlCenter {
    private static final int ELEV_NUM = 1;

    static void main() {
        SoftwareBusApi bus = new SoftwareBusWrapper();
        UUID id = bus.register();
        Random rng = new Random(1337);

        // We don't want the timer to shut down when we hit the end of main(), so don't wrap
        // in a try-with-resource.
        //noinspection resource
        ScheduledExecutorService timer  = Executors.newSingleThreadScheduledExecutor();
        AtomicLong last = new AtomicLong(System.nanoTime());
        timer.scheduleAtFixedRate(() -> {
           try {
               long now = System.nanoTime();
               if (rng.nextBoolean()) {
                   ServiceMessage msg = new ServiceMessage(rng.nextInt(1, 11));
                   bus.publish(id, msg.toMsg(ELEV_NUM));
               } else {
                   GoToMessage msg = new GoToMessage(rng.nextInt(1, 11));
                   bus.publish(id, msg.toMsg(ELEV_NUM));
               }
           } catch (Throwable e) {
               e.printStackTrace();
           }
        }, 0, 10, TimeUnit.SECONDS);
    }
}
