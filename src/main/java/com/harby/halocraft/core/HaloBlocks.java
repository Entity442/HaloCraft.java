package com.harby.halocraft.core;

import com.harby.halocraft.HaloBlocks.BeamEmitter;
import com.harby.halocraft.HaloBlocks.CustomDoor;
import com.harby.halocraft.HaloBlocks.LinedRoad;
import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloItems.BlockItemBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class HaloBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, HaloCraft.MODID);

    /**
     * Registers a block and his item
     *
     * @param name  the name of the block to be registered
     * @param block the supplier function that creates the block object
     * @return the registered block object
     */
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        HaloItems.ITEMS.register(name, () -> new BlockItemBase(toReturn.get(), new Item.Properties()));
        return toReturn;
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


    public static final RegistryObject<Block> CONCRETE_BRICK = registerBlock("concrete_brick", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_BRICK_POLISHED = registerBlock("concrete_brick_polished", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_LAYERED = registerBlock("concrete_layered", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_LAYERED_SECOND = registerBlock("concrete_layered_second", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> ASPHALT = registerBlock("asphalt", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> CONCRETE_YELLOW_DOUBLE = registerBlock("concrete_yellow_double", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> WHITE_LINE = registerBlock("white_line", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> WHITE_LINE_DOTS = registerBlock("white_line_dot", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_DOUBLE = registerBlock("yellow_double", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_DOUBLE_DOT = registerBlock("yellow_double_dot", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> YELLOW_LINE = registerBlock("yellow_line", () -> new LinedRoad(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));


    public static final RegistryObject<Block> DENSE_ASTEROID = registerBlock("dense_asteroid", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 6f).sound(SoundType.STONE)));
    public static final RegistryObject<Block> ASTEROID = registerBlock("asteroid", () -> new Block(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));

    public static final RegistryObject<Block> BEAM_EMITTER = registerBlock("beam_emitter", BeamEmitter::new);

    public static final RegistryObject<Block> CUSTOM_DOOR = registerBlock("custom_door", () -> new CustomDoor(BlockBehaviour.Properties.of().strength(2f, 4f).sound(SoundType.STONE)));

}
