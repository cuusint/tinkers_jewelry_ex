package com.oooooohmy.tinkers_jewelry_ex.curios;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.InterModComms;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotTypeMessage;

public class Curios {
    public static String NECKLACE_SLOT = "necklace";
    public static String RING_SLOT = "ring";
    public static String BRACELET_SLOT = "bracelet";
    public static String HANDS_SLOT = "hands";
    public static String HEAD_SLOT = "head";
    public static String CHARM_SLOT = "charm";

    public static void registerCurioSlot(final String identifier, final int slots, final boolean isHidden, @Nullable final ResourceLocation icon) {
        final SlotTypeMessage.Builder message = new SlotTypeMessage.Builder(identifier);

        message.size(slots);

        if (isHidden) {
            message.hide();
        }

        if (icon != null) {
            message.icon(icon);
        }
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> message.build());
    }
}
