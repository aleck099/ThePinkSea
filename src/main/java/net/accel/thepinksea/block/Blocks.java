package net.accel.thepinksea.block;

import net.accel.thepinksea.ModMain;
import net.accel.thepinksea.item.Items;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

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

    public static final Block PINK_SEA_PORTAL = new PinkSeaPortalBlock(FabricBlockSettings.of(Material.PORTAL, MapColor.BLACK).noCollision().luminance(15).strength(-1.0F, 3600000.0F).dropsNothing().nonOpaque().allowsSpawning(Blocks::never));

    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

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

        Registry.register(Registry.BLOCK, new Identifier(ModMain.ID, "pink_sea_portal"), PINK_SEA_PORTAL);
    }
}
