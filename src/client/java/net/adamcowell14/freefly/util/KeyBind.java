package net.adamcowell14.freefly.util;

import net.adamcowell14.freefly.Freefly;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;


public class KeyBind {
    public static final String KEY_CATEGORY = "Free Fly mod";
    public static KeyBinding ChangeRunning;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client ->{
            if(ChangeRunning.wasPressed()) {
                PlayerEntity player = MinecraftClient.getInstance().player;
                assert player != null;

                if(!Freefly.running) {
                    Freefly.running = true;
                    player.sendMessage(Text.of("§bFree Fly §7§l| §7Mod §a§lenabled§7!"));
                } else {
                    Freefly.running = false;
                    player.sendMessage(Text.of("§bFree Fly §7§l| §7Mod §c§ldisabled§7!"));
                }
            }
        });
    }

    public static void register() {
        ChangeRunning = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Disable/Enable mod",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KEY_CATEGORY
        ));

        registerKeyInputs();
    }
}
