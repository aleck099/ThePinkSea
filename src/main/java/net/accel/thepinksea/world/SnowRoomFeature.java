package net.accel.thepinksea.world;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class SnowRoomFeature extends StructureFeature<DefaultFeatureConfig> {
    public SnowRoomFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), SnowRoomFeature::addPieces));
    }

    private static void addPieces(StructurePiecesCollector collector, StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        var pos = new BlockPos.Mutable(context.chunkPos().getStartX(), 90, context.chunkPos().getStartZ());
        int y = context.chunkGenerator().getHeight(pos.getX(), pos.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        pos.setY(y);
        var rotation = BlockRotation.random(context.random());
        SnowRoomGenerator.addPieces(context.structureManager(), pos, rotation, collector, context.random());
    }
}
