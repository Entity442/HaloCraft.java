package com.harby.halocraft.HaloEntities.AI;

import com.google.common.base.Predicate;
import com.harby.halocraft.HaloEntities.BaseClasses.BasicNpcClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class FollowCommanderGoal extends Goal{
    private static Predicate<LivingEntity> PARTNER_TARGETING;
    private final BasicNpcClass npc;
    private final Class<? extends LivingEntity> desired_partner;

    public FollowCommanderGoal(BasicNpcClass npc, Class<? extends LivingEntity> desired_partner, @Nullable Predicate<LivingEntity> en) {
        PARTNER_TARGETING = en;
        this.npc = npc;
        this.desired_partner = desired_partner;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }


    @Override
    public boolean canUse() {
        if (npc.tickCount % 20 == 0){
            if(npc.getCommander() == null){
                getFreePartner();
                return false;
            }else{
                return npc.getCommander() != null;
            }
        }
        return false;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return npc.getCommander() != null;
    }

    @Override
    public void tick() {
        super.tick();
        if (npc.getCommander() != null){
            npc.getNavigation().moveTo(this.npc.getCommander(),1);
        }
    }

    @Nullable
    private LivingEntity getFreePartner() {
        List<? extends LivingEntity> list = npc.level().getEntitiesOfClass(desired_partner, this.npc.getBoundingBox().inflate(32),PARTNER_TARGETING);
        double d0 = Double.MAX_VALUE;
        for(LivingEntity inf1 : list) {
            if (this.npc.distanceToSqr(inf1) < d0 && inf1 != this.npc) {
                npc.setCommander(inf1);
                d0 = this.npc.distanceToSqr(inf1);
            }
        }
        return null;
    }

}
