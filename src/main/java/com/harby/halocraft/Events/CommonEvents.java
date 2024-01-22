package com.harby.halocraft.Events;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Alien.Grunt;
import com.harby.halocraft.HaloItems.HaloBaseArmor;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.HaloKeybinds;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HaloCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HaloEntities.GRUNT.get(), Grunt.createAttributes().build());
    }



    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(HaloKeybinds.RELOAD_KEY);
        event.register(HaloKeybinds.SHOOTING_KEY);
    }
    @SubscribeEvent
    public void hurtArmor(LivingDamageEvent hurtEvent){
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
