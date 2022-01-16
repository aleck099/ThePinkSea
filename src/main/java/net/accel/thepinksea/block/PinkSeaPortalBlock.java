package net.accel.thepinksea.block;

import net.accel.thepinksea.Helpers;
import net.accel.thepinksea.ModMain;
import net.accel.thepinksea.mixin.TeleportCommandInvoker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Set;

public class PinkSeaPortalBlock extends Block {

    protected static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 13.0, 16.0);

    public PinkSeaPortalBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld sw && !entity.hasVehicle() && !entity.hasPassengers() && entity.canUsePortals() && VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entity.getBoundingBox().offset(-pos.getX(), -pos.getY(), -pos.getZ())), state.getOutlineShape(world, pos), BooleanBiFunction.AND)) {
            var currentKey = world.getRegistryKey();
            RegistryKey<World> targetKey;
            if (currentKey == World.OVERWORLD)
                targetKey = ModMain.THE_PINK_SEA_WORLD;
            else if (currentKey == ModMain.THE_PINK_SEA_WORLD)
                targetKey = World.OVERWORLD;
            else
                return;

            ServerWorld targetWorld = sw.getServer().getWorld(targetKey);
            if (targetWorld == null) {
                return;
            }
            var t = Helpers.findTeleportTarget(pos, entity, sw, targetWorld);
            // ModMain.LOGGER.info("TARGET-> " + t.position.x + " " + t.position.y + " " + t.position.z);
            TeleportCommandInvoker.invokeTeleport(sw.getServer().getCommandSource(),
                    entity,
                    targetWorld,
                    t.position.x, t.position.y, t.position.z,
                    Set.of(),
                    t.yaw, t.pitch, null);
        }
    }

}
