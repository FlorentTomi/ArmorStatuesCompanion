package net.asch.asc.as_datapack.triggers

enum class Presets(val trigger: ArmorStatuesTrigger): ArmorStatuesTriggers {
    arabesque(Trigger.create<Unit>(28)),
    attention(Trigger.create<Unit>(20)),
    blocking(Trigger.create<Unit>(24)),
    confident(Trigger.create<Unit>(30)),
    confused(Trigger.create<Unit>(35)),
    cupid(Trigger.create<Unit>(29)),
    death(Trigger.create<Unit>(32)),
    facepalm(Trigger.create<Unit>(33)),
    formal(Trigger.create<Unit>(36)),
    joyous(Trigger.create<Unit>(38)),
    lazing(Trigger.create<Unit>(34)),
    lungeing(Trigger.create<Unit>(25)),
    pointing(Trigger.create<Unit>(23)),
    running(Trigger.create<Unit>(22)),
    sad(Trigger.create<Unit>(37)),
    salute(Trigger.create<Unit>(31)),
    stargazing(Trigger.create<Unit>(39)),
    sitting(Trigger.create<Unit>(27)),
    walking(Trigger.create<Unit>(21)),
    winning(Trigger.create<Unit>(26)),

    block(Trigger.create<Unit>(141)),
    item(Trigger.create<Unit>(142)),

    randomized(Trigger.create<Unit>(1150));

    override fun trigger(): ArmorStatuesTrigger = trigger
    override fun accept(value: Any) = trigger.accept(value)
}