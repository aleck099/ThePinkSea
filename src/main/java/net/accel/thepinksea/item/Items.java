package net.accel.thepinksea.item;

import net.accel.thepinksea.ModMain;
import net.accel.thepinksea.block.Blocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(ModMain.ID, "general"),
            () -> new ItemStack(Blocks.PINK_SAND)
    );

    public static final Item DEBUG_ITEM = new DebugItem(new FabricItemSettings());

    public static void registerAll() {
        Registry.register(Registry.ITEM, new Identifier(ModMain.ID, "debug_item"), DEBUG_ITEM);
    }
}
