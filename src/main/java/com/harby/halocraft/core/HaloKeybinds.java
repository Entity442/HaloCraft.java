package com.harby.halocraft.core;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;

public class HaloKeybinds {
    public static final KeyMapping RELOAD_KEY = new KeyMapping("key.halocraft.reload", InputConstants.KEY_R, "key.categories.misc");
    public static final KeyMapping SHOOTING_KEY = new KeyMapping("key.halocraft.shoot", InputConstants.MOUSE_BUTTON_LEFT, "key.categories.misc");

}
