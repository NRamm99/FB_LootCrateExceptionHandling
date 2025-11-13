package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.NegativeAmountException;
import exceptions.NotEnoughCreditsException;

public class Player {
    private String username;
    private int credits;
    private List<Item> items;

    public Player(String username, int credits) {
        this.username = username;
        this.credits = credits;
        this.items = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int amount) {
        if (amount < 0) {
            throw new NegativeAmountException("Amount must be greater than 0");
        }
        credits += amount;
    }

    public void deductCredits(int amount) {
        credits -= amount;
    }

    public void openCrate(LootCrate crate, Item item, int amount) throws NotEnoughCreditsException {
        if (credits < crate.getCreditPrice()) {
            throw new NotEnoughCreditsException(
                    username + " does not have enough credits to open crate " + crate.getId());
        }
        credits -= crate.getCreditPrice();
        addItem(item, amount);
    }

    public void addItem(Item item, int amount) {
        if (!items.isEmpty()) {
            items.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(item.getName()))
                    .findFirst()
                    .ifPresent(i -> i.addQuantity(amount));
        } else {
            item.setQuantity(amount);
            items.add(item);
        }
    }

    @Override
    public String toString() {
        return username + " (credits: " + credits + ")";
    }

    public List<Item> getItems() {
        return items;
    }

    public int calculateInventoryValue() {
        return items.stream()
                .mapToInt(item -> item.getValue() * item.getQuantity())
                .sum();
    }

    public void remveAllItems() {
        items.clear();
    }
}
