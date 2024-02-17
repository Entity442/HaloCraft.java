package com.harby.halocraft.gui.screen;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.ScopeGun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ScopeScreen extends Screen {
    public static final ResourceLocation SCOPE_TEXTURE = new ResourceLocation(HaloCraft.MODID, "textures/gui/scope.png");
    private final ScopeGun gun;
    private final ItemStack stack;

    public ScopeScreen(ItemStack stack,ScopeGun gun) {
        super(Component.translatable("gui.halocraft.scope"));
        gun.setScoping(true,stack);
        this.gun = gun;
        this.stack = stack;
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.blit(SCOPE_TEXTURE, 0, 0, 0, 0, this.width,  this.height);
    }

    @Override
    protected boolean shouldNarrateNavigation() {
        return false;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        HaloCraft.LOGGER.info(pButton+"");
        if (pButton != 1) return false;
        this.removed();
        this.setFocused(false);
        this.gun.setScoping(false,stack);
        if (Minecraft.getInstance().screen == this){
            Minecraft.getInstance().popGuiLayer();
        }
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        HaloCraft.LOGGER.info(pDelta+"");
        if (Math.abs(pDelta) < 0.5) return false;
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
