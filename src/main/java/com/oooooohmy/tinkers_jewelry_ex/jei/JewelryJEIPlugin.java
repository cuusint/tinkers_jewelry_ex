package com.oooooohmy.tinkers_jewelry_ex.jei;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import me.paypur.tconjei.TConJEI;
import me.paypur.tconjei.Utils;
import me.paypur.tconjei.jei.MaterialStatsWrapper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.List;

@SuppressWarnings("unused")
@JeiPlugin
public class JewelryJEIPlugin implements IModPlugin {
    ResourceLocation UID = new ResourceLocation(TConJEI.MOD_ID, "jei_plugin");
    private static final RecipeType<MaterialStatsWrapper> JEWELRY_STATS = RecipeType.create(TinkersJewelryEX.MODID, "jewelry_stats", MaterialStatsWrapper.class);

    @NotNull
    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        List<MaterialStatsWrapper> statsWrappers = Utils.getMaterialWrappers();
        registration.addRecipes(JEWELRY_STATS, statsWrappers.stream()
                .filter(w -> w.hasStats(TinkersJewelryEX.JEWELRY_STAT_IDS))
                .toList());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        final IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new JewelryStatsCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(TinkerTables.tinkerStation.asItem()), JEWELRY_STATS);
        registration.addRecipeCatalyst(new ItemStack(TinkerTables.tinkersAnvil.asItem()), JEWELRY_STATS);
        registration.addRecipeCatalyst(new ItemStack(TinkerTables.scorchedAnvil.asItem()), JEWELRY_STATS);
    }
}
