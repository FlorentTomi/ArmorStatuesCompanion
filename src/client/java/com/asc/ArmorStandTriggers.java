package com.asc;

import com.asc.AscCommand;

import net.minecraft.client.MinecraftClient;

public class ArmorStandTriggers {
    enum Identifiers {
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
        UNLOCK(1001);

        private final int value;

        private Identifiers(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum PosePresets {
        ATTENTION,
        CONFIDENT,
        WALKING,
        SALUTE,
        RUNNING,
        DEATH,
        POINTING,
        FACEPALM,
        BLOCKING,
        LAZING,
        LUNGEING,
        CONFUSED,
        WINNING,
        FORMAL,
        SITTING,
        SAD,
        ARABESQUE,
        JOYOUS,
        CUPID,
        STARGAZING,
        RANDOMIZED,
        BLOCK,
        ITEM
    }

    enum PositionIncrements {
        MINUS_8,
        MINUS_3,
        MINUS_1,
        PLUS_1,
        PLUS_3,
        PLUS_8
    }

    public static void Check(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.CHECK);
    }

    public static void ShowBasePlate(MinecraftClient client, boolean show) {
        if (show) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SHOW_BASE_PLATE);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.HIDE_BASE_PLATE);
        }
    }

    public static void ShowArms(MinecraftClient client, boolean show) {
        if (show) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SHOW_ARMS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.HIDE_ARMS);
        }
    }

    public static void ShowStand(MinecraftClient client, boolean show) {
        if (show) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SHOW_STAND);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.HIDE_STAND);
        }
    }

    public static void ShowName(MinecraftClient client, boolean show) {
        if (show) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SHOW_NAME);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.HIDE_NAME);
        }
    }

    public static void EnableSmallStand(MinecraftClient client, boolean enable) {
        if (enable) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ENABLE_SMALL_STAND);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.DISABLE_SMALL_STAND);
        }
    }

    public static void EnableGravity(MinecraftClient client, boolean enable) {
        if (enable) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ENABLE_GRAVITY);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.DISABLE_GRAVITY);
        }
    }

    public static void SetPosePreset(MinecraftClient client, PosePresets preset) {
        switch (preset) {
            case ATTENTION:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ATTENTION);
                break;
            case CONFIDENT:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_CONFIDENT);
                break;
            case WALKING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_WALKING);
                break;
            case SALUTE:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_SALUTE);
                break;
            case RUNNING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_RUNNING);
                break;
            case DEATH:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_DEATH);
                break;
            case POINTING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_POINTING);
                break;
            case FACEPALM:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_FACEPALM);
                break;
            case BLOCKING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_BLOCKING);
                break;
            case LAZING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_LAZING);
                break;
            case LUNGEING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_LUNGEING);
                break;
            case CONFUSED:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_CONFUSED);
                break;
            case WINNING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_WINNING);
                break;
            case FORMAL:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_FORMAL);
                break;
            case SITTING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_SITTING);
                break;
            case SAD:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_SAD);
                break;
            case ARABESQUE:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ARABESQUE);
                break;
            case JOYOUS:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_JOYOUS);
                break;
            case CUPID:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_CUPID);
                break;
            case STARGAZING:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_STARGAZING);
                break;
            case RANDOMIZED:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_RANDOMIZED);
                break;
            case BLOCK:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_BLOCK);
                break;
            case ITEM:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ITEM);
                break;
        }
    }

    public static void NudgePositionX(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_P8);
                break;
        }
    }

    public static void NudgePositionY(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_P8);
                break;
        }
    }

    public static void NudgePositionZ(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_P8);
                break;
        }
    }

    public static void NudgePositionAlignedX(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_ALIGNED_P8);
                break;
        }
    }

    public static void NudgePositionAlignedZ(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_ALIGNED_P8);
                break;
        }
    }

    public static void NudgePositionExactX(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_X_EXACT_P8);
                break;
        }
    }

    public static void NudgePositionExactY(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Y_EXACT_P8);
                break;
        }
    }

    public static void NudgePositionExactZ(MinecraftClient client, PositionIncrements increment) {
        switch (increment) {
            case MINUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M1);
                break;
            case MINUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M3);
                break;
            case MINUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_M8);
                break;
            case PLUS_1:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P1);
                break;
            case PLUS_3:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P3);
                break;
            case PLUS_8:
                AscCommand.ExecuteArmorStandTrigger(client, Identifiers.NUDGE_POS_Z_EXACT_P8);
                break;
        }
    }

    public static void SetRotationStepTo1(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_1);
    }

    public static void SetRotationStepTo5(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_5);
    }

    public static void SetRotationStepTo15(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_15);
    }

    public static void SetRotationStepTo45(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ROTATION_STEP_45);
    }

    public static void RotateLeft(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ROTATE_LEFT);
    }

    public static void RotateRight(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ROTATE_RIGHT);
    }

    public static void RotateToward(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ROTATE_TOWARD);
    }

    public static void RotateAway(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.ROTATE_AWAY);
    }

    public static void PointHeadTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_HEAD_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_HEAD_TO_FEET);
        }
    }

    public static void PointBodyTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_BODY_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_BODY_TO_FEET);
        }
    }

    public static void PointRightArmTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_RIGHT_ARM_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_RIGHT_ARM_TO_FEET);
        }
    }

    public static void PointLeftArmTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_LEFT_ARM_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_LEFT_ARM_TO_FEET);
        }
    }

    public static void PointRightLegTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_RIGHT_LEG_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_RIGHT_LEG_TO_FEET);
        }
    }

    public static void PointLeftLegTo(MinecraftClient client, boolean toHead) {
        if (toHead) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_LEFT_LEG_TO_HEAD);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POINT_LEFT_LEG_TO_FEET);
        }
    }

    public static void AdjustPoseHeadX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_X_MINUS);
        }
    }

    public static void AdjustPoseHeadY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_Y_MINUS);
        }
    }

    public static void AdjustPoseHeadZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_HEAD_Z_MINUS);
        }
    }

    public static void AdjustPoseBodyX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_X_MINUS);
        }
    }

    public static void AdjustPoseBodyY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_Y_MINUS);
        }
    }

    public static void AdjustPoseBodyZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_BODY_Z_MINUS);
        }
    }

    public static void AdjustPoseRightArmX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_X_MINUS);
        }
    }

    public static void AdjustPoseRightArmY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_Y_MINUS);
        }
    }

    public static void AdjustPoseRightArmZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_ARM_Z_MINUS);
        }
    }

    public static void AdjustPoseLeftArmX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_X_MINUS);
        }
    }

    public static void AdjustPoseLeftArmY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_Y_MINUS);
        }
    }

    public static void AdjustPoseLeftArmZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_ARM_Z_MINUS);
        }
    }

    public static void AdjustPoseRightLegX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_X_MINUS);
        }
    }

    public static void AdjustPoseRightLegY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_Y_MINUS);
        }
    }

    public static void AdjustPoseRightLegZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_RIGHT_LEG_Z_MINUS);
        }
    }

    public static void AdjustPoseLeftLegX(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_X_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_X_MINUS);
        }
    }

    public static void AdjustPoseLeftLegY(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_Y_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_Y_MINUS);
        }
    }

    public static void AdjustPoseLeftLegZ(MinecraftClient client, boolean increment) {
        if (increment) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_Z_PLUS);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.POSE_ADJ_LEFT_LEG_Z_MINUS);
        }
    }

    public static void SetBlockOnSurface(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_BLOCK_ON_SURFACE);
    }

    public static void SetItemOnSurface(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ITEM_ON_SURFACE);
    }

    public static void SetItemFlatOnSurface(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_ITEM_FLAT_ON_SURFACE);
    }

    public static void SetToolFlatOnSurface(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_TOOL_FLAT_ON_SURFACE);
    }

    public static void SetToolRack(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SET_TOOL_RACK);
    }

    public static void SwapMainHandOffhand(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SWAP_MAINHAND_OFFHAND);
    }

    public static void SwapMainHandHead(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.SWAP_MAINHAND_HEAD);
    }

    public static void MirrorArmsLeftToRight(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.MIRROR_ARMS_LR);
    }

    public static void MirrorArmsRightToLeft(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.MIRROR_ARMS_RL);
    }

    public static void MirrorLegsLeftToRight(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.MIRROR_LEGS_LR);
    }

    public static void MirrorLegsRightToLeft(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.MIRROR_LEGS_RL);
    }

    public static void Flip(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.FLIP);
    }

    public static void Copy(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.COPY);
    }

    public static void Paste(MinecraftClient client) {
        AscCommand.ExecuteArmorStandTrigger(client, Identifiers.PASTE);
    }

    public static void Lock(MinecraftClient client, boolean lock) {
        if (lock) {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.LOCK);
        } else {
            AscCommand.ExecuteArmorStandTrigger(client, Identifiers.UNLOCK);
        }
    }

    public static void Seal(MinecraftClient client, boolean seal) {
        if (seal) {
            AscCommand.ExecuteCommand(client, "function armor_statues:sealing/seal");
        } else {
            AscCommand.ExecuteCommand(client, "function armor_statues:sealing/unseal");
        }
    }
}
