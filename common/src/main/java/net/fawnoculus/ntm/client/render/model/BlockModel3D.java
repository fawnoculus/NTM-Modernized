package net.fawnoculus.ntm.client.render.model;

import net.fawnoculus.ntm.client.NtmClientConfig;
import net.fawnoculus.ntm.client.render.wavefront.model.Model3d;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Function;

public record BlockModel3D(BlockModelPart part) implements BlockStateModel {

    @Override
    public void collectParts(@NonNull RandomSource random, List<BlockModelPart> parts) {
        parts.add(this.part);
    }

    @Override
    public @NonNull TextureAtlasSprite particleIcon() {
        return this.part.particleIcon();
    }

    public record MultipartUnbaked(Model3d model3d, Material particleSprite,
                                   Function<Vector3f, Vector3f> offset) implements UnbakedRoot {
        @SuppressWarnings("deprecation")
        public MultipartUnbaked(Model3d model3d, Identifier particleTexture) {
            this(model3d, new Material(TextureAtlas.LOCATION_BLOCKS, particleTexture), model3d.defaultBlockTransforms());
        }

        @SuppressWarnings("deprecation")
        public MultipartUnbaked(Model3d model3d, Identifier particleTexture, Function<Vector3f, Vector3f> offset) {
            this(model3d, new Material(TextureAtlas.LOCATION_BLOCKS, particleTexture), offset);
        }


        @Override
        public @NonNull BlockModel3D bake(BlockState state, @NonNull ModelBaker baker) {
            final List<BakedQuad> quads = model3d.bake(baker, state::toString, offset);
            final TextureAtlasSprite particleSprite = baker.sprites().get(this.particleSprite, state::toString);

            return new BlockModel3D(new Part(quads, NtmClientConfig.BLOCK_MODEL_AMBIENT_OCCLUSION.getValue(), particleSprite));
        }

        @Override
        public @NonNull Object visualEqualityGroup(@NonNull BlockState state) {
            // I have no idea what this is for, but it just needs an object, so I guess this is fine?
            return this;
        }

        @Override
        public void resolveDependencies(@NonNull Resolver resolver) {
        }
    }

    public record Part(List<BakedQuad> quads, boolean useAmbientOcclusion,
                       TextureAtlasSprite particleIcon) implements BlockModelPart {
        @Override
        public @NonNull List<BakedQuad> getQuads(@Nullable Direction side) {
            return this.quads;
        }

        @Override
        public boolean useAmbientOcclusion() {
            return this.useAmbientOcclusion;
        }

        @Override
        public @NonNull TextureAtlasSprite particleIcon() {
            return this.particleIcon;
        }
    }
}
