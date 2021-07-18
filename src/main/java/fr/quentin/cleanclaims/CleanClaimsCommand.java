package fr.quentin.cleanclaims;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CleanClaimsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1 && args[0].equals(Configuration.COMMAND_RETRIEVE_QUEUE[0])) {
            Cleanclaims.logInfo(sender, Configuration.MESSAGE_START_RETRIEVING);

            Bukkit.getScheduler().runTaskAsynchronously(Cleanclaims.getInstance(), () -> {
                ClaimsQueue queue = new ClaimsQueue();
                queue.retrieveClaimsToRemove();
                Cleanclaims.logInfo(sender, String.format(Configuration.MESSAGE_NUMBER_OF_CLAIMS_TO_REMOVE_FOUND, queue.getQueue().size()));
            });

            return true;

        } else if (args.length == 1 && args[0].equals(Configuration.COMMAND_CHECK_QUEUE[0])) {

            if (ClaimsQueue.globalQueue.isEmpty()) {
                Cleanclaims.logInfo(sender, Configuration.MESSAGE_QUEUE_EMPTY);
                return true;
            }

            ClaimsQueue.globalQueue.forEach(land -> Cleanclaims.logInfo(sender, land.getName()));

            return true;

        } else if (args.length == 1 && args[0].equals(Configuration.COMMAND_CLEAN_QUEUE[0])) {

            Bukkit.getScheduler().runTaskAsynchronously(Cleanclaims.getInstance(), () -> {

                if (ClaimsQueue.globalQueue.isEmpty()) {
                    Cleanclaims.logInfo(sender, Configuration.MESSAGE_QUEUE_EMPTY);
                } else {
                    ClaimsQueue.globalQueue.forEach(land -> {
                        if (Configuration.SEND_BANK_BALANCE_AND_CASHBACK_TO_OWNER) {
                            land.delete(Bukkit.getPlayer(land.getOwnerUID()));
                        } else {
                            land.delete(null);
                        }
                    });
                    Cleanclaims.logInfo(sender, Configuration.MESSAGE_QUEUE_CLEANED);
                }
            });

            return true;

        } else if (args.length == 1 && args[0].equals(Configuration.COMMAND_RETRIEVE_AND_CLEAN_QUEUE[0])) {

            Cleanclaims.logInfo(sender, Configuration.MESSAGE_START_RETRIEVING);

            Bukkit.getScheduler().runTaskAsynchronously(Cleanclaims.getInstance(), () -> {
                ClaimsQueue queue = new ClaimsQueue();
                queue.retrieveClaimsToRemove();
                Cleanclaims.logInfo(sender, String.format(Configuration.MESSAGE_NUMBER_OF_CLAIMS_TO_REMOVE_FOUND, queue.getQueue().size()));

                ClaimsQueue.globalQueue.forEach(land -> {
                    if (Configuration.SEND_BANK_BALANCE_AND_CASHBACK_TO_OWNER) {
                        land.delete((Player) Bukkit.getPlayer(land.getOwnerUID()));
                    } else {
                        land.delete(null);
                    }
                });

                Cleanclaims.logInfo(sender, Configuration.MESSAGE_QUEUE_CLEANED);
            });

            return true;

        } else {
            Cleanclaims.sendMessage(sender,"-----------------------------------");
            Cleanclaims.sendMessage(sender, String.format("/%s %s", command.getName(), Configuration.COMMAND_RETRIEVE_QUEUE[0]));
            Cleanclaims.sendMessage(sender, Configuration.COMMAND_RETRIEVE_QUEUE[1]);
            Cleanclaims.sendMessage(sender, " ");
            Cleanclaims.sendMessage(sender, String.format("/%s %s", command.getName(), Configuration.COMMAND_CHECK_QUEUE[0]));
            Cleanclaims.sendMessage(sender, Configuration.COMMAND_CHECK_QUEUE[1]);
            Cleanclaims.sendMessage(sender," ");
            Cleanclaims.sendMessage(sender, String.format("/%s %s", command.getName(), Configuration.COMMAND_CLEAN_QUEUE[0]));
            Cleanclaims.sendMessage(sender, Configuration.COMMAND_CLEAN_QUEUE[1]);
            Cleanclaims.sendMessage(sender," ");
            Cleanclaims.sendMessage(sender, String.format("/%s %s", command.getName(), Configuration.COMMAND_RETRIEVE_AND_CLEAN_QUEUE[0]));
            Cleanclaims.sendMessage(sender, Configuration.COMMAND_RETRIEVE_AND_CLEAN_QUEUE[1]);
            Cleanclaims.sendMessage(sender, " ");
            Cleanclaims.sendMessage(sender, "-----------------------------------");
            return true;
        }

    }

}
