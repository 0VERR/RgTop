package pl.overr.top.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.overr.top.inventory.TopInventory;
import pl.overr.top.utils.ColorUtil;

import javax.lang.model.element.ElementVisitor;

public class InventoryClickListener implements Listener {

    private final TopInventory topInventory;

    public InventoryClickListener(TopInventory topInventory) {
        this.topInventory = topInventory;
    }

    @EventHandler
    public void onClickListener(InventoryClickEvent event){

        Inventory inventory = event.getInventory();
        String inventoryName = inventory.getName();

        if (inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTopki")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop Kopaczy")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop spedzonego czasu")) ||inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop smierci")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop killi")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop zrabanego drewna"))) event.setCancelled(true);
        else return;

        if (event.getCurrentItem().getType() == Material.AIR || event.getCurrentItem().getType() == null) {
            event.setCancelled(true);
            return;
        }

        String itemStackName = event.getCurrentItem().getItemMeta().getDisplayName();



        if (itemStackName == null){
            event.setCancelled(true);
            return;
        }

        if (inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTopki")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop Kopaczy")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop spedzonego czasu"))
        ||inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop smierci")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop killi")) || inventoryName.equalsIgnoreCase(ColorUtil.colorFix("&aTop zrabanego drewna"))) event.setCancelled(true);
        else return;

        Player player = (Player) event.getWhoClicked();

        if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&aNajlepsi Kopacze"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopStoneInventory());
        }
        else if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&aNajwieksze Nolify"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopsPlayedTime());
        } else if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&cCofnij"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopsInventory());
        }
        else if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&aNajwieksze Nooby"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopDeathsTime());
        }
        else if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&aNajwieksze Koxy"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopKillsItem());
        }
        else if (itemStackName.equalsIgnoreCase(ColorUtil.colorFix("&aNajlepsi drwale"))){
            player.closeInventory();
            player.openInventory(topInventory.getTopMinedWoodInventory());
        }

    }
}
