package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.core.HaloParticles;
import com.harby.halocraft.core.HaloTags;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public abstract class BaseProjectileEntity extends Projectile {
    private static final EntityDataAccessor<String> TYPE_AMMO_DATA =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> TYPE_FLIGHT_DURATION =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Vector3f> SHOUTED_POS =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.VECTOR3);
    private static final EntityDataAccessor<Vector3f> SHOUTED_DIRECTION =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.VECTOR3);
    private float setBaseDamage;

    public BaseProjectileEntity(Level level, Entity livingEntity, AmmoList ammo, EntityType<? extends BaseProjectileEntity> entityType, double velocity) {
        super(entityType, level);
        this.setOwner(livingEntity);
        this.setPos(livingEntity.getEyePosition());
        this.setProjectileType(ammo);
        this.setShoutedPos(livingEntity.getEyePosition());
        this.setShoutedDirection(-livingEntity.getYRot(), -livingEntity.getXRot(), velocity);
        this.reapplyPosition();
        this.level().addParticle(HaloParticles.PLASMA_TRAIL.get(), this.getX()+1, this.getY(), this.getZ(), 3, 1, 1);
        //this.mo
        //this.setDeltaMovement(getProjectile().get().movement(this.getPosition(0), this.getShoutedPos(), this.getShoutedDirection(), this.tickCount));
    }

    public BaseProjectileEntity(Level level, EntityType<? extends BaseProjectileEntity> entityType) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE_AMMO_DATA, AmmoList.NONE.name());
        this.entityData.define(TYPE_FLIGHT_DURATION, 300);
        this.entityData.define(SHOUTED_POS, new Vector3f(0, 0, 0));
        this.entityData.define(SHOUTED_DIRECTION, new Vector3f(0, 0, 0));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setProjectileType(AmmoList.valueOf(tag.getString("bullet_type")));
        this.setFlightDuration(tag.getInt("flight_duration"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putString("bullet_type", this.getProjectile().name());
        tag.putInt("flight_duration", this.getFlightDuration());
        CompoundTag tagPos = new CompoundTag();
        tagPos.putDouble("X", this.getShoutedPos().x);
        tagPos.putDouble("Y", this.getShoutedPos().y);
        tagPos.putDouble("Z", this.getShoutedPos().z);
        tag.put("pos", tagPos);
        CompoundTag tagDirection = new CompoundTag();
        tagDirection.putDouble("X", this.getShoutedPos().x);
        tagDirection.putDouble("Y", this.getShoutedPos().y);
        tagDirection.putDouble("Z", this.getShoutedPos().z);
        tag.put("direction", tagDirection);
    }

    private void setShoutedPos(Vec3 pos) {
        this.entityData.set(SHOUTED_POS, new Vector3f((float) pos.x, (float) pos.y, (float) pos.z));
    }

    public Vec3 getShoutedPos() {
        return new Vec3(this.entityData.get(SHOUTED_POS));
    }

    private void setShoutedDirection(double rotX, double rotY, double velocity) {
        this.entityData.set(SHOUTED_DIRECTION, new Vector3f(
                (float) (Math.sin(Math.toRadians(rotX)) * velocity * Math.cos(Math.toRadians(rotY))), (float) (Math.sin(Math.toRadians(rotY)) * velocity), (float) (Math.cos(Math.toRadians(rotX)) * velocity * Math.cos(Math.toRadians(rotY)))));
    }

    public Vec3 getShoutedDirection() {
        return new Vec3(this.entityData.get(SHOUTED_DIRECTION));
    }

    private void setProjectileType(AmmoList ammo) {
        this.entityData.set(TYPE_AMMO_DATA, ammo.name());
    }

    public AmmoList getProjectile() {
        return AmmoList.valueOf(this.entityData.get(TYPE_AMMO_DATA));
    }

    public void setFlightDuration(int ticks) {
        this.entityData.set(TYPE_FLIGHT_DURATION, ticks);
    }

    public int getFlightDuration() {
        return this.entityData.get(TYPE_FLIGHT_DURATION);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return entity != getOwner() || !(entity instanceof BaseProjectileEntity);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (this.level().isClientSide()) return;
        if (entityHitResult.getEntity() instanceof BasicVehicleEntity basicVehicle) {
            int damageMultipier = 1;
            if (this.getProjectile() == AmmoList.NORMAL_BULLET) {
                damageMultipier = 2;
            }
            if (this.getProjectile().getType() == AmmoTypes.PLASMA) {
                damageMultipier = 3;
            }
            basicVehicle.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), getDamage() * damageMultipier);
        }
        if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
            BaseAmmo projectile = this.getProjectile().get();
            projectile.onHitEntity(this, entityHitResult);
            livingEntity.hurt(projectile.getDamageSource(this.level().damageSources(), this, (LivingEntity) (this.getOwner())), getDamage());
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (this.level().isClientSide()) return;
        boolean discard = false;
        this.noPhysics = false;
        BlockState state = this.level().getBlockState(result.getBlockPos());
        if (state.is(HaloTags.Blocks.SHOOTING_THROUGH)) {
            this.noPhysics = true;
            discard = true;
        }
        if (state.is(HaloTags.Blocks.BREAK_ON_SHOOT)) {
            level().destroyBlock(result.getBlockPos(), true, this.getOwner());
            this.noPhysics = true;
            discard = true;
        }
        if (discard) {
            //this.discard();
        } else {
            BaseAmmo projectile = this.getProjectile().get();
            projectile.onHitBlock(this, result);
            this.remove(RemovalReason.KILLED);
        }

    }

    public float getDamage() {
        return this.setBaseDamage;
    }

    public void setDamage(float value) {
        this.setBaseDamage = value;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount >= this.getFlightDuration()) {
            this.remove(RemovalReason.DISCARDED);
        }
        BaseAmmo projectile = this.getProjectile().get();
        projectile.onMove(this);
        if (this.level().isClientSide()) return;
        Vec3 posBefore = this.getPosition(0);
        Vec3 posAfter = (projectile.movement(this.getPosition(0), this.getShoutedPos(), this.getShoutedDirection(), this.tickCount));

        HitResult hitresult = this.level().clip(new ClipContext(posBefore, posAfter, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            posAfter = hitresult.getLocation();
        }
        int entityCount = 0;
        while (!this.isRemoved() && hitresult != null && hitresult.getType() != HitResult.Type.MISS && entityCount == 0) {
            EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(this.level(), this, posBefore, posAfter, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
            if (entityhitresult != null) {
                hitresult = entityhitresult;
            }
            if (hitresult.getType() == HitResult.Type.ENTITY) {
                Entity entity = ((EntityHitResult) hitresult).getEntity();
                Entity owner = this.getOwner();
                if (entity instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) entity)) {
                    hitresult = null;
                } else {
                    entityCount++;
                    posAfter = hitresult.getLocation();
                }
            }
            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                if (net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
                    break;
                posAfter = hitresult.getLocation();
            }
            //hitresult = null;
        }
        this.setPos(posAfter);
        if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
            this.onHit(hitresult);
        }
    }
}
