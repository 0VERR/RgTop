package pl.overr.top.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.overr.top.inventory.TopInventory;

public class TopCommand implements CommandExecutor {
    private final TopInventory topInventory;

    public TopCommand(TopInventory topInventory) {
        this.topInventory = topInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        player.openInventory(topInventory.getTopsInventory());
        return true;
    }
}
