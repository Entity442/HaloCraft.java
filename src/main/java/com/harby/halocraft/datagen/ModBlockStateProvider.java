package com.harby.halocraft.datagen;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.core.HaloBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HaloCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegistryObject<Block> blockRegistryObject : HaloBlocks.BLOCKS.getEntries()) {
            if (ConfigDataGenerator.excludesBlocksGenerate.contains(blockRegistryObject)) {
                continue;
            }
            blockWithItem(blockRegistryObject);
        }

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
