package com.harby.halocraft.HaloItems;

import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

public class SniperRiffle extends Gun {
    //this class work as a test for scope gun
    public static final float ZOOM_FOV_MODIFIER = 0.1F;

    public SniperRiffle(Properties properties, boolean golden) {
        super(properties, true, AmmoTypes.BULLET, 4, 20, 200, golden ? 25.0f : 35.0f, 1000);
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.SPYGLASS;
    }

    @Override
    public int getShootingDelay() {
        return 20;
    }

    @Override
    public int getMaxAmmo() {
        return 15;
    }

    @Override
    public int getWeaponReloadCooldown() {
        return 50;
    }

    @Override
    public boolean twoHands() {
        return true;
    }

    public boolean isScopeing() {
        return true;
    }
}
