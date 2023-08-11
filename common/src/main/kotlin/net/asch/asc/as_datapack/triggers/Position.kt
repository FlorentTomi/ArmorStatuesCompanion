package net.asch.asc.as_datapack.triggers

enum class Position(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    x(Trigger.create<Offset>(40)),
    y(Trigger.create<Offset>(44)),
    z(Trigger.create<Offset>(48)),

    aligned_x(Trigger.create<AlignedExactOffset>(1100)),
    aligned_z(Trigger.create<AlignedExactOffset>(1106)),

    exact_x(Trigger.create<AlignedExactOffset>(1112)),
    exact_y(Trigger.create<AlignedExactOffset>(1118)),
    exact_z(Trigger.create<AlignedExactOffset>(1124));

    enum class Offset(private val offset: Int) : ArmorStatuesTriggerOffset {
        negative_8(0), positive_8(3),
        negative_3(61), positive_3(64),
        negative_1(62), positive_1(63);

        override fun offset(): Int = offset
    }

    enum class AlignedExactOffset(private val offset: Int) : ArmorStatuesTriggerOffset {
        negative_8(0), positive_8(5),
        negative_3(1), positive_3(4),
        negative_1(2), positive_1(3);

        override fun offset(): Int = offset
    }

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}