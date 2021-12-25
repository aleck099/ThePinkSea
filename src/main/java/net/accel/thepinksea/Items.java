package net.accel.thepinksea;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Items {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(ModMain.ID, "general"),
            () -> new ItemStack(Blocks.PINK_SAND)
    );
}
