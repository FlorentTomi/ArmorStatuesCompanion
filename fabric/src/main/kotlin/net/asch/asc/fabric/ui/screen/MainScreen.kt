package net.asch.asc.fabric.ui.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.OverlayContainer
import io.wispforest.owo.ui.core.Insets
import io.wispforest.owo.ui.core.OwoUIAdapter
import io.wispforest.owo.ui.core.Sizing
import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.asch.asc.as_datapack.triggers.Utility
import net.asch.asc.fabric.ui.component.PresetPanel
import net.asch.asc.fabric.ui.component.ToolbarComponent
import net.asch.asc.fabric.ui.component.ToolboxBuilder
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import java.util.function.BiConsumer

class MainScreen : BaseOwoScreen<FlowLayout>() {
    companion object {
        private const val GAP: Int = 1
        private const val MARGINS: Int = 2
        private var activeToolbox: String? = null
    }

    private val mainToolbar = ToolbarComponent()
    private val utilityToolbar = ToolbarComponent()
    private var presetPanel: OverlayContainer<PresetPanel>? = null

    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout) {
        rootComponent.gap(GAP)

        rootComponent.child(mainToolbar)
        buildMainToolbar(mainToolbar)

        rootComponent.child(utilityToolbar)
        buildUtilityToolbar(utilityToolbar)

        val mainLayout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        mainLayout.margins(Insets.of(MARGINS))
        mainLayout.id("main")

        val mainScrollable = Containers.verticalScroll(Sizing.content(), Sizing.fill(75), mainLayout)
        rootComponent.child(mainScrollable)

        mainToolbar.press("toolbox", activeToolbox)
    }

    override fun shouldPause(): Boolean {
        return false
    }

    private fun buildMainToolbar(toolbar: ToolbarComponent) {
        addToolbarButton(toolbar, "style", "toolbox", ToolboxBuilder::styleBuilder)
        addToolbarButton(toolbar, "position", "toolbox", ToolboxBuilder::positionBuilder)
        addToolbarButton(toolbar, "rotation", "toolbox", ToolboxBuilder::rotationBuilder)
        addToolbarButton(toolbar, "pose", "toolbox", ToolboxBuilder::poseBuilder)

        toolbar.button(Text.translatable("asc.screen.presets")) { openPresetOverlay() }
        toolbar.button(Text.translatable("asc.screen.craft_adjustment_wand")) {
            ArmorStatuesHelper.craftWand(MinecraftClient.getInstance(), ArmorStatuesHelper.WandTypes.adjustment)
        }
        toolbar.button(Text.translatable("asc.screen.craft_pointer_wand")) {
            ArmorStatuesHelper.craftWand(MinecraftClient.getInstance(), ArmorStatuesHelper.WandTypes.pointer)
        }
    }

    private fun buildUtilityToolbar(toolbar: ToolbarComponent) {
        toolbar.button(Text.translatable("asc.screen.highlight")) { Utility.highlight.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.lock")) { Utility.lock.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.unlock")) { Utility.unlock.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.copy")) { Utility.copy.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.paste")) { Utility.paste.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.undo")) { Utility.undo.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.redo")) { Utility.redo.accept(Unit) }
        toolbar.button(Text.translatable("asc.screen.repeat")) { ArmorStatuesHelper.repeat(MinecraftClient.getInstance()) }
    }

    private fun addToolbarButton(
        toolbarComponent: ToolbarComponent,
        key: String,
        exclusiveGroup: String? = null,
        builder: BiConsumer<FlowLayout, String>
    ) {
        toolbarComponent.toolbox(Text.translatable("asc.screen.$key"), key, exclusiveGroup) { toolbarBtn ->
            val mainLayout = uiAdapter.rootComponent.childById(FlowLayout::class.java, "main")!!
            mainLayout.clearChildren()
            builder.accept(mainLayout, key)
            toolbarBtn.setActive()
            activeToolbox = toolbarBtn.id()
        }
    }

    private fun openPresetOverlay() {
        if (presetPanel != null) {
            uiAdapter.rootComponent.removeChild(presetPanel)
        }

        val mainLayout = uiAdapter.rootComponent.childById(FlowLayout::class.java, "main")!!
        mainLayout.clearChildren()

        presetPanel = Containers.overlay(PresetPanel())
        uiAdapter.rootComponent.child(presetPanel)
    }
}