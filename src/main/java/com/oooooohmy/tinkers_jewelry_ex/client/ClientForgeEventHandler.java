package com.oooooohmy.tinkers_jewelry_ex.client;

import static com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX.JEWELRY_STAT_IDS;
import static com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX.allMaterialsTooltip;
import static me.paypur.tconjei.ColorProvider.*;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import me.paypur.tconjei.Utils;
import me.paypur.tconjei.client.ClientConfig;
import me.paypur.tconjei.jei.MaterialStatsWrapper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.tools.item.RepairKitItem;

@Mod.EventBusSubscriber(modid = TinkersJewelryEX.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventHandler {

    // TODO: might have problems if server changes and valid materials change too
    // runs on reload too
    @SubscribeEvent
    public static void onLogin(RecipesUpdatedEvent event) {
        allMaterialsTooltip.clear();

        if (ClientConfig.ENABLE_TOOLTIP.get()) {
            for (MaterialStatsWrapper wrapper : Utils.getMaterialWrappers()) {
                if (!wrapper.hasStats(JEWELRY_STAT_IDS)) {
                    continue;
                }

                int tier = wrapper.material().getTier();
                MutableComponent component = Component.translatable("tconjei.tooltip.tier", tier)
                        .withStyle(style -> style.withColor(getTierColor(tier).orElse(0xAAAAAA)))
                        .append(Component.translatable("tinkers_jewelry_ex.tooltip.jewelry")
                                .withStyle(ChatFormatting.GRAY));

                for (ItemStack stack : wrapper.getInputs()) {
                    if (!(stack.getItem() instanceof RepairKitItem)) {
                        allMaterialsTooltip.put(stack.getItem(), component);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event) {
        if (ClientConfig.ENABLE_TOOLTIP.get()) {
            Item key = event.getItemStack().getItem();
            if (allMaterialsTooltip.containsKey(key)) {
                event.getToolTip().add(allMaterialsTooltip.get(key));
            }
        }
    }

}