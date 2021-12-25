package net.accel.thepinksea;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModMain implements ModInitializer {
	public static final String ID = "thepinksea";
	public static final Logger LOGGER = LogManager.getLogger(ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("The Pink Sea 0.0.1");
		Blocks.registerAll();
	}
}
