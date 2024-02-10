package com.harby.halocraft.core;

import com.harby.halocraft.HaloBlockEntities.CustomDoorEntity;
import com.harby.halocraft.HaloCraft;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HaloBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HaloCraft.MODID);
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<CustomDoorEntity>> CUSTOM_DOOR =
            BLOCK_ENTITIES.register("custom_door_entity", () ->
                    BlockEntityType.Builder.of(CustomDoorEntity::new,
                            HaloBlocks.CUSTOM_DOOR.get()).build(null));
}
