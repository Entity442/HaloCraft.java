package com.harby.halocraft.core;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class HaloItems {
    public  static  final List<Item> HALO_ITEMS = new ArrayList<>();
    public  static  final List<Gun> GUNS_ITEMS = new ArrayList<>();

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, HaloCraft.MODID);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public  static final RegistryObject<Item> BULLET = ITEMS.register("bullet",
            () -> new ItemBase( new Item.Properties()));
    public  static final RegistryObject<Item> EXPLOSIVE_BULLET = ITEMS.register("explosive_bullet",
            () -> new ItemBase( new Item.Properties()));
    public  static final RegistryObject<Item> FIRE_BULLET = ITEMS.register("fire_bullet",
            () -> new ItemBase( new Item.Properties()));
    public  static final RegistryObject<Item> FROZEN_BULLET = ITEMS.register("frozen_bullet",
            () -> new ItemBase( new Item.Properties()));
    public  static final RegistryObject<Item> PENETRATING_BULLET = ITEMS.register("penetrating_bullet",
            () -> new ItemBase( new Item.Properties()));


    public  static final RegistryObject<Item> ASSAULT_RIFFLE = ITEMS.register("assault_riffle",
            () -> new AssaultRifle( new Item.Properties()));
    public  static final RegistryObject<Item> SNIPER_RIFFLE = ITEMS.register("sniper_riffle",
            () -> new SniperRiffle( new Item.Properties(),false));
    public  static final RegistryObject<Item> GOLDEN_SNIPER_RIFFLE = ITEMS.register("golden_sniper_riffle",
            () -> new SniperRiffle( new Item.Properties(),true));


    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemBase(block.get(), new Item.Properties()));
    }
}
