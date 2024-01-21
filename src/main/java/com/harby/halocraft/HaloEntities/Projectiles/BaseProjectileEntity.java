package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.core.HaloTags;
import com.harby.halocraft.core.projectiles.AmmoList;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import com.harby.halocraft.core.projectiles.BaseAmmo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public abstract class BaseProjectileEntity extends Projectile {
    private static final EntityDataAccessor<String> TYPE_AMMO_DATA =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> TYPE_FLIGHT_DURATION =
            SynchedEntityData.defineId(BaseProjectileEntity.class, EntityDataSerializers.INT);
    private float setBaseDamage;

    public BaseProjectileEntity(Level level, Entity livingEntity, AmmoList ammo, EntityType<? extends BaseProjectileEntity> entityType) {
        super(entityType, level);
        this.setOwner(livingEntity);
        BlockPos blockpos = livingEntity.blockPosition();
        double d0 = (double) blockpos.getX() + 0.5D;
        double d1 = (double) blockpos.getY() + 1.5D;
        double d2 = (double) blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, livingEntity.getYRot(), livingEntity.getXRot());
        this.setProjectileType(ammo);
    }

    public BaseProjectileEntity(Level level, EntityType<? extends BaseProjectileEntity> entityType) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE_AMMO_DATA, AmmoList.NONE.name());
        this.entityData.define(TYPE_FLIGHT_DURATION, 300);
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
        return entity != getOwner();
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
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!level().isClientSide) {
            boolean flag = false;
            this.noPhysics = false;
            BlockState state = this.level().getBlockState(result.getBlockPos());
            if (state.is(HaloTags.Blocks.SHOOTING_THROUGH)) {
                this.noPhysics = true;
                flag = true;
            }
            if (state.is(HaloTags.Blocks.BREAK_ON_SHOOT)) {
                level().destroyBlock(result.getBlockPos(), true, this.getOwner());
                flag = true;
            }
            if (flag) {
                this.discard();
            } else {
                BaseAmmo projectile = this.getProjectile().get();
                projectile.onHitBlock(this, result);
            }
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
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = projectile.moveX(this.getX(), vec3.x);
        double d1 = projectile.moveY(this.getY(), vec3.y);
        double d2 = projectile.moveZ(this.getZ(), vec3.z);
        this.setPos(d0, d1, d2);

        projectile.onMove(this);
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
        }
    }
}
