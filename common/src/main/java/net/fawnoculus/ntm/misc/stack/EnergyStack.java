package net.fawnoculus.ntm.misc.stack;

import net.fawnoculus.ntm.api.node.Node;
import net.fawnoculus.ntm.api.node.NodeValueContainer;
import net.fawnoculus.ntm.api.node.StorageMode;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Range;

public class EnergyStack implements NodeValueContainer {
    protected final Node PARENT;
    protected long value = 0;
    protected long maxValue = 0;
    protected long priority = 0;
    protected boolean consumes = false;
    protected boolean provides = false;
    protected Runnable onChange = () -> {
    };

    public EnergyStack(Node parent) {
        this.PARENT = parent;
    }

    @Override
    public long getValue() {
        return this.value;
    }

    @Override
    public EnergyStack setValue(@Range(from = 0, to = Long.MAX_VALUE) long value) {
        this.value = value;
        onChange.run();
        return this;
    }

    @Override
    public long getMaxValue() {
        return this.maxValue;
    }

    @Override
    public EnergyStack setMaxValue(@Range(from = 0, to = Long.MAX_VALUE) long value) {
        this.maxValue = value;
        onChange.run();
        return this;
    }

    @Override
    public long getPriority() {
        return this.priority;
    }

    @Override
    public EnergyStack setPriority(long value) {
        this.priority = value;
        onChange.run();
        return this;
    }

    public EnergyStack setProvides(boolean provides) {
        this.onUpdateProvides(this.provides, provides);
        this.provides = provides;
        onChange.run();
        return this;
    }

    @Override
    public boolean provides() {
        return this.provides;
    }

    public EnergyStack setConsumes(boolean consumes) {
        this.onUpdateConsumes(this.consumes, consumes);
        this.consumes = consumes;
        onChange.run();
        return this;
    }

    @Override
    public boolean consumes() {
        return this.consumes;
    }

    @Override
    public Node getParent() {
        return this.PARENT;
    }

    public EnergyStack onChange(Runnable runnable) {
        this.onChange = runnable;
        return this;
    }

    public void writeData(ValueOutput output) {
        output.putLong("energy_stack.stored_energy", this.value);
        output.putLong("energy_stack.max_energy", this.maxValue);
        output.putLong("energy_stack.priority", this.priority);
    }

    public void readData(ValueInput input) {
        this.value = input.getLongOr("energy_stack.stored_energy", 0);
        this.maxValue = input.getLongOr("energy_stack.max_energy", 0);
        this.priority = input.getLongOr("energy_stack.priority", 0);
    }

    @Override
    public String toString() {
        return "EnergyStack{" +
          "value=" + value +
          ", maxValue=" + maxValue +
          ", priority=" + priority +
          ", consumes=" + consumes +
          ", provides=" + provides +
          ", PARENT=" + PARENT +
          '}';
    }

    public static class Storage extends EnergyStack {
        private StorageMode mode = StorageMode.Consume;

        public Storage(Node parent) {
            super(parent);
        }

        public StorageMode getStorageMode() {
            return this.mode;
        }

        public Storage setStorageMode(StorageMode mode) {
            this.setConsumes(mode.consumes);
            this.setProvides(mode.provides);

            this.mode = mode;
            return this;
        }

        @Override
        public Storage setValue(@Range(from = 0, to = Long.MAX_VALUE) long value) {
            super.setValue(value);
            return this;
        }

        @Override
        public Storage setMaxValue(@Range(from = 0, to = Long.MAX_VALUE) long value) {
            super.setMaxValue(value);
            return this;
        }

        @Override
        public Storage setPriority(long value) {
            super.setPriority(value);
            return this;
        }

        @Override
        public Storage setConsumes(boolean consumes) {
            super.setConsumes(consumes);
            return this;
        }

        @Override
        public Storage setProvides(boolean provides) {
            super.setProvides(provides);
            return this;
        }

        @Override
        public Storage onChange(Runnable runnable) {
            super.onChange(runnable);
            return this;
        }

        @Override
        public void writeData(ValueOutput output) {
            super.writeData(output);
            output.store("energy_stack.storage_mode", StorageMode.CODEC, this.mode);
        }

        @Override
        public void readData(ValueInput input) {
            super.readData(input);
            this.setStorageMode(
              input.read("energy_stack.storage_mode", StorageMode.CODEC).orElse(StorageMode.Consume)
            );
        }

        @Override
        public String toString() {
            return "EnergyStack.Storage{" +
              "value=" + value +
              ", maxValue=" + maxValue +
              ", priority=" + priority +
              ", consumes=" + consumes +
              ", provides=" + provides +
              ", mode=" + mode +
              ", PARENT=" + PARENT +
              '}';
        }
    }
}
