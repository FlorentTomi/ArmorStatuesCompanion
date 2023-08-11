package net.asch.asc.mixin;

import net.asch.asc.ModClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.ScoreboardPlayerUpdateS2CPacket;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.scoreboard.ServerScoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow private ClientWorld world;

    @Inject(at = @At("TAIL"), method = "onScoreboardPlayerUpdate")
    public void onScoreboardPlayerUpdate(ScoreboardPlayerUpdateS2CPacket packet, CallbackInfo info) {
        if (packet.getUpdateMode() == ServerScoreboard.UpdateMode.CHANGE) {
            Scoreboard scoreboard = this.world.getScoreboard();
            String objectiveName = packet.getObjectiveName();
            ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
            String playerName = packet.getPlayerName();
            ScoreboardPlayerScore playerScore = scoreboard.getPlayerScore(playerName, objective);

            ModClient.onPlayerScoreboardUpdate(playerName, objectiveName, playerScore.getScore());
        }
    }
}
