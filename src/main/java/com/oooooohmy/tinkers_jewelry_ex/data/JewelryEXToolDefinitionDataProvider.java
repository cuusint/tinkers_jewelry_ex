package com.oooooohmy.tinkers_jewelry_ex.data;

import com.oooooohmy.tinkers_jewelry_ex.TinkersJewelryEX;
import com.oooooohmy.tinkers_jewelry_ex.tools.JewelryEXDefinitions;
import dev.ferriarnus.tinkersjewelry.tools.modules.OverSlimeDebufModule;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;

import static dev.ferriarnus.tinkersjewelry.items.ItemRegistry.GEM;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.CHAIN;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.CURVED_PLATE;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.LINING;

public class JewelryEXToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

    public JewelryEXToolDefinitionDataProvider(PackOutput packOutput) {
        super(packOutput, TinkersJewelryEX.MODID);
    }

    @Override
    protected void addToolDefinitions() {
        RandomMaterial tier1Material = RandomMaterial.random().tier(1).build();
        DefaultMaterialsModule defaultTwoParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultFourParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material, tier1Material).build();

        define(JewelryEXDefinitions.BRACELET)
                .module(PartStatsModule.parts()
                        .part(CURVED_PLATE)
                        .part(GEM).build())
                .module(defaultTwoParts)
                .module(OverSlimeDebufModule.INSTANCE)
                .build();

        define(JewelryEXDefinitions.CHARM)
                .module(PartStatsModule.parts()
                        .part(CHAIN)
                        .part(CURVED_PLATE)
                        .part(GEM).build())
                .module(defaultThreeParts)
                .module(OverSlimeDebufModule.INSTANCE)
                .build();

        define(JewelryEXDefinitions.CROWN)
                .module(PartStatsModule.parts()
                        .part(CURVED_PLATE)
                        .part(GEM)
                        .part(GEM)
                        .part(GEM).build())
                .module(defaultFourParts)
                .module(OverSlimeDebufModule.INSTANCE)
                .build();

        define(JewelryEXDefinitions.GLOVE)
                .module(PartStatsModule.parts()
                        .part(LINING)
                        .part(CURVED_PLATE)
                        .part(GEM).build())
                .module(defaultThreeParts)
                .module(OverSlimeDebufModule.INSTANCE)
                .build();

        define(JewelryEXDefinitions.NECKLACE)
                .module(PartStatsModule.parts()
                        .part(CHAIN)
                        .part(GEM).build())
                .module(defaultTwoParts)
                .module(OverSlimeDebufModule.INSTANCE)
                .build();
    }

    @Override
    public String getName() {
        return "Tinkers' Jewelry EX Tool Definition Data Generator";
    }

}
