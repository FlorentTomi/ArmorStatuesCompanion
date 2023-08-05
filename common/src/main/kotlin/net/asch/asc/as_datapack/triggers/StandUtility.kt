package net.asch.asc.as_datapack.triggers

enum class StandUtility(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    swap_main_hand(Trigger.create<SwapTarget>(161)),

    mirror_arms(Trigger.create<MirrorDirection>(131)),
    mirror_legs(Trigger.create<MirrorDirection>(133)),

    flip(Trigger.create<Unit>(135));

    enum class SwapTarget(private val offset: Int): ArmorStatuesTriggerOffset {
        with_offhand(0),
        with_head(1);

        override fun offset(): Int = offset
    }

    enum class MirrorDirection(private val offset: Int) : ArmorStatuesTriggerOffset {
        left_to_right(0),
        right_to_left(1);

        override fun offset(): Int = offset
    }

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}