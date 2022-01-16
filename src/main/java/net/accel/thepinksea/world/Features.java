package net.accel.thepinksea.world;

import net.accel.thepinksea.ModMain;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class Features {
    public static final StructurePieceType.ManagerAware SNOW_ROOM_PIECE = SnowRoomGenerator.Piece::new;
    public static final StructureFeature<DefaultFeatureConfig> SNOW_ROOM_FEATURE = new SnowRoomFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<?, ?> SNOW_ROOM_CONFIGURED = SNOW_ROOM_FEATURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void registerAll() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(ModMain.ID, "snow_room_piece"), SNOW_ROOM_PIECE);
        FabricStructureBuilder.create(new Identifier(ModMain.ID, "snow_room"), SNOW_ROOM_FEATURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 114514)
                .adjustsSurface()
                .register();

        var snowRoomKey = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                new Identifier(ModMain.ID, "snow_room"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, snowRoomKey.getValue(), SNOW_ROOM_CONFIGURED);

        var selector = BiomeSelectors.includeByKey(
                BiomeKeys.SNOWY_BEACH,
                BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.SNOWY_TAIGA,
                BiomeKeys.ICE_SPIKES,
                BiomeKeys.GROVE);
        BiomeModifications.addStructure(selector, snowRoomKey);
    }

}
