package com.harby.halocraft.Events;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Alien.Grunt;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.HaloKeybinds;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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
}
