package com.harby.halocraft.HaloEntities.BaseClasses;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.Vehicles.Ghost;
import com.harby.halocraft.Message.HaloKeys;
import com.harby.halocraft.Message.KeyBindList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class BasicVehicleEntity extends Entity {
    private static final EntityDataAccessor<Float> ACCELERATION = SynchedEntityData.defineId(BasicVehicleEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DAMAGE_LEVEL = SynchedEntityData.defineId(BasicVehicleEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> TINT = SynchedEntityData.defineId(Ghost.class, EntityDataSerializers.INT);
    private int lSteps;
    private double lx;
    private double ly;
    private double lz;
    private double lyr;
    private double lxr;
    private double lxd;
    private double lyd;
    private double lzd;
    private Vec3 movementVector = Vec3.ZERO;


    private int controlUpTicks = 0;
    private int controlDownTicks = 0;
    private int turnRightTicks = 0;
    private int turnLeftTicks = 0;

    public BasicVehicleEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;

    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ACCELERATION, 0.0F);
        this.entityData.define(DAMAGE_LEVEL, 0.0F);
        this.entityData.define(TINT, 0);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setDamageLevel(tag.getFloat("damage"));
        this.setAcceleration(tag.getFloat("acceleration"));
        this.setTint(tag.getInt("tint"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("damage",this.getDamageLevel());
        tag.putFloat("acceleration",this.getAccelerationLevel());
        tag.putInt("tint",this.getTint());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this, this.getId());
    }

    public void setTint(int i){
        this.entityData.set(TINT,i);
    }
    public int getTint(){
        return this.entityData.get(TINT);
    }
    public float getDamageLevel(){
        return entityData.get(DAMAGE_LEVEL);
    }
    public void setDamageLevel(float f){
        this.entityData.set(DAMAGE_LEVEL,f);
    }
    public float getAccelerationLevel(){
        return entityData.get(ACCELERATION);
    }
    public void setAcceleration(float f){this.entityData.set(ACCELERATION,f);}
    public float getMaxVehicleHealth(){
        return 40.0F;
    }
    public float getTopSpeed(){
        return 0.65F;
    }
    public float speed(){return 0.8F;}
    public float waterSpeed(){return 0.1F;}
    public float flyingSpeed(){return 0.1F;}


    public boolean hurt(DamageSource source, float value) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!this.level().isClientSide && !this.isRemoved()) {
            this.setDamageLevel(this.getDamageLevel() + value);
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
            this.playSound(SoundEvents.METAL_BREAK);
            boolean flag = source.getEntity() instanceof Player && ((Player)source.getEntity()).getAbilities().instabuild;
            if (flag || this.getDamageLevel() > this.getMaxVehicleHealth()) {
                if (!flag && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    this.spawnAtLocation(this.getDropItem());
                }

                this.discard();
            }

            return true;
        } else {
            return true;
        }
    }
    public boolean isWaterVehicle(){
        return false;
    }
    public boolean isFlyingVehicle(){return false;}
    public boolean isFlyingHoveringVehicle(){return false;}
    public boolean isHoveringVehicle(){
        return false;
    }

    public float setMinFlyingSpeed(){
        return 0.5f;
    }


    public Item getDropItem() {
        return ItemStack.EMPTY.getItem();
    }

    public Vec3 getVector(){
        return this.movementVector;
    }
    public void setVector(Vec3 vec3){
        this.movementVector = vec3;
    }

    @Override
    public boolean isNoGravity() {
        return this.isFlyingHoveringVehicle();
    }

    @Override
    public void tick() {
        super.tick();
        this.xRotO = this.getXRot();
        this.yRotO = Mth.wrapDegrees(this.getYRot());
        float acceleration = this.getAccelerationLevel();
        if (this.isWaterVehicle() || this.isFlyingHoveringVehicle() || this.isFlyingVehicle()){
            if (controlDownTicks > 0) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.08, 0));
                controlDownTicks--;
            } else if (controlUpTicks > 0) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.08, 0));
                controlUpTicks--;
            }
        }
        if (this.level().isClientSide) {
            Player player = (Player) this.getFirstPassenger();
            if (player != null && player.isPassengerOfSameVehicle(this)) {
                if (KeyBindList.UP.isDown() && controlUpTicks < 2) {
                    HaloCraft.sendMSGToServer(new HaloKeys(this.getId(), player.getId(), 0));
                    controlUpTicks = 10;
                }
                if (KeyBindList.FORWARD.isDown() && controlDownTicks < 2) {
                    HaloCraft.sendMSGToServer(new HaloKeys(this.getId(), player.getId(), 1));
                    controlDownTicks = 10;
                }
            }
            if (this.lSteps > 0) {
                double d5 = this.getX() + (this.lx - this.getX()) / (double) this.lSteps;
                double d6 = this.getY() + (this.ly - this.getY()) / (double) this.lSteps;
                double d7 = this.getZ() + (this.lz - this.getZ()) / (double) this.lSteps;
                this.setYRot(Mth.wrapDegrees((float) this.lyr));
                this.setXRot(this.getXRot() + (float) (this.lxr - (double) this.getXRot()) / (float) this.lSteps);
                --this.lSteps;
                this.setPos(d5, d6, d7);
            } else {
                this.reapplyPosition();
            }
        } else {
            if (acceleration < 0.0F) {
                this.setAcceleration(Math.min(0F, acceleration + 0.01F));
            }
            if (acceleration > 0.0F) {
                this.setAcceleration(Math.max(0F, acceleration - 0.01F));
            }
            if (Math.abs(acceleration) > 0) {
                Vec3 vec3 = new Vec3(0, 0, Mth.clamp(acceleration, -0.25F, this.getTopSpeed()) * 0.2F).xRot(-this.getXRot() * ((float) Math.PI / 180F)).yRot(-this.getYRot() * ((float) Math.PI / 180F));
                this.setVector(vec3);
                this.setDeltaMovement(this.getDeltaMovement().add(vec3));
            }

            if (this.isFlyingHoveringVehicle() && this.getFirstPassenger() != null){
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().multiply(flyingSpeed(), flyingSpeed(), flyingSpeed()));
            }
            else if (this.isFlyingVehicle() && this.getFirstPassenger() != null){
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().multiply(flyingSpeed(), flyingSpeed(), flyingSpeed()));
                if (!this.onGround()){
                    Vec3 vec3 = (new Vec3(0.0D, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
                    this.setDeltaMovement(this.getDeltaMovement().add(vec3));
                    if (this.getAccelerationLevel() < this.setMinFlyingSpeed() && this.controlUpTicks <= 0){
                        float fallingV = this.setMinFlyingSpeed() - this.getAccelerationLevel();
                        this.setDeltaMovement(this.getDeltaMovement().add(0,-fallingV,0));
                    }
                }
            }

            else if (this.isInWaterOrBubble() && this.isWaterVehicle()) {
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().multiply(waterSpeed(), waterSpeed(), waterSpeed()));
            }

            else if (this.isHoveringVehicle()) {
                if (this.isInFluidType()){
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 0.1F, 0));
                }else{
                    this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05F, 0));
                }
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().multiply(flyingSpeed(), flyingSpeed(), flyingSpeed()));
            }

            else {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.3F, 0));
                this.move(MoverType.SELF, this.getDeltaMovement().scale(0.9F));
                this.setDeltaMovement(this.getDeltaMovement().multiply(speed(),speed(),speed()));
            }
        }
        float xRotSet = Mth.clamp(-(float) this.getDeltaMovement().y * 2F, -1.0F, 1.0F) * -(float) (180F / (float) Math.PI) * (float) Math.signum(getAccelerationLevel() + 0.01);
        if (turnLeftTicks > 0) {
            turnLeftTicks--;
        }
        if (turnRightTicks > 0) {
            turnRightTicks--;
        }
        this.setXRot(approachRotation(this.getXRot(), Mth.clamp(getDamageLevel() >= 4 ? 0 : xRotSet, -50, 50), 2));

    }

    public static float approachRotation(float current, float target, float max) {
        float f = Mth.wrapDegrees(target - current);
        if (f > max) {
            f = max;
        }

        if (f < -max) {
            f = -max;
        }

        return Mth.wrapDegrees(current + f);
    }


    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (this.isControlledByLocalInstance() && this.lSteps > 0) {
            this.lSteps = 0;
            this.absMoveTo(this.lx, this.ly, this.lz, (float) this.lyr, (float) this.lxr);
        }
    }

    protected boolean canAddPassenger(Entity p_38390_) {
        return this.getPassengers().size() < this.getMaxPassengers();
    }

    protected int getMaxPassengers() {
        return 1;
    }

    @Override
    public void lerpTo(double x, double y, double z, float yr, float xr, int steps, boolean b) {
        this.lx = x;
        this.ly = y;
        this.lz = z;
        this.lyr = yr;
        this.lxr = xr;
        this.lSteps = steps;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    @Override
    public void lerpMotion(double lerpX, double lerpY, double lerpZ) {
        this.lxd = lerpX;
        this.lyd = lerpY;
        this.lzd = lerpZ;
        this.setDeltaMovement(this.lxd, this.lyd, this.lzd);
    }

    private void tickController(Player passenger) {
        if (passenger.xxa != 0) {
            float turn = -Math.signum(passenger.xxa);
            if (turn > 0.0F) {
                turnLeftTicks = 5;
            } else {
                turnRightTicks = 5;
            }
            this.setYRot(this.getYRot() + turn * 2.5f);
        }
        if (passenger.zza != 0) {
            float back = -Math.signum(passenger.zza);
            if (back < 0.0F) {
                this.setAcceleration(Mth.approach(this.getAccelerationLevel(), 1.0F, 0.02F));
            } else {
                this.setAcceleration(Mth.approach(this.getAccelerationLevel(), -0.5F, 0.02F));
            }
        }
    }

    protected void clampRotation(LivingEntity livingEntity) {
        livingEntity.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(livingEntity.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -105.0F, 105.0F);
        livingEntity.yRotO += f1 - f;
        livingEntity.yBodyRotO += f1 - f;
        livingEntity.setYRot(livingEntity.getYRot() + f1 - f);
        livingEntity.setYHeadRot(livingEntity.getYRot());

    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (this.isPassengerOfSameVehicle(passenger) && passenger instanceof LivingEntity living && !this.touchingUnloadedChunk()) {
            clampRotation(living);
            if (passenger instanceof Player) {
                tickController((Player) passenger);
            }

        }
        super.positionRider(passenger, moveFunction);
    }



    public boolean isPickable() {
        return !this.isRemoved();
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }


    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!player.level().isClientSide){
            if (player.isShiftKeyDown()){
                ItemStack stack = player.getItemInHand(hand);
                if (stack.getItem() instanceof DyeItem dyeItem){
                    this.setTint(dyeItem.getDyeColor().getFireworkColor());
                }
            }else{
                player.startRiding(this);
            }
            this.gameEvent(GameEvent.ENTITY_INTERACT);
        }
        return super.interact(player, hand);
    }

    public void onKeyPacket(Entity keyPresser, int type) {
        if (keyPresser.isPassengerOfSameVehicle(this)) {
            if (type == 0) {
                controlUpTicks = 10;
            }
            if (type == 1) {
                controlDownTicks = 10;
            }
        }
    }
}
