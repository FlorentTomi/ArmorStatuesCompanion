package net.asch.asc.fabric

import net.asch.asc.KeyMappings
import net.asch.asc.fabric.ui.screen.MainScreen
import net.asch.asc.fabric.ui.screen.PosePresetScreen
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient

object KeyMappingsImpl {
    @JvmStatic
    fun register() {
        KeyBindingHelper.registerKeyBinding(KeyMappings.OPEN_MAIN_GUI)
        KeyBindingHelper.registerKeyBinding(KeyMappings.OPEN_POSE_PRESET_GUI)
        KeyBindingHelper.registerKeyBinding(KeyMappings.HIGHLIGHT_ARMOR_STAND)
        KeyBindingHelper.registerKeyBinding(KeyMappings.MAKE_ITEM_FRAME_INVISIBLE)

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            KeyMappings.executeKeyMapping(client, KeyMappings.OPEN_MAIN_GUI, ::onOpenMainGUI)
            KeyMappings.executeKeyMapping(client, KeyMappings.OPEN_POSE_PRESET_GUI, ::onOpenPosePresetGUI)
            KeyMappings.executeKeyMapping(client, KeyMappings.HIGHLIGHT_ARMOR_STAND, KeyMappings::onHighlightArmorStand)
            KeyMappings.executeKeyMapping(client, KeyMappings.MAKE_ITEM_FRAME_INVISIBLE, KeyMappings::onMakeItemFrameInvisible)
        }
    }

    private fun onOpenMainGUI(client: MinecraftClient) {
        client.setScreen(MainScreen())
    }

    private fun onOpenPosePresetGUI(client: MinecraftClient) {
        client.setScreen(PosePresetScreen())
    }
}