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
        excludesBlocksGenerate.add(HaloBlocks.CUSTOM_DOOR.get());
    }

    static final ArrayList<Item> excludesItemsGenerate = new ArrayList<>();

    static {
        excludesItemsGenerate.add(HaloItems.ENERGIE_SWORD.get());
        excludesItemsGenerate.addAll(HaloItems.GUNS_ITEMS);
        excludesItemsGenerate.add(HaloItems.TEST.get());
        excludesItemsGenerate.add(HaloItems.TEST2.get());
        excludesItemsGenerate.add(HaloItems.TEST3.get());
        excludesItemsGenerate.add(HaloItems.TEST4.get());
    }

    static final ArrayList<String> langIndex = new ArrayList<>();

    static {
        langIndex.add("fr_fr");
        langIndex.add("en_us");
        langIndex.add("de_de");
    }
}
