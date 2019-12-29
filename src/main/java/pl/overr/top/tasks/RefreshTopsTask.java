package pl.overr.top.tasks;

import pl.overr.top.inventory.TopInventory;

public class RefreshTopsTask implements Runnable {

    private final TopInventory topInventory;

    public RefreshTopsTask(TopInventory topInventory) {
        this.topInventory = topInventory;
    }

    @Override
    public void run() {
        topInventory.loadInventories();
    }
}
