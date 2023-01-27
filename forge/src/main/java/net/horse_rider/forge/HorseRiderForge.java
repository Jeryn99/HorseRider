package net.horse_rider.forge;

import dev.architectury.platform.forge.EventBuses;
import net.horse_rider.horse_rider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(horse_rider.MOD_ID)
public class horse_riderForge {
    public horse_riderForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(horse_rider.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        horse_rider.init();
    }
}
