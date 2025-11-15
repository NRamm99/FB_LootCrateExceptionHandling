package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Item;
import models.LootCrate;
import models.Player;

public class FileHandler {
    private static final String USERDATA_FILE = "src/data/userData.txt";
    private static final String CRATES_FILE = "src/data/cratesData.txt";

    private FileHandler() {
    }

    public static void saveUserData(List<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERDATA_FILE))) {
            for (Player player : players) {
                writer.write(player.getUsername() + "," + player.getCredits());
                for (Item item : player.getItems()) {
                    writer.write("," + item.getData());
                }
                writer.newLine();
            }
            System.out.println("User data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    public static List<Player> loadUserData() {
        ArrayList<Player> players = new ArrayList<>();
        File file = new File(USERDATA_FILE);
        int userCount = getLineCount(USERDATA_FILE);

        if (!file.exists()) {
            System.out.println("User data file not found, creating new file");
            return players;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int currentUser = 0; currentUser < userCount; currentUser++) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");

                Player tempPlayer = readPlayerData(data);

                List<Item> tempItems = readItemsData(data);
                tempPlayer.setItems(tempItems);

                players.add(tempPlayer);
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
        System.out.println("User data loaded successfully");
        return players;
    }

    private static Player readPlayerData(String[] data) {
        String username = data[0];
        int credits = Integer.parseInt(data[1]);
        return new Player(username, credits);
    }

    private static List<Item> readItemsData(String[] data) {
        List<Item> items = new ArrayList<>();
        for (int itemIndex = 2; itemIndex < data.length; itemIndex += 3) {
            String itemName = data[itemIndex];
            int itemQuantity = Integer.parseInt(data[itemIndex + 1]);
            int itemValue = Integer.parseInt(data[itemIndex + 2]);
            items.add(new Item(itemName, itemQuantity, itemValue));
        }
        return items;
    }

    public static int getLineCount(String filePath) {
        int userCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                userCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCount;
    }

    public static void saveCratesData(List<LootCrate> crates) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CRATES_FILE))) {
            for (LootCrate crate : crates) {
                writer.write(crate.getId() + "," + crate.getCreditPrice());
                for (Item item : crate.getItems()) {
                    writer.write("," + item.getData());
                }
                writer.newLine();
            }
            System.out.println("Loot crate data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving loot crate data: " + e.getMessage());
        }
    }

    public static List<LootCrate> loadCratesData() {
        ArrayList<LootCrate> crates = new ArrayList<>();
        File file = new File(CRATES_FILE);
        int crateCount = getLineCount(CRATES_FILE);

        if (!file.exists()) {
            System.out.println("Crates data file not found, creating new file");
            return crates;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int currentCrate = 0; currentCrate < crateCount; currentCrate++) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(",");

                LootCrate tempCrate = readCrateData(data);

                List<Item> tempItems = readItemsData(data);
                tempCrate.setItems(tempItems);

                crates.add(tempCrate);
            }
        } catch (IOException e) {
            System.out.println("Error loading loot crate data: " + e.getMessage());
        }
        System.out.println("Loot crate data loaded successfully");
        return crates;
    }

    private static LootCrate readCrateData(String[] data) {
        String id = data[0];
        int creditPrice = Integer.parseInt(data[1]);
        return new LootCrate(id, creditPrice);
    }
}
