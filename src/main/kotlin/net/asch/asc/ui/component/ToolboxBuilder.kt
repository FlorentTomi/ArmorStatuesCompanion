package net.asch.asc.ui.component

import io.wispforest.owo.ui.base.BaseParentComponent
import io.wispforest.owo.ui.component.ButtonComponent
import io.wispforest.owo.ui.component.ButtonComponent.Renderer
import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.component.LabelComponent
import io.wispforest.owo.ui.container.CollapsibleContainer
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import io.wispforest.owo.ui.util.NinePatchTexture
import net.asch.asc.ModClient
import net.asch.asc.as_datapack.triggers.*
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.BiConsumer
import java.util.function.Consumer

object ToolboxBuilder {
    private const val PADDING = 0
    private const val GAP = 2
    private const val MARGINS = 1

    private val COLLAPSIBLE_TITLE_TEXTURE_ID = Identifier.of(ModClient.MOD_ID, "toolbar_button/active")
    private val COLLAPSIBLE_TITLE_SURFACE: Surface = Surface { context, component ->
        NinePatchTexture.draw(COLLAPSIBLE_TITLE_TEXTURE_ID, context, component)
    }

    private val EXPANDED: MutableMap<String, Boolean> = mutableMapOf()

    fun styleBuilder(mainLayout: FlowLayout, key: String) {
        fun addLine(action: Style, keyTrue: String, keyFalse: String, grid: GridLayout, row: Int) {
            grid.child(label("$action"), row, 0)
            grid.child(getInfo("$action"), row, 1)
            grid.child(
                buttonGroup(listOf(
                        OnOffButtonProperties(action, true, keyTrue),
                        OnOffButtonProperties(action, false, keyFalse)
                    )), row, 2
            )
        }

        val toolbox = GridToolboxBuilder(key, columns = 3)
            .add { grid, row -> addLine(Style.base_plate, "show", "hide", grid, row) }
            .add { grid, row -> addLine(Style.arms, "show", "hide", grid, row) }
            .add { grid, row -> addLine(Style.stand, "show", "hide", grid, row) }
            .add { grid, row -> addLine(Style.stand_name, "show", "hide", grid, row) }
            .add { grid, row -> addLine(Style.visual_fire, "show", "hide", grid, row) }
            .add { grid, row -> addLine(Style.small_stand, "enable", "disable", grid, row) }
            .add { grid, row -> addLine(Style.gravity, "enable", "disable", grid, row) }
            .build()
        mainLayout.child(toolbox)
    }

    fun positionBuilder(mainLayout: FlowLayout, key: String) {
        fun addPositionLine(action: Position, grid: GridLayout, row: Int, infoKey: String? = null, warningKey: String? = null) {
            grid.child(label("$action"), row, 0)
            grid.child(positionGroup(action, infoKey, warningKey), row, 1)
        }

        fun addAlignmentLine(action: Alignment, flow: FlowLayout, infoKey: String? = null) {
            val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
            layout.verticalAlignment(VerticalAlignment.CENTER)
            layout.gap(GAP)
            layout.child(button(action, Unit))
            if (infoKey != null) {
                layout.child(getInfo(infoKey))
            }
            flow.child(layout)
        }

        val toolbox = FlowToolboxBuilder(key)
            .add { flow ->
                val innerToolbox = GridToolboxBuilder("position", defaultExpanded = false, tooltipKey = "relative_position")
                    .add { grid, row -> addPositionLine(Position.x, grid, row) }
                    .add { grid, row -> addPositionLine(Position.y, grid, row, warningKey = "warn_gravity") }
                    .add { grid, row -> addPositionLine(Position.z, grid, row) }
                    .build()
                flow.child(innerToolbox)
            }
            .add { flow ->
                val innerToolbox = GridToolboxBuilder("aligned_position", defaultExpanded = false, tooltipKey = "aligned_position")
                    .add { grid, row -> addPositionLine(Position.aligned_x, grid, row) }
                    .add { grid, row -> addPositionLine(Position.aligned_z, grid, row) }
                    .build()
                flow.child(innerToolbox)
            }
            .add { flow ->
                val innerToolbox = GridToolboxBuilder("exact_position", defaultExpanded = false, tooltipKey = "exact_position")
                    .add { grid, row -> addPositionLine(Position.exact_x, grid, row) }
                    .add { grid, row -> addPositionLine(Position.exact_y, grid, row, warningKey = "warn_gravity") }
                    .add { grid, row -> addPositionLine(Position.exact_z, grid, row) }
                    .build()
                flow.child(innerToolbox)
            }
            .add { flow ->
                val innerToolbox = FlowToolboxBuilder("alignment", defaultExpanded = false)
                    .add { innerFlow -> addAlignmentLine(Alignment.block_to_surface, innerFlow) }
                    .add { innerFlow -> addAlignmentLine(Alignment.item_to_surface_upright, innerFlow) }
                    .add { innerFlow -> addAlignmentLine(Alignment.item_to_surface_flat, innerFlow) }
                    .add { innerFlow -> addAlignmentLine(Alignment.tool_to_surface_flat, innerFlow) }
                    .add { innerFlow -> addAlignmentLine(Alignment.rack, innerFlow, infoKey = "rack") }
                    .build()
                flow.child(innerToolbox)
            }
            .build()

        mainLayout.child(toolbox)
    }

    fun rotationBuilder(mainLayout: FlowLayout, key: String) {
        fun addAdjustmentsLine(action: Rotation, grid: GridLayout, row: Int) {
            grid.child(label("$action"), row, 0)
            grid.child(adjustmentGroup(action), row, 1)
        }

        val toolbox = FlowToolboxBuilder(key)
            .add { flow ->
                flow.child(Components.label(Text.translatable("asc.screen.angle_steps")))
                flow.child(angleStepGroup())
            }
            .add { innerFlow ->
                val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
                layout.verticalAlignment(VerticalAlignment.CENTER)
                layout.gap(GAP)
                layout.child(Components.label(Text.translatable("asc.screen.rotations")))
                innerFlow.child(layout)
                innerFlow.child(rotationGroup())
            }
            .add { flow ->
                val innerToolbox = GridToolboxBuilder("adjustments", defaultExpanded = false)
                    .add { grid, row -> addAdjustmentsLine(Rotation.head, grid, row) }
                    .add { grid, row -> addAdjustmentsLine(Rotation.body, grid, row) }
                    .add { grid, row -> addAdjustmentsLine(Rotation.left_arm, grid, row) }
                    .add { grid, row -> addAdjustmentsLine(Rotation.right_arm, grid, row) }
                    .add { grid, row -> addAdjustmentsLine(Rotation.left_leg, grid, row) }
                    .add { grid, row -> addAdjustmentsLine(Rotation.right_leg, grid, row) }
                    .build()
                flow.child(innerToolbox)
            }
            .build()

        mainLayout.child(toolbox)
    }

    fun poseBuilder(mainLayout: FlowLayout, key: String) {
        fun addPointingLine(action: Pointing, grid: GridLayout, row: Int, startColumn: Int) {
            grid.child(label("$action"), row, startColumn)
            grid.child(buttonGroup<Pointing.Direction>(action), row, startColumn + 1)
        }

        fun addSwapMainHandLine(grid: GridLayout, row: Int) {
            grid.child(Components.label(Text.translatable("asc.screen.swap_main_hand")), row, 0)
            grid.child(buttonGroup<StandUtility.SwapTarget>(StandUtility.swap_main_hand), row, 1)
        }

        fun addMirrorLine(action: StandUtility, grid: GridLayout, row: Int) {
            grid.child(label("$action"), row, 0)
            grid.child(buttonGroup<StandUtility.MirrorDirection>(action), row, 1)
        }

        val toolbox = FlowToolboxBuilder(key)
            .add { flow ->
                val innerToolbox = GridToolboxBuilder("pointing", columns = 4, defaultExpanded = false, tooltipKey = "pointing")
                    .add { grid, row ->
                        addPointingLine(Pointing.head, grid, row, 0)
                        addPointingLine(Pointing.body, grid, row, 2)
                    }.add { grid, row ->
                        addPointingLine(Pointing.left_arm, grid, row, 0)
                        addPointingLine(Pointing.right_arm, grid, row, 2)
                    }.add { grid, row ->
                        addPointingLine(Pointing.left_leg, grid, row, 0)
                        addPointingLine(Pointing.right_leg, grid, row, 2)
                    }.build()
                flow.child(innerToolbox)
            }
            .add { flow ->
                val innerToolbox = FlowToolboxBuilder("utility", defaultExpanded = false)
                    .add { innerFlow ->
                        val layout = GridLayout(Sizing.content(), Sizing.content(), 3, 2)
                        layout.verticalAlignment(VerticalAlignment.CENTER)
                        layout.horizontalAlignment(HorizontalAlignment.LEFT)
                        layout.margins(Insets.of(MARGINS))
                        layout.gap(GAP)
                        addSwapMainHandLine(layout, 0)
                        addMirrorLine(StandUtility.mirror_arms, layout, 1)
                        addMirrorLine(StandUtility.mirror_legs, layout, 2)
                        innerFlow.child(layout)
                    }
                    .add { innerFlow -> innerFlow.child(button(StandUtility.flip, Unit)) }
                    .build()
                flow.child(innerToolbox)
            }
            .build()

        mainLayout.child(toolbox)
    }

    private fun label(key: String) : LabelComponent {
        return Components.label(Text.translatable("asc.screen.$key"))
    }

    private fun button(trigger: ArmorStatuesTriggers, value: Any, key: String = "$trigger",
                       renderer: Renderer? = null, tooltipKey: String? = null) : ButtonComponent {
        val btn = Components.button(Text.translatable("asc.screen.$key")) { trigger.accept(value)  }
        addTooltip(btn, tooltipKey)
        if (renderer != null) {
            btn.renderer(renderer)
        } else {
            btn.renderer(ButtonTextures.DEFAULT_RENDERER)
        }

        return btn
    }

    private fun buttonGroup(btnProperties: Collection<ButtonProperties>): FlowLayout {
        val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.gap(GAP)
        for (btnProperty in btnProperties) {
            layout.child(button(btnProperty.action, btnProperty.value, btnProperty.key, btnProperty.renderer))
        }

        return layout
    }

    private inline fun <reified E : Enum<E>> buttonGroup(action: ArmorStatuesTriggers): FlowLayout {
        return buttonGroup(E::class.java.enumConstants.map { e -> BasicButtonProperties(action, e, "$e") })
    }

    private fun angleStepGroup(): FlowLayout {
        val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.gap(GAP)
        layout.child(button(Rotation.set_step_angle, Rotation.StepAngles.deg_1, "deg_1"))
        layout.child(button(Rotation.set_step_angle, Rotation.StepAngles.deg_5, "deg_5"))
        layout.child(button(Rotation.set_step_angle, Rotation.StepAngles.deg_15, "deg_15"))
        layout.child(button(Rotation.set_step_angle, Rotation.StepAngles.deg_45, "deg_45"))

        return layout
    }

    private fun positionGroup(action: Position, infoKey: String? = null, warningKey: String? = null): FlowLayout {
        val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.gap(GAP)
        if (action == Position.x || action == Position.y || action == Position.z) {
            layout.child(button(action, Position.Offset.negative_8, "negative_8"))
            layout.child(button(action, Position.Offset.negative_3, "negative_3"))
            layout.child(button(action, Position.Offset.negative_1, "negative_1"))
        } else {
            layout.child(button(action, Position.AlignedExactOffset.negative_8, "negative_8"))
            layout.child(button(action, Position.AlignedExactOffset.negative_3, "negative_3"))
            layout.child(button(action, Position.AlignedExactOffset.negative_1, "negative_1"))
        }
        layout.child(Components.label(Text.literal("-")))
        if (action == Position.x || action == Position.y || action == Position.z) {
            layout.child(button(action, Position.Offset.positive_1, "positive_1"))
            layout.child(button(action, Position.Offset.positive_3, "positive_3"))
            layout.child(button(action, Position.Offset.positive_8, "positive_8"))
        } else {
            layout.child(button(action, Position.AlignedExactOffset.positive_1, "positive_1"))
            layout.child(button(action, Position.AlignedExactOffset.positive_3, "positive_3"))
            layout.child(button(action, Position.AlignedExactOffset.positive_8, "positive_8"))
        }

        if (infoKey != null) {
            layout.child(getInfo(infoKey))
        }

        addWarning(layout, warningKey)

        return layout
    }

    private fun rotationGroup(): FlowLayout {
        val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.gap(GAP)
        layout.child(button(Rotation.rotate, Rotation.Direction.left, "left"))
        layout.child(button(Rotation.rotate, Rotation.Direction.right, "right"))
        layout.child(button(Rotation.rotate, Rotation.Direction.toward, "toward"))
        layout.child(button(Rotation.rotate, Rotation.Direction.away, "away"))
        return layout
    }

    private fun adjustmentGroup(action: Rotation): FlowLayout {
        val layout = Containers.horizontalFlow(Sizing.content(), Sizing.content())
        layout.verticalAlignment(VerticalAlignment.CENTER)
        layout.gap(GAP)
        layout.child(button(action, Rotation.AxisDirection.negative_x, "negative_x"))
        layout.child(button(action, Rotation.AxisDirection.positive_x, "positive_x"))
        layout.child(Components.label(Text.literal("-")))
        layout.child(button(action, Rotation.AxisDirection.negative_y, "negative_y"))
        layout.child(button(action, Rotation.AxisDirection.positive_y, "positive_y"))
        layout.child(Components.label(Text.literal("-")))
        layout.child(button(action, Rotation.AxisDirection.negative_z, "negative_z"))
        layout.child(button(action, Rotation.AxisDirection.positive_z, "positive_z"))
        return layout
    }

    private fun addTooltip(component: Component, key: String?) {
        if (key != null) {
            component.tooltip(Text.translatable("asc.screen.tooltip.$key"))
        }
    }

    private fun getInfo(key: String?): LabelComponent {
        val infoLbl = Components.label(Text.literal("\uD83D\uDEC8"))
        infoLbl.color(Color.BLUE)
        addTooltip(infoLbl, key)
        return infoLbl
    }

    private fun addWarning(layout: FlowLayout, key: String?) {
        if (key != null) {
            val warningLbl = Components.label(Text.literal("⚠"))
            warningLbl.color(Color.ofArgb(0xFFFFFF00.toInt()))
            addTooltip(warningLbl, key)
            layout.child(warningLbl)
        }
    }

    private interface ButtonProperties {
        val action: ArmorStatuesTriggers
        val value: Any
        val key: String
        val renderer: Renderer?
    }

    private data class BasicButtonProperties(
        override val action: ArmorStatuesTriggers,
        override val value: Any,
        override val key: String = "$action",
        override val renderer: Renderer? = null
    ) : ButtonProperties

    private data class OnOffButtonProperties(
        override val action: ArmorStatuesTriggers,
        override val value: Boolean,
        override val key: String = "$action",
        override val renderer: Renderer = if (value) ButtonTextures.ON_RENDERER else ButtonTextures.OFF_RENDERER
    ) : ButtonProperties

    private abstract class ToolboxBuilder<C>(private val key: String, private val defaultExpanded: Boolean = true, private val tooltipKey: String? = null) {
        val consumerList: MutableList<C> = mutableListOf()

        fun add(consumer: C): ToolboxBuilder<C> {
            consumerList.add(consumer)
            return this
        }

        fun build(): CollapsibleContainer {
            val collapsible = Containers.collapsible(
                Sizing.content(),
                Sizing.content(),
                Text.translatable("asc.screen.$key"),
                EXPANDED.getOrDefault(key, defaultExpanded)
            )
            collapsible.titleLayout().surface(COLLAPSIBLE_TITLE_SURFACE)
            collapsible.onToggled().subscribe { nowExpanded -> EXPANDED[key] = nowExpanded }
            if (tooltipKey != null) {
                collapsible.titleLayout().child(getInfo(tooltipKey))
                collapsible.titleLayout().padding(Insets.of(5, 5, 5, 5))
            }

            val layout = create()
            populate(layout)
            collapsible.child(layout)

            return collapsible
        }

        protected abstract fun create(): BaseParentComponent
        protected abstract fun populate(layout: BaseParentComponent)
    }

    private class GridToolboxBuilder(key: String, private val columns: Int = 2, defaultExpanded: Boolean = true, tooltipKey: String? = null) :
        ToolboxBuilder<BiConsumer<GridLayout, Int>>(key, defaultExpanded, tooltipKey) {
        override fun create(): BaseParentComponent {
            val layout = GridLayout(Sizing.content(), Sizing.content(), consumerList.size, columns)
            layout.verticalAlignment(VerticalAlignment.CENTER)
            layout.horizontalAlignment(HorizontalAlignment.LEFT)
            layout.margins(Insets.of(MARGINS))
            layout.gap(GAP)
            return layout
        }

        override fun populate(layout: BaseParentComponent) {
            for (row in 0 until consumerList.size) {
                consumerList[row].accept(layout as GridLayout, row)
            }
        }
    }

    private class FlowToolboxBuilder(key: String, defaultExpanded: Boolean = true, tooltipKey: String? = null) :
        ToolboxBuilder<Consumer<FlowLayout>>(key, defaultExpanded, tooltipKey) {
        override fun create(): BaseParentComponent {
            val layout = Containers.verticalFlow(Sizing.content(PADDING), Sizing.content(PADDING))
            layout.verticalAlignment(VerticalAlignment.CENTER)
            layout.horizontalAlignment(HorizontalAlignment.LEFT)
            layout.margins(Insets.of(MARGINS))
            layout.gap(GAP)
            return layout
        }

        override fun populate(layout: BaseParentComponent) {
            for (consumer in consumerList) {
                consumer.accept(layout as FlowLayout)
            }
        }
    }
}
