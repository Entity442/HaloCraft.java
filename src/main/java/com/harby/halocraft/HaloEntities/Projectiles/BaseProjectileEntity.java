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
    }
    public BaseProjectileEntity(Level level, Entity livingEntity, AmmoList ammo, EntityType<? extends BaseProjectileEntity> entityType, double velocity, Vec3 shoutedPos) {
        super(entityType, level);
        this.setOwner(livingEntity);
        this.setPos(shoutedPos);
        this.setProjectileType(ammo);
        this.setShoutedPos(livingEntity.getEyePosition());
        this.setShoutedDirection(-livingEntity.getYRot(), -livingEntity.getXRot(), velocity);
        this.reapplyPosition();
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
    protected boolean canHitEntity(@NotNull Entity entity) {
        return entity != getOwner() || !(entity instanceof BaseProjectileEntity);
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
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
    protected void onHitBlock(@NotNull BlockHitResult result) {
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
        if (!discard) {
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

        /*HitResult hitresult = this.entityHit(posBefore, posAfter);
        if (hitresult == null || hitresult.getType() == HitResult.Type.MISS) {
            hitresult = blockHit(posBefore, posAfter);
        }
        if (hitresult == null || hitresult.getType() != HitResult.Type.MISS) {
           posAfter = hitresult.getLocation();
        }
        this.setPos(posAfter);
        this.reapplyPosition();
        if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
            this.onHit(hitresult);
        }*/
        HitResult entityHit = this.entityHit(posBefore, posAfter);
        HitResult blockHit = this.blockHit(posBefore, posAfter);
        //HaloCraft.LOGGER.info(entityHit.getType() + " " + blockHit.getType());
        if (entityHit.getType() == HitResult.Type.MISS && blockHit.getType() == HitResult.Type.MISS) return;
        if (entityHit.getType() == HitResult.Type.MISS && blockHit.getType() != HitResult.Type.MISS) {
            BlockPos blockpos = BlockPos.containing(blockHit.getLocation());
            this.setPos(blockHit.getLocation());
            this.reapplyPosition();
            this.onHit(blockHit);
            //this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
        if (blockHit.getType() == HitResult.Type.MISS && entityHit.getType() != HitResult.Type.MISS) {
            BlockPos blockpos = BlockPos.containing(entityHit.getLocation());
            this.setPos(entityHit.getLocation());
            this.reapplyPosition();
            this.onHit(entityHit);
            //this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, (BlockState) null));
        }
        if (entityHit.getLocation().distanceTo(posBefore) < blockHit.getLocation().distanceTo(posBefore)) {
            BlockPos blockpos = BlockPos.containing(entityHit.getLocation());
            this.setPos(entityHit.getLocation());
            this.reapplyPosition();
            this.onHit(entityHit);
            //this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, (BlockState) null));
        } else {
            BlockPos blockpos = BlockPos.containing(blockHit.getLocation());
            this.setPos(blockHit.getLocation());
            this.reapplyPosition();
            this.onHit(blockHit);
            //this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
    }

    @Override
    public void lerpMotion(double pX, double pY, double pZ) {
        super.lerpMotion(pX, pY, pZ);
        BaseAmmo projectile = this.getProjectile().get();
        if (projectile == null) return;
        Vec3 posAfter = (projectile.movement(this.getPosition(0), this.getShoutedPos(), this.getShoutedDirection(), this.tickCount));
        this.setPos(posAfter);
    }

    private HitResult blockHit(Vec3 posBefore, Vec3 posAfter) {
        return this.level().clip(new ClipContext(posBefore, posAfter, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
    }

    private HitResult entityHit(Vec3 posBefore, Vec3 posAfter) {
        boolean isHit = false;
        EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(this.level(), this, posBefore, posAfter, this.getBoundingBox().expandTowards(posAfter.add(posBefore.reverse())).inflate(1.0D), this::canHitEntity);
        if (entityhitresult == null) {
            return new HitResult(posAfter) {
                @Override
                public @NotNull Type getType() {
                    return Type.MISS;
                }
            };
        }
        isHit = entityhitresult.getType() == HitResult.Type.ENTITY;
        if (!isHit) {
            return new HitResult(posAfter) {
                @Override
                public @NotNull Type getType() {
                    return Type.MISS;
                }
            };
        }
        if (entityhitresult instanceof EntityHitResult) {
            Entity entity = entityhitresult.getEntity();
            Entity owner = this.getOwner();
            if (entity instanceof BaseProjectileEntity) {
                return new HitResult(posAfter) {
                    @Override
                    public @NotNull Type getType() {
                        return Type.MISS;
                    }
                };
            }
            if (entity instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) entity)) {
                entityhitresult = null;
                isHit = false;
            }
        }
        if (entityhitresult != null && isHit /*&& net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, entityhitresult)*/) {
            return entityhitresult;
        }
        return new HitResult(posAfter) {
            @Override
            public @NotNull Type getType() {
                return Type.MISS;
            }
        };
    }
}
