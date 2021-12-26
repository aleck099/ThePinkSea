package net.accel.thepinksea;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Sounds {
    public static SoundEvent MUSIC;

    private static SoundEvent createAndRegister(String id) {
        var identifier = new Identifier(ModMain.ID, id);
        var se = new SoundEvent(identifier);
        Registry.register(Registry.SOUND_EVENT, identifier, se);
        return se;
    }

    public static void registerSounds() {
        MUSIC = createAndRegister("music.the_pink_sea");
    }
}
