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

    public  static final RegistryObject<BulletItem> BULLET = ITEMS.register("bullet",
            () -> new BulletItem( new Item.Properties()));
    public  static final RegistryObject<BulletItem> EXPLOSIVE_BULLET = ITEMS.register("explosive_bullet",
            () -> new BulletItem( new Item.Properties()));
    public  static final RegistryObject<BulletItem> FIRE_BULLET = ITEMS.register("fire_bullet",
            () -> new BulletItem( new Item.Properties()));
    public  static final RegistryObject<BulletItem> FROZEN_BULLET = ITEMS.register("frozen_bullet",
            () -> new BulletItem( new Item.Properties()));
    public  static final RegistryObject<BulletItem> PENETRATING_BULLET = ITEMS.register("penetrating_bullet",
            () -> new BulletItem( new Item.Properties()));


    public  static final RegistryObject<AssaultRifle> ASSAULT_RIFFLE = ITEMS.register("assault_riffle",
            () -> new AssaultRifle( new Item.Properties()));
    public  static final RegistryObject<SniperRiffle> SNIPER_RIFFLE = ITEMS.register("sniper_riffle",
            () -> new SniperRiffle( new Item.Properties(),false));
    public  static final RegistryObject<SniperRiffle> GOLDEN_SNIPER_RIFFLE = ITEMS.register("golden_sniper_riffle",
            () -> new SniperRiffle( new Item.Properties(),true));
    public static final RegistryObject<EnergieSword> ENERGIE_SWORD = ITEMS.register("energie_sword",
            () -> new EnergieSword( new Item.Properties()));


    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItemBase(block.get(), new Item.Properties()));
    }
}
