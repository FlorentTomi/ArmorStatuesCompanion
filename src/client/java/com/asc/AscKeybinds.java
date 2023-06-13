package com.asc;

import com.asc.AscCommand;
import com.asc.ArmorStandTriggers;
import com.asc.AscScreen;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.text.Text;

import org.lwjgl.glfw.GLFW;

class AscKeybinds {
    private static KeyBinding TOGGLE_ITEM_FRAME_VISIBILITY = new KeyBinding(
            "key.asc.toggle_item_frame_visibility",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "category.asc.armor_stand");

    private static KeyBinding CHECK_ARMOR_STAND = new KeyBinding(
            "key.asc.check_armor_stand",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            "category.asc.armor_stand");

    public static void RegisterKeybinds() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_ITEM_FRAME_VISIBILITY);
        KeyBindingHelper.registerKeyBinding(CHECK_ARMOR_STAND);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_ITEM_FRAME_VISIBILITY.wasPressed()) {
                AscCommand.ExecuteCommand(client, "trigger if_invisible");
            }

            while (CHECK_ARMOR_STAND.wasPressed()) {
//                ArmorStandTriggers.Check(client);
                 client.setScreen(new AscScreen(client));
            }
        });
    }
}