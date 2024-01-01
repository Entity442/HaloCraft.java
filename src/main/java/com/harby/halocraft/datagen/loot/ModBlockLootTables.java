package com.harby.halocraft.datagen.loot;

import com.harby.halocraft.core.HaloBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> block : HaloBlocks.BLOCKS.getEntries()) {
            this.dropSelf(block.get());
        }
        //Lootable for Mint
        {
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return HaloBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
