package com.harby.halocraft.datagen;

import com.harby.halocraft.core.HaloBlocks;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class ConfigDataGenerator {
    static final ArrayList<RegistryObject<Block>> excludesBlocksGenerate = new ArrayList<>();

    static {
        excludesBlocksGenerate.add(HaloBlocks.BEAM_EMITTER);
    }

    static final ArrayList<RegistryObject<Item>> excludesItemsGenerate = new ArrayList<>();

    static {
        excludesItemsGenerate.add(HaloItems.ASSAULT_RIFFLE);
        excludesItemsGenerate.add(HaloItems.SNIPER_RIFFLE);
        excludesItemsGenerate.add(HaloItems.GOLDEN_SNIPER_RIFFLE);
    }

    static final ArrayList<String> langIndex = new ArrayList<>();

    static {
        langIndex.add("fr_fr");
        langIndex.add("en_us");
        langIndex.add("de_de");
    }
}
