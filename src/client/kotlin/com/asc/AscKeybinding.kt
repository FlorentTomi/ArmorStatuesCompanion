package com.asc

import com.asc.ui.AscScreen
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object AscKeybinding {
    private val TOGGLE_ITEM_FRAME_VISIBILITY = KeyBinding("asc.key.toggle_item_frame_visibility", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "asc.category.armor_stand")
    private val OPEN_GUI = KeyBinding("asc.key.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_COMMA, "asc.category.armor_stand")
    private val CHECK_ARMOR_STAND = KeyBinding("asc.key.check_armor_stand", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "asc.category.armor_stand")

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
            client.setScreen(AscScreen())
        }

        while (CHECK_ARMOR_STAND.wasPressed()) {
            AscTriggers.check()
        }
    }
}