package net.asch.asc

import net.fabricmc.api.ClientModInitializer

class ModClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeyMappings.register()
    }

    companion object {
        const val MOD_ID = "armor-statues-companion"
    }
}