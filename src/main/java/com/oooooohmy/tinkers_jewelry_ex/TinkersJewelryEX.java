package com.oooooohmy.tinkers_jewelry_ex;

import com.mojang.logging.LogUtils;
import com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersJewelryEX.MODID)
public class TinkersJewelryEX {
    public static final String MODID = "tinkers_jewelry_ex";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TinkersJewelryEX() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the Deferred Register to the mod event bus so items get registered
        ItemRegistry.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
