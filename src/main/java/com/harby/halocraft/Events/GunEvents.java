package com.harby.halocraft.Events;

import com.harby.halocraft.HaloItems.Gun;
import com.harby.halocraft.HaloItems.ScopeGun;
import com.harby.halocraft.core.projectiles.AmmoTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class GunEvents extends PlayerEvent {
    private final Gun gun;
    private final InteractionHand hand;
    public GunEvents(Player player, Gun gun,InteractionHand hand) {
        super(player);
        this.gun = gun;
        this.hand = hand;
    }

    public Gun getGun(){
        return this.gun;
    }
    public String getDisplayName(){
       return this.gun.getDescriptionId();
    }
    public int getMaxAmmunition(){
        return this.gun.getMaxAmmo();
    }
    public int shootingDelay(){
        return this.gun.getShootingDelay();
    }
    public boolean isTwoHanded(){
        return this.gun.twoHands();
    }
    public float getProjectileSpeed(){
        return this.gun.getSpeed();
    }
    public float getProjectileDamage(){
        return this.gun.getDamage();
    }
    public AmmoTypes getAmmoType(){
        return this.gun.getAmmoType();
    }

    public InteractionHand getHand(){
        return this.hand;
    }
    public static class GunShootingEvent extends GunEvents{
        public GunShootingEvent(Player player, Gun gun, InteractionHand hand) {
            super(player, gun, hand);
        }
        public boolean isShooting(){
            return this.getGun().isShooting();
        }
    }
    public static class GunScopeEvent extends GunEvents{
        private final ScopeGun gun;
        public GunScopeEvent(Player player, Gun gun, InteractionHand hand) {
            super(player, gun, hand);
            this.gun = (ScopeGun) gun;
        }

        public boolean isScoping(){
            return gun.isScoping(this.getEntity().getItemInHand(this.getHand()));
        }
    }
    public static class GunStopScopingEvent extends GunEvents{
        private final ScopeGun gun;
        public GunStopScopingEvent(Player player, Gun gun, InteractionHand hand) {
            super(player, gun, hand);
            this.gun = (ScopeGun) gun;
        }

        public boolean isScoping(){
            return !gun.isScoping(this.getEntity().getItemInHand(this.getHand()));
        }
    }


}
