package com.harby.halocraft.HaloItems;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

public class EnergieSword extends SwordItem {

    public EnergieSword(Properties properties) {
        super(Tiers.DIAMOND, 3, -2.4F, properties);
        HaloItems.HALO_ITEMS.add(this);
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 20;//20 ticks = 1s
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (isOff(pPlayer.getItemInHand(pUsedHand)))
            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    public @NotNull UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (!entity.level().isClientSide() && count < 1) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, 1, true, false, true));
            entity.hurtMarked = true;
            entity.knockback(2.5F, -entity.getLookAngle().x, -entity.getLookAngle().z);
            if (!((Player) entity).isCreative()) {
                stack.hurtAndBreak(1, entity, (player) -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            if (player.getMainHandItem() == pStack) {
                if (HaloKeys.getKey(2) && !player.isUsingItem() && !player.getCooldowns().isOnCooldown(this)) {
                    HaloCraft.sendMSGToServer(new HaloKeys(player.getId(), 2));
                    player.getCooldowns().addCooldown(this, 5);
                    setOff(pStack, !isOff(pStack));
                }
            }
            if (!isOff(pStack) && !player.isCreative()) {
                if (pStack.getDamageValue() >= pStack.getMaxDamage() - 1)
                    pStack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                pStack.setDamageValue(pStack.getDamageValue() + 1);
            }
        }
    }

    public boolean isOff(ItemStack pStack) {
        if (!pStack.getOrCreateTag().contains("off")) {
            pStack.getOrCreateTag().putBoolean("off", true);
        }
        return pStack.getTag().getBoolean("off");
    }

    public void setOff(ItemStack pStack, boolean isOff) {
        pStack.getOrCreateTag().putBoolean("off", isOff);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;
    }

    @Override
    public boolean canPerformAction(ItemStack pStack, ToolAction toolAction) {
        return !isOff(pStack) && ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }


}
