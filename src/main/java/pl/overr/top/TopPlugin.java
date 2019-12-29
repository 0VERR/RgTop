package pl.overr.top;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.overr.top.commands.TopCommand;
import pl.overr.top.data.DataManager;
import pl.overr.top.inventory.TopInventory;
import pl.overr.top.listeners.InventoryClickListener;
import pl.overr.top.listeners.PlayerDeathListener;
import pl.overr.top.listeners.PlayerJoinListener;
import pl.overr.top.listeners.PlayerMinerListener;
import pl.overr.top.mysql.MySQL;
import pl.overr.top.tasks.PlayerPlayTimeTask;
import pl.overr.top.tasks.RefreshTopsTask;

public class TopPlugin extends JavaPlugin {

    static TopPlugin topPlugin;

    public static TopPlugin getInstance(){
        return topPlugin;
    }


    private DataManager dataManager;
    private MySQL mySQL;

    public void onEnable(){
        topPlugin = this;
        dataManager = new DataManager();
        mySQL = new MySQL(dataManager);

        saveDefaultConfig();

        mySQL.createTable();
        mySQL.loadUsers();

        TopInventory topInventory = new TopInventory(dataManager);
        topInventory.generateTopsInventoyItems();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(dataManager,mySQL), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(dataManager),this);
        getServer().getPluginManager().registerEvents(new PlayerMinerListener(dataManager), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(topInventory), this);

        getCommand("top").setExecutor(new TopCommand(topInventory));

        Bukkit.getScheduler().runTaskTimer(this, new PlayerPlayTimeTask(dataManager), 30, 30 * 20);
        Bukkit.getScheduler().runTaskTimer(this, new RefreshTopsTask(topInventory), 30, 30 * 20 );
    }

    public void onDisable(){
        mySQL.updateUser();
    }
}
