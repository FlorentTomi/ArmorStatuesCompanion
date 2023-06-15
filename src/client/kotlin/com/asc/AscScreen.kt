package com.asc

import io.wispforest.owo.ui.base.BaseUIModelScreen
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.container.CollapsibleContainer
import io.wispforest.owo.ui.container.CollapsibleContainer.OnToggled
import io.wispforest.owo.ui.container.FlowLayout
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.util.Identifier
import java.util.function.BiConsumer
import java.util.function.Consumer

@Environment(EnvType.CLIENT)
class AscScreen(client: MinecraftClient) : BaseUIModelScreen<FlowLayout>(FlowLayout::class.java, DataSource.asset(Identifier.of("asc", "main_model"))) {
    companion object {
        private var COLLAPSIBLE_STATES: HashMap<String, Boolean> = hashMapOf(
                "STYLE" to true,
                "NUDGE" to false,
                "ROTATION" to false,
                "POINTING" to false,
                "POSE_ADJ" to false,
                "AUTO_ALIGN" to false,
                "SWAP_SLOTS" to false,
                "MIRROR_FLIP" to false,
                "UTILS" to false)
    }

    private val _client: MinecraftClient by lazy { client }

    override fun build(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.CHECK, AscTriggers::check)

        buildStyle(rootComponent)
        buildNudge(rootComponent)
        buildRotation(rootComponent)
        buildPointing(rootComponent)
        buildPoseAdjust(rootComponent)
        buildAutoAlign(rootComponent)
        buildSwapSlots(rootComponent)
        buildMirrorFlip(rootComponent)
        buildUtils(rootComponent)

        COLLAPSIBLE_STATES.forEach{ collapsibleEntry ->
            addCollapsible(rootComponent, collapsibleEntry.key, collapsibleEntry.value)
        }
    }

    private fun buildStyle(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.SHOW_BASE_PLATE, { client: MinecraftClient, show: Boolean -> AscTriggers.showBasePlate(client, show) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.HIDE_BASE_PLATE, { client: MinecraftClient, show: Boolean -> AscTriggers.showBasePlate(client, show) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.SHOW_ARMS, { client: MinecraftClient, show: Boolean -> AscTriggers.showArms(client, show) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.HIDE_ARMS, { client: MinecraftClient, show: Boolean -> AscTriggers.showArms(client, show) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.SHOW_STAND, { client: MinecraftClient, show: Boolean -> AscTriggers.showStand(client, show) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.HIDE_STAND, { client: MinecraftClient, show: Boolean -> AscTriggers.showStand(client, show) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.SHOW_NAME, { client: MinecraftClient, show: Boolean -> AscTriggers.showName(client, show) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.HIDE_NAME, { client: MinecraftClient, show: Boolean -> AscTriggers.showName(client, show) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.ENABLE_SMALL_STAND, { client: MinecraftClient, enable: Boolean -> AscTriggers.enableSmallStand(client, enable) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.DISABLE_SMALL_STAND, { client: MinecraftClient, enable: Boolean -> AscTriggers.enableSmallStand(client, enable) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.ENABLE_GRAVITY, { client: MinecraftClient, enable: Boolean -> AscTriggers.enableGravity(client, enable) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.DISABLE_GRAVITY, { client: MinecraftClient, enable: Boolean -> AscTriggers.enableGravity(client, enable) }, false)
    }

    private fun buildNudge(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionX(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionY(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_ALIGNED_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedX(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_ALIGNED_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionAlignedZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_X_EXACT_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactX(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Y_EXACT_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactY(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_M8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_8)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_M3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_M1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.MINUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_P1, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_1)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_P3, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_3)
        addTrigger(rootComponent, AscTriggers.Identifiers.NUDGE_POS_Z_EXACT_P8, { client: MinecraftClient, increment: AscTriggers.PositionIncrements? -> AscTriggers.nudgePositionExactZ(client, increment) }, AscTriggers.PositionIncrements.PLUS_8)
    }

    private fun buildRotation(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.ROTATE_LEFT, AscTriggers::rotateLeft)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ROTATION_STEP_45, AscTriggers::setRotationStepTo45)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ROTATION_STEP_15, AscTriggers::setRotationStepTo15)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ROTATION_STEP_5, AscTriggers::setRotationStepTo5)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ROTATION_STEP_1, AscTriggers::setRotationStepTo1)
        addTrigger(rootComponent, AscTriggers.Identifiers.ROTATE_RIGHT, AscTriggers::rotateRight)
        addTrigger(rootComponent, AscTriggers.Identifiers.ROTATE_TOWARD, AscTriggers::rotateToward)
        addTrigger(rootComponent, AscTriggers.Identifiers.ROTATE_AWAY, AscTriggers::rotateAway)
    }

    private fun buildPointing(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_HEAD_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointHeadTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_HEAD_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointHeadTo(client, toHead) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_BODY_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointBodyTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_BODY_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointBodyTo(client, toHead) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_RIGHT_ARM_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointRightArmTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_RIGHT_ARM_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointRightArmTo(client, toHead) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_LEFT_ARM_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointLeftArmTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_LEFT_ARM_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointLeftArmTo(client, toHead) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_RIGHT_LEG_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointRightLegTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_RIGHT_LEG_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointRightLegTo(client, toHead) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_LEFT_LEG_TO_HEAD, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointLeftLegTo(client, toHead) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POINT_LEFT_LEG_TO_FEET, { client: MinecraftClient, toHead: Boolean -> AscTriggers.pointLeftLegTo(client, toHead) }, false)
    }

    private fun buildPoseAdjust(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_HEAD_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseHeadZ(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_BODY_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseBodyZ(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_ARM_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightArmZ(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_ARM_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftArmZ(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_RIGHT_LEG_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseRightLegZ(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_X_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegX(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_X_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegX(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_Y_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegY(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_Y_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegY(client, increment) }, false)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_Z_MINUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegZ(client, increment) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.POSE_ADJ_LEFT_LEG_Z_PLUS, { client: MinecraftClient, increment: Boolean -> AscTriggers.adjustPoseLeftLegZ(client, increment) }, false)
    }

    private fun buildAutoAlign(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_BLOCK_ON_SURFACE, AscTriggers::setBlockOnSurface)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ITEM_ON_SURFACE, AscTriggers::setItemOnSurface)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_ITEM_FLAT_ON_SURFACE, AscTriggers::setItemFlatOnSurface)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_TOOL_FLAT_ON_SURFACE, AscTriggers::setToolFlatOnSurface)
        addTrigger(rootComponent, AscTriggers.Identifiers.SET_TOOL_RACK, AscTriggers::setToolRack)
    }

    private fun buildSwapSlots(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.SWAP_MAINHAND_OFFHAND, AscTriggers::swapMainHandOffhand)
        addTrigger(rootComponent, AscTriggers.Identifiers.SWAP_MAINHAND_HEAD, AscTriggers::swapMainHandHead)
    }

    private fun buildMirrorFlip(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.MIRROR_ARMS_LR, AscTriggers::mirrorArmsLeftToRight)
        addTrigger(rootComponent, AscTriggers.Identifiers.MIRROR_ARMS_RL, AscTriggers::mirrorArmsRightToLeft)
        addTrigger(rootComponent, AscTriggers.Identifiers.MIRROR_LEGS_LR, AscTriggers::mirrorLegsLeftToRight)
        addTrigger(rootComponent, AscTriggers.Identifiers.MIRROR_LEGS_RL, AscTriggers::mirrorLegsRightToLeft)
        addTrigger(rootComponent, AscTriggers.Identifiers.FLIP, AscTriggers::flip)
    }

    private fun buildUtils(rootComponent: FlowLayout) {
        addTrigger(rootComponent, AscTriggers.Identifiers.COPY, AscTriggers::copy)
        addTrigger(rootComponent, AscTriggers.Identifiers.PASTE, AscTriggers::paste)
        addTrigger(rootComponent, AscTriggers.Identifiers.LOCK, { client: MinecraftClient, lock: Boolean -> AscTriggers.lock(client, lock) }, true)
        addTrigger(rootComponent, AscTriggers.Identifiers.UNLOCK, { client: MinecraftClient, lock: Boolean -> AscTriggers.lock(client, lock) }, false)
    }

    private fun addTrigger(rootComponent: FlowLayout, id: AscTriggers.Identifiers, fn: Consumer<MinecraftClient>) {
        rootComponent.childById(ButtonComponent::class.java, id.name)?.onPress(bindButton(fn))
    }

    private fun <T> addTrigger(rootComponent: FlowLayout, id: AscTriggers.Identifiers, fn: BiConsumer<MinecraftClient, T>, `val`: T) {
        rootComponent.childById(ButtonComponent::class.java, id.name)?.onPress(bindButton(fn, `val`))
    }

    private fun addCollapsible(rootComponent: FlowLayout, id: String, collapsed: Boolean) {
        val collapse = rootComponent.childById(CollapsibleContainer::class.java, id)
        if (collapse?.expanded() != collapsed) {
            collapse?.toggleExpansion()
        }
        collapse?.onToggled()?.subscribe(OnToggled { nowExpanded: Boolean -> COLLAPSIBLE_STATES.replace(id, nowExpanded) })
    }

    private fun bindButton(fn: Consumer<MinecraftClient>): Consumer<ButtonComponent?> {
        return Consumer { _: ButtonComponent? -> trigger { fn.accept(_client) } }
    }

    private fun <T> bindButton(fn: BiConsumer<MinecraftClient, T>, `val`: T): Consumer<ButtonComponent?> {
        return Consumer { _: ButtonComponent? -> trigger { fn.accept(_client, `val`) } }
    }

    private fun trigger(runnable: Runnable) {
        if (_client.server != null) {
            close()
        }
        runnable.run()
    }
}