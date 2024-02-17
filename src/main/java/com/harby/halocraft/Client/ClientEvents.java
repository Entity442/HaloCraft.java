package com.harby.halocraft.Client;

import com.harby.halocraft.Client.ArmorLayers.HaloArmorLayer;
import com.harby.halocraft.Client.BlockModels.forrunner_door_model;
import com.harby.halocraft.Client.BlockRenderers.CustomDoorRenderer;
import com.harby.halocraft.Client.Models.*;
import com.harby.halocraft.Client.Renderers.*;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.Message.HandleReloadingModels;
import com.harby.halocraft.Particles.PlasmaParticleTrail;
import com.harby.halocraft.core.HaloBlockEntities;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.HaloOverlay;
import com.harby.halocraft.core.HaloParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = HaloCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    private ClientEvents() {
    }
    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("scope", HaloOverlay.HUD_SCOPE_GUN);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WarthogModel.LAYER_LOCATION, WarthogModel::createBodyLayer);
        event.registerLayerDefinition(GhostModel.LAYER_LOCATION, GhostModel::createBodyLayer);
        event.registerLayerDefinition(BansheModel.LAYER_LOCATION, BansheModel::createBodyLayer);
        event.registerLayerDefinition(PlasmaProjectileModel.LAYER_LOCATION, PlasmaProjectileModel::createBodyLayer);
        event.registerLayerDefinition(F_19Model.LAYER_LOCATION, F_19Model::createBodyLayer);
        event.registerLayerDefinition(GruntModel.LAYER_LOCATION, GruntModel::createBodyLayer);
        event.registerLayerDefinition(PlasmaRing.LAYER_LOCATION, PlasmaRing::createBodyLayer);
        event.registerLayerDefinition(PlasmaRingH.LAYER_LOCATION, PlasmaRingH::createBodyLayer);
        event.registerLayerDefinition(unsc_helmetModel.LAYER_LOCATION, unsc_helmetModel::createBodyLayer);



        event.registerLayerDefinition(forrunner_door_model.LAYER_LOCATION, forrunner_door_model::createBodyLayer);
    }


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(HaloEntities.CAR.get(), WarthogRenderer::new);
        event.registerEntityRenderer(HaloEntities.GHOST.get(), GhostRenderer::new);
        event.registerEntityRenderer(HaloEntities.BANSHE.get(), BansheRenderer::new);
        event.registerEntityRenderer(HaloEntities.F29.get(), F_19Renderer::new);
        event.registerEntityRenderer(HaloEntities.BULLET.get(), BulletRenderer::new);
        event.registerEntityRenderer(HaloEntities.PLASMA_BALL.get(), PlasmaProjectileRenderer::new);
        event.registerEntityRenderer(HaloEntities.BEAM.get(), BeamRenderer::new);

        event.registerEntityRenderer(HaloEntities.GRUNT.get(), GruntRenderer::new);


        event.registerBlockEntityRenderer(HaloBlockEntities.CUSTOM_DOOR.get(), new CustomDoorRenderer());
    }

    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(HaloParticles.PLASMA_TRAIL.get(),
                PlasmaParticleTrail.MobProvider::new);
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        HandleReloadingModels.addReloading();
    }

    @SubscribeEvent
    public static void addLayers(final EntityRenderersEvent.AddLayers event) {
        event.getSkins().forEach(name -> {
            if(event.getSkin(name) instanceof PlayerRenderer renderer) {
                renderer.addLayer(new HaloArmorLayer<>(renderer));
            }
        });
    }
}
