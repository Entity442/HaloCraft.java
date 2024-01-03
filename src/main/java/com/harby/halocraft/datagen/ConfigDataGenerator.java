package com.harby.halocraft.datagen;

import com.harby.halocraft.core.HaloBlocks;
import com.harby.halocraft.core.HaloItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class ConfigDataGenerator {
    static final ArrayList<Block> excludesBlocksGenerate = new ArrayList<>();

    static {
        excludesBlocksGenerate.add(HaloBlocks.BEAM_EMITTER.get());
    }

    static final ArrayList<Item> excludesItemsGenerate = new ArrayList<>();

    static {
        excludesItemsGenerate.add(HaloItems.ASSAULT_RIFFLE.get());
        excludesItemsGenerate.add(HaloItems.SNIPER_RIFFLE.get());
        excludesItemsGenerate.add(HaloItems.GOLDEN_SNIPER_RIFFLE.get());
        excludesItemsGenerate.add(HaloItems.ENERGIE_SWORD.get());
    }

    static final ArrayList<String> langIndex = new ArrayList<>();

    static {
        langIndex.add("fr_fr");
        langIndex.add("en_us");
        langIndex.add("de_de");
    }
}
