package com.harby.halocraft.HaloBlocks;

import com.harby.halocraft.HaloEntities.Projectiles.BeamEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeamEmitter extends Block {
    public BeamEmitter() {
        super(BlockBehaviour.Properties.of().strength(-1).randomTicks());
    }

    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);


    public VoxelShape getShape(BlockState p_154699_, BlockGetter p_154700_, BlockPos p_154701_, CollisionContext p_154702_) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource randomSource) {
        super.randomTick(state, serverLevel, pos, randomSource);
        BeamEntity beam =  new BeamEntity(serverLevel);
        beam.moveTo(pos.getX() +0.5,pos.getY(),pos.getZ()+0.5);
        beam.setDeltaMovement(new Vec3(0,0.1,0));
        serverLevel.addFreshEntity(beam);
    }
}
