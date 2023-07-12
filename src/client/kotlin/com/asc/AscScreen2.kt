package com.asc

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import java.util.function.Consumer

class AscScreen2(private val client: MinecraftClient) : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout?) {
        rootComponent?.verticalAlignment(VerticalAlignment.CENTER)
        rootComponent?.horizontalAlignment(HorizontalAlignment.LEFT)

        val mainPanel = Containers.verticalFlow(Sizing.content(), Sizing.fill(100))
        mainPanel.padding(Insets.both(5, 5))
        mainPanel.gap(2)
        rootComponent?.child(mainPanel)

        mainPanel.child(Components.button(Text.translatable("screen.asc.CHECK")) {})
        mainPanel.child(Containers.verticalScroll(Sizing.content(), Sizing.fill(100), buildActionPanel()))
    }

    private fun buildActionPanel(): FlowLayout {
        val layout = Containers.verticalFlow(Sizing.content(), Sizing.content())

        addCollapsibleGrid(layout, "STYLE", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "BASE_PLATE", 0) { addBooleanAction(it, "SHOW", "HIDE") {} }
            addCollapsibleLine(contentLayout, "ARMS", 1) { addBooleanAction(it, "SHOW", "HIDE") {} }
            addCollapsibleLine(contentLayout, "STAND", 2) { addBooleanAction(it, "SHOW", "HIDE") {} }
            addCollapsibleLine(contentLayout, "NAME", 3) { addBooleanAction(it, "SHOW", "HIDE") {} }
            addCollapsibleLine(contentLayout, "SMALL_STAND", 4) { addBooleanAction(it, "ENABLE", "DISABLE") {} }
            addCollapsibleLine(contentLayout, "GRAVITY", 5) { addBooleanAction(it, "ENABLE", "DISABLE") {} }
        }

        addCollapsibleGrid(layout, "NUDGE", 11) { contentLayout ->
            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION", 0)
            addCollapsibleLine(contentLayout, "X", 1) { addPositionAction(it) {} }
            addCollapsibleLine(contentLayout, "Y", 2) { addPositionAction(it) {} }
            addCollapsibleLine(contentLayout, "Z", 3) { addPositionAction(it) {} }

            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION_ALIGNED", 4)
            addCollapsibleLine(contentLayout, "X", 5) { addPositionAction(it) {} }
            addCollapsibleLine(contentLayout, "Z", 6) { addPositionAction(it) {} }

            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION_EXACT", 7)
            addCollapsibleLine(contentLayout, "X", 8) { addPositionAction(it) {} }
            addCollapsibleLine(contentLayout, "Y", 9) { addPositionAction(it) {} }
            addCollapsibleLine(contentLayout, "Z", 10) { addPositionAction(it) {} }
        }

        addCollapsibleGrid(layout, "ROTATION", 2) { contentLayout ->
            addCollapsibleLine(contentLayout, "SET_ROTATION", 0) {
                addButton(it, "ANGLE1") {}
                addButton(it, "ANGLE5") {}
                addButton(it, "ANGLE15") {}
                addButton(it, "ANGLE45") {}
            }

            addCollapsibleLine(contentLayout, "ROTATE", 1) {
                addButton(it, "LEFT") {}
                addButton(it, "AWAY") {}
                addButton(it, "RIGHT") {}
                addButton(it, "TOWARD") {}
            }
        }

        addCollapsibleGrid(layout, "POINTING", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "HEAD", 0) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
            addCollapsibleLine(contentLayout, "BODY", 1) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
            addCollapsibleLine(contentLayout, "LEFT_ARM", 2) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
            addCollapsibleLine(contentLayout, "RIGHT_ARM", 3) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
            addCollapsibleLine(contentLayout, "LEFT_LEG", 4) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
            addCollapsibleLine(contentLayout, "RIGHT_LEG", 5) { addBooleanAction(it, "TO_HEAD", "TO_FEET") {} }
        }

        addCollapsibleGrid(layout, "POSE_ADJUSTMENT", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "HEAD", 0) { addAdjustments(it) }
            addCollapsibleLine(contentLayout, "BODY", 1) { addAdjustments(it) }
            addCollapsibleLine(contentLayout, "LEFT_ARM", 2) { addAdjustments(it) }
            addCollapsibleLine(contentLayout, "RIGHT_ARM", 3) { addAdjustments(it) }
            addCollapsibleLine(contentLayout, "LEFT_LEG", 4) { addAdjustments(it) }
            addCollapsibleLine(contentLayout, "RIGHT_LEG", 5) { addAdjustments(it) }
        }

        addCollapsibleFlow(layout, "AUTO_ALIGNMENT") { contentLayout ->
            addButton(contentLayout, "SET_BLOCK_ON_SURFACE") {}
            addButton(contentLayout, "SET_ITEM_ON_SURFACE") {}
            addButton(contentLayout, "SET_ITEM_FLAT_ON_SURFACE") {}
            addButton(contentLayout, "SET_TOOL_FLAT_ON_SURFACE") {}
            addButton(contentLayout, "SET_TOOL_RACK") {}
        }

        addCollapsibleFlow(layout, "SWAP_SLOTS") { contentLayout ->
            addButton(contentLayout, "SWAP_MAIN_HAND_OFFHAND") {}
            addButton(contentLayout, "SWAP_MAIN_HAND_HEAD") {}
        }

        addCollapsibleFlow(layout, "MIRROR_FLIP") { contentLayout ->
            addButton(contentLayout, "MIRROR_ARMS_LR") {}
            addButton(contentLayout, "MIRROR_ARMS_RL") {}
            addButton(contentLayout, "MIRROR_LEGS_LR") {}
            addButton(contentLayout, "MIRROR_LEGS_RL") {}
            addButton(contentLayout, "FLIP") {}
        }

        addCollapsibleFlow(layout, "UTILS") { contentLayout ->
            addButton(contentLayout, "COPY") {}
            addButton(contentLayout, "PASTE") {}
            addButton(contentLayout, "LOCK") {}
            addButton(contentLayout, "UNLOCK") {}
        }

        return layout
    }

    private fun addCollapsibleFlow(parentLayout: FlowLayout, id: String, buildFunction: Consumer<FlowLayout>) {
        val collapsible = Containers.collapsible(Sizing.fill(100), Sizing.content(), Text.translatable("screen.asc.${id}"), false)
        parentLayout.child(collapsible)

        val collapsibleLayout = Containers.verticalFlow(Sizing.content(), Sizing.content())
        collapsibleLayout.verticalAlignment(VerticalAlignment.CENTER)
        collapsibleLayout.horizontalAlignment(HorizontalAlignment.LEFT)
        collapsible.gap(2)
        buildFunction.accept(collapsibleLayout)
        collapsible.child(collapsibleLayout)
    }

    private fun addCollapsibleGrid(parentLayout: FlowLayout, id: String, nRows: Int, buildFunction: Consumer<GridLayout>) {
        val collapsible = Containers.collapsible(Sizing.fill(100), Sizing.content(), Text.translatable("screen.asc.${id}"), false)
        parentLayout.child(collapsible)

        val collapsibleLayout = Containers.grid(Sizing.content(), Sizing.content(), nRows, 2)
        collapsibleLayout.verticalAlignment(VerticalAlignment.CENTER)
        collapsibleLayout.horizontalAlignment(HorizontalAlignment.LEFT)
        collapsible.gap(2)
        buildFunction.accept(collapsibleLayout)
        collapsible.child(collapsibleLayout)
    }

    private fun addCollapsibleLine(parentLayout: GridLayout, id: String?, row: Int, buildFunction: Consumer<FlowLayout>) {
        if (id != null) {
            parentLayout.child(Components.label(Text.translatable("asc.screen.${id}")), row, 0)
        }

        val contentLayout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        contentLayout.verticalAlignment(VerticalAlignment.CENTER)
        contentLayout.gap(2)
        buildFunction.accept(contentLayout)
        parentLayout.child(contentLayout, row, 1)
    }

    private fun addCollapsibleSeparator(parentLayout: GridLayout, id: String?, row: Int) {
        parentLayout.child(Components.label(Text.translatable("asc.screen.${id}") ?: Text.empty()), row, 0)
    }

    private fun addButton(parentLayout: FlowLayout, id: String, action: Runnable) {
        parentLayout.child(Components.button(Text.translatable("screen.asc.${id}")) { action.run() })
    }

    private fun addBooleanAction(parentLayout: FlowLayout, idTrue: String, idFalse: String, action: Consumer<Boolean>) {
        addButton(parentLayout, idTrue) { action.accept(true) }
        addButton(parentLayout, idFalse) { action.accept(false) }
    }

    private fun addPositionAction(parentLayout: FlowLayout, action: Consumer<AscTriggers.PositionIncrements>) {
        addButton(parentLayout, "M8") { action.accept(AscTriggers.PositionIncrements.MINUS_8) }
        addButton(parentLayout, "M3") { action.accept(AscTriggers.PositionIncrements.MINUS_3) }
        addButton(parentLayout, "M1") { action.accept(AscTriggers.PositionIncrements.MINUS_1) }
        parentLayout.child(Components.label(Text.literal("-")))
        addButton(parentLayout, "P1") { action.accept(AscTriggers.PositionIncrements.PLUS_1) }
        addButton(parentLayout, "P3") { action.accept(AscTriggers.PositionIncrements.PLUS_3) }
        addButton(parentLayout, "P8") { action.accept(AscTriggers.PositionIncrements.PLUS_8) }
    }

    private fun addAdjustments(parentLayout: FlowLayout) {
        addButton(parentLayout, "MX") {}
        addButton(parentLayout, "PX") {}
        parentLayout.child(Components.label(Text.literal("-")))
        addButton(parentLayout, "MY") {}
        addButton(parentLayout, "PY") {}
        parentLayout.child(Components.label(Text.literal("-")))
        addButton(parentLayout, "MZ") {}
        addButton(parentLayout, "PZ") {}
    }
}