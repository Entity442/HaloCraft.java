package com.harby.halocraft.core;

import com.harby.halocraft.HaloCraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class HaloTags {
    public static class Blocks {

        public static final TagKey<Block> SHOOTING_THROUGH = tag("shooting_through");
        public static final TagKey<Block> BREAK_ON_SHOOT = tag("break_on_shoot");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(HaloCraft.MODID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(HaloCraft.MODID, name));
        }
    }
}
