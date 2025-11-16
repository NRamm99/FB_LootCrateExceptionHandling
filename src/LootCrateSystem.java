import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exceptions.LootCrateNotFoundException;
import exceptions.NotEnoughCreditsException;
import exceptions.PlayerNotFoundException;
import models.Item;
import models.LootCrate;
import models.Player;

public class LootCrateSystem {
    private static final Random random = new Random();
    private List<Player> players;
    private List<LootCrate> crates;

    public LootCrateSystem() {
        this.players = new ArrayList<>();
        this.crates = new ArrayList<>();
    }

    public LootCrateSystem(List<Player> players) {
        this.players = players;
        this.crates = new ArrayList<>();
    }

    public LootCrateSystem(List<Player> players, List<LootCrate> crates) {
        this.players = players;
        this.crates = crates;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addCrate(LootCrate crate) {
        crates.add(crate);
    }

    public Player getPlayerByUsername(String username) throws PlayerNotFoundException {
        return players.stream()
                .filter(player -> player.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new PlayerNotFoundException(username + " not found"));
    }

    public LootCrate getLootCrateById(String crateId) throws LootCrateNotFoundException {
        return crates.stream()
                .filter(crate -> crate.getId().equalsIgnoreCase(crateId))
                .findFirst()
                .orElseThrow(() -> new LootCrateNotFoundException(crateId + " not found"));
    }

    public String openCrate(Player player, LootCrate crate) throws NotEnoughCreditsException {
        Item item = crate.getRandomItem();
        int amount = random.nextInt(1, 3);
        player.openCrate(crate, item, amount);
        return player.getUsername() + " opened " + crate.getId() + " and received " + item.getName() + " x " + amount;

    }

    public void printAllCrates() {
        crates.forEach(System.out::println);
    }

    public void addDefaultCrates() throws LootCrateNotFoundException {
        addCrate(new LootCrate("Normal", 100));
        addCrate(new LootCrate("Rare", 200));
        addCrate(new LootCrate("Epic", 300));

        getLootCrateById("Normal").addItem(new Item("Wooden Shield", 1, 50));
        getLootCrateById("Normal").addItem(new Item("Adventure Hat", 1, 30));
        getLootCrateById("Normal").addItem(new Item("Health Potion", 1, 20));

        getLootCrateById("Rare").addItem(new Item("Silver Sword", 1, 200));
        getLootCrateById("Rare").addItem(new Item("Magic Compass", 1, 150));
        getLootCrateById("Rare").addItem(new Item("Enchanted Cloak", 1, 180));

        getLootCrateById("Epic").addItem(new Item("Dragon Slayer Axe", 1, 500));
        getLootCrateById("Epic").addItem(new Item("Phoenix Feather", 1, 450));
        getLootCrateById("Epic").addItem(new Item("Elder Spellbook", 1, 550));

    }

    public void addDefaultPlayers() {
        addPlayer(new Player("John", 100));
        addPlayer(new Player("Jane", 200));
        addPlayer(new Player("Jim", 300));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<LootCrate> getCrates() {
        return crates;
    }

    public void setPlayers(List<Player> userData) {
        this.players = userData;
    }

    public void setCrates(List<LootCrate> cratesData) {
        this.crates = cratesData;
    }

}