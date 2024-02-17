package com.harby.halocraft.core;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.ScopeGun;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HaloOverlay {
    public static final ResourceLocation SCOPE_TEXTURE = new ResourceLocation(HaloCraft.MODID, "textures/gui/scope.png");
    public static final IGuiOverlay HUD_SCOPE_GUN = (gui, guiGraphics, partialTicks, width, height) -> {
        if (!Minecraft.getInstance().options.getCameraType().isFirstPerson()) return;
        Player player = Minecraft.getInstance().player;
        if (player != null && player.getItemInHand(player.getUsedItemHand()).getItem() instanceof ScopeGun sGun && sGun.isScoping(player.getItemInHand(player.getUsedItemHand()))) {
            gui.setupOverlayRenderState(true, false);
            guiGraphics.blit(SCOPE_TEXTURE, width / 2 - 128, height / 2 - 128, 0, 0, 256, 256);
            guiGraphics.fill(0, 0, width / 2 - 128, height, 0xFF000000);
            guiGraphics.fill(width / 2 - 128, 0, width / 2 + 128, height / 2 - 128, 0xFF000000);
            guiGraphics.fill(width / 2 - 128, height / 2 + 128, width / 2 + 128, height, 0xFF000000);
            guiGraphics.fill(width / 2 + 128, 0, width, height, 0xFF000000);
        }
    };
}
