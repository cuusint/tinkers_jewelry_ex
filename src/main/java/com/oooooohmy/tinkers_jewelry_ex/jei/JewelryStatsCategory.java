package com.oooooohmy.tinkers_jewelry_ex.jei;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import me.paypur.tconjei.jei.AbstractMaterialStatsCategory;
import me.paypur.tconjei.jei.MaterialStatsWrapper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static me.paypur.tconjei.ColorProvider.*;

public class JewelryStatsCategory extends AbstractMaterialStatsCategory {
    public JewelryStatsCategory(IGuiHelper guiHelper) {
        super(guiHelper);

        ResourceLocation location = new ResourceLocation(TinkersJewelryEX.MODID, "textures/gui/jei.png");

        this.icon = guiHelper.createDrawable(location, 0, 0, 16, 16);
        this.title = Component.translatable("tinkers_jewelry_ex.tool_stats.jewelry");
        this.recipeType = RecipeType.create(TinkersJewelryEX.MODID, "jewelry_stats", MaterialStatsWrapper.class);
        this.tag = TagKey.create(Registries.ITEM, new ResourceLocation(TinkersJewelry.MODID,"modifiable/jewelry"));
    }

    @Override
    public void draw(MaterialStatsWrapper wrapper, IRecipeSlotsView recipeSlotsView, GuiGraphics gui, double mouseX, double mouseY) {
        super.draw(wrapper, recipeSlotsView, gui, mouseX, mouseY);

        final int color = MaterialTooltipCache.getColor(wrapper.getMaterialId()).getValue();
        float lineNumber = 2f;

        Optional<GemMaterialStats> gemOptional = wrapper.getStats(GemMaterialStats.ID);
        Optional<BlankBandMaterialStats> blankBandOptional = wrapper.getStats(BlankBandMaterialStats.ID);

        // TRAITS
        Optional<? extends IMaterialStats> statOptional = Stream.of(gemOptional, blankBandOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        if (statOptional.isPresent()) {
            drawTraits(gui, wrapper.getTraits(statOptional.get().getIdentifier()), lineNumber);
        }

        // GEM
        if (gemOptional.isPresent()) {
            GemMaterialStats gem = gemOptional.get();
            drawComponent(gui, gem.getLocalizedName().withStyle(ChatFormatting.UNDERLINE), 0, lineNumber++, color, true);
            drawComponent(gui, gem.getLocalizedInfo().get(0), 0, lineNumber++, TEXT, false);
            lineNumber += LINE_SPACING;
        }

        // BLANK BAND
        if (blankBandOptional.isPresent()) {
            BlankBandMaterialStats blankBand = blankBandOptional.get();
            drawComponent(gui, blankBand.getLocalizedName().withStyle(ChatFormatting.UNDERLINE), 0, lineNumber++, color, true);
            drawStatComponent(gui, blankBand.getLocalizedInfo().get(0), lineNumber++); // durability
            drawStatComponent(gui, blankBand.getLocalizedInfo().get(1), lineNumber++); // amplification
        }
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, MaterialStatsWrapper wrapper, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        // MATERIAL
        List<Component> materialTooltips = getMaterialTooltip(wrapper, mouseX, mouseY);
        if (!materialTooltips.isEmpty()) {
            tooltip.addAll(materialTooltips);
            return;
        }

        float lineNumber = 2f;

        Optional<GemMaterialStats> gemOptional = wrapper.getStats(GemMaterialStats.ID);
        Optional<BlankBandMaterialStats> blankBandOptional = wrapper.getStats(BlankBandMaterialStats.ID);

        // TRAIT
        Optional<? extends IMaterialStats> statOptional = Stream.of(gemOptional, blankBandOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        if (statOptional.isPresent()) {
            List<Component> traitTooltips = getTraitTooltips(wrapper.getTraits(statOptional.get().getIdentifier()), mouseX, mouseY, lineNumber);
            if (!traitTooltips.isEmpty()) {
                tooltip.addAll(traitTooltips);
                return;
            }
        }

        // EXTRA
        if (gemOptional.isPresent()) {
            lineNumber += LINE_SPACING + 2;
        }

        // HANDLE
        if (blankBandOptional.isPresent()) {
            lineNumber++;
            BlankBandMaterialStats blankBand = blankBandOptional.get();
            Optional<List<Component>> component = Stream.of(
                            getStatTooltip(blankBand, 0,  mouseX, mouseY, lineNumber++),
                            getStatTooltip(blankBand, 1,  mouseX, mouseY, lineNumber++))
                    .filter(list -> !list.isEmpty())
                    .findFirst();
            if (component.isPresent()) {
                tooltip.addAll(component.get());
            }
        }
    }
}
