package fr.quentin.cleanclaims;

import me.angeschossen.lands.api.land.Land;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClaimsQueue {

    public static List<Land> globalQueue = new ArrayList<>();

    private final List<Land> queue = new ArrayList<>();

    public void retrieveClaimsToRemove() {
        for (Land land : Cleanclaims.getLandsIntegration().getLands()) {
            if (hasAllPlayersInactive(land) && hasMinChunks(land)) {
                queue.add(land);
            }
        }

        globalQueue = new ArrayList<>(queue);
    }

    public List<Land> getQueue() {
        return queue;
    }

    private boolean hasAllPlayersInactive(Land land) {

        for (UUID uuid : land.getTrustedPlayers()) {


            if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
                return false;
            }

            if ((System.currentTimeMillis() - Bukkit.getOfflinePlayer(uuid).getLastPlayed()) < (Configuration.MIN_SECONDS_WITH_LAST_JOIN * 1000L )) {
                return false;
            }
        }

        return true;
    }


    private boolean hasMinChunks(Land land) {

        int totalChunks = 0;

        for (World world : Bukkit.getWorlds()) {

            if (land.getChunks(world) == null) {
                continue;
            }

            totalChunks = totalChunks + land.getChunks(world).size();
        }

        return totalChunks <= Configuration.MAX_NUMBER_OF_CHUNKS;
    }

}
