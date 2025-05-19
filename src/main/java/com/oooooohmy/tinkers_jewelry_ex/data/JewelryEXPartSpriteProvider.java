package com.oooooohmy.tinkers_jewelry_ex.data;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

public class JewelryEXPartSpriteProvider extends AbstractPartSpriteProvider {

    public JewelryEXPartSpriteProvider() {
        super(TinkersJewelryEX.MODID);
    }

    @Override
    protected void addAllSpites() {
        addPart("chain", BlankBandMaterialStats.ID);
        addPart("curved_plate", BlankBandMaterialStats.ID);
        addPart("lining", BlankBandMaterialStats.ID);

        buildTool("bracelet")
                .addPart("gem", GemMaterialStats.ID)
                .addPart("curved_plate", BlankBandMaterialStats.ID);

        buildTool("charm")
                .addPart("gem", GemMaterialStats.ID)
                .addPart("curved_plate", GemMaterialStats.ID)
                .addPart("chain", BlankBandMaterialStats.ID);

        buildTool("crown")
                .addPart("gem", GemMaterialStats.ID)
                .addPart("gem", GemMaterialStats.ID)
                .addPart("gem", GemMaterialStats.ID)
                .addPart("curved_plate", BlankBandMaterialStats.ID);

        buildTool("glove")
                .addPart("gem", GemMaterialStats.ID)
                .addPart("curved_plate", GemMaterialStats.ID)
                .addPart("lining", BlankBandMaterialStats.ID);

        buildTool("necklace")
                .addPart("gem", GemMaterialStats.ID)
                .addPart("chain", BlankBandMaterialStats.ID);

    }

    @Override
    public String getName() {
        return "Tinkers' Jewelry EX Materials";
    }
}
