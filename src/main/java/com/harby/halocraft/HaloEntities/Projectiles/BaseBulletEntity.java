package com.harby.halocraft.HaloEntities.Projectiles;

import com.harby.halocraft.HaloCraft;
import com.harby.halocraft.HaloEntities.BaseClasses.BasicVehicleEntity;
import com.harby.halocraft.core.HaloEntities;
import com.harby.halocraft.core.HaloTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BaseBulletEntity extends Projectile {
    private static final EntityDataAccessor<Integer> TYPE =
            SynchedEntityData.defineId(BaseBulletEntity.class, EntityDataSerializers.INT);
    private float setBaseDamage;

    public BaseBulletEntity(Level level) {
        super(HaloEntities.BULLET.get(), level);
    }

    public BaseBulletEntity(Level level, Entity livingEntity) {
        super(HaloEntities.BULLET.get(), level);
        this.setOwner(livingEntity);
        BlockPos blockpos = livingEntity.blockPosition();
        double d0 = (double) blockpos.getX() + 0.5D;
        double d1 = (double) blockpos.getY() + 1.5D;
        double d2 = (double) blockpos.getZ() + 0.5D;
        this.moveTo(d0, d1, d2, livingEntity.getYRot(), this.getXRot());
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setProjectileType(tag.getInt("bullet_type"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putInt("bullet_type", this.getProjectileType());
    }


    public void setProjectileType(int i) {
        this.entityData.set(TYPE, i);
    }

    public int getProjectileType() {
        return this.entityData.get(TYPE);
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
        if (!this.level().isClientSide()) {
            if (entityHitResult.getEntity() instanceof LivingEntity livingEntity) {
                if (this.getProjectileType() == 0) {
                    livingEntity.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), getDamage());
                } else if (this.getProjectileType() == 1) {
                    livingEntity.hurt(this.level().damageSources().explosion(this, this.getOwner()), getDamage());
                    livingEntity.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0f, Level.ExplosionInteraction.NONE);
                } else if (this.getProjectileType() == 2) {
                    livingEntity.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), getDamage());
                    livingEntity.setSecondsOnFire(6);
                } else if (this.getProjectileType() == 3) {
                    livingEntity.hurt(this.level().damageSources().freeze(), getDamage());
                } else if (this.getProjectileType() == 4) {
                    livingEntity.hurt(this.level().damageSources().sonicBoom(this), getDamage());
                }
            } else {
                if (entityHitResult.getEntity() instanceof BasicVehicleEntity basicVehicle) {
                    int i = 1;
                    if (this.getProjectileType() == 1 || this.getProjectileType() == 4) {
                        i = 2;
                    }
                    basicVehicle.hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), getDamage() * i);
                } else {
                    entityHitResult.getEntity().hurt(this.level().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), getDamage());
                }
            }
        } else {
            super.onHitEntity(entityHitResult);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!level().isClientSide) {
            boolean flag = true;
            this.noPhysics = false;
            BlockState state = this.level().getBlockState(result.getBlockPos());
            if (state.is(HaloTags.Blocks.SHOOTING_THROUGH)) {
                this.noPhysics = true;
                flag = false;
            }
            if (state.is(HaloTags.Blocks.BREAK_ON_SHOOT)) {
                float durability = state.getDestroySpeed(level(), result.getBlockPos());
                //if (durability >= 0 && durability <= 1) {
                    level().destroyBlock(result.getBlockPos(), true, this.getOwner());
                //}
            }
            if (flag) {
                this.discard();
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
        if (this.tickCount >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.setPos(d0, d1, d2);

        if (hitresult.getType() != HitResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
            this.onHit(hitresult);
            this.playSound(SoundEvents.FIREWORK_ROCKET_BLAST_FAR, 1.0F, 1.0F);
        }
    }
}
