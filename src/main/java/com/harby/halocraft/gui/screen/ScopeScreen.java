package com.harby.halocraft.gui.screen;

import com.harby.halocraft.HaloCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ScopeScreen extends Screen {

    public static final ResourceLocation SCOPE_TEXTURE = new ResourceLocation(HaloCraft.MODID, "textures/gui/scope.png");

    public ScopeScreen() {
        super(Component.translatable("gui.halocraft.scope"));
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.blit(SCOPE_TEXTURE, (pGuiGraphics.guiWidth() - this.width) / 2, (pGuiGraphics.guiHeight() - this.height) / 2, 0, 0, pGuiGraphics.guiWidth(), pGuiGraphics.guiHeight());
    }

    @Override
    protected boolean shouldNarrateNavigation() {
        return false;
    }

    /*@Override
    public void renderBackground(GuiGraphics pGuiGraphics) {
        super.renderBackground(pGuiGraphics);
    }*/
}
