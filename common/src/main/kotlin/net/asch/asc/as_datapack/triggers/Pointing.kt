package net.asch.asc.as_datapack.triggers

enum class Pointing(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    head(Trigger.create<Direction>(1160)),
    body(Trigger.create<Direction>(1161)),
    left_arm(Trigger.create<Direction>(1163)),
    right_arm(Trigger.create<Direction>(1162)),
    left_leg(Trigger.create<Direction>(1165)),
    right_leg(Trigger.create<Direction>(1164));

    enum class Direction(private val offset: Int) : ArmorStatuesTriggerOffset {
        to_head(0),
        to_feet(6);

        override fun offset(): Int = offset
    }

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}