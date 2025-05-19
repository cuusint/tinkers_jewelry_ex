package com.oooooohmy.tinkers_jewelry_ex.data;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public class JewelryEXToolsRecipeProvider extends RecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

    public JewelryEXToolsRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";
        String folder = "tools/building/";

        toolBuilding(consumer, ItemRegistry.BRACELET, folder);
        toolBuilding(consumer, ItemRegistry.CHARM, folder);
        toolBuilding(consumer, ItemRegistry.CROWN, folder);
        toolBuilding(consumer, ItemRegistry.GLOVE, folder);
        toolBuilding(consumer, ItemRegistry.NECKLACE, folder);

        partRecipes(consumer, ItemRegistry.CHAIN.get(), ItemRegistry.CHAIN_CAST, 1, partFolder, castFolder);
        partRecipes(consumer, ItemRegistry.CURVED_PLATE.get(), ItemRegistry.CURVED_PLATE_CAST, 1, partFolder, castFolder);
        partRecipes(consumer, ItemRegistry.LINING.get(), ItemRegistry.LINING_CAST, 1, partFolder, castFolder);

        uncastablePart(consumer, ItemRegistry.LINING.get(), 1, null, partFolder);
    }

    @Override
    public String getModId() {
        return TinkersJewelryEX.MODID;
    }
}
