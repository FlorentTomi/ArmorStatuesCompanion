package net.asch.asc.as_datapack.triggers

import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.minecraft.client.MinecraftClient
import java.util.function.Consumer
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

abstract class ArmorStatuesTrigger: Consumer<Any> {
    inline fun <reified V> acceptType(): Boolean = acceptClass(V::class.java)
    abstract fun <V> acceptClass(clazz: Class<V>): Boolean
}

interface ArmorStatuesTriggerOffset {
    fun offset(): Int
}

interface ArmorStatuesTriggers: Consumer<Any> {
    fun trigger(): ArmorStatuesTrigger
}

class Trigger <T: Any>(private val id: Int, private val clazz: KClass<T>) : ArmorStatuesTrigger() {
    companion object {
        inline fun <reified T: Any> create(id: Int) = Trigger(id, T::class)
    }

    override fun accept(value: Any) {
        val offset = getOffset(value)
        print("id=$id + $offset")
        ArmorStatuesHelper.trigger(MinecraftClient.getInstance(), id + offset)
    }

    override fun <V> acceptClass(clazz: Class<V>): Boolean = this.clazz == clazz

    private fun getOffset(value: Any): Int {
        if (clazz.isSubclassOf(ArmorStatuesTriggerOffset::class)) {
            return (value as ArmorStatuesTriggerOffset).offset()
        }

        return when (clazz) {
            Unit::class -> 0
            Boolean::class -> if (value as Boolean) 0 else 1
            else -> throw AssertionError()
        }
    }
}

