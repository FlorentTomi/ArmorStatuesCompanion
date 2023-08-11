package net.asch.asc.as_datapack.triggers

enum class Style(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    base_plate(Trigger.create<Boolean>(1)),
    arms(Trigger.create<Boolean>(3)),
    small_stand(Trigger.create<Boolean>(5)),
    gravity(Trigger.create<Boolean>(7)),
    stand(Trigger.create<Boolean>(9)),
//    stand_name(Trigger.create<Boolean>(11)),
    visual_fire(Trigger.create<Boolean>(13));

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}