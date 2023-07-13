package com.asc

import com.asc.AscCommands.executeArmorStandTrigger
import com.asc.AscCommands.executeCommand
import net.minecraft.client.MinecraftClient

object AscTriggers {
    enum class Identifiers(val id: Int) {
        CHECK(999),

        // Style
        SHOW_BASE_PLATE(1),
        HIDE_BASE_PLATE(2),

        SHOW_ARMS(3),
        HIDE_ARMS(4),

        ENABLE_SMALL_STAND(5),
        DISABLE_SMALL_STAND(6),

        ENABLE_GRAVITY(7),
        DISABLE_GRAVITY(8),

        SHOW_STAND(9),
        HIDE_STAND(10),

        SHOW_NAME(11),
        HIDE_NAME(12),

        // Presets
        POSE_ATTENTION(20),
        POSE_CONFIDENT(30),
        POSE_WALKING(21),
        POSE_SALUTE(31),
        POSE_RUNNING(22),
        POSE_DEATH(32),
        POSE_POINTING(23),
        POSE_FACEPALM(33),
        POSE_BLOCKING(24),
        POSE_LAZING(34),
        POSE_LUNGEING(25),
        POSE_CONFUSED(35),
        POSE_WINNING(26),
        POSE_FORMAL(36),
        POSE_SITTING(27),
        POSE_SAD(37),
        POSE_ARABESQUE(28),
        POSE_JOYOUS(38),
        POSE_CUPID(29),
        POSE_STARGAZING(39),
        POSE_RANDOMIZED(1150),
        POSE_BLOCK(141),
        POSE_ITEM(142),

        // Nudge
        NUDGE_POS_X_M8(40),
        NUDGE_POS_X_M3(101),
        NUDGE_POS_X_M1(102),
        NUDGE_POS_X_P1(103),
        NUDGE_POS_X_P3(104),
        NUDGE_POS_X_P8(43),

        NUDGE_POS_Y_M8(44),
        NUDGE_POS_Y_M3(105),
        NUDGE_POS_Y_M1(106),
        NUDGE_POS_Y_P1(107),
        NUDGE_POS_Y_P3(108),
        NUDGE_POS_Y_P8(47),

        NUDGE_POS_Z_M8(48),
        NUDGE_POS_Z_M3(109),
        NUDGE_POS_Z_M1(110),
        NUDGE_POS_Z_P1(111),
        NUDGE_POS_Z_P3(112),
        NUDGE_POS_Z_P8(51),

        NUDGE_POS_X_ALIGNED_M8(1100),
        NUDGE_POS_X_ALIGNED_M3(1101),
        NUDGE_POS_X_ALIGNED_M1(1102),
        NUDGE_POS_X_ALIGNED_P1(1103),
        NUDGE_POS_X_ALIGNED_P3(1104),
        NUDGE_POS_X_ALIGNED_P8(1105),

        NUDGE_POS_Z_ALIGNED_M8(1106),
        NUDGE_POS_Z_ALIGNED_M3(1107),
        NUDGE_POS_Z_ALIGNED_M1(1108),
        NUDGE_POS_Z_ALIGNED_P1(1109),
        NUDGE_POS_Z_ALIGNED_P3(1110),
        NUDGE_POS_Z_ALIGNED_P8(1111),

        NUDGE_POS_X_EXACT_M8(1112),
        NUDGE_POS_X_EXACT_M3(1113),
        NUDGE_POS_X_EXACT_M1(1114),
        NUDGE_POS_X_EXACT_P1(1115),
        NUDGE_POS_X_EXACT_P3(1116),
        NUDGE_POS_X_EXACT_P8(1117),

        NUDGE_POS_Y_EXACT_M8(1118),
        NUDGE_POS_Y_EXACT_M3(1119),
        NUDGE_POS_Y_EXACT_M1(1120),
        NUDGE_POS_Y_EXACT_P1(1121),
        NUDGE_POS_Y_EXACT_P3(1122),
        NUDGE_POS_Y_EXACT_P8(1123),

        NUDGE_POS_Z_EXACT_M8(1124),
        NUDGE_POS_Z_EXACT_M3(1125),
        NUDGE_POS_Z_EXACT_M1(1126),
        NUDGE_POS_Z_EXACT_P1(1127),
        NUDGE_POS_Z_EXACT_P3(1128),
        NUDGE_POS_Z_EXACT_P8(1129),

        // Rotation
        ROTATE_LEFT(56),
        SET_ROTATION_STEP_45(120),
        SET_ROTATION_STEP_15(121),
        SET_ROTATION_STEP_5(122),
        SET_ROTATION_STEP_1(123),
        ROTATE_RIGHT(57),
        ROTATE_TOWARD(124),
        ROTATE_AWAY(125),

        // Pointing
        POINT_HEAD_TO_HEAD(1160),
        POINT_HEAD_TO_FEET(1166),
        POINT_BODY_TO_HEAD(1161),
        POINT_BODY_TO_FEET(1167),
        POINT_RIGHT_ARM_TO_HEAD(1162),
        POINT_RIGHT_ARM_TO_FEET(1168),
        POINT_LEFT_ARM_TO_HEAD(1163),
        POINT_LEFT_ARM_TO_FEET(1169),
        POINT_RIGHT_LEG_TO_HEAD(1164),
        POINT_RIGHT_LEG_TO_FEET(1170),
        POINT_LEFT_LEG_TO_HEAD(1165),
        POINT_LEFT_LEG_TO_FEET(1171),

        // Pose adjust.
        POSE_ADJ_HEAD_X_MINUS(60),
        POSE_ADJ_HEAD_X_PLUS(61),
        POSE_ADJ_HEAD_Y_MINUS(62),
        POSE_ADJ_HEAD_Y_PLUS(63),
        POSE_ADJ_HEAD_Z_MINUS(64),
        POSE_ADJ_HEAD_Z_PLUS(65),

        POSE_ADJ_BODY_X_MINUS(67),
        POSE_ADJ_BODY_X_PLUS(66),
        POSE_ADJ_BODY_Y_MINUS(68),
        POSE_ADJ_BODY_Y_PLUS(69),
        POSE_ADJ_BODY_Z_MINUS(70),
        POSE_ADJ_BODY_Z_PLUS(71),

        POSE_ADJ_RIGHT_ARM_X_MINUS(72),
        POSE_ADJ_RIGHT_ARM_X_PLUS(73),
        POSE_ADJ_RIGHT_ARM_Y_MINUS(74),
        POSE_ADJ_RIGHT_ARM_Y_PLUS(75),
        POSE_ADJ_RIGHT_ARM_Z_MINUS(77),
        POSE_ADJ_RIGHT_ARM_Z_PLUS(76),

        POSE_ADJ_LEFT_ARM_X_MINUS(78),
        POSE_ADJ_LEFT_ARM_X_PLUS(79),
        POSE_ADJ_LEFT_ARM_Y_MINUS(81),
        POSE_ADJ_LEFT_ARM_Y_PLUS(80),
        POSE_ADJ_LEFT_ARM_Z_MINUS(82),
        POSE_ADJ_LEFT_ARM_Z_PLUS(83),

        POSE_ADJ_RIGHT_LEG_X_MINUS(84),
        POSE_ADJ_RIGHT_LEG_X_PLUS(85),
        POSE_ADJ_RIGHT_LEG_Y_MINUS(87),
        POSE_ADJ_RIGHT_LEG_Y_PLUS(86),
        POSE_ADJ_RIGHT_LEG_Z_MINUS(89),
        POSE_ADJ_RIGHT_LEG_Z_PLUS(88),

        POSE_ADJ_LEFT_LEG_X_MINUS(90),
        POSE_ADJ_LEFT_LEG_X_PLUS(91),
        POSE_ADJ_LEFT_LEG_Y_MINUS(92),
        POSE_ADJ_LEFT_LEG_Y_PLUS(93),
        POSE_ADJ_LEFT_LEG_Z_MINUS(94),
        POSE_ADJ_LEFT_LEG_Z_PLUS(95),

        // Auto align.
        SET_BLOCK_ON_SURFACE(151),
        SET_ITEM_ON_SURFACE(152),
        SET_ITEM_FLAT_ON_SURFACE(153),
        SET_TOOL_FLAT_ON_SURFACE(154),
        SET_TOOL_RACK(155),

        SWAP_MAINHAND_OFFHAND(161),
        SWAP_MAINHAND_HEAD(162),

        MIRROR_ARMS_LR(131),
        MIRROR_ARMS_RL(132),
        MIRROR_LEGS_LR(133),
        MIRROR_LEGS_RL(134),
        FLIP(135),

        COPY(1004),
        PASTE(1005),
        LOCK(1000),
        UNLOCK(1001)
    }

    enum class PosePresets(val id: Identifiers) {
        ATTENTION(Identifiers.POSE_ATTENTION),
        CONFIDENT(Identifiers.POSE_CONFIDENT),
        WALKING(Identifiers.POSE_WALKING),
        SALUTE(Identifiers.POSE_SALUTE),
        RUNNING(Identifiers.POSE_RUNNING),
        DEATH(Identifiers.POSE_DEATH),
        POINTING(Identifiers.POSE_POINTING),
        FACEPALM(Identifiers.POSE_FACEPALM),
        BLOCKING(Identifiers.POSE_BLOCKING),
        LAZING(Identifiers.POSE_LAZING),
        LUNGEING(Identifiers.POSE_LUNGEING),
        CONFUSED(Identifiers.POSE_CONFUSED),
        WINNING(Identifiers.POSE_WINNING),
        FORMAL(Identifiers.POSE_FORMAL),
        SITTING(Identifiers.POSE_SITTING),
        SAD(Identifiers.POSE_SAD),
        ARABESQUE(Identifiers.POSE_ARABESQUE),
        JOYOUS(Identifiers.POSE_JOYOUS),
        CUPID(Identifiers.POSE_CUPID),
        STARGAZING(Identifiers.POSE_STARGAZING),
        RANDOMIZED(Identifiers.POSE_RANDOMIZED),
        BLOCK(Identifiers.POSE_BLOCK),
        ITEM(Identifiers.POSE_ITEM)
    }

    enum class PositionIncrements {
        MINUS_8,
        MINUS_3,
        MINUS_1,
        PLUS_1,
        PLUS_3,
        PLUS_8
    }

    enum class BodyParts {
        HEAD,
        BODY,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG
    }

    private val client = MinecraftClient.getInstance()

    fun check() {
        AscCommands.executeArmorStandTrigger(client, Identifiers.CHECK)
    }

    fun showBasePlate(show: Boolean) {
        if (show) AscCommands.executeArmorStandTrigger(client, Identifiers.SHOW_BASE_PLATE)
        else AscCommands.executeArmorStandTrigger(client, Identifiers.HIDE_BASE_PLATE)
    }

    fun showArms(show: Boolean) {
        if (show) executeArmorStandTrigger(client, Identifiers.SHOW_ARMS)
        else executeArmorStandTrigger(client, Identifiers.HIDE_ARMS)
    }

    fun showStand(show: Boolean) {
        if (show) executeArmorStandTrigger(client, Identifiers.SHOW_STAND)
        else executeArmorStandTrigger(client, Identifiers.HIDE_STAND)
    }

    fun showName(show: Boolean) {
        if (show) executeArmorStandTrigger(client, Identifiers.SHOW_NAME)
        else executeArmorStandTrigger(client, Identifiers.HIDE_NAME)
    }

    fun enableSmallStand(enable: Boolean) {
        if (enable) executeArmorStandTrigger(client, Identifiers.ENABLE_SMALL_STAND)
        else executeArmorStandTrigger(client, Identifiers.DISABLE_SMALL_STAND)
    }

    fun enableGravity(enable: Boolean) {
        if (enable) executeArmorStandTrigger(client, Identifiers.ENABLE_GRAVITY)
        else executeArmorStandTrigger(client, Identifiers.DISABLE_GRAVITY)
    }

    fun setPosePreset(preset: PosePresets) {
        executeArmorStandTrigger(client, preset.id)
    }

    fun nudgePositionX(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P8)
            null -> {}
        }
    }

    fun nudgePositionY(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P8)
            null -> {}
        }
    }

    fun nudgePositionZ(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P8)
            null -> {}
        }
    }

    fun nudgePositionAlignedX(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P8)
            null -> {}
        }
    }

    fun nudgePositionAlignedZ(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P8)
            null -> {}
        }
    }

    fun nudgePositionExactX(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P8)
            null -> {}
        }
    }

    fun nudgePositionExactY(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P8)
            null -> {}
        }
    }

    fun nudgePositionExactZ(increment: PositionIncrements?) {
        when (increment) {
            PositionIncrements.MINUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M1)
            PositionIncrements.MINUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M3)
            PositionIncrements.MINUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M8)
            PositionIncrements.PLUS_1 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P1)
            PositionIncrements.PLUS_3 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P3)
            PositionIncrements.PLUS_8 -> executeArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P8)
            null -> {}
        }
    }

    fun setRotationStepTo1() {
        executeArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_1)
    }

    fun setRotationStepTo5() {
        executeArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_5)
    }

    fun setRotationStepTo15() {
        executeArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_15)
    }

    fun setRotationStepTo45() {
        executeArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_45)
    }

    fun rotateLeft() {
        executeArmorStandTrigger(client, Identifiers.ROTATE_LEFT)
    }

    fun rotateRight() {
        executeArmorStandTrigger(client, Identifiers.ROTATE_RIGHT)
    }

    fun rotateToward() {
        executeArmorStandTrigger(client, Identifiers.ROTATE_TOWARD)
    }

    fun rotateAway() {
        executeArmorStandTrigger(client, Identifiers.ROTATE_AWAY)
    }

    fun pointHeadTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_HEAD_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_HEAD_TO_FEET)
    }

    fun pointBodyTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_BODY_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_BODY_TO_FEET)
    }

    fun pointRightArmTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_RIGHT_ARM_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_RIGHT_ARM_TO_FEET)
    }

    fun pointLeftArmTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_LEFT_ARM_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_LEFT_ARM_TO_FEET)
    }

    fun pointRightLegTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_RIGHT_LEG_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_RIGHT_LEG_TO_FEET)
    }

    fun pointLeftLegTo(toHead: Boolean) {
        if (toHead) executeArmorStandTrigger(client, Identifiers.POINT_LEFT_LEG_TO_HEAD)
        else executeArmorStandTrigger(client, Identifiers.POINT_LEFT_LEG_TO_FEET)
    }

    fun adjustPoseX(increment: Boolean, bodyPart: BodyParts) {
        val id: Identifiers = when (bodyPart) {
            BodyParts.HEAD -> if (increment) Identifiers.POSE_ADJ_HEAD_X_PLUS else Identifiers.POSE_ADJ_HEAD_X_MINUS
            BodyParts.BODY -> if (increment) Identifiers.POSE_ADJ_BODY_X_PLUS else Identifiers.POSE_ADJ_BODY_X_MINUS
            BodyParts.LEFT_ARM -> if (increment) Identifiers.POSE_ADJ_LEFT_ARM_X_PLUS else Identifiers.POSE_ADJ_LEFT_ARM_X_MINUS
            BodyParts.RIGHT_ARM -> if (increment) Identifiers.POSE_ADJ_RIGHT_ARM_X_PLUS else Identifiers.POSE_ADJ_RIGHT_ARM_X_MINUS
            BodyParts.LEFT_LEG -> if (increment) Identifiers.POSE_ADJ_LEFT_LEG_X_PLUS else Identifiers.POSE_ADJ_LEFT_LEG_X_MINUS
            BodyParts.RIGHT_LEG -> if (increment) Identifiers.POSE_ADJ_RIGHT_LEG_X_PLUS else Identifiers.POSE_ADJ_RIGHT_LEG_X_MINUS
        }

        executeArmorStandTrigger(client, id)
    }

    fun adjustPoseY(increment: Boolean, bodyPart: BodyParts) {
        val id: Identifiers = when (bodyPart) {
            BodyParts.HEAD -> if (increment) Identifiers.POSE_ADJ_HEAD_Y_PLUS else Identifiers.POSE_ADJ_HEAD_Y_MINUS
            BodyParts.BODY -> if (increment) Identifiers.POSE_ADJ_BODY_Y_PLUS else Identifiers.POSE_ADJ_BODY_Y_MINUS
            BodyParts.LEFT_ARM -> if (increment) Identifiers.POSE_ADJ_LEFT_ARM_Y_PLUS else Identifiers.POSE_ADJ_LEFT_ARM_Y_MINUS
            BodyParts.RIGHT_ARM -> if (increment) Identifiers.POSE_ADJ_RIGHT_ARM_Y_PLUS else Identifiers.POSE_ADJ_RIGHT_ARM_Y_MINUS
            BodyParts.LEFT_LEG -> if (increment) Identifiers.POSE_ADJ_LEFT_LEG_Y_PLUS else Identifiers.POSE_ADJ_LEFT_LEG_Y_MINUS
            BodyParts.RIGHT_LEG -> if (increment) Identifiers.POSE_ADJ_RIGHT_LEG_Y_PLUS else Identifiers.POSE_ADJ_RIGHT_LEG_Y_MINUS
        }

        executeArmorStandTrigger(client, id)
    }

    fun adjustPoseZ(increment: Boolean, bodyPart: BodyParts) {
        val id: Identifiers = when (bodyPart) {
            BodyParts.HEAD -> if (increment) Identifiers.POSE_ADJ_HEAD_Z_PLUS else Identifiers.POSE_ADJ_HEAD_Z_MINUS
            BodyParts.BODY -> if (increment) Identifiers.POSE_ADJ_BODY_Z_PLUS else Identifiers.POSE_ADJ_BODY_Z_MINUS
            BodyParts.LEFT_ARM -> if (increment) Identifiers.POSE_ADJ_LEFT_ARM_Z_PLUS else Identifiers.POSE_ADJ_LEFT_ARM_Z_MINUS
            BodyParts.RIGHT_ARM -> if (increment) Identifiers.POSE_ADJ_RIGHT_ARM_Z_PLUS else Identifiers.POSE_ADJ_RIGHT_ARM_Z_MINUS
            BodyParts.LEFT_LEG -> if (increment) Identifiers.POSE_ADJ_LEFT_LEG_Z_PLUS else Identifiers.POSE_ADJ_LEFT_LEG_Z_MINUS
            BodyParts.RIGHT_LEG -> if (increment) Identifiers.POSE_ADJ_RIGHT_LEG_Z_PLUS else Identifiers.POSE_ADJ_RIGHT_LEG_Z_MINUS
        }

        executeArmorStandTrigger(client, id)
    }

    fun setBlockOnSurface() {
        executeArmorStandTrigger(client, Identifiers.SET_BLOCK_ON_SURFACE)
    }

    fun setItemOnSurface() {
        executeArmorStandTrigger(client, Identifiers.SET_ITEM_ON_SURFACE)
    }

    fun setItemFlatOnSurface() {
        executeArmorStandTrigger(client, Identifiers.SET_ITEM_FLAT_ON_SURFACE)
    }

    fun setToolFlatOnSurface() {
        executeArmorStandTrigger(client, Identifiers.SET_TOOL_FLAT_ON_SURFACE)
    }

    fun setToolRack() {
        executeArmorStandTrigger(client, Identifiers.SET_TOOL_RACK)
    }

    fun swapMainHandOffhand() {
        executeArmorStandTrigger(client, Identifiers.SWAP_MAINHAND_OFFHAND)
    }

    fun swapMainHandHead() {
        executeArmorStandTrigger(client, Identifiers.SWAP_MAINHAND_HEAD)
    }

    fun mirrorArmsLeftToRight() {
        executeArmorStandTrigger(client, Identifiers.MIRROR_ARMS_LR)
    }

    fun mirrorArmsRightToLeft() {
        executeArmorStandTrigger(client, Identifiers.MIRROR_ARMS_RL)
    }

    fun mirrorLegsLeftToRight() {
        executeArmorStandTrigger(client, Identifiers.MIRROR_LEGS_LR)
    }

    fun mirrorLegsRightToLeft() {
        executeArmorStandTrigger(client, Identifiers.MIRROR_LEGS_RL)
    }

    fun flip() {
        executeArmorStandTrigger(client, Identifiers.FLIP)
    }

    fun copy() {
        executeArmorStandTrigger(client, Identifiers.COPY)
    }

    fun paste() {
        executeArmorStandTrigger(client, Identifiers.PASTE)
    }

    fun lock(lock: Boolean) {
        if (lock) executeArmorStandTrigger(client, Identifiers.LOCK)
        else executeArmorStandTrigger(client, Identifiers.UNLOCK)
    }

    fun seal(seal: Boolean) {
        if (seal) executeCommand(client, "function armor_statues:sealing/seal")
        else executeCommand(client, "function armor_statues:sealing/unseal")
    }
}