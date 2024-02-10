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

    public  static final RegistryObject<TestArmor> TEST = ITEMS.register("test",
            () -> new TestArmor(HaloBaseArmor.Type.HELMET));
    public  static final RegistryObject<TestArmor> TEST2 = ITEMS.register("test2",
            () -> new TestArmor(HaloBaseArmor.Type.CHESTPLATE));
    public  static final RegistryObject<TestArmor> TEST3 = ITEMS.register("test3",
            () -> new TestArmor(HaloBaseArmor.Type.LEGGINGS));
    public  static final RegistryObject<TestArmor> TEST4 = ITEMS.register("test4",
            () -> new TestArmor(HaloBaseArmor.Type.BOOTS));

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

}
