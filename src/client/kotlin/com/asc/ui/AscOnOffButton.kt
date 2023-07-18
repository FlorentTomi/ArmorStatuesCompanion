package com.asc.ui

import io.wispforest.owo.ui.core.Size
import io.wispforest.owo.ui.util.NinePatchRenderer
import net.minecraft.util.Identifier

object AscOnOffButton {
    private val textureId = Identifier("asc", "textures/gui/buttons.png")

    private const val offU = 64
    private const val onU = 128

    private const val activeV = 0
    private const val hoveredV = 64
    private const val disabledV = 128

    private val cornerPatchSize = Size.square(3)
    private val centerPatchSize = Size.square(58)
    private val textureSize = Size.square(192)

    val onActiveTexture = NinePatchRenderer(textureId, onU, activeV, cornerPatchSize, centerPatchSize, textureSize, true)
    val onDisabledTexture = NinePatchRenderer(textureId, onU, disabledV, cornerPatchSize, centerPatchSize, textureSize, true)
    val onHoveredTexture = NinePatchRenderer(textureId, onU, hoveredV, cornerPatchSize, centerPatchSize, textureSize, true)

    val offActiveTexture = NinePatchRenderer(textureId, offU, activeV, cornerPatchSize, centerPatchSize, textureSize, true)
    val offDisabledTexture = NinePatchRenderer(textureId, offU, disabledV, cornerPatchSize, centerPatchSize, textureSize, true)
    val offHoveredTexture = NinePatchRenderer(textureId, offU, hoveredV, cornerPatchSize, centerPatchSize, textureSize, true)
}