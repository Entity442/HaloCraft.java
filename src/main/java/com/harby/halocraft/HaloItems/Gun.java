package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.BulletType;
import com.harby.halocraft.core.HaloItems;
import com.harby.halocraft.core.HaloTags;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public abstract class Gun extends Item {
    private boolean isReloading = false;
    private boolean isShooting = false;
    private int shootingTicks = 0;

    public Gun(Properties properties) {
        super(properties);
        HaloItems.HALO_ITEMS.add(this);
        HaloItems.GUNS_ITEMS.add(this);
    }

    /*public static final Predicate<ItemStack> AMMO = (stack) -> {
        return stack.getItem() == HaloItems.BULLETS.get().asItem() || stack.getItem() == HaloItems.EXPLOSIVE_BULLET.get().asItem()
                || stack.getItem() == HaloItems.FIRE_BULLET.get().asItem() || stack.getItem() == HaloItems.FROZEN_BULLET.get().asItem()
                || stack.getItem() == HaloItems.PENETRATING_BULLET.get().asItem();
    };*/

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player livingEntity, InteractionHand hand) {
        ItemStack itemstack = livingEntity.getItemInHand(hand);
        livingEntity.awardStat(Stats.ITEM_USED.get(this));
        livingEntity.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }


    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public abstract int getShootingDelay();

    public abstract void shotProjectile(Level level, LivingEntity livingEntity, ItemStack stack);

    public abstract int getMaxAmmo();

    public abstract int getWeaponReloadCooldown();

    public void reloadGun(Player player, ItemStack stack) {
        player.getCooldowns().addCooldown(this, this.getWeaponReloadCooldown());
        this.setAmmo(stack, getMaxAmmo());
    }

    public boolean isReloading() {
        return this.isReloading;
    }

    public boolean isShooting() {
        return this.isShooting;
    }


    public int getAmmo(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null ? compoundtag.getInt("ammo") : 0;
    }

    public void setAmmo(ItemStack stack, int value) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putInt("ammo", value);
    }

    public BulletType getAmmoType(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        if (compoundtag != null) HaloCraft.LOGGER.info(compoundtag.getString("type"));
        return compoundtag != null ? (Arrays.stream(BulletType.values()).filter(bt -> bt.name().equals(compoundtag.getString("type"))).count() == 1 ? BulletType.valueOf(compoundtag.getString("type")) : BulletType.NONE) : BulletType.NONE;
    }

    public void setAmmoType(ItemStack stack, BulletType bt) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putString("type", bt.name());
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int va) {
        if (va % this.getShootingDelay() != 0) {
            return;
        }
        if (this.getAmmo(stack) > 0) {
            this.isShooting = true;
            this.shotProjectile(level, livingEntity, stack);
            this.setAmmo(stack, this.getAmmo(stack) - 1);
            livingEntity.playSound(SoundEvents.FIREWORK_ROCKET_BLAST, 1.0F, 1.0F);
        } else {
            if (livingEntity instanceof Player player) {
                player.displayClientMessage(Component.literal("Out of Ammo"), true);
            }
        }
        super.onUseTick(level, livingEntity, stack, va);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("tooltip.halocraft.ammo", this.getAmmo(itemStack), getMaxAmmo()).withStyle(ChatFormatting.RED));
        components.add(Component.literal("Ammo type: " + this.getAmmoType(itemStack).name()).withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(itemStack, level, components, flag);
    }

    public ItemStack lookForAmmo(Player player, ItemStack gun) {
        int size = player.getInventory().getContainerSize();
        for (int i = 0; i <= size; i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack.is(HaloTags.Items.BULLETS)) {
                if (this.getAmmo(gun) != 0 && !this.getAmmoType(gun).equals(this.getAmmunition(itemStack.getItem()))) {
                    continue;
                }
                return itemStack;
            }
        }
        //if (player.isCreative()) return new ItemStack(HaloItems.BULLET.get());
        return ItemStack.EMPTY;
    }

    public int flashTicks() {
        return 10;
    }


    @Override
    public void inventoryTick(ItemStack gunStack, Level level, Entity entity, int value, boolean devalue) {
        if (entity instanceof Player livingEntity) {
            if (livingEntity.getMainHandItem() == gunStack) {
                if (!gunStack.hasTag()) {
                    this.setAmmoType(gunStack, BulletType.NONE);
                }
                if (this.getAmmo(gunStack) < this.getMaxAmmo()) {
                    if (HaloKeys.getKey(2)) {
                        HaloCraft.sendMSGToServer(new HaloKeys(livingEntity.getId(), 2));
                        //check if player is creative
                        if (livingEntity.getAbilities().instabuild) {
                            this.reloadGun(livingEntity, gunStack);
                        } else {
                            ItemStack ammoStack = lookForAmmo(livingEntity, gunStack);
                            if (ammoStack != ItemStack.EMPTY) {
                                if (getAmmunition(ammoStack.getItem()).equals(BulletType.NONE)) return;
                                this.setAmmoType(gunStack, getAmmunition(ammoStack.getItem()));
                                if (ammoStack.getCount() < this.getMaxAmmo()) return;
                                ammoStack.shrink(this.getMaxAmmo() - this.getAmmo(gunStack));
                                this.reloadGun(livingEntity, gunStack);
                            }
                        }
                    }
                }
            }
            this.isReloading = livingEntity.getCooldowns().isOnCooldown(this);
            if (this.isShooting) {
                this.shootingTicks++;
                if (this.shootingTicks >= this.flashTicks()) {
                    this.isShooting = false;
                    this.shootingTicks = 0;
                }
            }
        }
        super.inventoryTick(gunStack, level, entity, value, devalue);
    }

    public BulletType getAmmunition(Item item) {
        return item instanceof BulletItem bulletItem ? bulletItem.getType() : BulletType.NONE;
    }
}
