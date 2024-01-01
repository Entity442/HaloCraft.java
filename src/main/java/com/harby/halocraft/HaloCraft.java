package com.harby.halocraft;

import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.core.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HaloCraft.MODID)
public class HaloCraft
{
    public static final String MODID = "halocraft";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final ResourceLocation PACKET_NETWORK_NAME = new ResourceLocation("halocraft:main_channel");
    public static final SimpleChannel NETWORK_WRAPPER = NetworkRegistry.ChannelBuilder
            .named(PACKET_NETWORK_NAME)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    public HaloCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        HaloItems.register(modEventBus);
        HaloBlocks.register(modEventBus);
        HaloCreativeTab.register(modEventBus);
        HaloEntities.register(modEventBus);
        HaloParticles.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HaloConfig.SERVER_SPEC ,HaloCraft.MODID+"_config.toml");
        HaloConfig.loadConfig(HaloConfig.SERVER_SPEC, FMLPaths.CONFIGDIR.get().resolve(HaloCraft.MODID+"_config.toml").toString());
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        int packetsRegistered = 0;
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, HaloKeys.class, HaloKeys::write, HaloKeys::read, HaloKeys::handle);
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        NETWORK_WRAPPER.sendToServer(message);
    }
}
