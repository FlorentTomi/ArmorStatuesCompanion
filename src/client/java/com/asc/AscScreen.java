package com.asc;

import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.container.CollapsibleContainer;
import io.wispforest.owo.ui.core.Component;
import io.wispforest.owo.ui.core.ParentComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class AscScreen extends BaseUIModelScreen<FlowLayout> {
    static private final Map<String, Boolean> COLLAPSIBLE_STATES = new HashMap<>(Map.of(
            "STYLE", true,
            "NUDGE", false,
            "ROTATION", false,
            "POINTING", false,
            "POSE_ADJ", false,
            "AUTO_ALIGN", false,
            "SWAP_SLOTS", false,
            "MIRROR_FLIP", false,
            "UTILS", false));

    final private MinecraftClient client;

    public AscScreen(MinecraftClient client) {
        super(FlowLayout.class, DataSource.asset(Identifier.of("asc", "main_model")));
//        super(FlowLayout.class, DataSource.file("main_model.xml"));

        this.client = client;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        addTrigger(rootComponent, "CHECK", ArmorStandTriggers::Check);

        buildStyle(rootComponent);
        buildNudge(rootComponent);
        buildRotation(rootComponent);
        buildPointing(rootComponent);
        buildPoseAdjust(rootComponent);
        buildAutoAlign(rootComponent);
        buildSwapSlots(rootComponent);
        buildMirrorFlip(rootComponent);
        buildUtils(rootComponent);

        for (var entry : COLLAPSIBLE_STATES.entrySet()) {
            addCollapsible(rootComponent, entry.getKey(), entry.getValue());
        }
    }

    private void buildStyle(FlowLayout rootComponent) {
        addTrigger(rootComponent, "SHOW_BASE_PLATE", ArmorStandTriggers::ShowBasePlate, true);
        addTrigger(rootComponent, "HIDE_BASE_PLATE", ArmorStandTriggers::ShowBasePlate, false);

        addTrigger(rootComponent, "SHOW_ARMS", ArmorStandTriggers::ShowArms, true);
        addTrigger(rootComponent, "HIDE_ARMS", ArmorStandTriggers::ShowArms, false);

        addTrigger(rootComponent, "SHOW_STAND", ArmorStandTriggers::ShowStand, true);
        addTrigger(rootComponent, "HIDE_STAND", ArmorStandTriggers::ShowStand, false);

        addTrigger(rootComponent, "SHOW_NAME", ArmorStandTriggers::ShowName, true);
        addTrigger(rootComponent, "HIDE_NAME", ArmorStandTriggers::ShowName, false);

        addTrigger(rootComponent, "ENABLE_SMALL_STAND", ArmorStandTriggers::EnableSmallStand, true);
        addTrigger(rootComponent, "DISABLE_SMALL_STAND", ArmorStandTriggers::EnableSmallStand, false);

        addTrigger(rootComponent, "ENABLE_GRAVITY", ArmorStandTriggers::EnableGravity, true);
        addTrigger(rootComponent, "DISABLE_GRAVITY", ArmorStandTriggers::EnableGravity, false);
    }

    private void buildNudge(FlowLayout rootComponent) {
        addTrigger(rootComponent, "NUDGE_POS_X_M8", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_X_M3", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_M1", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_P1", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_P3", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_P8", ArmorStandTriggers::NudgePositionX, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_Y_M8", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_Y_M3", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Y_M1", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Y_P1", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Y_P3", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Y_P8", ArmorStandTriggers::NudgePositionY, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_Z_M8", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_Z_M3", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_M1", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_P1", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_P3", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_P8", ArmorStandTriggers::NudgePositionZ, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_M8", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_M3", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_M1", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_P1", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_P3", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_ALIGNED_P8", ArmorStandTriggers::NudgePositionAlignedX, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_M8", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_M3", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_M1", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_P1", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_P3", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_ALIGNED_P8", ArmorStandTriggers::NudgePositionAlignedZ, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_M8", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_M3", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_M1", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_P1", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_P3", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_X_EXACT_P8", ArmorStandTriggers::NudgePositionExactX, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_M8", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_M3", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_M1", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_P1", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_P3", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Y_EXACT_P8", ArmorStandTriggers::NudgePositionExactY, ArmorStandTriggers.PositionIncrements.PLUS_8);

        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_M8", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.MINUS_8);
        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_M3", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.MINUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_M1", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.MINUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_P1", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.PLUS_1);
        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_P3", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.PLUS_3);
        addTrigger(rootComponent, "NUDGE_POS_Z_EXACT_P8", ArmorStandTriggers::NudgePositionExactZ, ArmorStandTriggers.PositionIncrements.PLUS_8);
    }

    private void buildRotation(FlowLayout rootComponent) {
        addTrigger(rootComponent, "ROTATE_LEFT", ArmorStandTriggers::RotateLeft);
        addTrigger(rootComponent, "SET_ROTATION_STEP_45", ArmorStandTriggers::SetRotationStepTo45);
        addTrigger(rootComponent, "SET_ROTATION_STEP_15", ArmorStandTriggers::SetRotationStepTo15);
        addTrigger(rootComponent, "SET_ROTATION_STEP_5", ArmorStandTriggers::SetRotationStepTo5);
        addTrigger(rootComponent, "SET_ROTATION_STEP_1", ArmorStandTriggers::SetRotationStepTo1);
        addTrigger(rootComponent, "ROTATE_RIGHT", ArmorStandTriggers::RotateRight);
        addTrigger(rootComponent, "ROTATE_TOWARD", ArmorStandTriggers::RotateToward);
        addTrigger(rootComponent, "ROTATE_AWAY", ArmorStandTriggers::RotateAway);
    }

    private void buildPointing(FlowLayout rootComponent) {
        addTrigger(rootComponent, "POINT_HEAD_TO_HEAD", ArmorStandTriggers::PointHeadTo, true);
        addTrigger(rootComponent, "POINT_HEAD_TO_FEET", ArmorStandTriggers::PointHeadTo, false);

        addTrigger(rootComponent, "POINT_BODY_TO_HEAD", ArmorStandTriggers::PointBodyTo, true);
        addTrigger(rootComponent, "POINT_BODY_TO_FEET", ArmorStandTriggers::PointBodyTo, false);

        addTrigger(rootComponent, "POINT_RIGHT_ARM_TO_HEAD", ArmorStandTriggers::PointRightArmTo, true);
        addTrigger(rootComponent, "POINT_RIGHT_ARM_TO_FEET", ArmorStandTriggers::PointRightArmTo, false);

        addTrigger(rootComponent, "POINT_LEFT_ARM_TO_HEAD", ArmorStandTriggers::PointLeftArmTo, true);
        addTrigger(rootComponent, "POINT_LEFT_ARM_TO_FEET", ArmorStandTriggers::PointLeftArmTo, false);

        addTrigger(rootComponent, "POINT_RIGHT_LEG_TO_HEAD", ArmorStandTriggers::PointRightLegTo, true);
        addTrigger(rootComponent, "POINT_RIGHT_LEG_TO_FEET", ArmorStandTriggers::PointRightLegTo, false);

        addTrigger(rootComponent, "POINT_LEFT_LEG_TO_HEAD", ArmorStandTriggers::PointLeftLegTo, true);
        addTrigger(rootComponent, "POINT_LEFT_LEG_TO_FEET", ArmorStandTriggers::PointLeftLegTo, false);
    }

    private void buildPoseAdjust(FlowLayout rootComponent) {
        addTrigger(rootComponent, "POSE_ADJ_HEAD_X_MINUS", ArmorStandTriggers::AdjustPoseHeadX, true);
        addTrigger(rootComponent, "POSE_ADJ_HEAD_X_PLUS", ArmorStandTriggers::AdjustPoseHeadX, false);
        addTrigger(rootComponent, "POSE_ADJ_HEAD_Y_MINUS", ArmorStandTriggers::AdjustPoseHeadY, true);
        addTrigger(rootComponent, "POSE_ADJ_HEAD_Y_PLUS", ArmorStandTriggers::AdjustPoseHeadY, false);
        addTrigger(rootComponent, "POSE_ADJ_HEAD_Z_MINUS", ArmorStandTriggers::AdjustPoseHeadZ, true);
        addTrigger(rootComponent, "POSE_ADJ_HEAD_Z_PLUS", ArmorStandTriggers::AdjustPoseHeadZ, false);

        addTrigger(rootComponent, "POSE_ADJ_BODY_X_MINUS", ArmorStandTriggers::AdjustPoseBodyX, true);
        addTrigger(rootComponent, "POSE_ADJ_BODY_X_PLUS", ArmorStandTriggers::AdjustPoseBodyX, false);
        addTrigger(rootComponent, "POSE_ADJ_BODY_Y_MINUS", ArmorStandTriggers::AdjustPoseBodyY, true);
        addTrigger(rootComponent, "POSE_ADJ_BODY_Y_PLUS", ArmorStandTriggers::AdjustPoseBodyY, false);
        addTrigger(rootComponent, "POSE_ADJ_BODY_Z_MINUS", ArmorStandTriggers::AdjustPoseBodyZ, true);
        addTrigger(rootComponent, "POSE_ADJ_BODY_Z_PLUS", ArmorStandTriggers::AdjustPoseBodyZ, false);

        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_X_MINUS", ArmorStandTriggers::AdjustPoseRightArmX, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_X_PLUS", ArmorStandTriggers::AdjustPoseRightArmX, false);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_Y_MINUS", ArmorStandTriggers::AdjustPoseRightArmY, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_Y_PLUS", ArmorStandTriggers::AdjustPoseRightArmY, false);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_Z_MINUS", ArmorStandTriggers::AdjustPoseRightArmZ, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_ARM_Z_PLUS", ArmorStandTriggers::AdjustPoseRightArmZ, false);

        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_X_MINUS", ArmorStandTriggers::AdjustPoseLeftArmX, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_X_PLUS", ArmorStandTriggers::AdjustPoseLeftArmX, false);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_Y_MINUS", ArmorStandTriggers::AdjustPoseLeftArmY, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_Y_PLUS", ArmorStandTriggers::AdjustPoseLeftArmY, false);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_Z_MINUS", ArmorStandTriggers::AdjustPoseLeftArmZ, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_ARM_Z_PLUS", ArmorStandTriggers::AdjustPoseLeftArmZ, false);

        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_X_MINUS", ArmorStandTriggers::AdjustPoseRightLegX, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_X_PLUS", ArmorStandTriggers::AdjustPoseRightLegX, false);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_Y_MINUS", ArmorStandTriggers::AdjustPoseRightLegY, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_Y_PLUS", ArmorStandTriggers::AdjustPoseRightLegY, false);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_Z_MINUS", ArmorStandTriggers::AdjustPoseRightLegZ, true);
        addTrigger(rootComponent, "POSE_ADJ_RIGHT_LEG_Z_PLUS", ArmorStandTriggers::AdjustPoseRightLegZ, false);

        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_X_MINUS", ArmorStandTriggers::AdjustPoseLeftLegX, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_X_PLUS", ArmorStandTriggers::AdjustPoseLeftLegX, false);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_Y_MINUS", ArmorStandTriggers::AdjustPoseLeftLegY, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_Y_PLUS", ArmorStandTriggers::AdjustPoseLeftLegY, false);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_Z_MINUS", ArmorStandTriggers::AdjustPoseLeftLegZ, true);
        addTrigger(rootComponent, "POSE_ADJ_LEFT_LEG_Z_PLUS", ArmorStandTriggers::AdjustPoseLeftLegZ, false);
    }

    private void buildAutoAlign(FlowLayout rootComponent) {
        addTrigger(rootComponent, "SET_BLOCK_ON_SURFACE", ArmorStandTriggers::SetBlockOnSurface);
        addTrigger(rootComponent, "SET_ITEM_ON_SURFACE", ArmorStandTriggers::SetItemOnSurface);
        addTrigger(rootComponent, "SET_ITEM_FLAT_ON_SURFACE", ArmorStandTriggers::SetItemFlatOnSurface);
        addTrigger(rootComponent, "SET_TOOL_FLAT_ON_SURFACE", ArmorStandTriggers::SetToolFlatOnSurface);
        addTrigger(rootComponent, "SET_TOOL_RACK", ArmorStandTriggers::SetToolRack);
    }

    private void buildSwapSlots(FlowLayout rootComponent) {
        addTrigger(rootComponent, "SWAP_MAINHAND_OFFHAND", ArmorStandTriggers::SwapMainHandOffhand);
        addTrigger(rootComponent, "SWAP_MAINHAND_HEAD", ArmorStandTriggers::SwapMainHandHead);
    }

    private void buildMirrorFlip(FlowLayout rootComponent) {
        addTrigger(rootComponent, "MIRROR_ARMS_LR", ArmorStandTriggers::MirrorArmsLeftToRight);
        addTrigger(rootComponent, "MIRROR_ARMS_RL", ArmorStandTriggers::MirrorArmsRightToLeft);
        addTrigger(rootComponent, "MIRROR_LEGS_LR", ArmorStandTriggers::MirrorLegsLeftToRight);
        addTrigger(rootComponent, "MIRROR_LEGS_RL", ArmorStandTriggers::MirrorLegsRightToLeft);
        addTrigger(rootComponent, "FLIP", ArmorStandTriggers::Flip);
    }

    private void buildUtils(FlowLayout rootComponent) {
        addTrigger(rootComponent, "COPY", ArmorStandTriggers::Copy);
        addTrigger(rootComponent, "PASTE", ArmorStandTriggers::Paste);
        addTrigger(rootComponent, "LOCK", ArmorStandTriggers::Lock, true);
        addTrigger(rootComponent, "UNLOCK", ArmorStandTriggers::Lock, false);
    }

    private void addTrigger(FlowLayout rootComponent, String id, Consumer<MinecraftClient> fn) {
        rootComponent.childById(ButtonComponent.class, id).onPress(bindButton(fn));
    }

    private <T> void addTrigger(FlowLayout rootComponent, String id, BiConsumer<MinecraftClient, T> fn, T val) {
        rootComponent.childById(ButtonComponent.class, id).onPress(bindButton(fn, val));
    }

    private void addCollapsible(FlowLayout rootComponent, String id, Boolean collapsed) {
        CollapsibleContainer collapse = rootComponent.childById(CollapsibleContainer.class, id);
        if (collapse.expanded() != collapsed) {
            collapse.toggleExpansion();
        }
        collapse.onToggled().subscribe(nowExpanded -> AscScreen.COLLAPSIBLE_STATES.replace(id, nowExpanded));
    }

    private Consumer<ButtonComponent> bindButton(Consumer<MinecraftClient> fn) {
        return button -> trigger(() -> fn.accept(this.client));
    }

    private <T> Consumer<ButtonComponent> bindButton(BiConsumer<MinecraftClient, T> fn, T val) {
        return button -> trigger(() -> fn.accept(this.client, val));
    }

    private void trigger(Runnable runnable) {
        if (this.client.getServer() != null) {
            this.close();
        }

        runnable.run();
    }
}
