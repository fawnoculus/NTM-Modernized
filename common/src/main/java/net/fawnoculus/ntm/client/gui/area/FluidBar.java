package net.fawnoculus.ntm.client.gui.area;

import dev.architectury.fluid.FluidStack;
import net.fawnoculus.ntm.NtmPlatform;
import net.fawnoculus.ntm.client.data.ClientFluidDataRegistry;
import net.fawnoculus.ntm.client.misc.NtmKeybinds;
import net.fawnoculus.ntm.fluid.FluidUnit;
import net.fawnoculus.ntm.misc.NtmDataComponentTypes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FluidBar implements InfoBar {
    private final int X;
    private final int Y;
    private final int WIDTH;
    private final int HEIGHT;
    private final FluidStack FLUID_STACK;
    private int OFFSET_X;
    private int OFFSET_Y;

    public FluidBar(int x, int y, int width, int height, FluidStack fluidStack) {
        this.X = x;
        this.Y = y;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.FLUID_STACK = fluidStack;
    }

    public void setOffsets(int offsetX, int offsetY) {
        this.OFFSET_X = offsetX;
        this.OFFSET_Y = offsetY;
    }

    @Override
    public int getX() {
        return this.X;
    }

    @Override
    public int getY() {
        return this.Y;
    }

    @Override
    public int getWidth() {
        return this.WIDTH;
    }

    @Override
    public int getHeigh() {
        return this.HEIGHT;
    }

    @Override
    public int getOffsetX() {
        return this.OFFSET_X;
    }

    @Override
    public int getOffsetY() {
        return this.OFFSET_Y;
    }

    @Override
    public int getU() {
        return 0;
    }

    @Override
    public int getV() {
        return 0;
    }

    @Override
    public int getTextureHeight() {
        TextureAtlasSprite sprite = this.getFluidSprite();
        if (sprite == null) return 16;
        return sprite.contents().height();
    }

    @Override
    public int getTextureWidth() {
        TextureAtlasSprite sprite = this.getFluidSprite();
        if (sprite == null) return 16;
        return sprite.contents().width();
    }

    @Override
    public Identifier getTexture() {
        TextureAtlasSprite sprite = this.getFluidSprite();
        if (sprite == null) return null;
        return sprite.atlasLocation();
    }

    private @Nullable TextureAtlasSprite getFluidSprite() {
        return NtmPlatform.getFluidSprites(this.FLUID_STACK.getFluid());
    }

    @Override
    public void appendTooltip(Consumer<Component> tooltip) {
        Component amount = Component.literal(String.format("%,d", this.FLUID_STACK.getAmount()));
        Component capacity = FluidUnit.text(this.FLUID_STACK.getOrDefault(NtmDataComponentTypes.MAX_FLUID.get(), 0L));

        tooltip.accept(Component.translatable("generic.ntm.amount_stored", amount, capacity));

        ClientFluidDataRegistry.getOrCreate(this.FLUID_STACK.getFluid()).appendTooltip(NtmKeybinds.DISPLAY_EXTRA_INFO.isDown(), tooltip);
    }

    @Override
    public double getFillState() {
        return (double) this.FLUID_STACK.getAmount() / (double) this.FLUID_STACK.getOrDefault(NtmDataComponentTypes.MAX_FLUID.get(), 0L);
    }
}
