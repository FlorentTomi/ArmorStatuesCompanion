package net.asch.asc

import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.text.TextColor
import org.apache.logging.log4j.LogManager

object ModClient {
    const val MOD_ID: String = "armor-statues-companion"
    val LOGGER = LogManager.getLogger()
    private var HAS_AS_DATAPACK = false

    @JvmStatic
    fun initialize() {
        KeyMappings.register()
    }

    @JvmStatic
    fun onPlayerScoreboardUpdate(playerName: String, objectiveName: String, score: Int) {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        if (player.name.string != playerName) {
            return
        }

        if (objectiveName == ArmorStatuesHelper.AS_TRIGGER_SCOREBOARD_OBJECTIVE) {
            HAS_AS_DATAPACK = true
        }
    }

    fun hasDatapack(): Boolean {
        return HAS_AS_DATAPACK
    }

    fun sendWarning(key: String) {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return

        val msg = Text.empty()
            .append(getModTitle())
            .append(": ")
            .append(Text.translatable("asc.warning.$key"))
        player.sendMessage(msg)
    }

    private fun getModTitle(): Text {
        val style = Style.EMPTY
            .withFont(Style.DEFAULT_FONT_ID)
            .withBold(true)
            .withColor(TextColor.fromRgb(0xFFFF00))

        return Text.literal("[")
            .append(Text.translatable("asc.mod.title"))
            .append("]")
            .setStyle(style)
    }
}