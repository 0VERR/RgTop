package pl.overr.top.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.overr.top.data.DataManager;
import pl.overr.top.utils.ColorUtil;

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

    ItemStack stoneItem;
    ItemStack timePlayedItem;
    ItemStack deathsItem;
    ItemStack killsItem;
    ItemStack woodItem;
    ItemStack backItem;

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

        fillInventory(topsInventory,0,11,16,27);
    }

    public void createTopsInventory(){
        topsInventory.setItem(11,stoneItem );
        topsInventory.setItem(12,timePlayedItem);
        topsInventory.setItem(13,deathsItem);
        topsInventory.setItem(14,killsItem);
        topsInventory.setItem(15,woodItem);
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

        fillInventory(getTopKillsItem(),0,9,18,27);

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
