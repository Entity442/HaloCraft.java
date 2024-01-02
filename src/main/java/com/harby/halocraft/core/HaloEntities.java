package com.harby.halocraft.core;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Alien.Grunt;
import com.harby.halocraft.HaloEntities.Projectiles.BaseBulletEntity;
import com.harby.halocraft.HaloEntities.Projectiles.BeamEntity;
import com.harby.halocraft.HaloEntities.Projectiles.PlasmaProjectileEntity;
import com.harby.halocraft.HaloEntities.Vehicles.Banshe;
import com.harby.halocraft.HaloEntities.Vehicles.CarEntity;
import com.harby.halocraft.HaloEntities.Vehicles.F_19;
import com.harby.halocraft.HaloEntities.Vehicles.Ghost;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HaloEntities {
    public static DeferredRegister<EntityType<?>> HALO_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
            HaloCraft.MODID);
    public static void register(IEventBus eventBus) {
        HALO_ENTITIES.register(eventBus);
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return HALO_ENTITIES.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    public static final RegistryObject<EntityType<Grunt>> GRUNT = HALO_ENTITIES.register("grunt",
            () -> EntityType.Builder.of(Grunt::new, MobCategory.MONSTER).sized(0.6f, 1.9f)
                    .build(new ResourceLocation(HaloCraft.MODID, "grunt").toString()));


    public static final RegistryObject<EntityType<CarEntity>> CAR = HALO_ENTITIES.register("car",
            () -> EntityType.Builder.of((EntityType<CarEntity> p_33002_, Level level) -> new CarEntity(level), MobCategory.MISC).sized(2f, 2f)
                    .build(new ResourceLocation(HaloCraft.MODID, "car").toString()));

    public static final RegistryObject<EntityType<Ghost>> GHOST = HALO_ENTITIES.register("ghost",
            () -> EntityType.Builder.of((EntityType<Ghost> p_33002_, Level level) -> new Ghost(level), MobCategory.MISC).sized(2f, 1.1f)
                    .build(new ResourceLocation(HaloCraft.MODID, "ghost").toString()));
    public static final RegistryObject<EntityType<Banshe>> BANSHE = HALO_ENTITIES.register("banshe",
            () -> EntityType.Builder.of((EntityType<Banshe> p_33002_, Level level) -> new Banshe(level), MobCategory.MISC).sized(2f, 2f)
                    .build(new ResourceLocation(HaloCraft.MODID, "banshe").toString()));
    public static final RegistryObject<EntityType<F_19>> F29 = HALO_ENTITIES.register("f29",
            () -> EntityType.Builder.of((EntityType<F_19> p_33002_, Level level) -> new F_19(level), MobCategory.MISC).sized(2f, 2f)
                    .build(new ResourceLocation(HaloCraft.MODID, "f29").toString()));

    public static final RegistryObject<EntityType<BaseBulletEntity>> BULLET = register("bullet",
            EntityType.Builder.of((EntityType<BaseBulletEntity> p_33002_, Level level) -> new BaseBulletEntity(level), MobCategory.MISC)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));

    public static final RegistryObject<EntityType<BeamEntity>> BEAM = register("beam",
            EntityType.Builder.of((EntityType<BeamEntity> p_33002_, Level level) -> new BeamEntity(level), MobCategory.MISC)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(1f, 1f));


    public static final RegistryObject<EntityType<PlasmaProjectileEntity>> LASER = register("laser",
            EntityType.Builder.of((EntityType<PlasmaProjectileEntity> p_33002_, Level level) -> new PlasmaProjectileEntity(level), MobCategory.MISC)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.4f, 0.4f));

}
