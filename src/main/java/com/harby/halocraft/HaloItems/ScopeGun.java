package com.harby.halocraft.HaloItems;

import com.harby.halocraft.Events.GunEvents;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.gui.screen.ScopeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

public class ScopeGun extends Gun {
    //this class work as a test for scope gun
    public final float fovOnScope;

    public ScopeGun(Properties properties, boolean golden, float fovOnScope) {
        super(properties, true, AmmoTypes.BULLET, 4, 20, 200, golden ? 25.0f : 35.0f, 25);
        this.fovOnScope = fovOnScope;
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

    public boolean isScoping(ItemStack gun) {
        CompoundTag compoundtag = gun.getTag();
        if (compoundtag == null || !compoundtag.contains("scoping")) {
            return false;
        }
        return compoundtag.getBoolean("scoping");
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int va) {
        super.onUseTick(level, livingEntity, stack, va);
        this.setScoping(true, stack);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        super.onStopUsing(stack, entity, count);
        this.setScoping(false, stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        super.use(pLevel, pPlayer, pUsedHand);
        if (!this.isTwoHandAvailable(pPlayer)) {
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        } else {
            ItemUtils.startUsingInstantly(pLevel, pPlayer, pPlayer.getUsedItemHand());
            //this.setScoping(true, pPlayer.getItemInHand(pUsedHand));
            if (pLevel.isClientSide) {
                MinecraftForge.EVENT_BUS.post(new GunEvents.GunScopeEvent(pPlayer,this,pUsedHand));
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().setScreen(new ScopeScreen(pPlayer.getItemInHand(pUsedHand),this)));
            }
            return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
        }
    }


    public float getFovModifier() {
        return this.fovOnScope;
    }

    public void setScoping(boolean scoping, ItemStack pStack) {
        pStack.getOrCreateTag().putBoolean("scoping", scoping);
    }
}
