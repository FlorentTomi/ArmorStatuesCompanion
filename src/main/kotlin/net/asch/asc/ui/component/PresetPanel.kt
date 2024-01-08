package net.asch.asc.ui.component

import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import io.wispforest.owo.ui.util.UISounds
import io.wispforest.owo.util.Observable
import net.asch.asc.ModClient
import net.asch.asc.as_datapack.triggers.Presets
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class PresetPanel : FlowLayout(Sizing.fill(80), Sizing.fill(80), Algorithm.HORIZONTAL) {
    val presetLayout: FlowLayout = Containers.verticalFlow(Sizing.fill(100), Sizing.content())
    private var presetPreviewLayout: FlowLayout? = null

    init {
        surface(Surface.DARK_PANEL)

        val presetList = Containers.verticalFlow(Sizing.fill(49), Sizing.fill(100))
        presetList.surface(Surface.flat(Color.BLACK.argb()).and(Surface.outline(java.awt.Color.DARK_GRAY.rgb)))
        presetList.margins(Insets.of(5))
        child(presetList)

        for (preset in Presets.values()) {
            val presetBtn = PresetButton(this, preset) { addPreview(preset) }
            presetLayout.child(presetBtn)
        }

        val presetScroll = Containers.verticalScroll(Sizing.fill(100), Sizing.fill(100), presetLayout)
        presetScroll.margins(Insets.of(5))
        presetList.child(presetScroll)
    }

    private fun addPreview(preset: Presets) {
        if (presetPreviewLayout != null) {
            removeChild(presetPreviewLayout)
        }

        presetPreviewLayout = Containers.verticalFlow(Sizing.fill(49), Sizing.fill(100))
        presetPreviewLayout!!.horizontalAlignment(HorizontalAlignment.CENTER)
        presetPreviewLayout!!.verticalAlignment(VerticalAlignment.CENTER)
        presetPreviewLayout!!.margins(Insets.of(2))
        presetPreviewLayout!!.gap(2)
        child(presetPreviewLayout)

        val setPresetKey: String = if (preset == Presets.randomized) "set_randomized_preset" else "set_preset"
        val btn = Components.button(Text.translatable("asc.screen.$setPresetKey")) {
            parent?.remove()
            preset.accept(Unit)
        }.renderer(ButtonTextures.DEFAULT_RENDERER)
        presetPreviewLayout!!.child(btn)

        val previewSize = presetLayout.fullSize().width - gap.get()
        if (preset != Presets.randomized) {
            presetPreviewLayout!!.child(
                Components.texture(
                    Identifier.of(
                        ModClient.MOD_ID,
                        "textures/presets/$preset.png"
                    ),
                    0,
                    0,
                    previewSize,
                    previewSize,
                    previewSize,
                    previewSize
                ).blend(true)
            )
        }
    }

    override fun onMouseDown(mouseX: Double, mouseY: Double, button: Int): Boolean {
        super.onMouseDown(mouseX, mouseY, button)
        return true
    }

    class PresetButton(
        private val presetPanel: PresetPanel,
        private val preset: Presets,
        private val onSelected: Consumer<Presets>
    ) : LabelComponent(Text.translatable("asc.screen.preset.$preset")) {
        private val selected: Observable<Boolean> = Observable.of(false)

        init {
            margins(Insets.vertical(1))
            cursorStyle(CursorStyle.HAND)

            selected.observe { newSelected ->
                if (newSelected) {
                    onSelected.accept(preset)

                    for (presetBtn in presetPanel.presetLayout.children()) {
                        if (presetBtn != this) {
                            (presetBtn as PresetButton).selected.set(false)
                        }
                    }
                }
            }
        }

        override fun onMouseDown(mouseX: Double, mouseY: Double, button: Int): Boolean {
            super.onMouseDown(mouseX, mouseY, button)

            selected.set(true)
            UISounds.playInteractionSound()

            return true
        }

        override fun draw(context: OwoUIDrawContext, mouseX: Int, mouseY: Int, partialTicks: Float, delta: Float) {
            if (selected.get()) {
                val margins = margins.get()
                context.fill(
                    x - margins.left,
                    y - margins.top,
                    x + width + margins.right,
                    y + height + margins.bottom,
                    0x4400FF00
                )
            } else if (isInBoundingBox(mouseX.toDouble(), mouseY.toDouble())) {
                val margins = margins.get()
                context.fill(
                    x - margins.left,
                    y - margins.top,
                    x + width + margins.right,
                    y + height + margins.bottom,
                    0x44FFFFFF
                )
            }

            super.draw(context, mouseX, mouseY, partialTicks, delta)
        }
    }
}