package com.harby.halocraft.HaloItems;

import com.harby.halocraft.core.HaloItems;
import net.minecraft.world.item.Item;

public enum AmmoTypes {
    BASE_BULLET(HaloItems.BULLET.get(),0),
    EXPLOSIVE_BULLET(HaloItems.EXPLOSIVE_BULLET.get(),1),
    FIRE_BULLET(HaloItems.FIRE_BULLET.get(),2),
    FROZEN_BULLET(HaloItems.FROZEN_BULLET.get(),3),
    PENETRATING_BULLET(HaloItems.PENETRATING_BULLET.get(),4),
    ;
    private final Item item;
    private final int value;

    AmmoTypes(Item item, int e){
        this.item = item;
        this.value = e;
    }

    public Item getItem() {
        return item;
    }

    public int getValue() {
        return value;
    }

    public boolean compareItem(Item item1){
        return item == item1;
    }
}
