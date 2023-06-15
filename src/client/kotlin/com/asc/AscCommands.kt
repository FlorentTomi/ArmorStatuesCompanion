package com.asc

import net.minecraft.client.MinecraftClient

object AscCommands {
    fun executeArmorStandTrigger(client: MinecraftClient, triggerId: AscTriggers.Identifiers) {
        executeCommand(client, "trigger as_trigger set ${triggerId.id}")
    }

    fun executeCommand(client: MinecraftClient, command: String) {
        val integratedServer = client.server
        if (integratedServer != null) {
            val chatCommand = "/execute as @p run $command"
            val commandManager = integratedServer.commandManager
            commandManager.executeWithPrefix(integratedServer.commandSource, chatCommand)
        }
        else {
            val networkHandler = client.networkHandler
            networkHandler?.sendChatCommand(command)
        }
    }
}