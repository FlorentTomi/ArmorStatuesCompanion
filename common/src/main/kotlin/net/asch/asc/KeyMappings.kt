package net.asch.asc

import dev.architectury.injectables.annotations.ExpectPlatform
import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.asch.asc.as_datapack.triggers.Utility
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import java.util.function.Consumer

object KeyMappings {
    val OPEN_MAIN_GUI = KeyBinding("asc.key.open_main_gui", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_COMMA, "asc.category.armor_statues_companion")
    val OPEN_POSE_PRESET_GUI = KeyBinding("asc.key.open_pose_preset_gui", InputUtil.Type.KEYSYM, -1, "asc.category.armor_statues_companion")
    val HIGHLIGHT_ARMOR_STAND =
        KeyBinding("asc.key.highlight_armor_stand", InputUtil.Type.KEYSYM, -1, "asc.category.armor_statues_companion")
    val MAKE_ITEM_FRAME_INVISIBLE =
        KeyBinding("asc.key.make_item_frame_invisible", InputUtil.Type.KEYSYM, -1, "asc.category.armor_statues_companion")

    @ExpectPlatform
    @JvmStatic
    fun register(): Unit = throw AssertionError()

    fun executeKeyMapping(client: MinecraftClient, keyMapping: KeyBinding, consumer: Consumer<MinecraftClient>) {
        while (keyMapping.wasPressed()) {
//            if (!ModClient.hasDatapack()) {
//                ModClient.sendWarning("no_as_datapack")
//                return
//            }

            consumer.accept(client)
        }
    }

    fun onHighlightArmorStand(client: MinecraftClient) {
        Utility.highlight.accept(Unit)
    }

    fun onMakeItemFrameInvisible(client: MinecraftClient) {
        ArmorStatuesHelper.makeItemFrameInvisible(client)
    }
}