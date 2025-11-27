package net.asch.asc.ui.component

import io.wispforest.owo.ui.component.ButtonComponent.Renderer
import io.wispforest.owo.ui.core.OwoUIDrawContext
import io.wispforest.owo.ui.util.NinePatchTexture
import net.asch.asc.ModClient
import net.minecraft.util.Identifier

object ButtonTextures {

    private val DEFAULT_ACTIVE_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/default_active")
    private val DEFAULT_HOVERED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/default_hovered")
    private val DEFAULT_DISABLED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/default_disabled")

    private val ON_ACTIVE_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/on_active")
    private val ON_HOVERED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/on_hovered")
    private val ON_DISABLED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/on_disabled")

    private val OFF_ACTIVE_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/off_active")
    private val OFF_HOVERED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/off_hovered")
    private val OFF_DISABLED_TEXTURE_ID =
        Identifier.of(ModClient.MOD_ID, "button/off_disabled")

    /**
     * Shared helper that draws a nine-patch button using owo's draw context.
     */
    private fun drawButton(
        texture: Identifier,
        context: OwoUIDrawContext,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        NinePatchTexture.draw(texture, context, x, y, width, height)
    }

    // Used by generic buttons
    val DEFAULT_RENDERER: Renderer = Renderer { context, button, _ ->
        val texture = if (button.active) {
            if (button.isHovered) DEFAULT_HOVERED_TEXTURE_ID else DEFAULT_ACTIVE_TEXTURE_ID
        } else {
            DEFAULT_DISABLED_TEXTURE_ID
        }

        drawButton(texture, context, button.x, button.y, button.width, button.height)
    }

    // Used when a toggle is "ON"
    val ON_RENDERER: Renderer = Renderer { context, button, _ ->
        val texture = if (button.active) {
            if (button.isHovered) ON_HOVERED_TEXTURE_ID else ON_ACTIVE_TEXTURE_ID
        } else {
            ON_DISABLED_TEXTURE_ID
        }

        drawButton(texture, context, button.x, button.y, button.width, button.height)
    }

    // Used when a toggle is "OFF"
    val OFF_RENDERER: Renderer = Renderer { context, button, _ ->
        val texture = if (button.active) {
            if (button.isHovered) OFF_HOVERED_TEXTURE_ID else OFF_ACTIVE_TEXTURE_ID
        } else {
            OFF_DISABLED_TEXTURE_ID
        }

        drawButton(texture, context, button.x, button.y, button.width, button.height)
    }
}
