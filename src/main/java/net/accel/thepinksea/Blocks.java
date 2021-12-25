package net.accel.thepinksea;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Blocks {

    public static final Block PINK_SAND = new SandBlock(0xea55ce, FabricBlockSettings.of(Material.AGGREGATE, MapColor.PINK).strength(0.5F).sounds(BlockSoundGroup.SAND));
    public static final Block PINK_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(0.8F));
    public static final Block CHISELED_PINK_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(0.8F));
    public static final Block CUT_PINK_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(0.8F));
    public static final Block PINK_SANDSTONE_STAIRS = new StairsBlock(PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copyOf(PINK_SANDSTONE)) {};
    public static final Block PINK_SANDSTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(2.0F, 6.0F));
    public static final Block CUT_PINK_SANDSTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(2.0F, 6.0F));
    public static final Block SMOOTH_PINK_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PINK).requiresTool().strength(2.0F, 6.0F));
    public static final Block SMOOTH_PINK_SANDSTONE_STAIRS = new StairsBlock(SMOOTH_PINK_SANDSTONE.getDefaultState(), FabricBlockSettings.copyOf(SMOOTH_PINK_SANDSTONE)) {};
    public static final Block SMOOTH_PINK_SANDSTONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(SMOOTH_PINK_SANDSTONE));
    public static final Block PINK_SANDSTONE_WALL = new WallBlock(FabricBlockSettings.copyOf(PINK_SANDSTONE));

    private static void registerBlockAndItem(String id, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(ModMain.ID, id), block);
        Registry.register(Registry.ITEM, new Identifier(ModMain.ID, id), new BlockItem(block, new FabricItemSettings().group(Items.ITEM_GROUP)));
    }

    public static void registerAll() {
        registerBlockAndItem("pink_sand", PINK_SAND);
        registerBlockAndItem("pink_sandstone", PINK_SANDSTONE);
        registerBlockAndItem("chiseled_pink_sandstone", CHISELED_PINK_SANDSTONE);
        registerBlockAndItem("cut_pink_sandstone", CUT_PINK_SANDSTONE);
        registerBlockAndItem("pink_sandstone_stairs", PINK_SANDSTONE_STAIRS);
        registerBlockAndItem("pink_sandstone_slab", PINK_SANDSTONE_SLAB);
        registerBlockAndItem("cut_pink_sandstone_slab", CUT_PINK_SANDSTONE_SLAB);
        registerBlockAndItem("smooth_pink_sandstone", SMOOTH_PINK_SANDSTONE);
        registerBlockAndItem("smooth_pink_sandstone_stairs", SMOOTH_PINK_SANDSTONE_STAIRS);
        registerBlockAndItem("smooth_pink_sandstone_slab", SMOOTH_PINK_SANDSTONE_SLAB);
        registerBlockAndItem("pink_sandstone_wall", PINK_SANDSTONE_WALL);
    }
}
