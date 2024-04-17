package com.harby.halocraft.HaloEntities.Alien;

import com.harby.halocraft.HaloEntities.AI.FollowCommanderGoal;
import com.harby.halocraft.HaloEntities.AI.PatrolAroundTheAreaGoal;
import com.harby.halocraft.HaloEntities.BaseClasses.BasicNpcClass;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;

public class Grunt extends BasicNpcClass {
    public Grunt(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 10)
                .add(Attributes.ARMOR, 5)
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.ATTACK_KNOCKBACK, 1);

    }

    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(Grunt.class));
        this.goalSelector.addGoal(2,new MeleeAttackGoal(this,1.1,true));

        this.goalSelector.addGoal(5,new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6,new PatrolAroundTheAreaGoal(this));
        this.goalSelector.addGoal(6,new FollowCommanderGoal(this,BasicNpcClass.class,entity ->{return true;}));
        this.goalSelector.addGoal(7,new RandomStrollGoal(this,1));

    }



}
