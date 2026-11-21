package common;

/**
 * Something that advances in simulated time.
 */
@FunctionalInterface
public interface Tickable {
    /**
     * @param dt Time delta in seconds.
     */
    void tick(double dt); // seconds
}
