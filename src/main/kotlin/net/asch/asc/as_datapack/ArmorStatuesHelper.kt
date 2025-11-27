package net.asch.asc.as_datapack

import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ArmorStatuesHelper {
    const val AS_TRIGGER_SCOREBOARD_OBJECTIVE = "as_trigger"

    // Use Identifiers directly instead of raw Strings
    enum class WandTypes(val expectedItemId: Identifier, val triggerValue: String) {
        adjustment(Identifier.of("minecraft", "warped_fungus_on_a_stick"), "adjustment_wand"),
        pointer(Identifier.of("minecraft", "stick"), "pointer_wand")
    }

    fun trigger(client: MinecraftClient, actionId: Int) {
        sendCommandToServer(client, "trigger $AS_TRIGGER_SCOREBOARD_OBJECTIVE set $actionId")
    }


    fun makeItemFrameInvisible(client: MinecraftClient) {
        sendCommandToServer(client, "trigger if_invisible")
    }

    fun repeat(client: MinecraftClient) {
        sendCommandToServer(client, "trigger as_repeat set 1")
    }

    fun craftWand(client: MinecraftClient, wandType: WandTypes) {
        if (!canCraftWand(client, wandType)) {
            val expectedItem = Registries.ITEM.get(wandType.expectedItemId)
            client.player?.sendMessage(
                Text.translatable(
                    "asc.chat.cant_craft_wand",
                    Text.translatable("asc.item.${wandType.name.lowercase()}"),
                    expectedItem.name
                ),
                false // not overlay
            )
            return
        }

        // Trigger the datapack action
        sendCommandToServer(client, "trigger ${wandType.triggerValue}")
    }

    private fun canCraftWand(client: MinecraftClient, wandType: WandTypes): Boolean {
        val player = client.player ?: return false
        val expectedItem = Registries.ITEM.get(wandType.expectedItemId)

        fun matches(stack: ItemStack): Boolean =
            !stack.isEmpty && stack.isOf(expectedItem) && stack.componentChanges.isEmpty

        // Do NOT use player.inventory.main (it's private in your mappings).
        val inv = player.inventory
        val size = inv.size()
        for (slot in 0 until size) {
            if (matches(inv.getStack(slot))) return true
        }

        if (matches(player.mainHandStack)) return true
        if (matches(player.offHandStack)) return true

        return false
    }

    /**
     * Sends a command as if typed in chat.
     * In modern mappings this is usually `sendChatCommand`.
     */
    private fun sendCommandToServer(client: MinecraftClient, command: String) {
        val handler = client.networkHandler ?: return
        handler.sendChatCommand(command)
        // If this line errors (method name mismatch), change it back to:
        // handler.sendCommand(command)
    }
}
