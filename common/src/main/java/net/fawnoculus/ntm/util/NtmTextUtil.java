package net.fawnoculus.ntm.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class NtmTextUtil {
    public static MutableComponent unit(long val) {
        if (val >= 1_000_000_000_000_000_000L || val <= -1_000_000_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.e", String.format("%.1f", val / 1_000_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000_000L || val <= -1_000_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.p", String.format("%.1f", val / 1_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000L || val <= -1_000_000_000_000L) {
            return Component.translatable("generic.ntm.unit.t", String.format("%.1f", val / 1_000_000_000_000.0));
        }
        if (val >= 1_000_000_000L || val <= -1_000_000_000L) {
            return Component.translatable("generic.ntm.unit.g", String.format("%.1f", val / 1_000_000_000.0));
        }
        if (val >= 1_000_000L || val <= -1_000_000L) {
            return Component.translatable("generic.ntm.unit.m", String.format("%.1f", val / 1_000_000.0));
        }
        if (val >= 1_000L || val <= -1_000L) {
            return Component.translatable("generic.ntm.unit.k", String.format("%.1f", val / 1_000.0));
        }
        return Component.literal(String.valueOf(val));
    }

    public static MutableComponent unit(long val, String suffixKey) {
        return unit(val).append(Component.translatable(suffixKey));
    }

    public static MutableComponent unit(double val) {
        if (val >= 1_000_000_000_000_000_000.0 || val <= -1_000_000_000_000_000_000.0) {
            return Component.translatable("generic.ntm.unit.e", String.format("%.1f", val / 1_000_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000_000.0 || val <= -1_000_000_000_000_000.0) {
            return Component.translatable("generic.ntm.unit.p", String.format("%.1f", val / 1_000_000_000_000_000.0));
        }
        if (val >= 1_000_000_000_000.0 || val <= -1_000_000_000_000.0) {
            return Component.translatable("generic.ntm.unit.t", String.format("%.1f", val / 1_000_000_000_000.0));
        }
        if (val >= 1_000_000_000.0 || val <= -1_000_000_000.0) {
            return Component.translatable("generic.ntm.unit.g", String.format("%.1f", val / 1_000_000_000.0));
        }
        if (val >= 1_000_000.0 || val <= -1_000_000.0) {
            return Component.translatable("generic.ntm.unit.m", String.format("%.1f", val / 1_000_000.0));
        }
        if (val >= 1_000.0 || val <= -1_000.0) {
            return Component.translatable("generic.ntm.unit.k", String.format("%.1f", val / 1_000.0));
        }
        return Component.literal(String.format("%.1f", val));
    }

    public static MutableComponent unit(double val, String suffixKey) {
        return unit(val).append(Component.translatable(suffixKey));
    }

    public static MutableComponent romanNumeral(int number) {
        if (number < 1 || number >= 3999) {
            return Component.literal(Integer.toString(number));
        }

        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                builder.append(symbols[i]);
                number -= values[i];
            }
        }

        return Component.literal(builder.toString());

    }
}
