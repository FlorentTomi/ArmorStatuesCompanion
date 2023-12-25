package net.asch.asc.as_datapack.triggers

enum class Alignment(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    block_to_surface(Trigger.create<Unit>(151)),
    item_to_surface_upright(Trigger.create<Unit>(152)),
    item_to_surface_flat(Trigger.create<Unit>(153)),
    tool_to_surface_flat(Trigger.create<Unit>(154)),
    rack(Trigger.create<Unit>(155));

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}