package com.asc.ui

import com.asc.AscTriggers
import com.mojang.blaze3d.systems.RenderSystem
import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.core.*
import io.wispforest.owo.ui.util.NinePatchTexture
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class AscScreen() : BaseOwoScreen<FlowLayout>() {
    companion object {
        private val onActiveTextureId = Identifier("asc", "button/on_active")
        private val offActiveTextureId = Identifier("asc", "button/off_active")
        private val onHoveredTextureId = Identifier("asc", "button/on_hovered")
        private val offHoveredTextureId = Identifier("asc", "button/off_hovered")
        private val onDisabledTextureId = Identifier("asc", "button/on_disabled")
        private val offDisabledTextureId = Identifier("asc", "button/off_disabled")

        private val onRenderer = ButtonComponent.Renderer { context, button, _ ->
            RenderSystem.enableDepthTest()
            val texture =
                if (button.active) (if (button.isHovered) onHoveredTextureId else onActiveTextureId) else onDisabledTextureId
            NinePatchTexture.draw(texture, context, button.x, button.y, button.width, button.height)
        }

        private val offRenderer = ButtonComponent.Renderer { context, button, _ ->
            RenderSystem.enableDepthTest()
            val texture =
                if (button.active) (if (button.isHovered) offHoveredTextureId else offActiveTextureId) else offDisabledTextureId
            NinePatchTexture.draw(texture, context, button.x, button.y, button.width, button.height)
        }
    }

    override fun createAdapter(): OwoUIAdapter<FlowLayout> {
        return OwoUIAdapter.create(this, Containers::verticalFlow)
    }

    override fun build(rootComponent: FlowLayout?) {
        rootComponent?.verticalAlignment(VerticalAlignment.CENTER)
        rootComponent?.horizontalAlignment(HorizontalAlignment.LEFT)
        rootComponent?.surface(Surface.VANILLA_TRANSLUCENT)

        val mainPanel = Containers.verticalFlow(Sizing.content(), Sizing.content())
        mainPanel.padding(Insets.both(5, 5))
        mainPanel.gap(2)

        val mainScroll = Containers.verticalScroll(Sizing.content(), Sizing.fill(100), mainPanel)
        rootComponent?.child(mainScroll)

        mainPanel.child(Components.button(Text.translatable("asc.screen.CHECK")) { triggerAction(AscTriggers::check) })
        mainPanel.child(buildActionPanel())
    }

    private fun buildActionPanel(): FlowLayout {
        val layout = Containers.verticalFlow(Sizing.content(), Sizing.content())

        addCollapsibleGrid(layout, "STYLE", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "BASE_PLATE", 0) { addBooleanAction(it, true, "SHOW", "HIDE", AscTriggers::showBasePlate) }
            addCollapsibleLine(contentLayout, "ARMS", 1) { addBooleanAction(it, true, "SHOW", "HIDE", AscTriggers::showArms) }
            addCollapsibleLine(contentLayout, "STAND", 2) { addBooleanAction(it, true, "SHOW", "HIDE", AscTriggers::showStand) }
            addCollapsibleLine(contentLayout, "NAME", 3) { addBooleanAction(it, true, "SHOW", "HIDE", AscTriggers::showName) }
            addCollapsibleLine(contentLayout, "SMALL_STAND", 4) { addBooleanAction(it, true, "ENABLE", "DISABLE", AscTriggers::enableSmallStand) }
            addCollapsibleLine(contentLayout, "GRAVITY", 5) { addBooleanAction(it, true, "ENABLE", "DISABLE", AscTriggers::enableGravity) }
        }

        addCollapsibleGrid(layout, "NUDGE", 11) { contentLayout ->
            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION", 0)
            addCollapsibleLine(contentLayout, "X", 1) { addPositionAction(it, AscTriggers::nudgePositionX) }
            addCollapsibleLine(contentLayout, "Y", 2) { addPositionAction(it, AscTriggers::nudgePositionY) }
            addCollapsibleLine(contentLayout, "Z", 3) { addPositionAction(it, AscTriggers::nudgePositionZ) }

            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION_ALIGNED", 4)
            addCollapsibleLine(contentLayout, "X", 5) { addPositionAction(it, AscTriggers::nudgePositionAlignedX) }
            addCollapsibleLine(contentLayout, "Z", 6) { addPositionAction(it, AscTriggers::nudgePositionAlignedZ) }

            addCollapsibleSeparator(contentLayout, "NUDGE_POSITION_EXACT", 7)
            addCollapsibleLine(contentLayout, "X", 8) { addPositionAction(it, AscTriggers::nudgePositionExactX) }
            addCollapsibleLine(contentLayout, "Y", 9) { addPositionAction(it, AscTriggers::nudgePositionExactY) }
            addCollapsibleLine(contentLayout, "Z", 10) { addPositionAction(it, AscTriggers::nudgePositionExactZ) }
        }

        addCollapsibleGrid(layout, "ROTATION", 2) { contentLayout ->
            addCollapsibleLine(contentLayout, "SET_ROTATION", 0) {
                addButton(it, "ANGLE1", AscTriggers::setRotationStepTo1)
                addButton(it, "ANGLE5", AscTriggers::setRotationStepTo5)
                addButton(it, "ANGLE15", AscTriggers::setRotationStepTo15)
                addButton(it, "ANGLE45", AscTriggers::setRotationStepTo45)
            }

            addCollapsibleLine(contentLayout, "ROTATE", 1) {
                addButton(it, "LEFT", AscTriggers::rotateLeft)
                addButton(it, "AWAY", AscTriggers::rotateAway)
                addButton(it, "RIGHT", AscTriggers::rotateRight)
                addButton(it, "TOWARD", AscTriggers::rotateToward)
            }
        }

        addCollapsibleGrid(layout, "POINTING", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "HEAD", 0) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointHeadTo) }
            addCollapsibleLine(contentLayout, "BODY", 1) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointBodyTo) }
            addCollapsibleLine(contentLayout, "LEFT_ARM", 2) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointLeftArmTo) }
            addCollapsibleLine(contentLayout, "RIGHT_ARM", 3) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointRightArmTo) }
            addCollapsibleLine(contentLayout, "LEFT_LEG", 4) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointLeftLegTo) }
            addCollapsibleLine(contentLayout, "RIGHT_LEG", 5) { addBooleanAction(it, false, "TO_HEAD", "TO_FEET", AscTriggers::pointRightLegTo) }
        }

        addCollapsibleGrid(layout, "POSE_ADJUSTMENT", 6) { contentLayout ->
            addCollapsibleLine(contentLayout, "HEAD", 0) { addAdjustments(it, AscTriggers.BodyParts.HEAD) }
            addCollapsibleLine(contentLayout, "BODY", 1) { addAdjustments(it, AscTriggers.BodyParts.BODY) }
            addCollapsibleLine(contentLayout, "LEFT_ARM", 2) { addAdjustments(it, AscTriggers.BodyParts.LEFT_ARM) }
            addCollapsibleLine(contentLayout, "RIGHT_ARM", 3) { addAdjustments(it, AscTriggers.BodyParts.RIGHT_ARM) }
            addCollapsibleLine(contentLayout, "LEFT_LEG", 4) { addAdjustments(it, AscTriggers.BodyParts.LEFT_LEG) }
            addCollapsibleLine(contentLayout, "RIGHT_LEG", 5) { addAdjustments(it, AscTriggers.BodyParts.RIGHT_LEG) }
        }

        addCollapsibleFlow(layout, "AUTO_ALIGNMENT") { contentLayout ->
            addButton(contentLayout, "SET_BLOCK_ON_SURFACE", AscTriggers::setBlockOnSurface)
            addButton(contentLayout, "SET_ITEM_ON_SURFACE", AscTriggers::setItemOnSurface)
            addButton(contentLayout, "SET_ITEM_FLAT_ON_SURFACE", AscTriggers::setItemFlatOnSurface)
            addButton(contentLayout, "SET_TOOL_FLAT_ON_SURFACE", AscTriggers::setToolFlatOnSurface)
            addButton(contentLayout, "SET_TOOL_RACK", AscTriggers::setToolRack)
        }

        addCollapsibleFlow(layout, "SWAP_SLOTS") { contentLayout ->
            addButton(contentLayout, "SWAP_MAIN_HAND_OFFHAND", AscTriggers::swapMainHandOffhand)
            addButton(contentLayout, "SWAP_MAIN_HAND_HEAD", AscTriggers::swapMainHandHead)
        }

        addCollapsibleFlow(layout, "MIRROR_FLIP") { contentLayout ->
            addCollapsibleLine(contentLayout, "ARMS") {
                addButton(it, "MIRROR_LR", AscTriggers::mirrorArmsLeftToRight)
                addButton(it, "MIRROR_RL", AscTriggers::mirrorArmsRightToLeft)
            }

            addCollapsibleLine(contentLayout, "LEGS") {
                addButton(it, "MIRROR_LR", AscTriggers::mirrorLegsLeftToRight)
                addButton(it, "MIRROR_RL", AscTriggers::mirrorLegsRightToLeft)
            }

            addButton(contentLayout, "FLIP", AscTriggers::flip)
        }

        addCollapsibleFlow(layout, "UTILS") { contentLayout ->
            addCollapsibleLine(contentLayout, null) {
                addButton(it, "COPY", AscTriggers::copy)
                addButton(it, "PASTE", AscTriggers::paste)
            }

            addCollapsibleLine(contentLayout, "LOCK_UNLOCK") {
                addButton(it, "LOCK", offRenderer) { AscTriggers.lock(true) }
                addButton(it, "UNLOCK", onRenderer) { AscTriggers.lock(false) }
            }
        }

        return layout
    }

    private fun addCollapsibleFlow(parentLayout: FlowLayout, id: String, buildFunction: Consumer<FlowLayout>) {
        val collapsible = Containers.collapsible(Sizing.fill(100), Sizing.content(), Text.translatable("asc.screen.${id}"), false)
        parentLayout.child(collapsible)

        val collapsibleLayout = Containers.verticalFlow(Sizing.content(), Sizing.content())
        collapsibleLayout.verticalAlignment(VerticalAlignment.CENTER)
        collapsibleLayout.horizontalAlignment(HorizontalAlignment.LEFT)
        collapsibleLayout.gap(2)
        buildFunction.accept(collapsibleLayout)
        collapsible.child(collapsibleLayout)
    }

    private fun addCollapsibleGrid(parentLayout: FlowLayout, id: String, nRows: Int, buildFunction: Consumer<GridLayout>) {
        val collapsible = Containers.collapsible(Sizing.fill(100), Sizing.content(), Text.translatable("asc.screen.${id}"), false)
        parentLayout.child(collapsible)

        val collapsibleLayout = Containers.grid(Sizing.content(), Sizing.content(), nRows, 2)
        collapsibleLayout.verticalAlignment(VerticalAlignment.CENTER)
        collapsibleLayout.horizontalAlignment(HorizontalAlignment.LEFT)
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
        contentLayout.margins(Insets.both(1, 1))
        buildFunction.accept(contentLayout)
        parentLayout.child(contentLayout, row, 1)
    }

    private fun addCollapsibleLine(parentLayout: FlowLayout, id: String?, buildFunction: Consumer<FlowLayout>) {
        if (id != null) {
            parentLayout.child(Components.label(Text.translatable("asc.screen.${id}")))
        }

        val contentLayout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        contentLayout.verticalAlignment(VerticalAlignment.CENTER)
        contentLayout.gap(2)
        contentLayout.margins(Insets.both(1, 1))
        buildFunction.accept(contentLayout)
        parentLayout.child(contentLayout)
    }

    private fun addCollapsibleSeparator(parentLayout: GridLayout, id: String?, row: Int) {
        parentLayout.child(Components.label(Text.translatable("asc.screen.${id}") ?: Text.empty()), row, 0)
    }

    private fun addButton(parentLayout: FlowLayout, id: String, action: Runnable) {
        parentLayout.child(Components.button(Text.translatable("asc.screen.${id}")) { triggerAction(action) })
    }

    private fun addButton(parentLayout: FlowLayout, id: String, renderer: ButtonComponent.Renderer, action: Runnable) {
        parentLayout.child(Components.button(Text.translatable("asc.screen.${id}")) { triggerAction(action) }.renderer(renderer))
    }

    private fun addBooleanAction(parentLayout: FlowLayout, onOffTextures: Boolean, idTrue: String, idFalse: String, action: Consumer<Boolean>) {
        if (onOffTextures) {
            addButton(parentLayout, idTrue, onRenderer) { action.accept(true) }
            addButton(parentLayout, idFalse, offRenderer) { action.accept(false) }
        } else {
            addButton(parentLayout, idTrue) { action.accept(true) }
            addButton(parentLayout, idFalse) { action.accept(false) }
        }
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

    private fun addAdjustments(parentLayout: FlowLayout, bodyPart: AscTriggers.BodyParts) {
        addButton(parentLayout, "MX") { AscTriggers.adjustPoseX(false, bodyPart) }
        addButton(parentLayout, "PX") { AscTriggers.adjustPoseX(true, bodyPart) }
        parentLayout.child(Components.label(Text.literal("-")))
        addButton(parentLayout, "MY") { AscTriggers.adjustPoseY(false, bodyPart) }
        addButton(parentLayout, "PY") { AscTriggers.adjustPoseY(true, bodyPart) }
        parentLayout.child(Components.label(Text.literal("-")))
        addButton(parentLayout, "MZ") { AscTriggers.adjustPoseZ(false, bodyPart) }
        addButton(parentLayout, "PZ") { AscTriggers.adjustPoseZ(true, bodyPart) }
    }

    private fun triggerAction(action: Runnable) {
        if (MinecraftClient.getInstance().server != null) {
            close()
        }

        action.run()
    }
}