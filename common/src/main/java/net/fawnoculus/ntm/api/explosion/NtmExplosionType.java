package net.fawnoculus.ntm.api.explosion;

import org.jetbrains.annotations.Range;

/**
 * A type of Explosion
 *
 * @param <E> ExtraData used by this type (like explosion radius), should not change after an explosion has been created with it
 * @param <S> Explosion State, Used by explosions that exist over multiple ticks to keep state over multiple method calls
 */
public interface NtmExplosionType<E, S> {
    /**
     * Create Explosion State
     *
     * @param explosionData the generic explosion data
     * @param extraData     the extra data
     * @return explosion State
     */
    S createExplosion(NtmExplosionData explosionData, final E extraData);

    /**
     * Process the Explosion (calculate the full explosion for types explosions or process parts of it for complex types).
     * Also send explosion information to players if necessary.
     *
     * @param maxNanos the amount of nanoseconds to spend processing the explosion (simple explosion types may simply ignore this value)
     */
    void processExplosion(@Range(from = 0, to = Long.MAX_VALUE) long maxNanos, NtmExplosionData explosionData, final E extraData, S explosionState);

    /**
     * check if the Explosion is done yet, the explosion will be removed from to Que once this returns true
     *
     * @return if the Explosion is done yet
     */
    boolean isDone(NtmExplosionData explosionData, final E extraData, S explosionState);
}
