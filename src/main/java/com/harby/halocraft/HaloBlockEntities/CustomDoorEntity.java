package com.harby.halocraft.HaloBlockEntities;

import com.harby.halocraft.HaloBlocks.CustomDoor;
import com.harby.halocraft.core.HaloBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomDoorEntity extends BlockEntity {
    public AnimationState openAnimationState = new AnimationState();
    public AnimationState closedAnimationState = new AnimationState();
    private int animationState = 0;
    private boolean is_open;
    public int tick;
    public CustomDoorEntity(BlockPos pPos, BlockState pBlockState) {
        super(HaloBlockEntities.CUSTOM_DOOR.get(), pPos, pBlockState);
    }
    public int getAnimationState(){
        return animationState;
    }

    public void setAnimationTime(int animationState) {
        this.animationState = animationState;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("open",this.getOpen());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.setOpen(tag.getBoolean("open"));
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }


    public static <E extends CustomDoorEntity> void clientTick(Level level, BlockPos pos, BlockState state, E e) {
        ClientTick(level,pos,state,e);
        setTick(e);
    }

    public static <E extends CustomDoorEntity> void serverTick(Level level, BlockPos pos, BlockState state, E e) {
        setTick(e);
    }

    public static void ClientTick(Level level ,BlockPos pos,BlockState state,CustomDoorEntity entity){
        if(entity.getAnimationState() > 0){
            if(state.getBlock() instanceof CustomDoor door){
                entity.openAnimationState.start(entity.tick);
            }

        }
    }

    public static void setTick(CustomDoorEntity entity){
        if (entity.tick < 359){
            entity.tick++;
        }else{
            entity.tick = 0;
        }
    }

    public boolean getOpen(){
        return this.is_open;
    }
    public void setOpen(boolean value){
        this.is_open = value;
    }
}
