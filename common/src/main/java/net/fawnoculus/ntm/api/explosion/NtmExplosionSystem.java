package net.fawnoculus.ntm.api.explosion;

import net.fawnoculus.ntm.NtmConfig;
import org.jetbrains.annotations.Range;

import java.util.ArrayDeque;
import java.util.Deque;

public class NtmExplosionSystem {
    private static final Deque<ExplosionEntry<?, ?>> EXPLOSIONS = new ArrayDeque<>(NtmConfig.MAX_EXPLOSIONS.getValue() / 8);

    public static <E, S> void addExplosion(NtmExplosionType<E, S> type, NtmExplosionData explosionData, final E extraData) {
        if (EXPLOSIONS.size() >= NtmConfig.MAX_EXPLOSIONS.getValue()) {
            return;
        }

        EXPLOSIONS.addLast(new ExplosionEntry<>(type, explosionData, extraData, type.createExplosion(explosionData, extraData)));
    }

    public static void processExplosions(@Range(from = 0, to = Long.MAX_VALUE) long maxNanos) {
        maxNanos = Math.min(maxNanos, NtmConfig.MIN_EXPLOSION_NANOS.getValue());

        final long endNanoTime = System.nanoTime() + maxNanos;
        long currentNanoTime = System.nanoTime();

        while (currentNanoTime < endNanoTime && !EXPLOSIONS.isEmpty()) {
            ExplosionEntry<?, ?> explosionEntry = EXPLOSIONS.peekFirst();
            explosionEntry.processExplosion(endNanoTime - currentNanoTime);

            if (explosionEntry.isDone()) {
                EXPLOSIONS.pollFirst();
            }

            currentNanoTime = System.nanoTime();
        }
    }


    public record ExplosionEntry<E, S>(NtmExplosionType<E, S> type, NtmExplosionData explosionData, E extraData,
                                       S explosionState) {
        void processExplosion(@Range(from = 0, to = Long.MAX_VALUE) long maxNanos) {
            type.processExplosion(maxNanos, explosionData, extraData, explosionState);
        }

        boolean isDone() {
            return type.isDone(explosionData, extraData, explosionState);
        }
    }
}
