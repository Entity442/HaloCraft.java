package com.harby.halocraft.HaloBlocks;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.HaloEntities.BaseClasses.VehiculeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LinedRoad extends RotatedPillarBlock {
    public LinedRoad(Properties pProperties) {
        super(pProperties);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(AXIS, pContext.getNearestLookingDirection().getOpposite().getAxis());
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);
        if (pLevel.isClientSide) return;
        if (pEntity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new net.minecraft.world.effect.MobEffectInstance(net.minecraft.world.effect.MobEffects.MOVEMENT_SPEED, 1, 1, false, false, false));
        }
        if (pEntity instanceof BasicVehicleEntity v) {
            float amplifier = 1F;
            if (v.getVehiculeTypes() == VehiculeTypes.TIRED) {
                amplifier = 2F;
            }
            if (v.getVehiculeTypes() == VehiculeTypes.HOVERING) {
                amplifier = 1.5F;
            }
            //add here vehicule speed modifier
        }
    }
}
