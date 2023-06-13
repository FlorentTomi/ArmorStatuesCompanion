package com.asc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.integrated.IntegratedServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AscCommand {
    public static void ExecuteArmorStandTrigger(MinecraftClient client, ArmorStandTriggers.Identifiers triggerId) {
        ExecuteCommand(client, String.format("trigger as_trigger set %d", triggerId.getValue()));
    }

    public static void ExecuteCommand(MinecraftClient client, String command) {
        IntegratedServer integratedServer = client.getServer();
        String chatCommand = "/execute as @p run " + command;
        if (integratedServer != null) {
            CommandManager cmdManager = integratedServer.getCommandManager();
            cmdManager.executeWithPrefix(integratedServer.getCommandSource(), chatCommand);
        } else {
            ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
            networkHandler.sendChatCommand(chatCommand);
        }
    }
}
