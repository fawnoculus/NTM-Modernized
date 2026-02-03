package net.fawnoculus.ntm.fluid.data;

import com.mojang.serialization.Codec;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.fluid.FluidUnit;
import net.fawnoculus.ntm.fluid.data.custom.*;
import net.fawnoculus.ntm.util.TextUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FluidDataTypes {
    public static final FluidDataType<Double> TEMPERATURE = register("temperature", Codec.DOUBLE, 20.0, Tooltips::temperature);
    public static final FluidDataType<HeatingData> HEATABLE = register("heatable", HeatingData.CODEC, HeatingData.DEFAULT, Tooltips::boilable, true);
    public static final FluidDataType<CoolingData> COOLABLE = register("coolable", CoolingData.CODEC, CoolingData.DEFAULT, Tooltips::coolable, true);
    public static final FluidDataType<Double> PWR_FLUX_MULTIPLIER = register("pwr_flux_multiplier", Codec.DOUBLE, 0.0, Tooltips::pwrFluxMultiplier, true);
    public static final FluidDataType<Double> FLAMMABLE = register("flammable", Codec.DOUBLE, 0.0, Tooltips::flammable);
    public static final FluidDataType<Combustible> COMBUSTIBLE = register("combustible", Combustible.CODEC, Combustible.DEFAULT, Tooltips::combustible);
    public static final FluidDataType<Polluting> POLLUTING = register("polluting", Polluting.CODEC, Polluting.DEFAULT, Tooltips::polluting, true);
    public static final FluidDataType<Boolean> RADIOACTIVE = register("radioactive", Codec.BOOL, false, Tooltips::radioactive);
    public static final FluidDataType<Boolean> TOXIC_FUMES = register("toxic_fumes", Codec.BOOL, false, Tooltips::toxicFumes, true);
    public static final FluidDataType<ToxinData> TOXIN = register("toxin", ToxinData.CODEC, ToxinData.DEFAULT, Tooltips::toxin, true);
    public static final FluidDataType<Boolean> GLYPHID_PHEROMONES = register("glyphid_pheromones", Codec.BOOL, false, Tooltips::glyphidPheromones);
    public static final FluidDataType<Boolean> MODIFIED_PHEROMONES = register("modified_pheromones", Codec.BOOL, false, Tooltips::modifiedPheromones);
    public static final FluidDataType<Boolean> GASEOUS_AT_ROOM_TEMPERATURE = register("gaseous_at_room_temperature", Codec.BOOL, false, Tooltips::gaseousAtRoomTemperature, true);
    public static final FluidDataType<StateOfMatter> STATE_OF_MATTER = register("state_of_matter", StateOfMatter.CODEC, StateOfMatter.LIQUID, Tooltips::stateOfMatter, true);
    public static final FluidDataType<Boolean> IGNORED_BY_SIPHON = register("ignored_by_siphon", Codec.BOOL, false, Tooltips::ignoredBySiphon, true);
    public static final FluidDataType<Boolean> BREATHABLE = register("breathable", Codec.BOOL, false, Tooltips::breathable, true);
    public static final FluidDataType<Boolean> VISCOUS = register("viscous", Codec.BOOL, false, Tooltips::viscous, true);
    public static final FluidDataType<Boolean> DELICIOUS = register("delicious", Codec.BOOL, false, Tooltips::delicious, true);
    public static final FluidDataType<Boolean> ANTIMATTER = register("antimatter", Codec.BOOL, false, Tooltips::antimatter);


    private static <T> @NotNull FluidDataType<T> register(String name, Codec<T> codec, @Nullable T defaultValue, @Nullable FluidDataType.TooltipProvider<T> tooltip) {
        return register(name, codec, defaultValue, tooltip, false);
    }

    private static <T> @NotNull FluidDataType<T> register(String name, Codec<T> codec, @Nullable T defaultValue, @Nullable FluidDataType.TooltipProvider<T> tooltip, boolean hasExtraInfo) {
        return new FluidDataType<>(Ntm.id(name), codec, defaultValue, tooltip, hasExtraInfo).register();
    }

    private static class Tooltips {
        private static void temperature(Double celsius, boolean showExtraInfo, Consumer<Component> tooltip) {
            ChatFormatting formatting = ChatFormatting.RED;
            if (celsius < 0) {
                formatting = ChatFormatting.BLUE;
            }

            tooltip.accept(NtmConfig.TEMP_UNIT.getValue()
              .CELSIUS_TO_TEXT.apply(celsius).withStyle(formatting)
            );
        }

        // Helper
        private static Component thermalCapacity(Double tuPerDroplet) {
            return switch (NtmConfig.FLUID_UNIT.getValue()) {
                case NtmConfig.FluidUnit.MilliBuckets ->
                  Component.translatable("fluid_tooltip.ntm.thermal_capacity", FluidUnit.dropletsToMB(tuPerDroplet), Component.translatable("generic.ntm.fluid.mb")).withStyle(ChatFormatting.RED);
                case NtmConfig.FluidUnit.Droplets ->
                  Component.translatable("fluid_tooltip.ntm.thermal_capacity", tuPerDroplet, Component.translatable("generic.ntm.fluid.droplets")).withStyle(ChatFormatting.RED);
            };
        }

        // Helper
        private static Component efficiency(Double multiplier) {
            return Component.translatable("fluid_tooltip.ntm.efficiency", String.format("%1$.0f", multiplier * 100)).withStyle(ChatFormatting.AQUA);
        }

        private static void boilable(HeatingData data, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (!showExtraInfo) return;
            tooltip.accept(thermalCapacity(data.tuPerDroplet()));
            if (data.isBoilable()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.boilable", efficiency(data.boilingMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
            if (data.isHeatable()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.heatable", efficiency(data.heatingMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
            if (data.isPwrCoolant()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.pwr_coolant", efficiency(data.pwrCoolantMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
            if (data.isIcfCoolant()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.icf_coolant", efficiency(data.icfCoolantMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
            if (data.isParticleAcceleratorCoolant()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.particle_accelerator_coolant", efficiency(data.particleAcceleratorCoolantMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
        }

        private static void coolable(CoolingData data, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (!showExtraInfo) return;
            tooltip.accept(thermalCapacity(data.tuPerDroplet()));
            if (data.isTurbineable()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.turbine_steam", efficiency(data.turbineMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
            if (data.isCoolable()) {
                tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.coolable", efficiency(data.coolingMultiplier())).withStyle(ChatFormatting.YELLOW)
                );
            }
        }

        private static void pwrFluxMultiplier(Double multiplier, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (multiplier < 0.001 && multiplier > -0.001) return; // No Multipliers under 1%
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.pwr_flux_multiplier").withStyle(ChatFormatting.BLUE));

            if (!showExtraInfo) return;
            if (multiplier < 0) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.pwr_flux_multiplier.val", String.format("%1$.0f", multiplier * 100)).withStyle(ChatFormatting.BLUE));
            } else {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.pwr_flux_multiplier.val", String.format("+%1$.0f", multiplier * 100)).withStyle(ChatFormatting.BLUE));
            }
        }

        private static void flammable(Double tuPerDroplet, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (tuPerDroplet <= 0) return;
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.flammable").withStyle(ChatFormatting.YELLOW));
            switch (NtmConfig.FLUID_UNIT.getValue()) {
                case NtmConfig.FluidUnit.MilliBuckets -> tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.provides", TextUtil.unit(FluidUnit.dropletsToMB(tuPerDroplet)), Component.translatable("generic.ntm.fluid.mb")).withStyle(ChatFormatting.YELLOW)
                );
                case NtmConfig.FluidUnit.Droplets -> tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.provides", TextUtil.unit(tuPerDroplet), Component.translatable("generic.ntm.fluid.droplet")).withStyle(ChatFormatting.YELLOW)
                );
            }
        }

        private static void combustible(Combustible data, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (!data.isCombustible()) return;
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.combustible"));
            switch (NtmConfig.FLUID_UNIT.getValue()) {
                case NtmConfig.FluidUnit.MilliBuckets -> tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.provides",
                    TextUtil.unit(FluidUnit.dropletsToMB(data.ntePerDroplet()), "generic.ntm.delta_kelvin"),
                    Component.translatable("generic.ntm.fluid.mb")
                  ).withStyle(ChatFormatting.GOLD)
                );
                case NtmConfig.FluidUnit.Droplets -> tooltip.accept(
                  Component.translatable("fluid_tooltip.ntm.provides",
                    TextUtil.unit(data.ntePerDroplet(), "generic.ntm.delta_kelvin").withStyle(ChatFormatting.RED),
                    Component.translatable("generic.ntm.fluid.droplets")
                  ).withStyle(ChatFormatting.GOLD)
                );
            }
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.combustible.fuel_grade", data.fuelGrade().NAME).withStyle(ChatFormatting.GOLD));
        }

        private static void polluting(Polluting data, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (!data.isPolluting()) return;
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.polluting"));

            if (!showExtraInfo) return;
            if (!data.whenSpilled().isEmpty()) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.polluting.spilled").withStyle(ChatFormatting.GREEN));
                for (PollutionData spilled : data.whenSpilled()) {
                    tooltip.accept(spilled.getTooltip().withStyle(ChatFormatting.GREEN));
                }
            }
            if (!data.whenBurned().isEmpty()) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.polluting.burned").withStyle(ChatFormatting.RED));
                for (PollutionData spilled : data.whenBurned()) {
                    tooltip.accept(spilled.getTooltip().withStyle(ChatFormatting.RED));
                }
            }
        }

        private static void radioactive(Boolean isRadioactive, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (isRadioactive) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.radioactive").withStyle(ChatFormatting.YELLOW));
            }
        }

        private static void toxicFumes(Boolean isModifiedPheromone, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isModifiedPheromone) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.toxic_fumes").withStyle(ChatFormatting.GREEN));
            }
        }

        private static void toxin(ToxinData data, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (!(showExtraInfo && data.isToxic())) return;
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.toxin").withStyle(ChatFormatting.LIGHT_PURPLE));
            tooltip.accept(Component.translatable("fluid_tooltip.ntm.toxin.type", data.type().NAME).withStyle(ChatFormatting.YELLOW));
            if (data.damagePerSec() > 0) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.toxin.dps", data.damagePerSec()).withStyle(ChatFormatting.YELLOW));
            }
            if (!data.effects().isEmpty()) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.toxin.effects").withStyle(ChatFormatting.YELLOW));
            }
            for (MobEffectInstance effect : data.effects()) {
                tooltip.accept(
                  Component.literal(" - ")
                    .append(Component.translatable(effect.getDescriptionId()))
                    .append(" ")
                    .append(Component.translatable("potion.potency." + effect.getAmplifier()))
                    .append(String.format(" %1$d:%2$d", (effect.getDuration() / 20) / 60, (effect.getDuration() / 20) % 60))
                    .withStyle(ChatFormatting.YELLOW)
                );
            }
        }

        private static void glyphidPheromones(Boolean isGlyphidPheromone, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (isGlyphidPheromone) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.glyphid_pheromones").withStyle(ChatFormatting.AQUA));
            }
        }

        private static void modifiedPheromones(Boolean isModifiedPheromone, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (isModifiedPheromone) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.modified_pheromones").withStyle(ChatFormatting.BLUE));
            }
        }

        private static void gaseousAtRoomTemperature(Boolean isModifiedPheromone, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isModifiedPheromone) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.gaseous_at_room_temperature").withStyle(ChatFormatting.BLUE));
            }
        }

        private static void stateOfMatter(StateOfMatter state, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo) {
                tooltip.accept(state.TOOLTIP);
            }
        }

        private static void ignoredBySiphon(Boolean isIgnored, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isIgnored) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.ignored_by_siphon").withStyle(ChatFormatting.BLUE));
            }
        }

        private static void breathable(Boolean isViscous, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isViscous) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.breathable").withStyle(ChatFormatting.AQUA));
            }
        }

        private static void viscous(Boolean isViscous, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isViscous) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.viscous").withStyle(ChatFormatting.BLUE));
            }
        }

        private static void delicious(Boolean isDelicious, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (showExtraInfo && isDelicious) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.delicious").withStyle(ChatFormatting.DARK_GREEN));
            }
        }

        private static void antimatter(Boolean isAntimatter, boolean showExtraInfo, Consumer<Component> tooltip) {
            if (isAntimatter) {
                tooltip.accept(Component.translatable("fluid_tooltip.ntm.antimatter").withStyle(ChatFormatting.DARK_RED));
            }
        }
    }
}
