package com.harby.halocraft.HaloItems;

import com.google.common.base.Predicate;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

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

    public static final Predicate<ItemStack> AMMO = (stack) -> {
        return stack.getItem() instanceof BulletItem;
    };

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player livingEntity, InteractionHand hand) {
        ItemStack itemstack = livingEntity.getItemInHand(hand);
        if (twoHands() && isTwoHandAvailable(livingEntity)){
            return InteractionResultHolder.fail(itemstack);
        }
        livingEntity.awardStat(Stats.ITEM_USED.get(this));
        livingEntity.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }


    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 72000;
    }

    public abstract int getShootingDelay();
    public abstract boolean twoHands();

    public abstract void shotProjectile(Level level, LivingEntity livingEntity,ItemStack stack);

    public abstract int getMaxAmmo();

    public abstract int getWeaponReloadCooldown();

    public void reloadGun(Player player,ItemStack stack){
        player.getCooldowns().addCooldown(this, this.getWeaponReloadCooldown());
        this.setAmmo(stack,getMaxAmmo());
    }

    public boolean isReloading(){
        return this.isReloading;
    }
    public boolean isShooting(){
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

    public int getAmmoType(ItemStack stack) {
        CompoundTag compoundtag = stack.getTag();
        return compoundtag != null ? compoundtag.getInt("type") : 0;
    }

    public void setAmmoType(ItemStack stack, int value) {
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.putInt("type", value);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int va) {
        if (va % this.getShootingDelay() != 0){
            return;
        }
        if (this.getAmmo(stack) > 0){
            this.isShooting = true;
            this.shotProjectile(level,livingEntity,stack);
            this.setAmmo(stack,this.getAmmo(stack)-1);
            livingEntity.playSound(SoundEvents.FIREWORK_ROCKET_BLAST, 1.0F, 1.0F);
        }else{
            if (livingEntity instanceof Player player){
                player.displayClientMessage(Component.literal("Out of Ammo"),true);
            }
        }
        super.onUseTick(level, livingEntity, stack, va);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.literal("Ammo: " + this.getAmmo(itemStack) + "/" + getMaxAmmo()).withStyle(ChatFormatting.RED));
        components.add(Component.literal("Ammo type: ").withStyle(ChatFormatting.YELLOW));
        super.appendHoverText(itemStack, level, components, flag);
    }

    public ItemStack lookForAmmo(Player player){
        int size = player.getInventory().getContainerSize();
        for (int i = 0;i <= size;i++){
            ItemStack itemStack = player.getInventory().getItem(i);
            if (AMMO.test(itemStack)){
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }
    public int flashTicks(){
        return 10;
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int value, boolean devalue) {
        if (entity instanceof Player livingEntity){
            if (livingEntity.getMainHandItem() == stack){
                if (this.getAmmo(stack) < this.getMaxAmmo()){
                    if (HaloKeys.getKey(2) && isTwoHandAvailable(livingEntity)){
                        HaloCraft.sendMSGToServer(new HaloKeys(livingEntity.getId(), 2));
                        if (livingEntity.getAbilities().instabuild){
                            this.reloadGun(livingEntity,stack);
                        }else {
                            ItemStack stack1 = lookForAmmo(livingEntity);
                            if (stack1 != ItemStack.EMPTY){
                                stack1.shrink(1);
                                this.setAmmoType(stack,getAmmunition(stack1.getItem()));
                                this.reloadGun(livingEntity,stack);
                            }
                        }
                    }
                }
            }
            this.isReloading = livingEntity.getCooldowns().isOnCooldown(this);
            if (this.isShooting){
                this.shootingTicks++;
                if (this.shootingTicks >= this.flashTicks()){
                    this.isShooting = false;
                    this.shootingTicks = 0;
                }
            }
            if (twoHands() && isTwoHandAvailable(livingEntity)){
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1, 2, false, false, false));
            }
        }
        super.inventoryTick(stack, level, entity, value, devalue);
    }

    public int getAmmunition(Item item){
        if (AmmoTypes.BASE_BULLET.compareItem(item)){
            return AmmoTypes.BASE_BULLET.getValue();
        }if (AmmoTypes.FIRE_BULLET.compareItem(item)){
            return AmmoTypes.FIRE_BULLET.getValue();
        }if (AmmoTypes.PENETRATING_BULLET.compareItem(item)){
            return AmmoTypes.PENETRATING_BULLET.getValue();
        }if (AmmoTypes.EXPLOSIVE_BULLET.compareItem(item)){
            return AmmoTypes.EXPLOSIVE_BULLET.getValue();
        }if (AmmoTypes.FROZEN_BULLET.compareItem(item)){
            return AmmoTypes.FROZEN_BULLET.getValue();
        }
        return 0;
    }

    public boolean isTwoHandAvailable(Player pPlayer){
        return (pPlayer.getItemInHand(InteractionHand.MAIN_HAND) != ItemStack.EMPTY && pPlayer.getItemInHand(InteractionHand.OFF_HAND) != ItemStack.EMPTY);
    }
}
