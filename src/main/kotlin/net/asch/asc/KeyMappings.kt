package net.asch.asc

import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.asch.asc.as_datapack.triggers.Utility
import net.asch.asc.ui.screen.MainScreen
import net.asch.asc.ui.screen.PosePresetScreen
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.util.Identifier
import java.util.function.Consumer

object KeyMappings {

    private val ASC_CATEGORY = KeyBinding.Category(
        Identifier.of("armor_statues_companion", "key_category")
    )

    private val OPEN_MAIN_GUI = KeyBinding(
        "asc.key.open_main_gui",
        InputUtil.Type.KEYSYM,
        InputUtil.GLFW_KEY_COMMA,
        ASC_CATEGORY
    )

    private val OPEN_POSE_PRESET_GUI = KeyBinding(
        "asc.key.open_pose_preset_gui",
        InputUtil.Type.KEYSYM,
        -1,
        ASC_CATEGORY
    )

    private val HIGHLIGHT_ARMOR_STAND = KeyBinding(
        "asc.key.highlight_armor_stand",
        InputUtil.Type.KEYSYM,
        -1,
        ASC_CATEGORY
    )

    private val MAKE_ITEM_FRAME_INVISIBLE = KeyBinding(
        "asc.key.make_item_frame_invisible",
        InputUtil.Type.KEYSYM,
        -1,
        ASC_CATEGORY
    )

    fun register() {
        KeyBindingHelper.registerKeyBinding(OPEN_MAIN_GUI)
        KeyBindingHelper.registerKeyBinding(OPEN_POSE_PRESET_GUI)
        KeyBindingHelper.registerKeyBinding(HIGHLIGHT_ARMOR_STAND)
        KeyBindingHelper.registerKeyBinding(MAKE_ITEM_FRAME_INVISIBLE)

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            executeKeyMapping(client, OPEN_MAIN_GUI, ::onOpenMainGUI)
            executeKeyMapping(client, OPEN_POSE_PRESET_GUI, ::onOpenPosePresetGUI)
            executeKeyMapping(client, HIGHLIGHT_ARMOR_STAND, ::onHighlightArmorStand)
            executeKeyMapping(client, MAKE_ITEM_FRAME_INVISIBLE, ::onMakeItemFrameInvisible)
        }
    }

    private fun executeKeyMapping(
        client: MinecraftClient,
        keyMapping: KeyBinding,
        consumer: Consumer<MinecraftClient>
    ) {
        while (keyMapping.wasPressed()) {
            consumer.accept(client)
        }
    }

    private fun onOpenMainGUI(client: MinecraftClient) {
        client.setScreen(MainScreen())
    }

    private fun onOpenPosePresetGUI(client: MinecraftClient) {
        client.setScreen(PosePresetScreen())
    }

    private fun onHighlightArmorStand(client: MinecraftClient) {
        Utility.highlight.accept(Unit)
    }

    private fun onMakeItemFrameInvisible(client: MinecraftClient) {
        ArmorStatuesHelper.makeItemFrameInvisible(client)
    }
}
