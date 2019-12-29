package pl.overr.top.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.overr.top.data.DataManager;
import pl.overr.top.user.User;

import java.util.UUID;

public class PlayerMinerListener implements Listener {

    private final DataManager dataManager;

    public PlayerMinerListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @EventHandler
    public void onStoneBreakListener(BlockBreakEvent event){
        Material type = event.getBlock().getType();

        if (type == Material.STONE){

            Player miner = event.getPlayer();
            UUID minerUUID = miner.getUniqueId();

            User user = dataManager.getUser(minerUUID);
            user.setMinedStone((user.getMinedStone() + 1));
        }

        if (type == Material.LOG || type == Material.LOG_2){

            Player miner = event.getPlayer();
            UUID minerUUID = miner.getUniqueId();

            User user = dataManager.getUser(minerUUID);
            user.setMinedStone((user.getMinedWood() + 1));
        }
    }
}
