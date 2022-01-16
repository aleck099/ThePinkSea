package net.accel.thepinksea.item;

import net.accel.thepinksea.ModMain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugItem extends Item {
    public DebugItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ModMain.LOGGER.info("DEBUG item used");
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
