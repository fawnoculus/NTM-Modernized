package net.fawnoculus.ntm.client.render.model;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import net.fawnoculus.ntm.NtmConfig;
import net.fawnoculus.ntm.client.NtmClient;
import net.fawnoculus.ntm.client.render.wavefront.model.Model3d;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.TextureSlots;
import net.minecraft.client.renderer.item.*;
import net.minecraft.client.renderer.item.ItemStackRenderState.LayerRenderState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemModel3D implements ItemModel {
    private final List<BakedQuad> quads;
    private final Supplier<Vector3fc[]> vector;
    private final ModelRenderProperties settings;

    public ItemModel3D(List<BakedQuad> quads, ModelRenderProperties settings) {
        this.quads = quads;
        this.settings = settings;
        this.vector = Suppliers.memoize(() -> BlockModelWrapper.computeExtents(this.quads));
    }

    @Override
    public void update(ItemStackRenderState state, @NonNull ItemStack stack, @NonNull ItemModelResolver resolver, @NonNull ItemDisplayContext displayContext, @org.jspecify.annotations.Nullable ClientLevel world, @org.jspecify.annotations.Nullable ItemOwner heldItemContext, int seed) {
        LayerRenderState layer = state.newLayer();

        layer.setExtents(this.vector);
        layer.setRenderType(Sheets.translucentItemSheet());
        this.settings.applyToLayer(layer, displayContext);
        layer.prepareQuadList().addAll(this.quads);
    }

    public record Unbaked(Model3d model3d, Identifier baseModelId,
                          Function<Vector3f, Vector3f> offset) implements ItemModel.Unbaked {
        public Unbaked(Model3d model3d, Identifier baseModelId) {
            this(model3d, baseModelId, model3d.defaultItemTransforms());
        }

        @Override
        public void resolveDependencies(Resolver resolver) {
            resolver.markDependency(this.baseModelId);
        }

        @Override
        public @NonNull ItemModel bake(BakingContext context) {

            ModelBaker baker = context.blockModelBaker();
            ResolvedModel bakedSimpleModel = baker.getModel(this.baseModelId);
            TextureSlots modelTextures = bakedSimpleModel.getTopTextureSlots();
            ModelRenderProperties modelSettings = ModelRenderProperties.fromResolvedModel(baker, bakedSimpleModel, modelTextures);

            List<BakedQuad> quads = model3d.bake(baker, bakedSimpleModel, offset);

            return new ItemModel3D(quads, modelSettings);
        }

        @Override
        public MapCodec<Unbaked> type() {
            if (NtmConfig.DEV_MODE.getValue()) {
                NtmClient.LOGGER.warn("FUCK this is actually called somewhere");
                NtmClient.LOGGER.warn("This is probably not good");
            }
            return null;
        }
    }
}
