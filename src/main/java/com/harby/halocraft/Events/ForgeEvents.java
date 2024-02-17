package com.harby.halocraft.Events;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.ScopeGun;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HaloCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    @SubscribeEvent
    public static void ComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if (!Minecraft.getInstance().options.getCameraType().isFirstPerson()) return;
        Player player = event.getPlayer();
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        if (stack.getItem() instanceof ScopeGun gun && gun.isScoping(stack)) {
            event.setNewFovModifier(gun.getFovModifier());
        } else {
            event.setNewFovModifier(1.0f);
        }
    }
}
