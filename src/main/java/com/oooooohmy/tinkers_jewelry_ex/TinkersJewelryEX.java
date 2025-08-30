package com.oooooohmy.tinkers_jewelry_ex;

import com.mojang.logging.LogUtils;
import com.oooooohmy.tinkers_jewelry_ex.curios.Curios;
import com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.HashMap;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinkersJewelryEX.MODID)
public class TinkersJewelryEX {
    public static final String MODID = "tinkers_jewelry_ex";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final List<MaterialStatsId> JEWELRY_STAT_IDS;
    public static HashMap<Item, Component> allMaterialsTooltip;

    public static ResourceLocation ResourceLocation(String str) {
        return ResourceLocation.fromNamespaceAndPath(MODID, str);
    }

    static {
        JEWELRY_STAT_IDS = List.of(GemMaterialStats.ID, BlankBandMaterialStats.ID);
        allMaterialsTooltip = new HashMap();
    }

    public TinkersJewelryEX() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the Deferred Register to the mod event bus so items get registered
        ItemRegistry.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        Curios.registerCurioSlot(Curios.RING_SLOT, 2, false, null);
        Curios.registerCurioSlot(Curios.NECKLACE_SLOT, 1, false, null);
        Curios.registerCurioSlot(Curios.BRACELET_SLOT, 2, false, null);
        Curios.registerCurioSlot(Curios.HANDS_SLOT, 2, false, null);
        Curios.registerCurioSlot(Curios.HEAD_SLOT, 1, false, null);
        Curios.registerCurioSlot(Curios.CHARM_SLOT, 2, false, null);
    }
}
