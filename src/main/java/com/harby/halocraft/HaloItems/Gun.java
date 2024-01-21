package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Projectiles.BaseProjectileEntity;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.HaloItems;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Gun extends Item {
    private boolean isReloading = false;
    private boolean isShooting = false;
    private int shootingTicks = 0;
    private final int shootingDelay;
    private final int reloadCooldown;
    private final int maxAmmo;
    private final boolean twoHand;
    private final float damage;
    private final int range;
    private final AmmoTypes ammoType;

    public Gun(Properties properties, boolean twoHand, AmmoTypes ammoType, int maxAmmo, int shootingDelay, int reloadCooldown, float damage, int range) {
        super(properties.stacksTo(1));
        this.shootingDelay = shootingDelay;
        this.reloadCooldown = reloadCooldown;
        this.maxAmmo = maxAmmo;
        this.twoHand = twoHand;
        this.ammoType = ammoType;
        this.damage = damage;
        this.range = range;
        HaloItems.HALO_ITEMS.add(this);
        HaloItems.GUNS_ITEMS.add(this);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player livingEntity, InteractionHand hand) {
        ItemStack itemstack = livingEntity.getItemInHand(hand);
        if (this.twoHands() && !isTwoHandAvailable(livingEntity)) {
            return InteractionResultHolder.fail(itemstack);
        }
        if (this.isShooting) {
            return InteractionResultHolder.fail(itemstack);
        }
        livingEntity.awardStat(Stats.ITEM_USED.get(this));
        livingEntity.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
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
        HaloCraft.LOGGER.info(!level.isClientSide() + " " + this.getAmmoType(stack));
        if (!level.isClientSide() && this.getAmmoType(stack) != AmmoList.NONE) {
            AmmoList ammo = this.getAmmoType(stack);
            BaseProjectileEntity bulletEntity = ammo.getType().createBullet(level, livingEntity, ammo);
            bulletEntity.setDamage(this.getDamage());
            bulletEntity.setPos(livingEntity.getEyePosition());
            bulletEntity.shootFromRotation(livingEntity, livingEntity.getXRot(), livingEntity.getYRot(), 0.0F, 6.0F, 1.0F);
            level.addFreshEntity(bulletEntity);
            HaloCraft.LOGGER.info("Shooting"+bulletEntity.getProjectile().name());
        }
    }

    public void reloadGun(Player player, ItemStack stack, int ammo) {
        player.getCooldowns().addCooldown(this, this.getWeaponReloadCooldown());
        this.setAmountAmmoStored(stack, Math.min(this.getMaxAmmo(), this.getAmountAmmoStored(stack) + ammo));
    }

    public boolean isReloading() {
        return this.isReloading;
    }

    public boolean isShooting() {
        return this.isShooting;
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
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int va) {
        this.shotProjectile(level, livingEntity, stack);
        HaloCraft.LOGGER.info("ShootingDelay "+this.getShootingDelay());
        return;
        /*if (va % this.getShootingDelay() != 0 && !level.isClientSide()) {
            return;
        }
        if (this.getAmountAmmoStored(stack) > 0 && this.getAmmoType(stack) != AmmoList.NONE) {
            this.isShooting = true;
            this.shotProjectile(level, livingEntity, stack);
            this.setAmountAmmoStored(stack, this.getAmountAmmoStored(stack) - 1);
            //livingEntity.playSound(SoundEvents.FIREWORK_ROCKET_BLAST, 0.5F, 1.0F);
        } else {
            if (livingEntity instanceof Player player) {
                player.displayClientMessage(Component.literal("Out of Ammo"), true);
            }
        }
        super.onUseTick(level, livingEntity, stack, va);*/
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Ammo: " + this.getAmountAmmoStored(itemStack) + "/" + getMaxAmmo()).withStyle(ChatFormatting.RED));
        components.add(Component.literal("Ammo type: " + this.getAmmoType(itemStack).name()).withStyle(ChatFormatting.YELLOW));
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

    public int flashTicks() {
        return 10;
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int value, boolean devalue) {
        if (entity instanceof Player player) {
            if (stack.getItem() instanceof Gun gunStack && (player.getMainHandItem() == stack || player.getOffhandItem() == stack)) {
                if (this.getAmountAmmoStored(stack) < this.getMaxAmmo()) {
                    if (HaloKeys.getKey(2) && isTwoHandAvailable(player)) {
                        HaloCraft.sendMSGToServer(new HaloKeys(player.getId(), 2));
                        ItemStack ammoStack = lookForAmmo(player);
                        if (ammoStack.getItem() instanceof AmmoItem ammo && (ammo.getBullet() == this.getAmmoType(stack) || this.getAmmoType(stack) == AmmoList.NONE || this.getAmountAmmoStored(stack) == 0)) {
                            int ammoAdditional = Math.min(this.getMaxAmmo() - this.getAmountAmmoStored(stack), ammoStack.getCount());
                            if (player.isCreative()) {
                                ammoAdditional = this.getMaxAmmo();
                            } else {
                                ammoStack.shrink(ammoAdditional);
                            }
                            this.setAmmoType(stack, ammo.getBullet());
                            this.reloadGun(player, stack, ammoAdditional);
                        }
                    }
                }
                if (HaloKeys.getKey(3) && isTwoHandAvailable(player)) {
                    HaloCraft.sendMSGToServer(new HaloKeys(player.getId(), 3));
                    ItemUtils.startUsingInstantly(level, player, player.getUsedItemHand());
                }
                if (!isTwoHandAvailable(player) && gunStack.twoHands()) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 1, false, false, true));
                }
            }
            this.isReloading = player.getCooldowns().isOnCooldown(this);
            if (this.isShooting) {
                this.shootingTicks++;
                if (this.shootingTicks >= this.flashTicks()) {
                    this.isShooting = false;
                    this.shootingTicks = 0;
                }
            }
        }
        super.inventoryTick(stack, level, entity, value, devalue);
    }


    public boolean isTwoHandAvailable(Player pPlayer) {
        return (pPlayer.getItemInHand(InteractionHand.MAIN_HAND) == ItemStack.EMPTY && pPlayer.getItemInHand(InteractionHand.OFF_HAND).getItem() instanceof Gun)
                ||
                (pPlayer.getItemInHand(InteractionHand.OFF_HAND) == ItemStack.EMPTY && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof Gun);
    }

    public float getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
