package net.accel.thepinksea;

import net.accel.thepinksea.block.Blocks;
import net.accel.thepinksea.item.Items;
import net.accel.thepinksea.world.Features;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModMain implements ModInitializer {
    public static final String ID = "thepinksea";
    public static final Logger LOGGER = LogManager.getLogger(ID);
    public static final RegistryKey<World> THE_PINK_SEA_WORLD = RegistryKey.of(Registry.WORLD_KEY, new Identifier(ID, "the_pink_sea"));

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("The Pink Sea 0.0.2");
        Blocks.registerAll();
        Items.registerAll();
        Features.registerAll();
        Sounds.registerSounds();

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.PINK_SEA_PORTAL, RenderLayer.getCutout());
    }

}
