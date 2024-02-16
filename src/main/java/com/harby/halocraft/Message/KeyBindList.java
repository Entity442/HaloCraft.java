package com.harby.halocraft.Message;

import com.harby.halocraft.core.HaloKeybinds;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.settings.KeyModifier;

public enum KeyBindList {
    UP(Minecraft.getInstance().options.keyJump),
    FORWARD(Minecraft.getInstance().options.keySprint),
    RELOAD(HaloKeybinds.RELOAD_KEY),
    SHOOT(HaloKeybinds.SHOOTING_KEY);

    private final KeyMapping key;
    KeyBindList(KeyMapping key) {
        this.key = key;
    }

    public boolean isDown() {
        return this.key.isDown();
    }
    public KeyModifier getKeyModifier(){
        return key.getKeyModifier();
    }
}
