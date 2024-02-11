package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.HaloItems;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gun extends Item {
    private int shootingTicks = 0;
    private final int shootingDelay;
    private final int reloadCooldown;
    private final int maxAmmo;
    private final boolean twoHand;
    private final float damage;
    private final float speed;
    private final AmmoTypes ammoType;

    public Gun(Properties properties, boolean twoHand, AmmoTypes ammoType, int maxAmmo, int shootingDelay, int reloadCooldown, float damage, float speed) {
        super(properties.stacksTo(1));
        this.shootingDelay = shootingDelay;
        this.reloadCooldown = reloadCooldown;
        this.maxAmmo = maxAmmo;
        this.twoHand = twoHand;
        this.ammoType = ammoType;
        this.damage = damage;
        this.speed = speed;
        HaloItems.HALO_ITEMS.add(this);
        HaloItems.GUNS_ITEMS.add(this);
    }



    @Override
    public int getUseDuration(@NotNull ItemStack pItemStack) {
        return 72000;
    }

    public int getShootingDelay() {
        return this.shootingDelay;
    }

    public boolean twoHands() {
        return this.twoHand;
    }

    public int getMaxAmmo() {
        return this.maxAmmo;
    }

    public int getWeaponReloadCooldown() {
        return this.reloadCooldown;
    }

    public AmmoTypes getAmmoType() {
        return ammoType;
    }

    public void shotProjectile(Level level, LivingEntity livingEntity, ItemStack stack) {
        if (this.isShooting()) {
            return;
        }
        if (!level.isClientSide() && this.getAmmoType(stack) != AmmoList.NONE && this.getAmountAmmoStored(stack) > 0 && !this.isShooting()) {
            AmmoList ammo = this.getAmmoType(stack);
            BaseProjectileEntity bulletEntity = ammo.getType().createBullet(level, livingEntity, ammo, this.getSpeed());
            bulletEntity.setDamage(this.getDamage());
            level.addFreshEntity(bulletEntity);
            this.setAmountAmmoStored(stack, this.getAmountAmmoStored(stack) - 1);
            if (this.getAmountAmmoStored(stack) == 0) {
                this.setAmmoType(stack, AmmoList.NONE);
                if (livingEntity instanceof Player player) {
                    player.displayClientMessage(Component.translatable("message.halocraft.out_of_ammo"), true);
                }
            }
            this.shootingTicks = this.getShootingDelay();
        }
    }

    public boolean isReloading(Player player) {
        return player.getCooldowns().isOnCooldown(this);
    }

    public boolean isShooting() {
        return this.shootingTicks != 0;
    }

    /**
     * get the amount of ammo currently stored in the gun
     */
    public int getAmountAmmoStored(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null ? compoundtag.getInt("ammo") : 0;
    }

    /**
     * set the amount of ammo stored in the gun
     */
    public void setAmountAmmoStored(ItemStack gunStack, int amount) {
        CompoundTag compoundtag = gunStack.getOrCreateTag();
        compoundtag.putInt("ammo", amount);
    }

    /**
     * get the ammo type currently use by the gun
     */
    public AmmoList getAmmoType(ItemStack gunStack) {
        CompoundTag compoundtag = gunStack.getTag();
        return compoundtag != null ? AmmoList.valueOf(compoundtag.getString("type")) : AmmoList.NONE;
    }

    /**
     * set the ammo type currently use by the gun
     */
    public void setAmmoType(ItemStack stack, AmmoList ammo) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putString("type", ammo.name());
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
         components.add(Component.translatable("tooltip.halocraft.ammo", this.getAmountAmmoStored(itemStack), this.getMaxAmmo()).withStyle(ChatFormatting.GRAY));
        if (this.getAmmoType(itemStack) != AmmoList.NONE) {
            components.add(Component.translatable("ammo_list.halocraft." + this.getAmmoType(itemStack).name().toLowerCase()).withStyle(ChatFormatting.YELLOW));
        }
        super.appendHoverText(itemStack, level, components, flag);
    }

    /**
     * Looks for ammo in the player's inventory.
     */
    public ItemStack lookForAmmo(Player player) {
        for (int i = 0; i <= player.getInventory().getContainerSize(); i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.getItem() instanceof AmmoItem ammo && ammo.isValidGun(this)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int value, boolean devalue) {
        if (entity instanceof Player player) {
            super.inventoryTick(stack, level, entity, value, devalue);
            if (this.isShooting()) {
                this.shootingTicks--;
            }
            if (stack.getItem() instanceof Gun gunStack && (player.getMainHandItem() == stack || player.getOffhandItem() == stack)) {
                if (!isTwoHandAvailable(player) && gunStack.twoHands()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 1, false, false, true));
                    return;
                }
                if (HaloKeys.getKey(2) && isTwoHandAvailable(player)) {//reload key
                    if (this.getAmountAmmoStored(stack) < this.getMaxAmmo()) {
                        HaloCraft.sendMSGToServer(new HaloKeys(player.getId(), 2));
                        ItemStack ammoStack = lookForAmmo(player);
                        if (ammoStack.getItem() instanceof AmmoItem ammo && (ammo.getBullet() == this.getAmmoType(stack) || this.getAmmoType(stack) == AmmoList.NONE || this.getAmountAmmoStored(stack) == 0)) {
                            int ammoAdditional = Math.min(this.getMaxAmmo() - this.getAmountAmmoStored(stack), ammoStack.getCount());
                            if (player.isCreative()) {
                                ammoAdditional = this.getMaxAmmo();
                            } else {
                                ammoStack.shrink(ammoAdditional);
                            }
                            //reload gun
                            player.getCooldowns().addCooldown(this, this.getWeaponReloadCooldown());
                            this.setAmmoType(stack, ammo.getBullet());
                            this.setAmountAmmoStored(stack, ammoAdditional);
                        }
                    }
                }
                if (HaloKeys.getKey(3) && isTwoHandAvailable(player)) {//shoot key
                    if (this.getAmountAmmoStored(stack) == 0) {
                        player.displayClientMessage(Component.translatable("message.halocraft.out_of_ammo"), true);
                    } else {
                        HaloCraft.sendMSGToServer(new HaloKeys(player.getId(), 3));
                        this.shotProjectile(level, player, stack);
                    }
                }
            }
        }
    }

    public boolean isTwoHandAvailable(Player pPlayer) {
        return (pPlayer.getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY && pPlayer.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof Gun)
                ||
                (pPlayer.getItemInHand(InteractionHand.OFF_HAND) == ItemStack.EMPTY && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof Gun);
    }

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }
}
