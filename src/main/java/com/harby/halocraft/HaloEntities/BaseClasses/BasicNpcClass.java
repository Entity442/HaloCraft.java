package com.harby.halocraft.HaloEntities.BaseClasses;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class BasicNpcClass extends PathfinderMob implements Enemy {
    private static final EntityDataAccessor<BlockPos> PATROL_POSITION = SynchedEntityData.defineId(BasicNpcClass.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> IS_PERSISTENT = SynchedEntityData.defineId(BasicNpcClass.class, EntityDataSerializers.BOOLEAN);
    @Nullable
    private LivingEntity commander;
    @Nullable
    private BlockPos immediateSearchArea;
    public BasicNpcClass(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return this.entityData.get(IS_PERSISTENT);
    }

    public BlockPos getPatrolPos(){
        return entityData.get(PATROL_POSITION);
    }
    public void setPatrolPosition(BlockPos pos){
        this.entityData.set(PATROL_POSITION,pos);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PATROL_POSITION,BlockPos.ZERO);
        this.entityData.define(IS_PERSISTENT,false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("PatrolX",this.getPatrolPos().getX());
        tag.putInt("PatrolY",this.getPatrolPos().getY());
        tag.putInt("PatrolZ",this.getPatrolPos().getZ());
        tag.putBoolean("persistent",this.entityData.get(IS_PERSISTENT));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        int i = tag.getInt("PatrolX");
        int j = tag.getInt("PatrolY");
        int k = tag.getInt("PatrolZ");
        this.setPatrolPosition(new BlockPos(i, j, k));
        this.entityData.set(IS_PERSISTENT,tag.getBoolean("persistent"));
        super.readAdditionalSaveData(tag);
    }

    public void setPersistence(boolean value){
        this.entityData.set(IS_PERSISTENT,value);
    }

    public void setCommander(@Nullable LivingEntity commander) {
        this.commander = commander;
    }
    public LivingEntity getCommander(){
        return this.commander;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!player.level().isClientSide && player.getAbilities().instabuild && player.isShiftKeyDown()){
            this.setPatrolPosition(new BlockPos(this.getBlockX(),this.getBlockY(),this.getBlockZ()));
            player.displayClientMessage(Component.literal(this.getName() + " is now patrolling " + this.getPatrolPos()),true);
            this.gameEvent(GameEvent.ENTITY_INTERACT);
        }
        return super.mobInteract(player, hand);
    }
}
