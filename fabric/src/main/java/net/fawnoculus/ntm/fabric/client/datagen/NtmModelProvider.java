package net.fawnoculus.ntm.fabric.client.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fawnoculus.ntm.Ntm;
import net.fawnoculus.ntm.blocks.NtmBlocks;
import net.fawnoculus.ntm.blocks.custom.AlloyFurnaceBlock;
import net.fawnoculus.ntm.blocks.custom.ElectricFurnaceBlock;
import net.fawnoculus.ntm.items.NtmItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.Optional;


public class NtmModelProvider extends FabricModelProvider {
    public static final TextureSlot BARREL_TEXTURE_KEY = TextureSlot.create("barrel");
    public static final TexturedModel.Provider SIMPLE_BARREL = TexturedModel.createDefault(
      block -> new TextureMapping().put(BARREL_TEXTURE_KEY, TextureMapping.getBlockTexture(block)),
      new ModelTemplate(Optional.empty(), Optional.empty(), BARREL_TEXTURE_KEY)
    );
    public static final TextureMapping EMPTY_BLOCK_TEXTURE = new TextureMapping();
    public static final TexturedModel.Provider EMPTY_BLOCK_MODEL = TexturedModel.createDefault(block -> EMPTY_BLOCK_TEXTURE, new ModelTemplate(Optional.of(Identifier.withDefaultNamespace("block/block")), Optional.empty()));
    public static final ModelTemplate HANDHELD_LARGE = item("handheld_large", TextureSlot.LAYER0);


    public NtmModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static void registerSimpleHorizontalOrientable(@NotNull BlockModelGenerators blockStateModelGenerator, Block block) {
        MultiVariant weightedVariant = BlockModelGenerators.plainVariant(TexturedModel.ORIENTABLE_ONLY_TOP.create(block, blockStateModelGenerator.modelOutput));
        blockStateModelGenerator.blockStateOutput
          .accept(
            MultiVariantGenerator.dispatch(block)
              .with(
                PropertyDispatch.initial(BlockStateProperties.HORIZONTAL_FACING)
                  .select(Direction.NORTH, weightedVariant)
                  .select(Direction.EAST, weightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, weightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, weightedVariant.with(BlockModelGenerators.Y_ROT_270))
              )
          );
    }

    private static ModelTemplate item(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Ntm.id("item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCORCHED_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_SCORCHED_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TITANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_TITANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SULFUR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_SULFUR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.THORIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_THORIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NITER_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_NITER_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TUNGSTEN_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_TUNGSTEN_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ALUMINIUM_BEARING_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_ALUMINIUM_BEARING_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.FLUORITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_FLUORITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LEAD_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_LEAD_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHRABIDIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_SCHRABIDIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BERYLLIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_BERYLLIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.AUSTRALIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_AUSTRALIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.RARE_EARTH_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_RARE_EARTH_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.CINNEBAR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_CINNEBAR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COLTAN_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_COLTAN_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LIGNITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_LIGNITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ASBESTOS_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_ASBESTOS_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.OIL_DEPOSIT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_OIL_DEPOSIT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.EMPTY_OIL_DEPOSIT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_EMPTY_OIL_DEPOSIT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ALUMINIUM_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_ALUMINIUM_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COPPER_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_COPPER_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.IRON_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_IRON_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TITANIUM_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_TITANIUM_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEAD_DIRT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.OILY_DIRT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.OILY_SAND.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_ROCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_CINNABAR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_ZIRCONIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_BORAX_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_IRON_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_TITANIUM_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEPTH_TUNGSTEN_ORE_CLUSTER.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ALEXANDRITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DEEPSLATE_ALEXANDRITE_ORE.get());
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.SULFUR_RICH_VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.FLUORITE_RICH_VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.ASBESTOS_RICH_VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.GEM_RICH_VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.MOLYSITE_RICH_VOLCANIC_BASALT.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SMOLDERING_NETHERRACK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_COAL_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCORCHED_NETHER_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_PLUTONIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_TUNGSTEN_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_SULFUR_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_PHOSPHORUS_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_SCHRABIDIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_DEPTH_ROCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NETHER_DEPTH_NEODYMIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEORITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BROKEN_METEORITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEORITE_COBBLESTONE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.HOT_METEORITE_COBBLESTONE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEORITE_TREASURE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEOR_IRON_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEOR_COPPER_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEOR_ALUMINIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEOR_RARE_EARTH_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.METEOR_COBALT_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.GRAPHITIC_SCHIST.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_IRON_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_GOLD_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCORCHED_SCHIST_URANIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_COPPER_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_ASBESTOS_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_LITHIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_SCHRABIDIUM_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHIST_RARE_EARTH_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.GAS_SHALE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BAUXITE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.CHRYSOTILE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.HEMATITE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LIMESTONE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.MALACHITE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SULFUROUS_STONE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TEKTITE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.OSMIRIDIUM_INFUSED_TEKTITE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TRIXITE_ORE.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.GEOTHERMAL_VENT.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BEDROCK_OIL_DEPOSIT.get());

        blockStateModelGenerator.createTrivialCube(NtmBlocks.ACTINIUM_227_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ADVANCED_ALLOY_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ALUMINIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ASBESTOS_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.AUSTRALIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BAKELITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BERYLLIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BISMUTH_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.BORON_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.CADMIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.CADMIUM_STEEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.CMB_STEEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COAL_COKE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COBALT_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.COLTAN_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DESH_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.DINEUTRONIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.EUPHEMIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.FERRIC_SCHRABIDATE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.FLUORITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.GRAPHITE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.HIGH_SPEED_STEEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LIGNITE_COKE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LEAD_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.LITHIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.MAGNETIZED_TUNGSTEN_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.MOX_FUEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NEPTUNIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NIOBIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.NITER_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PETROLEUM_COKE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_FUEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.REACTOR_GRADE_PLUTONIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_238_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_239_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_240_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.PLUTONIUM_241_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.POLONIUM_210_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.POLYMER_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.RADIUM_226_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.RED_COPPER_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.RED_PHOSPHORUS_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.RUBBER_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHRABIDIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHRABIDIUM_FUEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SCHRARANIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.SEMTEX_BLOCK.get(), TexturedModel.CUBE_TOP);
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SOLINIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.STARMETAL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.STEEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.SULFUR_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TANTALUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TECHNETIUM_STEEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.THORIUM_232_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.THORIUM_FUEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TITANIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.TUNGSTEN_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_FUEL_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_233_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_235_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.URANIUM_238_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.WHITE_PHOSPHORUS_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.YELLOWCAKE_BLOCK.get());
        blockStateModelGenerator.createTrivialCube(NtmBlocks.ZIRCONIUM_BLOCK.get());

        blockStateModelGenerator.createTrivialBlock(NtmBlocks.ALLOY_FURNACE_EXTENSION.get(), EMPTY_BLOCK_MODEL);

        /* FIXME
        blockStateModelGenerator.createTrivialBlock(NtmBlocks.EXPLOSIVE_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.IMP_RESIDUE_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.KEROSENE_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.LOX_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.RADIOACTIVE_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.VITRIFIED_NUCLEAR_WASTE_DRUM, SIMPLE_BARREL);

        blockStateModelGenerator.registerSingleton(NtmBlocks.CORRODED_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.SAFE_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.IRON_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.STEEL_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.TECHNETIUM_STEEL_BARREL, SIMPLE_BARREL);
        blockStateModelGenerator.registerSingleton(NtmBlocks.MAGNETIC_BARREL, SIMPLE_BARREL);
         */

        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.POTATO_BATTERY_BLOCK.get());
        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.ENERGY_STORAGE_BLOCK.get());
        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.LITHIUM_ION_ENERGY_STORAGE_BLOCK.get());
        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.SCHRABIDIUM_ENERGY_STORAGE_BLOCK.get());
        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.SPARK_ENERGY_STORAGE_BLOCK.get());


        TextureMapping alloyFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_top"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_side"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_front"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_bottom"));
        TextureMapping litAlloyFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_top_lit"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_side"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_front_lit"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_bottom"));
        TextureMapping tallAlloyFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_top"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_side_tall"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_front_tall"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_bottom"));
        TextureMapping litTallAlloyFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_top_lit"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_side_tall"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_front_lit_tall"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ALLOY_FURNACE.get(), "_bottom"));

        MultiVariant alloyFurnaceweightedVariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix(NtmBlocks.ALLOY_FURNACE.get(), "", alloyFurnaceTextureMap, blockStateModelGenerator.modelOutput));
        MultiVariant litAlloyFurnaceweightedVariant = BlockModelGenerators.plainVariant(blockStateModelGenerator.createSuffixedVariant(NtmBlocks.ALLOY_FURNACE.get(), "_lit", ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM, a -> litAlloyFurnaceTextureMap));
        MultiVariant tallAlloyFurnaceweightedVariant = BlockModelGenerators.plainVariant(blockStateModelGenerator.createSuffixedVariant(NtmBlocks.ALLOY_FURNACE.get(), "_tall", ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM, a -> tallAlloyFurnaceTextureMap));
        MultiVariant litTallAlloyFurnaceweightedVariant = BlockModelGenerators.plainVariant(blockStateModelGenerator.createSuffixedVariant(NtmBlocks.ALLOY_FURNACE.get(), "_lit_tall", ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM, a -> litTallAlloyFurnaceTextureMap));
        blockStateModelGenerator.blockStateOutput
          .accept(
            MultiVariantGenerator.dispatch(NtmBlocks.ALLOY_FURNACE.get())
              .with(
                PropertyDispatch.initial(AlloyFurnaceBlock.FACING, AlloyFurnaceBlock.LIT, AlloyFurnaceBlock.EXTENSION)
                  .select(Direction.NORTH, false, false, alloyFurnaceweightedVariant)
                  .select(Direction.EAST, false, false, alloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, false, false, alloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, false, false, alloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
                  .select(Direction.NORTH, true, false, litAlloyFurnaceweightedVariant)
                  .select(Direction.EAST, true, false, litAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, true, false, litAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, true, false, litAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
                  .select(Direction.NORTH, false, true, tallAlloyFurnaceweightedVariant)
                  .select(Direction.EAST, false, true, tallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, false, true, tallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, false, true, tallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
                  .select(Direction.NORTH, true, true, litTallAlloyFurnaceweightedVariant)
                  .select(Direction.EAST, true, true, litTallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, true, true, litTallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, true, true, litTallAlloyFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
              )
          );

        TextureMapping electricFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_top"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_side"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_front"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_bottom"));
        TextureMapping litElectricFurnaceTextureMap = new TextureMapping()
          .put(TextureSlot.TOP, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_top"))
          .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_side"))
          .put(TextureSlot.FRONT, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_front_lit"))
          .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(NtmBlocks.ELECTRIC_FURNACE.get(), "_bottom"));
        MultiVariant electricFurnaceweightedVariant = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix(NtmBlocks.ELECTRIC_FURNACE.get(), "", electricFurnaceTextureMap, blockStateModelGenerator.modelOutput));
        MultiVariant litElectricFurnaceweightedVariant = BlockModelGenerators.plainVariant(blockStateModelGenerator.createSuffixedVariant(NtmBlocks.ELECTRIC_FURNACE.get(), "_lit", ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM, a -> litElectricFurnaceTextureMap));
        blockStateModelGenerator.blockStateOutput
          .accept(
            MultiVariantGenerator.dispatch(NtmBlocks.ELECTRIC_FURNACE.get())
              .with(
                PropertyDispatch.initial(ElectricFurnaceBlock.FACING, ElectricFurnaceBlock.LIT)
                  .select(Direction.NORTH, false, electricFurnaceweightedVariant)
                  .select(Direction.EAST, false, electricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, false, electricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, false, electricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
                  .select(Direction.NORTH, true, litElectricFurnaceweightedVariant)
                  .select(Direction.EAST, true, litElectricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_90))
                  .select(Direction.SOUTH, true, litElectricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_180))
                  .select(Direction.WEST, true, litElectricFurnaceweightedVariant.with(BlockModelGenerators.Y_ROT_270))
              )
          );
        registerSimpleHorizontalOrientable(blockStateModelGenerator, NtmBlocks.PWR_CONTROLLER.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        // Basic Items
        itemModelGenerator.generateFlatItem(NtmItems.NULL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_238_RTG_PELLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_REACHER.get(), ModelTemplates.FLAT_ITEM);

        // Raw Resources
        itemModelGenerator.generateFlatItem(NtmItems.ACTINIUM_227_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ACTINIUM_227_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ACTINIUM_227_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_ACTINIUM_227_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ACTINIUM_227_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ACTINIUM_227_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_ADVANCED_ALLOY_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_ADVANCED_ALLOY_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ALEXANDRITE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_METEORIC_ALUMINIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_ALUMINIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_ALUMINIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_SHELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_PIPE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ALUMINIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_241_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_241_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_241_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_242_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_242_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_242_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AMERICIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_AMERICIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_AMERICIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_AMERICIUM_ZFB_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_AMERICIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ARSENIC_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ARSENIC_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ARSENIC_BRONZE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_ARSENIC_BRONZE_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ASBESTOS_SHEET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ASBESTOS_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ASTATINE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ASTATINE_209_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ASH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WOOD_ASH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COAL_ASH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FLY_ASH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FINE_SOOT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_AUSTRALIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AUSTRALIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AUSTRALIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AUSTRALIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LESSER_AUSTRALIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LESSER_AUSTRALIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GREATER_AUSTRALIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GREATER_AUSTRALIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AUSTRALIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BAKELITE_BAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BAKELITE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BALEFIRE_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BALEFIRE_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THERMONUCLEAR_ASHES.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_BERYLLIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BERYLLIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BERYLLIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BERYLLIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BERYLLIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BERYLLIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_ZFB_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_BRONZE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_BISMUTH_BRONZE_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BORAX_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BORON_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BORON_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_BORON_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BORON_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BROMINE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BSCCO_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_BSCCO_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CADMIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CADMIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CAESIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAESIUM_137_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_CAESIUM_137_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CALCIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CALCIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CADMIUM_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_CADMIUM_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_CADMIUM_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CEMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CERIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_CERIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CERIUM_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CHLOROCALCITE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CHLOROPHYTE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CINNABAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CINNABAR_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_CMB_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_CMB_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.COAL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_COAL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CARBON_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COAL_BRIQUETTE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COAL_COKE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.COBALT_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_COBALT_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_60_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_60_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_60_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_60_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAW_METEORIC_COBALT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.COLTAN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CRUSHED_COLTAN.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.COPPER_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COPPER_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_COPPER_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_COPPER_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COPPER_PIPE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COPPER_SHELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COPPER_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_COPPER_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COPPER_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAW_METEORIC_COPPER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CRYO_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_CRYOLITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CRYOLITE_CHUNK.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.DESH_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_BLEND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESHREADY_BLEND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_DESH_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.DIAMOND_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DIAMOND_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.DINEUTRONIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DINEUTRONIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DINEUTRONIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_DINEUTRONIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ELECTRONIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.EMERALD_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ENERGY_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.EUPHEMIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EUPHEMIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EUPHEMIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FERRIC_SCHARBIDATE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FERRIC_SCHARBIDATE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_FERRIC_SCHARBIDATE_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_FERRIC_SCHARBIDATE_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FERROURANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_FERROURANIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FLASH_GOLD.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FLASH_LEAD.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FLUORITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FLUORITE_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FLUX.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.FULLERENE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CRYSTALLINE_FULLERENE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.GHIORSIUM_336_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GHIORSIUM_336_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GHIORSIUM_336_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.GOLD_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_GOLD_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_GOLD_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_198_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_198_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_198_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOLD_198_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.GRAPHITE_INGOT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.GUNMETAL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GUNMETAL_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.HARD_PLASTIC_BAR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.HIGH_SPEED_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGH_SPEED_STEEL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_HIGH_SPEED_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGH_SPEED_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGH_SPEED_STEEL_BOLT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGH_SPEED_STEEL_PIPE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.IODINE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IODINE_131_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_IODINE_131_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.IRON_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IRON_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_IRON_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_IRON_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IRON_PIPE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IRON_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAW_METEORIC_IRON.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.INDUSTRIAL_FERTILIZER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.INFERNAL_COAL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SEMI_STABLE_LANTHANUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LANTHANUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_LANTHANUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LANTHANUM_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.LAPIS_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LAPIS_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.LATEX.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LATEX_BAR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_LEAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_209_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_209_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_209_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_LEAD_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_PIPE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_BOLT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEAD_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.LIGNITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LIGNITE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LIGNITE_COKE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LIGNITE_BRIQUETTE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.LIMESTONE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.LITHIUM_CUBE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LITHIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_LITHIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LITHIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.MAGNETIZED_TUNGSTEN_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MAGNETIZED_TUNGSTEN_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MAGNETIZED_TUNGSTEN_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_MAGNETIZED_TUNGSTEN_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.METEORITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.METEORITE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_METEORITE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.METEORITE_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.MOLYSITE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.MOX_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MOX_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MOX_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NEODYMIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_NEODYMIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_NEODYMIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEODYMIUM_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NEPTUNIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NIOBIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NIOBIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_NIOBIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NIOBIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_NIOBIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NIOBIUM_FRAGMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NITAN_BLEND.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NITER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NITER_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.NITRA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SMALL_PILE_OF_NITRA.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_OSMIRIDIUM_INFUSED_TEKTITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.OSMIRIDIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.OSMIRIDIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IMPURE_OSMIRIDIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_OSMIRIDIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_OSMIRIDIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.OSMIRIDIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PALEOGENITE_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_PALEOGENITE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RED_PHOSPHORUS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WHITE_PHOSPHORUS_BAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PHOSPHORUS_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PETROLEUM_COKE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_PLUTONIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_PLUTONIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_PLUTONIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REACTOR_GRADE_PLUTONIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_238_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_238_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_238_BE_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_238_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_239_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_239_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_239_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_240_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_240_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_240_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_241_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_241_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_241_ZFB_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_241_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PLUTONIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.POISON_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.POLONIUM_210_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POLONIUM_210_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POLONIUM_210_BE_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POLONIUM_210_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POLONIUM_210_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.POLYMER_BAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POLYMER_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PULVERIZED_ENCHANTMENT.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PVC_BAR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.QUARTZ_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_226_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_226_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_226_BE_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_226_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_226_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RARE_EARTH_ORE_CHUNK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RARE_EARTH_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAW_METEORIC_RARE_EARTH.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RED_COPPER_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RED_COPPER_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RED_COPPER_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.REDSTONE_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RUBBER_BAR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RUBBER_PIPE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SATURNITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SATURNITE_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_SATURNITE_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SATURNITE_SHELL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SAWDUST_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SAWDUST_BRIQUETTE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_SCHRABIDIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LOW_ENRICHED_SCHRABIDIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.HIGHLY_ENRICHED_SCHRABIDIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_SCHRABIDIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_SCHRABIDIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SCHRARANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRARANIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SEMTEX_BLEND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SEMTEX_BAR.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SILICON_BOULE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SILICON_WAFER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PRINTED_SILICON_WAFER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SILICON_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SODIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SOLINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SOLINIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SOLINIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SPARK_BLEND.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_STARMETAL_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_RING.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.STRONTIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STRONTIUM_90_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STRONTIUM_90_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STRONTIUM_90_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_STRONTIUM_90_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STRONTIUM_90_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_STEEL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_BOLT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_PIPE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_SHELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_WIRE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SULFUR.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SULFUR_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PURIFIED_TANTALITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TANTALUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TANTALUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TANTALUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TANTALUM_POLYCRYSTAL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.TECHNETIUM_99_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TECHNETIUM_99_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TECHNETIUM_99_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.TECHNETIUM_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TECHNETIUM_STEEL_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_TECHNETIUM_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_TECHNETIUM_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.TEKTITE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.TENNESSINE_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.THERMITE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_THORIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_232_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_232_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_232_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.THORIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_TITANIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_TITANIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_TITANIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_SHELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_TITANIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_TRIXITE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TRIXITE_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_TUNGSTEN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_BOLT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DENSE_TUNGSTEN_WIRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TUNGSTEN_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.RAW_URANIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAW_SCORCHED_URANIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_FUEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_FUEL_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_FUEL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_233_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_233_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_233_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_235_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_235_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_235_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_238_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_238_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_238_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.URANIUM_CRYSTALS.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.VOLCANIC_GEM.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.WEAPON_STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WEAPON_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_WEAPON_STEEL_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WEAPON_STEEL_SHELL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.XENON_135_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TINY_PILE_OF_XENON_135_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.YHARONITE_BILLET.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.YELLOWCAKE.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ZIRCONIUM_SPLINTER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ZIRCONIUM_CUBE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ZIRCONIUM_BILLET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ZIRCONIUM_POWDER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CAST_ZIRCONIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WELDED_ZIRCONIUM_PLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ZIRCONIUM_WIRE.get(), ModelTemplates.FLAT_ITEM);

        // Usable Items
        itemModelGenerator.generateFlatItem(NtmItems.DEBUG_WAND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CONSTRUCTION_WAND.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NETWORK_DEBUG_TOOL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GEIGER_COUNTER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DOSIMETER.get(), ModelTemplates.FLAT_ITEM);

        // Batteries
        itemModelGenerator.generateFlatItem(NtmItems.BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.REDSTONE_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SIXFOLD_REDSTONE_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TWENTY_FOUR_FOLD_REDSTONE_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.QUADRUPLE_ADVANCED_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TWELVEFOLD_ADVANCED_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LITHIUM_ION_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LITHIUM_ION_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TRIPLE_LITHIUM_ION_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SIXFOLD_LITHIUM_ION_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DOUBLE_SCHRABIDIUM_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.QUADRUPLE_SCHRABIDIUM_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.OFF_BRAND_SPARK_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_POWER_CELL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_ARCANE_CAR_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_ARCANE_ENERGY_STORAGE_ARRAY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_ARCANE_MASS_ENERGY_VOID.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_ARCANE_DIRAC_SEA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_SOLID_SPACE_TIME_CRYSTAL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPARK_LUDICROUS_ENERGY_STORAGE_UNIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ELECTRONIUM_CUBE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.INFINITE_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POTATO_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POTATOS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_URANIUM_238_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_TECHNETIUM_99_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_PLUTONIUM_238_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_POLONIUM_210_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_GOLD_198_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_LEAD_209_BATTERY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SELF_CHARGING_AMERICIUM_241_BATTERY.get(), ModelTemplates.FLAT_ITEM);

        // Consumables
        itemModelGenerator.generateFlatItem(NtmItems.EMPTY_SYRINGE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.POISONOUS_INJECTION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ANTIDOTE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.AWESOME.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.METAL_SYRINGE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STIMPAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MED_X.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PSYCHO.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SUPER_STIMPAK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.WATERY_TAINT_INJECTION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FIRST_AID_KIT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IV_BAG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BLOOD_BAG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EMPTY_EXPERIENCE_BAG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EXPERIENCE_BAG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAD_AWAY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STRONG_RAD_AWAY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ELITE_RAD_AWAY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RAD_X.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IODINE_PILL.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.PLAN_C.get(), ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.WAFFLE_OF_MASS_DESTRUCTION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.VEGAN_SCHNITZEL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIOACTIVE_COTTON_CANDY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BASIC_LEAD_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOOD_LEAD_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EPIC_LEAD_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BASIC_SCHRABIDIUM_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GOOD_SCHRABIDIUM_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EPIC_SCHRABIDIUM_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EUPHEMIUM_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CHEAP_TEM_FLAKES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TEM_FLAKES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EXPENSIVE_TEM_FLAKES.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GLOWING_MUSHROOM_STEW.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCRAMBLED_BALEFIRE_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCRAMBLED_BALEFIRE_EGG_AND_HAM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LEMON.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MRE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.LOOPS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IT_BREAKFAST.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SPONGEBOB_MACARONI.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FOOD_ITEM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TWINKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TV_STATIC_SANDWICH.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PUDDING.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCRAP_PANCAKE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CHICKEN_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PEAS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MARSHMALLOW_ON_A_STICK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ROASTED_MARSHMALLOW_ON_A_STICK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CHEESE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CHEESE_QUESADILLA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GLYPHID_MEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GRILLED_GLYPHID_MEAT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.GLYPHID_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.IPECAC_SYRUP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.PTSD_MEDICATION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STYLISH_FLASK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ARIZONA_MUCHO_MANGO.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_CHOCOLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EMPTY_CAN.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RING_PULL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SMART_ENERGY_DRINK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CREATURE_ENERGY_DRINK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RED_BOMB_ENERGY_DRINK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DR_SUGAR_SOFT_DRINK.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.OVERCHARGE_DELIRIUM_XT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BLACK_MESA_LUNA_DARK_COLA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BEPIS.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DR_BREENS_PRIVATE_RESERVE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.MUG_ROOT_BEER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COFFEE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.RADIUM_COFFEE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OPENER.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EMPTY_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.EMPTY_BOMB_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NUKA_COLA_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.NUKA_COLA_QUANTUM_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.S_COLA_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.S_COLA_RAD_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.KAROL_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FRITZ_COLA_BOTTLE_CAP.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_NUKA_COLA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_NUKA_CHERRY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_NUKA_COLA_QUANTUM.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_S_COLA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_S_COLA_RAD.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_KAROL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FIRST_BOTTLE_OF_KAROL.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.BOTTLE_OF_FRITZ_COLA.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.FIRST_BOTTLE_OF_FRITZ_COLA.get(), ModelTemplates.FLAT_ITEM);

        // Tools
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.TITANIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.ADVANCED_ALLOY_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.CMB_STEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.DESH_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DESH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.COBALT_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.COBALT_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.DECORATED_COBALT_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.DECORATED_COBALT_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DECORATED_COBALT_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DECORATED_COBALT_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.DECORATED_COBALT_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.STARMETAL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_SWORD.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(NtmItems.SCHRABIDIUM_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_PICKAXE.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.BISMUTH_AXE.get(), HANDHELD_LARGE);

        itemModelGenerator.generateFlatItem(NtmItems.MOLTEN_PICKAXE.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.MOLTEN_AXE.get(), HANDHELD_LARGE);

        itemModelGenerator.generateFlatItem(NtmItems.CHLOROPHYTE_PICKAXE.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.CHLOROPHYTE_AXE.get(), HANDHELD_LARGE);

        itemModelGenerator.generateFlatItem(NtmItems.MESE_PICKAXE.get(), HANDHELD_LARGE);
        itemModelGenerator.generateFlatItem(NtmItems.MESE_AXE.get(), HANDHELD_LARGE);
    }

    @Override
    public @NonNull String getName() {
        return Ntm.MOD_NAME + " Model Provider";
    }
}
