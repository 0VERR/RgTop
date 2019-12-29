package pl.overr.top.tasks;

import org.bukkit.Bukkit;
import pl.overr.top.data.DataManager;
import pl.overr.top.user.User;

import java.util.concurrent.TimeUnit;

public class PlayerPlayTimeTask implements Runnable {

    private final DataManager dataManager;

    public PlayerPlayTimeTask(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void run(){
        Bukkit.getOnlinePlayers().forEach(x ->{
            User user = dataManager.getUser(x.getUniqueId());
            user.setTimeplayed((user.getTimeplayed() + TimeUnit.SECONDS.toMillis(30)));
        });

    }
}
