package com.oooooohmy.tinkers_jewelry_ex.jei;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import me.paypur.tconjei.jei.AbstractMaterialStatsCategory;
import me.paypur.tconjei.jei.MaterialStatsWrapper;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class JewelryStatsCategory extends AbstractMaterialStatsCategory {
    public JewelryStatsCategory(IGuiHelper guiHelper) {
        super(guiHelper);

        ResourceLocation location = TinkersJewelryEX.ResourceLocation("textures/gui/jei.png");

        this.icon = guiHelper.createDrawable(location, 0, 0, 16, 16);
        this.title = Component.translatable("tinkers_jewelry_ex.tool_stats.jewelry");
        this.recipeType = RecipeType.create(TinkersJewelryEX.MODID, "jewelry_stats", MaterialStatsWrapper.class);
        this.statsIds = TinkersJewelryEX.JEWELRY_STAT_IDS;
        this.tag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TinkersJewelry.MODID,"modifiable/jewelry"));
    }
}
