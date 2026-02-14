package untamedwilds.init;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import java.util.ArrayList;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.GrassColor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import untamedwilds.UntamedWilds;
import untamedwilds.block.*;
import untamedwilds.block.blockentity.CageBlockEntity;
import untamedwilds.block.blockentity.CritterBurrowBlockEntity;
import untamedwilds.block.blockentity.EggBlockEntity;
import untamedwilds.block.blockentity.ReptileNestBlockEntity;
import untamedwilds.util.ModCreativeModeTab;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = UntamedWilds.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, UntamedWilds.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, UntamedWilds.MOD_ID);

    private final static List<Pair<DeferredHolder<Block, Block>, String>> RENDER_TYPE_DATA = new ArrayList<>();

    // Carpets
    public static DeferredHolder<Block, Block> CARPET_STRAW  = createBlock("carpet_straw", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).destroyTime(0.1F).sound(SoundType.CROP)), null);
    public static DeferredHolder<Block, Block> CARPET_BEAR_ASHEN  = createBlock("carpet_bear_ashen", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).destroyTime(0.1F).sound(SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BEAR_BLACK  = createBlock("carpet_bear_black", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).destroyTime(0.1F).sound(SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BEAR_BROWN  = createBlock("carpet_bear_brown", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BEAR_WHITE  = createBlock("carpet_bear_white", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_JAGUAR  = createBlock("carpet_bigcat_jaguar", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_LEOPARD  = createBlock("carpet_bigcat_leopard", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_LION  = createBlock("carpet_bigcat_lion", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_PANTHER  = createBlock("carpet_bigcat_panther", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_PUMA  = createBlock("carpet_bigcat_puma", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_SNOW  = createBlock("carpet_bigcat_snow_leopard", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).destroyTime(0.1F).sound( SoundType.WOOL)), null);
    public static DeferredHolder<Block, Block> CARPET_BIGCAT_TIGER  = createBlock("carpet_bigcat_tiger", () -> new CarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).destroyTime(0.1F).sound( SoundType.WOOL)), null);

    // Storage
    public static DeferredHolder<Block, Block> LARD_BLOCK  = createBlock("block_lard", () -> new LardBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).destroyTime(0.1F).sound(SoundType.SLIME_BLOCK)), null);
    public static DeferredHolder<Block, Block> PEARL_BLOCK  = createBlock("block_pearl", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).destroyTime(5.0F).sound(SoundType.STONE)), null);

    // Machines
    public static DeferredHolder<Block, Block> TRAP_CAGE  = createBlock("trap_cage", () -> new CageBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).destroyTime(3.0F).sound(SoundType.WOOD)), null);

    // Fauna
    public static DeferredHolder<Block, Block> ANEMONE_ROSE_BULB  = createBlock("anemone_rose_bulb", () -> new AnemoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).destroyTime(0.1F).sound(SoundType.SLIME_BLOCK)), "cutout", null);
    public static DeferredHolder<Block, Block> ANEMONE_SAND  = createBlock("anemone_sand", () -> new AnemoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).destroyTime(0.1F).sound(SoundType.SLIME_BLOCK)), "cutout", null);
    public static DeferredHolder<Block, Block> ANEMONE_SEBAE  = createBlock("anemone_sebae", () -> new AnemoneBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).destroyTime(0.1F).sound(SoundType.SLIME_BLOCK)), "cutout", null);

    // Flora - Reeds
    public static DeferredHolder<Block, Block> COMMON_REED = createBlock("flora_common_reed", () -> new ReedBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.1F).sound(SoundType.VINE).noCollission()), "cutout", null, 100);
    // Flora - Bushes
    public static DeferredHolder<Block, Block> BUSH_TEMPERATE = createBlock("flora_bush_temperate", () -> new UndergrowthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(1.0F).sound(SoundType.AZALEA_LEAVES).noCollission()), "cutout", null);
    public static DeferredHolder<Block, Block> BUSH_CREOSOTE = createBlock("flora_bush_creosote", () -> new UndergrowthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(1.0F).sound(SoundType.AZALEA_LEAVES).noCollission()), "cutout", null);
    public static DeferredHolder<Block, Block> ELEPHANT_EAR = createBlock("flora_elephant_ear", () -> new UndergrowthBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(1.0F).sound(SoundType.WET_GRASS).noCollission(), BlockBehaviour.OffsetType.XYZ), "cutout", null);
    public static DeferredHolder<Block, Block> HEMLOCK = createBlock("flora_hemlock", () -> new UndergrowthPoisonousBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.1F).sound(SoundType.GRASS).noCollission(), BlockBehaviour.OffsetType.XYZ), "cutout", null);
    public static DeferredHolder<Block, Block> YARROW = createBlock("flora_yarrow", () -> new CustomGrassBlock(MobEffects.REGENERATION, 4, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.0F).sound(SoundType.GRASS).noCollission()), "cutout", null);
    public static DeferredHolder<Block, Block> JUNEGRASS = createBlock("flora_junegrass", () -> new CustomGrassBlock(MobEffects.UNLUCK, 4, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.0F).sound(SoundType.GRASS).noCollission()), "cutout", null);
    public static DeferredHolder<Block, Block> CANOLA = createBlock("flora_canola", () -> new CustomGrassBlock(MobEffects.DAMAGE_BOOST, 4, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.0F).sound(SoundType.GRASS).noCollission()), "cutout", null);
    // Flora - Multistage
    public static DeferredHolder<Block, Block> ZIMBABWE_ALOE = createItemlessBlock("flora_zimbabwe_aloe", () -> new TallPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(1.0F).sound(SoundType.WOOD).dynamicShape()), "cutout");
    public static DeferredHolder<Block, Block> PAMPAS_GRASS = createBlock("flora_pampas_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(1.0F).sound(SoundType.GRASS).dynamicShape()), "cutout", null);
    // Flora - Floating
    public static DeferredHolder<Block, Block> WATER_HYACINTH = createItemlessBlock("flora_water_hyacinth", () -> new FloatingPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0.0F).sound(SoundType.LILY_PAD).noCollission()), "cutout");
    // Flora - Algae
    public static DeferredHolder<Block, Block> AMAZON_SWORD = createBlock("flora_amazon_sword", () -> new AlgaeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0).sound(SoundType.WET_GRASS).noCollission()), "cutout", null);
    public static DeferredHolder<Block, Block> EELGRASS = createBlock("flora_eelgrass", () -> new AlgaeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(0).sound(SoundType.WET_GRASS).noCollission()), "cutout", null);
    // Flora - Flowers
    public static DeferredHolder<Block, Block> ORCHID_MAGENTA = createBlock("flora_orchid_magenta", () -> new EpyphitePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_MAGENTA).destroyTime(0.0F).sound(SoundType.VINE)), "cutout", null);
    public static DeferredHolder<Block, Block> ORCHID_PURPLE = createBlock("flora_orchid_purple", () -> new EpyphitePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).destroyTime(0.0F).sound(SoundType.VINE)), "cutout", null);
    public static DeferredHolder<Block, Block> ORCHID_PINK = createBlock("flora_orchid_pink", () -> new EpyphitePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).destroyTime(0.0F).sound(SoundType.VINE)), "cutout", null);
    public static DeferredHolder<Block, Block> ORCHID_RED = createBlock("flora_orchid_red", () -> new EpyphitePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).destroyTime(0.0F).sound(SoundType.VINE)), "cutout", null);
    public static DeferredHolder<Block, Block> TITAN_ARUM = createItemlessBlock("flora_titan_arum", () -> new TitanArumBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).destroyTime(2.0F).sound(SoundType.WET_GRASS).noCollission().dynamicShape()), "cutout");

    // Nests
    public static DeferredHolder<Block, Block> NEST_REPTILE = createBlock("nest_reptile", () -> new NestReptileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).destroyTime(1.0F).sound(SoundType.GRAVEL)),  "translucent", null);

    // Eggs
    public static DeferredHolder<Block, Block> EGG_SPITTER = createBlock("egg_spitter", () -> new StrangeEggBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).destroyTime(1.0F).sound(SoundType.SLIME_BLOCK)), "cutout", null);

    // Technical Blocks
    public static DeferredHolder<Block, Block> BURROW = createBlock("block_burrow", () -> new CritterBurrowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).destroyTime(1.0F).sound(SoundType.GRAVEL).noCollission()),  "translucent", null);

    // Block Entities
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> TILE_ENTITY_CAGE = TILE_ENTITIES.register("trap_cage", () -> BlockEntityType.Builder.of(CageBlockEntity::new, ModBlock.TRAP_CAGE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> TILE_ENTITY_BURROW = TILE_ENTITIES.register("critter_burrow", () -> BlockEntityType.Builder.of(CritterBurrowBlockEntity::new, ModBlock.BURROW.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> TILE_ENTITY_NEST_REPTILE = TILE_ENTITIES.register("nest_reptile_block_entity", () -> BlockEntityType.Builder.of(ReptileNestBlockEntity::new, ModBlock.NEST_REPTILE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<?>> TILE_ENTITY_EGG = TILE_ENTITIES.register("strange_egg", () -> BlockEntityType.Builder.of(EggBlockEntity::new, ModBlock.EGG_SPITTER.get()).build(null));

    public static <B extends Block> DeferredHolder<Block, Block> createBlock(String name, Supplier<? extends B> supplier, CreativeModeTab group) {
        return createBlock(name, supplier, null, group, 0);
    }

    public static <B extends Block> DeferredHolder<Block, Block> createBlock(String name, Supplier<? extends B> supplier, @Nullable String renderType, CreativeModeTab group) {
        return createBlock(name, supplier, renderType, group, 0);
    }

    public static <B extends Block> DeferredHolder<Block, Block> createBlock(String name, Supplier<? extends B> supplier, @Nullable String renderType, CreativeModeTab group, int burnTime) {
        DeferredHolder<Block, Block> block = ModBlock.BLOCKS.register(name, supplier);
        if (renderType != null) {
            RENDER_TYPE_DATA.add(new Pair<>(block, renderType));
        }
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
            public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
                return burnTime;
            }
        });
        return block;
    }

    public static <B extends Block> DeferredHolder<Block, Block> createItemlessBlock(String name, Supplier<? extends B> supplier) {
        return createItemlessBlock(name, supplier, null);
    }

    public static <B extends Block> DeferredHolder<Block, Block> createItemlessBlock(String name, Supplier<? extends B> supplier, @Nullable String renderType) {
        DeferredHolder<Block, Block> block = ModBlock.BLOCKS.register(name, supplier);
        if (renderType != null) {
            RENDER_TYPE_DATA.add(new Pair<>(block, renderType));
        }
        return block;
    }

    public static void registerRendering() {
        for (Pair<DeferredHolder<Block, Block>, String> i : RENDER_TYPE_DATA) {
            RenderType j = RenderType.cutout();
            if (Objects.equals(i.getSecond(), "cutout"))
                j = RenderType.cutout();
            else if (Objects.equals(i.getSecond(), "translucent"))
                j = RenderType.translucent();
            ItemBlockRenderTypes.setRenderLayer(i.getFirst().get(), j);
        }
    }
}

