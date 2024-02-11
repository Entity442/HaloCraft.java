package com.harby.halocraft.gui.screen;

import com.harby.halocraft.HaloCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ScopeScreen extends Screen {

    public static final ResourceLocation SCOPE_TEXTURE = new ResourceLocation(HaloCraft.MODID, "textures/gui/scope.png");

    public ScopeScreen() {
        super(Component.translatable("gui.halocraft.scope"));
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.blit(SCOPE_TEXTURE, 0, 0, 0, 0, this.width, this.height);
    }
}
