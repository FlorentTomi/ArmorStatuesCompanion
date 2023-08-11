package net.asch.asc.fabric

import net.asch.asc.ModClient
import net.asch.asc.as_datapack.ArmorStatuesHelper
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler

class ModClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModClient.initialize()
    }
}