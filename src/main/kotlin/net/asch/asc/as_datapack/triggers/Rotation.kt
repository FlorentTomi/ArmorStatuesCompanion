package net.asch.asc.as_datapack.triggers

enum class Rotation(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    set_step_angle(Trigger.create<StepAngles>(120)),

    rotate(Trigger.create<Direction>(56)),

    head(Trigger.create<AxisDirection>(60)),
    body(Trigger.create<AxisDirection>(66)),
    right_arm(Trigger.create<AxisDirection>(72)),
    left_arm(Trigger.create<AxisDirection>(78)),
    right_leg(Trigger.create<AxisDirection>(84)),
    left_leg(Trigger.create<AxisDirection>(90));

    enum class StepAngles(private val offset: Int) : ArmorStatuesTriggerOffset {
        deg_45(0),
        deg_15(1),
        deg_5(2),
        deg_1(3);

        override fun offset(): Int = offset
    }

    enum class Direction(private val offset: Int) : ArmorStatuesTriggerOffset {
        left(0),
        right(1),
        toward(68),
        away(69);

        override fun offset(): Int = offset
    }

    enum class AxisDirection(private val offset: Int) : ArmorStatuesTriggerOffset {
        negative_x(0),
        positive_x(1),
        negative_y(2),
        positive_y(3),
        negative_z(4),
        positive_z(5);

        override fun offset(): Int = offset
    }

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}