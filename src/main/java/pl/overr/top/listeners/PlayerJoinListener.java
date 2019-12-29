package pl.overr.top.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.overr.top.data.DataManager;
import pl.overr.top.mysql.MySQL;
import pl.overr.top.user.User;

import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final DataManager dataManager;
    private final MySQL mySQL;

    public PlayerJoinListener(DataManager dataManager, MySQL mySQL){

        this.dataManager = dataManager;
        this.mySQL = mySQL;
    }


    @EventHandler
    public void onJoinListener(PlayerJoinEvent event){
        Player joiner = event.getPlayer();
        UUID joinerUUID = joiner.getUniqueId();

        if (dataManager.getUserMap().containsKey(joinerUUID)) return;


        String[] ipchar = joiner.getAddress().toString().split(":");
        String ip = ipchar[0];

        User user = new User(joinerUUID.toString(), joiner.getName(),ip,0,0,0,0,0);
        mySQL.insertUser(user);
        dataManager.addToUserMap(user);


    }
}
