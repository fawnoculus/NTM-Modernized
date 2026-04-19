package net.fawnoculus.ntm.util;

import net.fawnoculus.ntm.misc.NtmTranslations;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;

public class NtmTextUtil {
    public static @NonNull MutableComponent unit(long val) {
        if (val >= 1_000_000_000_000_000_000L || val <= -1_000_000_000_000_000_000L) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_EXA));
        }
        if (val >= 1_000_000_000_000_000L || val <= -1_000_000_000_000_000L) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_PETA));
        }
        if (val >= 1_000_000_000_000L || val <= -1_000_000_000_000L) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_TERA));
        }
        if (val >= 1_000_000_000L || val <= -1_000_000_000L) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_GIGA));
        }
        if (val >= 1_000_000L || val <= -1_000_000L) {
            return Component.literal(String.format("%.1f", val / 1_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_MEGA));
        }
        if (val >= 1_000L || val <= -1_000L) {
            return Component.literal(String.format("%.1f", val / 1_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_KILO));
        }
        return Component.literal(String.valueOf(val));
    }

    public static @NonNull MutableComponent unit(long val, String suffixKey) {
        return unit(val).append(Component.translatable(suffixKey));
    }

    public static @NonNull MutableComponent unit(double val) {
        if (val >= 1_000_000_000_000_000_000.0 || val <= -1_000_000_000_000_000_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_EXA));
        }
        if (val >= 1_000_000_000_000_000.0 || val <= -1_000_000_000_000_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_PETA));
        }
        if (val >= 1_000_000_000_000.0 || val <= -1_000_000_000_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_TERA));
        }
        if (val >= 1_000_000_000.0 || val <= -1_000_000_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_GIGA));
        }
        if (val >= 1_000_000.0 || val <= -1_000_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_MEGA));
        }
        if (val >= 1_000.0 || val <= -1_000.0) {
            return Component.literal(String.format("%.1f", val / 1_000.0))
              .append(Component.translatable(NtmTranslations.GENERIC_UNIT_PREFIX_KILO));
        }
        return Component.literal(String.valueOf(val));
    }

    public static @NonNull MutableComponent unit(double val, String suffixKey) {
        return unit(val).append(Component.translatable(suffixKey));
    }

    public static @NonNull MutableComponent romanNumeral(@Range(from = 1, to = 3999) int number) {
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

    @Contract("_ -> new")
    public static @NonNull MutableComponent tooltip11(@NonNull Item item) {
        return Component.translatable(NtmTranslations.tooltipKey11(item));
    }

    @Contract("_, _ -> new")
    public static @NonNull MutableComponent tooltip11(@NonNull Item item, int line) {
        return Component.translatable(NtmTranslations.tooltipKey11(item, line));
    }

    @Contract("_ -> new")
    public static @NonNull MutableComponent tooltip(@NonNull Item item) {
        return Component.translatable(NtmTranslations.tooltipKey(item));
    }

    @Contract("_, _ -> new")
    public static @NonNull MutableComponent tooltip(@NonNull Item item, int line) {
        return Component.translatable(NtmTranslations.tooltipKey(item, line));
    }
}
