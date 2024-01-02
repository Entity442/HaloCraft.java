package com.harby.halocraft.core;

import com.harby.halocraft.HaloBlocks.BeamEmitter;
import com.harby.halocraft.HaloCraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HaloBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HaloCraft.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    public static final RegistryObject<Block> CONCRETE_BRICK = BLOCKS.register("concrete_brick", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_BRICK_POLISHED = BLOCKS.register("concrete_brick_polished", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_LAYERED = BLOCKS.register("concrete_layered", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_LAYERED_SECOND = BLOCKS.register("concrete_layered_second", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> ASPHALT = BLOCKS.register("asphalt", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_YELLOW_DOUBLE = BLOCKS.register("concrete_yellow_double", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> WHITE_LINE = BLOCKS.register("white_line", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> WHITE_LINE_DOTS = BLOCKS.register("white_line_dot", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_DOUBLE = BLOCKS.register("yellow_double", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_DOUBLE_DOT = BLOCKS.register("yellow_double_dot", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_LINE = BLOCKS.register("yellow_line", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));


    public static final RegistryObject<Block> DENSE_ASTEROID = BLOCKS.register("dense_asteroid", () -> new Block(BlockBehaviour.Properties.of().strength(2f,6f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> ASTEROID = BLOCKS.register("asteroid", () -> new Block(BlockBehaviour.Properties.of().strength(2f,4f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> BEAM_EMITTER = BLOCKS.register("beam_emitter", BeamEmitter::new);

}
