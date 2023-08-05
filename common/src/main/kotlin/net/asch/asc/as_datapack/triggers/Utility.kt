package net.asch.asc.as_datapack.triggers

enum class Utility(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    highlight(Trigger.create<Unit>(999)),
    copy(Trigger.create<Unit>(1004)),
    paste(Trigger.create<Unit>(1005)),
    lock(Trigger.create<Unit>(1000)),
    unlock(Trigger.create<Unit>(1001)),
    undo(Trigger.create<Unit>(1200)),
    redo(Trigger.create<Unit>(1201));

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}