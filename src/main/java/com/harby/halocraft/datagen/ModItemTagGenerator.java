package com.harby.halocraft.datagen;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.core.HaloItems;
import com.harby.halocraft.core.HaloTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, HaloCraft.MODID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(HaloTags.Items.BULLETS)
                .add(HaloItems.BASIC_BULLET.get())
                .add(HaloItems.EXPLOSIVE_BULLET.get())
                .add(HaloItems.FIRE_BULLET.get())
                .add(HaloItems.FROZEN_BULLET.get())
                .add(HaloItems.PENETRATING_BULLET.get());

    }
}
