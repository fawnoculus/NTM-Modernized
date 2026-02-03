package net.fawnoculus.ntm.api.node;

import net.fawnoculus.ntm.api.node.network.NodeNetwork;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface NodeValueContainer {
    long getValue();

    NodeValueContainer setValue(@Range(from = 0, to = Long.MAX_VALUE) long value);

    long getMaxValue();

    NodeValueContainer setMaxValue(@Range(from = 0, to = Long.MAX_VALUE) long value);

    long getPriority();

    NodeValueContainer setPriority(long value);

    boolean provides();

    boolean consumes();

    Node getParent();

    @Nullable
    default NodeNetwork getNetwork() {
        return this.getParent().getNetwork();
    }

    default boolean hasAtLeast(long value) {
        return this.getValue() >= value;
    }

    default boolean isFull() {
        return this.getValue() >= this.getMaxValue();
    }

    default boolean isEmpty() {
        return this.getValue() <= 0;
    }

    /**
     * @param value the Value to be Stored
     * @return the amount that could not be stored (aka the remainder)
     */
    default long add(long value) {
        long remainingSpace = this.getMaxValue() - this.getValue();
        if (value > remainingSpace) {
            this.setValue(this.getMaxValue());
            return value - remainingSpace;
        }
        this.setValue(this.getValue() + value);
        return 0;
    }

    /**
     * @param value the Value to be Removed
     * @return the amount that could not be removed (aka the remainder)
     */
    default long remove(long value) {
        long previousValue = this.getValue();
        if (value > previousValue) {
            this.setValue(0);
            return value - previousValue;
        }
        this.setValue(previousValue - value);
        return 0;
    }

    default void onUpdatePriority(long oldPriority, long newPriority) {
        if (this.getNetwork() != null) {
            this.getNetwork().removeFromSorted(this, oldPriority, this.provides(), this.consumes());
            this.getNetwork().addToSorted(this, newPriority, this.provides(), this.consumes());
        }
    }

    default void onUpdateProvides(boolean oldProvides, boolean newProvides) {
        if (this.getNetwork() != null) {
            this.getNetwork().removeFromSorted(this, this.getPriority(), oldProvides, this.consumes());
            this.getNetwork().addToSorted(this, this.getPriority(), newProvides, this.consumes());
        }
    }

    default void onUpdateConsumes(boolean oldConsumes, boolean newConsumes) {
        if (this.getNetwork() != null) {
            this.getNetwork().removeFromSorted(this, this.getPriority(), this.provides(), oldConsumes);
            this.getNetwork().addToSorted(this, this.getPriority(), this.provides(), newConsumes);
        }
    }
}
