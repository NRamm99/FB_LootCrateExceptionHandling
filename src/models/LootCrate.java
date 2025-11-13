package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootCrate {
    private static final Random random = new Random();
    private String id;
    private int creditPrice;
    private List<Item> items;

    public LootCrate(String id, int creditPrice) {
        this.id = id;
        this.creditPrice = creditPrice;
        this.items = new ArrayList<>();
    }

    public int getCreditPrice() {
        return creditPrice;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " (credit price: " + creditPrice + ")";
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getRandomItem() {
        return items.get(random.nextInt(items.size()));
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> tempItems) {
        this.items = tempItems;
    }

}
