package com.oooooohmy.tinkers_jewelry_ex.items;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import com.oooooohmy.tinkers_jewelry_ex.tools.JewelryEXDefinitions;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static dev.ferriarnus.tinkersjewelry.items.ItemRegistry.GEM;

public class ItemRegistry {
    protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinkersJewelryEX.MODID);
    private static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersJewelryEX.MODID);
    private static final Item.Properties TOOLS_PROPS = new Item.Properties().stacksTo(1);
    private static final Item.Properties PARTS_PROPS = new Item.Properties();
    private static final Item.Properties CASTS_PROPS = new Item.Properties();

    //tools
    public static final ItemObject<TinkerCuriosBraceletItem> BRACELET = ITEMS.register("bracelet", () -> new TinkerCuriosBraceletItem(TOOLS_PROPS, JewelryEXDefinitions.BRACELET));
    public static final ItemObject<TinkerCuriosCharmItem> CHARM = ITEMS.register("charm", () -> new TinkerCuriosCharmItem(TOOLS_PROPS, JewelryEXDefinitions.CHARM));
    public static final ItemObject<TinkerCuriosCrownItem> CROWN = ITEMS.register("crown", () -> new TinkerCuriosCrownItem(TOOLS_PROPS, JewelryEXDefinitions.CROWN));
    public static final ItemObject<TinkerCuriosGloveItem> GLOVE = ITEMS.register("glove", () -> new TinkerCuriosGloveItem(TOOLS_PROPS, JewelryEXDefinitions.GLOVE));
    public static final ItemObject<TinkerCuriosNecklaceItem> NECKLACE = ITEMS.register("necklace", () -> new TinkerCuriosNecklaceItem(TOOLS_PROPS, JewelryEXDefinitions.NECKLACE));

    //tool parts
    public static final ItemObject<ToolPartItem> CHAIN = ITEMS.register("chain", () -> new ToolPartItem(PARTS_PROPS, BlankBandMaterialStats.ID));
    public static final ItemObject<ToolPartItem> CURVED_PLATE = ITEMS.register("curved_plate", () -> new ToolPartItem(PARTS_PROPS, BlankBandMaterialStats.ID));
    public static final ItemObject<ToolPartItem> LINING = ITEMS.register("lining", () -> new ToolPartItem(PARTS_PROPS, BlankBandMaterialStats.ID));

    //casts
    public static final CastItemObject CHAIN_CAST = ITEMS.registerCast(CHAIN, CASTS_PROPS);
    public static final CastItemObject CURVED_PLATE_CAST = ITEMS.registerCast(CURVED_PLATE, CASTS_PROPS);
    public static final CastItemObject LINING_CAST = ITEMS.registerCast(LINING, CASTS_PROPS);

    public static final RegistryObject<CreativeModeTab> tabTools = CREATIVE_TABS.register(
            "tools", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + TinkersJewelryEX.MODID + ".all"))
                    .icon(() -> {
                        MaterialVariantId material;
                        if (MaterialRegistry.isFullyLoaded()) {
                            material = ToolBuildHandler.RANDOM.getMaterial(GemMaterialStats.ID, RandomSource.create());
                        } else {
                            material = ToolBuildHandler.getRenderMaterial(0);
                        }
                        return GEM.get().withMaterialForDisplay(material);
                    })
                    .displayItems(ItemRegistry::addTabItems)
                    .withTabsBefore(TinkerTables.tabTables.getId())
                    .build());

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Consumer<ItemStack> output = tab::accept;
        acceptTool(output, BRACELET);
        acceptTool(output, CHARM);
        acceptTool(output, CROWN);
        acceptTool(output, GLOVE);
        acceptTool(output, NECKLACE);
        accept(output, CHAIN);
        accept(output, CURVED_PLATE);
        accept(output, LINING);
        output.accept(new ItemStack(CHAIN_CAST.get()));
        output.accept(new ItemStack(CHAIN_CAST.getSand()));
        output.accept(new ItemStack(CHAIN_CAST.getRedSand()));
        output.accept(new ItemStack(CURVED_PLATE_CAST.get()));
        output.accept(new ItemStack(CURVED_PLATE_CAST.getSand()));
        output.accept(new ItemStack(CURVED_PLATE_CAST.getRedSand()));
        output.accept(new ItemStack(LINING_CAST.get()));
        output.accept(new ItemStack(LINING_CAST.getSand()));
        output.accept(new ItemStack(LINING_CAST.getRedSand()));
    }

    private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
        ToolBuildHandler.addVariants(output, tool.get(), "");
    }

    private static void accept(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }

    public static void register(IEventBus modEventBus) {
        CREATIVE_TABS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}
