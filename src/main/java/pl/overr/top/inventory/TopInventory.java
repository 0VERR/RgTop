package pl.overr.top.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.overr.top.data.DataManager;
import pl.overr.top.user.User;
import pl.overr.top.utils.ColorUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class TopInventory {

    private final DataManager dataManager;

    public TopInventory(DataManager dataManager) {
        this.dataManager = dataManager;
        generateTopsInventoyItems();
        createTopsInventory();
        createTopStoneInventory();
        createTopDeathsInventory();
        createTopKillsInventory();
        createTopMinedWoodInventory();
        createTopPlayedTimeInventory();
        loadInventories();
    }

    private int i = 1;
    private int slot = 9;

    public void loadInventories(){
        createTopPlayedTimeInventory();
        createTopMinedWoodInventory();
        createTopKillsInventory();
        createTopPlayedTimeInventory();
        createTopDeathsInventory();
        createTopStoneInventory();
    }

    public Inventory getTopStoneInventory() {
        return topStoneInventory;
    }

    private Inventory topStoneInventory = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTop Kopaczy"));

    public Inventory getTopsInventory() {
        return topsInventory;
    }

    private Inventory topsInventory = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTopki"));

    public Inventory getTopsPlayedTime() {
        return topsPlayedTime;
    }

    private Inventory topsPlayedTime = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTop spedzonego czasu"));

    public Inventory getTopDeathsTime() {
        return topDeathsTime;
    }

    private Inventory topDeathsTime = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTop smierci"));

    public Inventory getTopKillsItem() {
        return topKillsItem;
    }

    private Inventory topKillsItem = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTop killi"));

    public Inventory getTopMinedWoodInventory() {
        return topMinedWoodInventory;
    }

    private Inventory topMinedWoodInventory = Bukkit.createInventory(null,27, ColorUtil.colorFix("&aTop zrabanego drewna"));

    public Inventory getYourStats() {
        return yourStats;
    }

    private Inventory yourStats = Bukkit.createInventory(null, 27, ColorUtil.colorFix("&aTwoje statystyki"));

    private ItemStack stoneItem;
    private ItemStack timePlayedItem;
    private ItemStack deathsItem;
    private ItemStack killsItem;
    private ItemStack woodItem;
    private ItemStack backItem;
    private ItemStack infoItem;

    public void generateTopsInventoyItems() {
        stoneItem = new ItemStack(Material.STONE);
        ItemMeta stoneItemMeta = stoneItem.getItemMeta();
        stoneItemMeta.setDisplayName(ColorUtil.colorFix("&aNajlepsi Kopacze"));
        stoneItem.setItemMeta(stoneItemMeta);

        timePlayedItem = new ItemStack(Material.WATCH);
        ItemMeta timePlayedItemMeta = timePlayedItem.getItemMeta();
        timePlayedItemMeta.setDisplayName(ColorUtil.colorFix("&aNajwieksze Nolify"));
        timePlayedItem.setItemMeta(timePlayedItemMeta);

        deathsItem = new ItemStack(Material.DIRT);
        ItemMeta deathsItemMeta = deathsItem.getItemMeta();
        deathsItemMeta.setDisplayName(ColorUtil.colorFix("&aNajwieksze Nooby"));
        deathsItem.setItemMeta(deathsItemMeta);

        killsItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta killsItemMeta = killsItem.getItemMeta();
        killsItemMeta.setDisplayName(ColorUtil.colorFix("&aNajwieksze Koxy"));
        killsItem.setItemMeta(killsItemMeta);

        woodItem = new ItemStack(Material.LOG);
        ItemMeta woodItemMeta = woodItem.getItemMeta();
        woodItemMeta.setDisplayName(ColorUtil.colorFix("&aNajlepsi drwale"));
        woodItem.setItemMeta(woodItemMeta);

        backItem = new ItemStack(Material.BARRIER);
        ItemMeta backItemMeta = backItem.getItemMeta();
        backItemMeta.setDisplayName(ColorUtil.colorFix("&cCofnij"));
        backItem.setItemMeta(backItemMeta);

        infoItem = new ItemStack(Material.SKULL_ITEM);
        ItemMeta itemInfoMeta = infoItem.getItemMeta();
        itemInfoMeta.setDisplayName(ColorUtil.colorFix("&aTwoje statystyki"));
        infoItem.setItemMeta(itemInfoMeta);

    }

    public void createTopsInventory(){
        fillInventory(topsInventory,0,11,16,26);
        topsInventory.setItem(11,stoneItem );
        topsInventory.setItem(12,timePlayedItem);
        topsInventory.setItem(13,deathsItem);
        topsInventory.setItem(14,killsItem);
        topsInventory.setItem(15,woodItem);
        topsInventory.setItem(26,infoItem);
    }

    public void createTopStoneInventory(){
        dataManager.createTopTenMinedStone();
        dataManager.getTopTenMinedStone().forEach(x ->{
            ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ColorUtil.colorFix("&AMiejsce&8: " + "&e"+i));
            itemMeta.setLore(Arrays.asList("",ColorUtil.colorFix( "&aNick&8: " + "&e"+ x.getPlayerName()), ColorUtil.colorFix("&aWykopany stone&8: ") + ColorUtil.colorFix("&e"+ x.getMinedStone())));
            itemStack.setItemMeta(itemMeta);
            topStoneInventory.setItem(slot,itemStack);
            i++;
            slot++;
        });

        fillInventory(topStoneInventory,0,9,18,26);

        i = 1;
        slot = 9;
    }

    public void createTopPlayedTimeInventory(){
        dataManager.createTopTenTimePlayed();
        dataManager.getTopTenTimePlayed().forEach(x ->{
            ItemStack itemStack = new ItemStack(Material.WATCH);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ColorUtil.colorFix("&aMiejsce&8: " + "&e"+i));
            itemMeta.setLore(Arrays.asList("", ColorUtil.colorFix("&aNick&8: " + "&e"+ x.getPlayerName()), ColorUtil.colorFix("&aCzas przegrany&8: " + "&e"+ ColorUtil.timeFix(x.getTimeplayed()) + " godzin")));
            itemStack.setItemMeta(itemMeta);
            topsPlayedTime.setItem(slot,itemStack);
            i++;
            slot++;
        });

        fillInventory(getTopsPlayedTime(), 0,9,18,26);

        i = 1;
        slot = 9;

    }

    public void createTopDeathsInventory(){
        dataManager.createTopTenDeaths();
        dataManager.getTopTenDeaths().forEach(x ->{
            ItemStack itemStack = new ItemStack(Material.WOOD_SWORD);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ColorUtil.colorFix("&aMiejsce&8: " + "&e"+i));
            itemMeta.setLore(Arrays.asList("", ColorUtil.colorFix("&aNick&8: " + "&e"+ x.getPlayerName()), ColorUtil.colorFix("&aSmierci&8: " + "&e"+ x.getDeaths())));
            itemStack.setItemMeta(itemMeta);
            topDeathsTime.setItem(slot,itemStack);
            i++;
            slot++;
        });

        fillInventory(getTopDeathsTime(),0,9,18,26);

        i = 1;
        slot = 9;
    }

    public void createTopKillsInventory(){
        dataManager.createTopTenKills();
        dataManager.getTopTenKills().forEach(x ->{
            ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ColorUtil.colorFix("&aMiejsce&8: " + "&e"+i));
            itemMeta.setLore(Arrays.asList("", ColorUtil.colorFix("&aNick&8: " + "&e"+ x.getPlayerName()), ColorUtil.colorFix("&aZabojstw&8: " + "&e"+ x.getKills())));
            itemStack.setItemMeta(itemMeta);
            topKillsItem.setItem(slot,itemStack);
            i++;
            slot++;
        });

        fillInventory(getTopKillsItem(),0,9,18,26);

        i = 1;
        slot = 9;
    }

    public void createTopMinedWoodInventory(){
        dataManager.createTopTenMinedWood();
        dataManager.getGetTopTenMinedWood().forEach(x ->{
            ItemStack itemStack = new ItemStack(Material.DIAMOND_AXE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ColorUtil.colorFix("&AMiejsce&8: " + "&e"+i));
            itemMeta.setLore(Arrays.asList("", ColorUtil.colorFix("&aNick&8: " + "&e"+ x.getPlayerName()), ColorUtil.colorFix("&aWykopnago drewna&8: " + "&e"+ x.getMinedWood())));
            itemStack.setItemMeta(itemMeta);
            topMinedWoodInventory.setItem(slot,itemStack);
            i++;
            slot++;
        });

        fillInventory(getTopMinedWoodInventory(),0,9,18,26);

        i = 1;
        slot = 9;
    }

    public void createYourStatsInventory(Player player){
        User user = dataManager.getUserMap().get(player.getUniqueId());


        ItemStack minedStoneInfo = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta minedStoneInfoMeta = minedStoneInfo.getItemMeta();
        minedStoneInfoMeta.setDisplayName(ColorUtil.colorFix("&aWykopany Stone&8:"));
        minedStoneInfoMeta.setLore(Arrays.asList(ColorUtil.colorFix("&e"+ user.getMinedStone())));
        minedStoneInfo.setItemMeta(minedStoneInfoMeta);

        ItemStack playedTimeInfo = new ItemStack(Material.WATCH);
        ItemMeta playedTimeInfoMeta = playedTimeInfo.getItemMeta();
        playedTimeInfoMeta.setDisplayName(ColorUtil.colorFix("&aPrzegrany Czas&8:"));
        playedTimeInfoMeta.setLore(Arrays.asList(ColorUtil.colorFix("&e"+ColorUtil.timeFix(user.getTimeplayed()))));
        playedTimeInfo.setItemMeta(playedTimeInfoMeta);

        ItemStack deathsInfo = new ItemStack(Material.DIRT);
        ItemMeta deathsInfoMeta = deathsInfo.getItemMeta();
        deathsInfoMeta.setDisplayName(ColorUtil.colorFix("&aSmierci&8:"));
        deathsInfoMeta.setLore(Arrays.asList(ColorUtil.colorFix("&e"+ user.getDeaths())));
        deathsInfo.setItemMeta(deathsInfoMeta);

        ItemStack killsInfo = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta killsInfoMeta = killsInfo.getItemMeta();
        killsInfoMeta.setDisplayName(ColorUtil.colorFix("&aZabojstwa&8:"));
        killsInfoMeta.setLore(Arrays.asList(ColorUtil.colorFix("&e"+ user.getKills())));
        killsInfo.setItemMeta(killsInfoMeta);

        ItemStack minedWoodInfo = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta minedWoodInfoMeta = minedWoodInfo.getItemMeta();
        minedWoodInfoMeta.setDisplayName(ColorUtil.colorFix("&aZrabane Drewno&:"));
        minedWoodInfoMeta.setLore(Arrays.asList(ColorUtil.colorFix("&e"+ user.getMinedWood())));
        minedWoodInfo.setItemMeta(minedWoodInfoMeta);

        yourStats.setItem(11,minedStoneInfo );
        yourStats.setItem(12,playedTimeInfo);
        yourStats.setItem(13,deathsInfo);
        yourStats.setItem(14,killsInfo);
        yourStats.setItem(15,minedWoodInfo);

        fillInventory(yourStats,0,11,16,26);

        player.openInventory(yourStats);
    }


    public void fillInventory(Inventory inventory, int firstRowStart, int firstRowEnd, int secondRowStart, int secondRowEnd){

        inventory.setItem(26, backItem);

        for (int i = firstRowStart; i< firstRowEnd; i++){
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5));
        }

        for (int i = secondRowStart; i<secondRowEnd;i++){
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5));
        }
    }
}
