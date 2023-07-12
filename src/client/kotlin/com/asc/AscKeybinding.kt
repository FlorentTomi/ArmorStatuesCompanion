package com.asc

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object AscKeybinding {
    private val TOGGLE_ITEM_FRAME_VISIBILITY = KeyBinding("key.asc.toggle_item_frame_visibility", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.asc.armor_stand")
    private val OPEN_GUI = KeyBinding("key.asc.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, "category.asc.armor_stand")
    private val CHECK_ARMOR_STAND = KeyBinding("key.asc.check_armor_stand", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.asc.armor_stand")

    fun registerKeybindings() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_ITEM_FRAME_VISIBILITY)
        KeyBindingHelper.registerKeyBinding(OPEN_GUI)
        KeyBindingHelper.registerKeyBinding(CHECK_ARMOR_STAND)

        ClientTickEvents.END_CLIENT_TICK.register{ client -> onPress(client) }
    }

    private fun onPress(client: MinecraftClient) {
        while (TOGGLE_ITEM_FRAME_VISIBILITY.wasPressed()) {
            AscCommands.executeCommand(client, "trigger if_invisible")
        }

        while (OPEN_GUI.wasPressed()) {
            client.setScreen(AscScreen2(client))
        }

        while (CHECK_ARMOR_STAND.wasPressed()) {
            AscTriggers.check(client)
        }
    }
}