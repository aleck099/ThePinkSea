package net.accel.thepinksea;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;

public class Helpers {

    public static TeleportTarget findTeleportTarget(BlockPos portalPos, Entity entity, ServerWorld source, ServerWorld destination) {
        WorldBorder worldBorder = destination.getWorldBorder();
        double d = DimensionType.getCoordinateScaleFactor(source.getDimension(), destination.getDimension());
        BlockPos firstPos = worldBorder.clamp(portalPos.getX() * d, portalPos.getY(), portalPos.getZ() * d);
        var targetPos = findAvailablePosition(firstPos, destination, entity);
        return new TeleportTarget(new Vec3d(targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5), entity.getVelocity(), entity.getYaw(), entity.getPitch());
    }

    public static BlockPos findAvailablePosition(BlockPos starting, ServerWorld world, Entity entity) {
        {
            int x = starting.getX();
            int z = starting.getZ();
            int y1 = starting.getY();
            int y2 = y1 - 1;
            while (true) {
                if (y1 >= world.getTopY())
                    break;
                var p = new BlockPos(x, y1, z);
                if (canStand(p, world, entity))
                    return p;
                ++y1;

                if (y2 <= world.getBottomY())
                    break;
                p = new BlockPos(x, y2, z);
                if (canStand(p, world, entity))
                    return p;
                --y2;
            }

        }
        for (int dis = 1; dis < 128; ++dis) {
            int ez = starting.getZ() + dis + 1;
            // int ey = starting.getY() + dis + 1;
            int ex = starting.getX() + dis + 1;

            int y = starting.getY() - dis;
            for (int z = starting.getZ() - dis; z < ez; ++z) {
                for (int x = starting.getX() - dis; x < ex; ++x) {
                    var bp = new BlockPos(x, y, z);
                    if (canStand(bp, world, entity))
                        return bp;
                }
            }
            int ay = starting.getY() + dis;
            for (++y; y < ay; ++y) {
                for (int z = starting.getZ() - dis; z < ez; ++z) {
                    int x = starting.getX() - dis;
                    var bp = new BlockPos(x, y, z);
                    if (canStand(bp, world, entity))
                        return bp;
                    x = starting.getX() + dis;
                    bp = new BlockPos(x, y, z);
                    if (canStand(bp, world, entity))
                        return bp;
                }
                for (int x = starting.getX() - dis + 1; x < ex - 1; ++x) {
                    int z = starting.getZ() - dis;
                    var bp = new BlockPos(x, y, z);
                    if (canStand(bp, world, entity))
                        return bp;
                    z = starting.getZ() + dis;
                    bp = new BlockPos(x, y, z);
                    if (canStand(bp, world, entity))
                        return bp;
                }
            }
        }
        return world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, starting);
    }

    private static boolean likeAir(BlockState s) {
        return !s.isOpaque() && s.getMaterial() != Material.PORTAL && s.getFluidState().getFluid() == Fluids.EMPTY;
    }

    private static boolean canStand(BlockPos p, ServerWorld world, Entity entity) {
        var s = world.getBlockState(p);
        if (likeAir(s)) {
            var s2 = world.getBlockState(p.up());
            if (likeAir(s)) {
                var s3 = world.getBlockState(p.down());
                if (s3.hasSolidTopSurface(world, p.down(), entity, Direction.UP))
                    return true;
            }
        }
        return false;
    }
}
