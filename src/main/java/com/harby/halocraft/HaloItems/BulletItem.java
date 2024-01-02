package com.harby.halocraft.HaloItems;

import com.harby.halocraft.core.BulletType;

public class BulletItem extends ItemBase{
    private final BulletType type;
    public BulletItem(Properties properties, BulletType type) {
        super(properties);
        this.type = type;
    }

    public BulletType getType() {
        return type;
    }
}
