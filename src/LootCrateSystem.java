import java.util.ArrayList;
import java.util.List;

import crate.LootCrate;
import crate.LootCrateNotFoundException;
import crate.NotEnoughCreditsException;
import player.Player;
import player.PlayerNotFoundException;

public class LootCrateSystem {

    private List<Player> players;
    private List<LootCrate> crates;

    public LootCrateSystem() {
        this.players = new ArrayList<>();
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

    public void openCrate(Player player, LootCrate crate) throws NotEnoughCreditsException {
        player.openCrate(crate);
    }

    public void printAllPlayers() {
        players.forEach(System.out::println);
    }

    public void printAllCrates() {
        crates.forEach(System.out::println);
    }

}
