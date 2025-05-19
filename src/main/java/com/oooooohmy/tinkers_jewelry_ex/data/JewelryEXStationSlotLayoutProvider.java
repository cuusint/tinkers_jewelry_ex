package com.oooooohmy.tinkers_jewelry_ex.data;

import com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;

import static dev.ferriarnus.tinkersjewelry.items.ItemRegistry.GEM;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.CHAIN;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.CURVED_PLATE;
import static com.oooooohmy.tinkers_jewelry_ex.items.ItemRegistry.LINING;

public class JewelryEXStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {

    public JewelryEXStationSlotLayoutProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {

        defineModifiable(ItemRegistry.BRACELET)
                .sortIndex(SORT_ARMOR)
                .addInputItem(CURVED_PLATE, 25, 50)
                .addInputItem(GEM, 43, 32)
                .build();

        defineModifiable(ItemRegistry.CHARM)
                .sortIndex(SORT_ARMOR)
                .addInputItem(CHAIN, 15, 60)
                .addInputItem(CURVED_PLATE, 34, 41)
                .addInputItem(GEM, 53, 22)
                .build();

        defineModifiable(ItemRegistry.CROWN)
                .sortIndex(SORT_ARMOR)
                .addInputItem(CURVED_PLATE, 34, 41)
                .addInputItem(GEM, 34, 21)
                .addInputItem(GEM, 33, 41)
                .addInputItem(GEM, 33, 21)
                .build();

        defineModifiable(ItemRegistry.GLOVE)
                .sortIndex(SORT_ARMOR)
                .addInputItem(LINING, 15, 60)
                .addInputItem(CURVED_PLATE, 34, 41)
                .addInputItem(GEM, 53, 22)
                .build();

        defineModifiable(ItemRegistry.NECKLACE)
                .sortIndex(SORT_ARMOR)
                .addInputItem(CHAIN, 25, 50)
                .addInputItem(GEM, 43, 32)
                .build();

    }

    @Override
    public String getName() {
        return "Tinkers' Jewelry EX Tinker Station Slot Layouts";
    }

}