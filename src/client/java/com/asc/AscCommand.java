package com.asc;

import net.minecraft.client.MinecraftClient;
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
        MinecraftServer server = client.player.getServer();
        if (server == null) {
            IntegratedServer integratedServer = client.getServer();
            ExecuteCommand(integratedServer, command);
        } else {
            ExecuteCommand(server, command);
        }
    }

    private static void ExecuteCommand(MinecraftServer server, String command) {
        CommandManager cmdManager = server.getCommandManager();
        cmdManager.executeWithPrefix(server.getCommandSource(), "/execute as @p run " + command);
    }
}
