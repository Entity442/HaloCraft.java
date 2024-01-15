package com.harby.halocraft.Events;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.SniperRiffle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HaloCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void ComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        System.out.println(player.getName());
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (stack.getItem() instanceof SniperRiffle gun && gun .isScopeing()) {
            event.setNewFovModifier(0.1f);
        } else {
            event.setNewFovModifier(1.0f);
        }
    }
}
