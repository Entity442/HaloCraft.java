package com.harby.halocraft.datagen;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.core.HaloBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
            if (ConfigDataGenerator.excludesBlocksGenerate.contains(blockRegistryObject.get())) {
                continue;
            }
            if (blockRegistryObject.get() instanceof RotatedPillarBlock rpBlock) {
                rotatedPillarBlockWithItem(rpBlock);
            } else {
                blockWithItem(blockRegistryObject.get());
            }
        }

    }

    private void blockWithItem(Block blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject, cubeAll(blockRegistryObject));
    }

    private void rotatedPillarBlockWithItem(RotatedPillarBlock blockRegistryObject) {
        ResourceLocation texture = blockTexture(blockRegistryObject);
        HaloCraft.LOGGER.info(name(blockRegistryObject));
        axisBlock(
                blockRegistryObject,
                models().cubeColumnHorizontal(name(blockRegistryObject), texture, texture),
                models().cubeColumn(name(blockRegistryObject), texture, texture)
        );
        itemModels().getBuilder(name(blockRegistryObject)).parent(cubeAll(blockRegistryObject));
    }

    private String name(Block block) {
        return block.getDescriptionId().split("\\.")[2];
    }
}

