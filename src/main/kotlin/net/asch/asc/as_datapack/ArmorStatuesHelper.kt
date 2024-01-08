package net.asch.asc.as_datapack

import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ArmorStatuesHelper {
    const val AS_TRIGGER_SCOREBOARD_OBJECTIVE = "as_trigger"

    enum class WandTypes(val expectedItem: String, val triggerValue: String) {
        adjustment("minecraft:warped_fungus_on_a_stick", "adjustment_wand"),
        pointer("minecraft:stick", "pointer_wand")
    }

    fun trigger(client: MinecraftClient, actionId: Int) {
        sendCommand(client, "trigger $AS_TRIGGER_SCOREBOARD_OBJECTIVE set $actionId")
    }

    fun makeItemFrameInvisible(client: MinecraftClient) {
        sendCommand(client, "trigger if_invisible")
    }

    fun repeat(client: MinecraftClient) {
        sendCommand(client, "trigger as_repeat set 1")
    }

    fun craftWand(client: MinecraftClient, wandType: WandTypes) {
        if (canCraftWand(client, wandType)) {
            sendCommand(client, "trigger ${wandType.triggerValue}")
        } else {
            val expectedItem = Registries.ITEM.get(Identifier(wandType.expectedItem))
            client.player?.sendMessage(Text.translatable("asc.chat.cant_craft_wand", Text.translatable("asc.item.$wandType"), expectedItem.name))
        }
    }

    private fun canCraftWand(client: MinecraftClient, wandType: WandTypes): Boolean {
        val player = client.player ?: return false

        val expectedItem = Registries.ITEM.get(Identifier(wandType.expectedItem))
        val checkForItem = { itemStack: ItemStack -> itemStack.isOf(expectedItem) && !itemStack.hasNbt() }

        val playerMainInventory = player.inventory.main
        for (item in playerMainInventory) {
            if (checkForItem(item)) {
                return true
            }
        }

        if (checkForItem(player.mainHandStack)) {
            return true
        }


        if (checkForItem(player.offHandStack)) {
            return true
        }

        return false
    }

    private fun sendCommand(client: MinecraftClient, command: String) {
        val networkHandler = client.networkHandler ?: return
        networkHandler.sendCommand(command)
    }
}