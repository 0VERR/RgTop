package pl.overr.top.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.overr.top.data.DataManager;
import pl.overr.top.mysql.MySQL;
import pl.overr.top.user.User;

import java.util.UUID;

public class PlayerDeathListener implements Listener {

    private final DataManager dataManager;


    public PlayerDeathListener(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @EventHandler
    public void onPlayerDeathListener(PlayerDeathEvent event){
        Player deather = event.getEntity().getPlayer();
        UUID deatherUUID = deather.getUniqueId();

        User user = dataManager.getUser(deatherUUID);
        user.setDeaths((user.getDeaths() + 1));

        Player killer = deather.getKiller();
        if (killer == null)return;

        UUID killerUUID = killer.getUniqueId();

        User deatherKiller = dataManager.getUser(killerUUID);
        deatherKiller.setKills((deatherKiller.getKills() + 1));
    }
}
