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
import net.minecraft.client.gui.Click
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class PresetPanel : FlowLayout(Sizing.fill(80), Sizing.fill(80), Algorithm.HORIZONTAL) {

    val presetLayout: FlowLayout =
        Containers.verticalFlow(Sizing.fill(100), Sizing.content())

    private val previewContainer: FlowLayout =
        Containers.verticalFlow(Sizing.fill(49), Sizing.fill(100))

    init {
        surface(Surface.DARK_PANEL)

        // ================= LEFT LIST =================

        val presetList =
            Containers.verticalFlow(Sizing.fill(49), Sizing.fill(100))

        presetList.surface(
            Surface.flat(Color.BLACK.argb())
                .and(Surface.outline(java.awt.Color.DARK_GRAY.rgb))
        )

        presetList.margins(Insets.of(5))
        child(presetList)

        for (preset in Presets.values()) {
            val presetBtn = PresetButton(this, preset) { addPreview(preset) }
            presetLayout.child(presetBtn)
        }

        val presetScroll =
            Containers.verticalScroll(
                Sizing.fill(100),
                Sizing.fill(100),
                presetLayout
            )

        presetScroll.margins(Insets.of(5))
        presetList.child(presetScroll)

        // ================= RIGHT PREVIEW =================

        previewContainer.horizontalAlignment(HorizontalAlignment.CENTER)
        previewContainer.verticalAlignment(VerticalAlignment.CENTER)
        previewContainer.margins(Insets.of(8))
        previewContainer.gap(10)

        child(previewContainer)
    }

    private fun addPreview(preset: Presets) {
        // Remove old preview completely
        previewContainer.clearChildren()

        val setPresetKey =
            if (preset == Presets.randomized) "set_randomized_preset"
            else "set_preset"

        // ===== BUTTON (ABOVE IMAGE) =====
        val button = Components.button(
            Text.translatable("asc.screen.$setPresetKey")
        ) {
            preset.accept(Unit)
        }.renderer(ButtonTextures.DEFAULT_RENDERER)

        previewContainer.child(button)

        // ===== IMAGE (SINGLE, CENTERED, NO TILING) =====
        if (preset != Presets.randomized) {
            val textureId = Identifier.of(
                ModClient.MOD_ID,
                "textures/presets/${preset.name.lowercase()}.png"
            )

            // IMPORTANT:
            // - Source size == render size = NO TILING
            // - Use 128 or 160 max to fit any UI
            val previewSize = 160

            val texture = Components.texture(
                textureId,
                0, 0,
                previewSize, previewSize,   // SOURCE SIZE
                previewSize, previewSize    // RENDER SIZE
            ).blend(true)

            previewContainer.child(texture)
        }
    }

    override fun onMouseDown(click: Click, doubled: Boolean): Boolean {
        return super.onMouseDown(click, doubled)
    }

    // ================= PRESET BUTTON =================

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

                    for (child in presetPanel.presetLayout.children()) {
                        if (child !== this && child is PresetButton) {
                            child.selected.set(false)
                        }
                    }
                }
            }
        }

        override fun onMouseDown(click: Click, doubled: Boolean): Boolean {
            super.onMouseDown(click, doubled)
            selected.set(true)
            UISounds.playInteractionSound()
            return true
        }

        override fun draw(
            context: OwoUIDrawContext,
            mouseX: Int,
            mouseY: Int,
            partialTicks: Float,
            delta: Float
        ) {
            val m = margins.get()

            if (selected.get()) {
                context.fill(
                    x - m.left,
                    y - m.top,
                    x + width + m.right,
                    y + height + m.bottom,
                    0x4400FF00.toInt()
                )
            } else if (isInBoundingBox(mouseX.toDouble(), mouseY.toDouble())) {
                context.fill(
                    x - m.left,
                    y - m.top,
                    x + width + m.right,
                    y + height + m.bottom,
                    0x44FFFFFF.toInt()
                )
            }

            super.draw(context, mouseX, mouseY, partialTicks, delta)
        }
    }
}
