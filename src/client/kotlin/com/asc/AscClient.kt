package com.asc

import net.fabricmc.api.ClientModInitializer

class AscClient : ClientModInitializer {
    override fun onInitializeClient() {
        AscKeybinding.registerKeybindings()
    }
}