package net.minecraftforge.common;

import java.util.List;

@SuppressWarnings("unused")
public class ForgeConfigSpec {
    public static class Builder {
        public Builder push(String name) { return this; }
        public Builder pop() { return this; }
        public Builder comment(String c) { return this; }
        public BooleanValue define(String name, boolean defaultValue) { return new BooleanValue(defaultValue); }
        public IntValue defineInRange(String name, int defaultValue, int min, int max) { return new IntValue(defaultValue); }
        public DoubleValue defineInRange(String name, double defaultValue, double min, double max) { return new DoubleValue(defaultValue); }
        public <T> ConfigValue<T> define(String name, T defaultValue) { return new ConfigValue<>(); }
    public <T> ConfigValue<java.util.List<T>> defineList(String name, java.util.function.Supplier<? extends java.util.List<T>> defaultValue, java.util.function.Predicate<Object> validator) { return new ConfigValue<>(); }
        public ForgeConfigSpec build() { return new ForgeConfigSpec(); }
    }

    public static class BooleanValue {
        private final boolean v;
        public BooleanValue() { this.v = false; }
        public BooleanValue(boolean v) { this.v = v; }
        public boolean get() { return v; }
    }
    public static class IntValue {
        private final int v;
        public IntValue() { this.v = 0; }
        public IntValue(int v) { this.v = v; }
        public int get() { return v; }
    }
    public static class DoubleValue {
        private final double v;
        public DoubleValue() { this.v = 0.0; }
        public DoubleValue(double v) { this.v = v; }
        public double get() { return v; }
    }

    public static class ConfigValue<T> {
        public T get() { return null; }
    }
}
