package com.harby.halocraft.datagen;

import com.harby.halocraft.core.HaloItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class ConfigDataGenerator {
    static final ArrayList<RegistryObject<Block>> excludesBlocksGenerate = new ArrayList<>();

    static {

    }

    static final ArrayList<RegistryObject<Item>> excludesItemsGenerate = new ArrayList<>();

    static {
        excludesItemsGenerate.add(HaloItems.AssaultRifle);
    }

    static final ArrayList<String> langIndex = new ArrayList<>();

    static {
        //langIndex.add("fr_fr");
        //langIndex.add("en_us");
    }
}
