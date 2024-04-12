package com.harby.halocraft.HaloBlocks;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LinedRoad extends RotatedPillarBlock {
    public LinedRoad(Properties pProperties) {
        super(pProperties);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AXIS, pContext.getNearestLookingDirection().getOpposite().getAxis());
    }
}
