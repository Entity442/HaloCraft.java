package com.harby.halocraft.core;

import com.harby.halocraft.HaloCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HaloCreativeTab {
    public static final DeferredRegister<CreativeModeTab> HALO_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HaloCraft.MODID);

    public static final RegistryObject<CreativeModeTab> HALOCRAFT = HALO_TABS.register("halocraft",
            () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.halocraft.halocraft"))
                    .icon(Items.NETHER_STAR::getDefaultInstance).displayItems((parameters, output) -> {
                        HaloItems.HALO_ITEMS.forEach(item -> output.accept(item.asItem()));
                    }).build()
    );

    public static void register(IEventBus eventBus) {
        HALO_TABS.register(eventBus);
    }
}
