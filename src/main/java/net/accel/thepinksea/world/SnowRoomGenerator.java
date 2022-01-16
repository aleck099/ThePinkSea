package net.accel.thepinksea.world;

import net.accel.thepinksea.ModMain;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class SnowRoomGenerator {
    public static final Identifier MAIN_TEMPLATE = new Identifier(ModMain.ID, "snow_room/main");

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesCollector holder, Random random) {
        holder.addPiece(new Piece(manager, MAIN_TEMPLATE, pos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {

        public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
            super(Features.SNOW_ROOM_PIECE, 0, manager, identifier, identifier.toString(), Piece.createPlacementData(rotation), pos);
        }

        public Piece(StructureManager manager, NbtCompound nbt) {
            super(Features.SNOW_ROOM_PIECE, nbt, manager, identifier -> createPlacementData(BlockRotation.valueOf(nbt.getString("Rot"))));
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {

        }


        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pos) {
            var previousPos = this.pos;
            this.pos = this.pos.add(0, -1, 0);
            super.generate(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pos);
            this.pos = previousPos;
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation) {
            return new StructurePlacementData().setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }
    }
}
