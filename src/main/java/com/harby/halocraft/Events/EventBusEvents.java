package com.harby.halocraft.Events;

import com.harby.halocraft.HaloItems.HaloBaseArmor;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventBusEvents {
    @SubscribeEvent
    public static void hurtArmor(LivingDamageEvent hurtEvent){
        if (hurtEvent.getEntity() instanceof Player player){
            for (int i: Inventory.ALL_ARMOR_SLOTS){
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.getItem() instanceof HaloBaseArmor){
                    stack.hurtAndBreak(1,player, (p_35997_) -> {
                        p_35997_.broadcastBreakEvent(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i));
                    });
                }
            }
        }
    }
}
