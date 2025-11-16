import java.util.Scanner;

import exceptions.LootCrateNotFoundException;
import exceptions.NegativeAmountException;
import exceptions.NotEnoughCreditsException;
import exceptions.PlayerNotFoundException;
import models.LootCrate;
import models.Player;
import utils.FileHandler;

public class App {
    private static final Scanner input = new Scanner(System.in);
    private static LootCrateSystem lootCrateSystem = new LootCrateSystem(FileHandler.loadUserData(),
            FileHandler.loadCratesData());

    public static void main(String[] args) throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {

        while (true) {
            promptMainMenu();
        }
    }

    private static void saveData() {
        FileHandler.saveUserData(lootCrateSystem.getPlayers());
        FileHandler.saveCratesData(lootCrateSystem.getCrates());
    }

    private static void runTests(LootCrateSystem lootCrateSystem)
            throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {
        lootCrateSystem.resetTestData();
        clearConsole();
        System.out.println("Running tests...");
        System.out.println("--------------------------------");

        System.out.println("Testing openCrate...");
        try {
            String result = lootCrateSystem.openCrate(lootCrateSystem.getPlayerByUsername("John"),
                    lootCrateSystem.getLootCrateById("Normal"));
            System.out.println(result);
        } catch (NotEnoughCreditsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------");
        System.out.println("Testing openCrate with not enough credits...");
        try {
            String result = lootCrateSystem.openCrate(lootCrateSystem.getPlayerByUsername("John"),
                    lootCrateSystem.getLootCrateById("Normal"));
            System.out.println(result);
        } catch (NotEnoughCreditsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------");
        System.out.println("Testing addCredits with negative amount...");
        try {
            lootCrateSystem.getPlayerByUsername("John").addCredits(-100);
        } catch (NegativeAmountException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------");
        System.out.println("Testing openCrate with not found player...");
        try {
            String result = lootCrateSystem.openCrate(lootCrateSystem.getPlayerByUsername("this is not a player"),
                    lootCrateSystem.getLootCrateById("Normal"));
            System.out.println(result);
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------");
        waitForUser();
    }

    private static void promptMainMenu()
            throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {
        clearConsole();
        System.out.println("Welcome to the Loot Crate System");
        System.out.println("--------------------------------");
        System.out.println("""
                Select an option:
                1. Open Loot Crate
                2. View Inventory
                3. Sell player inventory
                4. Run tests (Will reset all data)
                5. Exit
                """);
        int choice = validateInt("Select an option");
        switch (choice) {
            case 1:
                promptOpenCrate();
                break;
            case 2:
                printAllPlayerInventory();
                waitForUser();
                break;
            case 3:
                promptSellInventory();
                break;
            case 4:
                runTests(lootCrateSystem);
                break;
            case 5:
                saveData();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static void promptSellInventory() throws PlayerNotFoundException {
        clearConsole();
        printAllPlayerInventory();
        String username = validateString("Enter the username of the player who will sell their inventory");
        Player player = null;
        try {
            player = lootCrateSystem.getPlayerByUsername(username);
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
            waitForUser();
            return;
        }

        int inventoryValue = player.calculateInventoryValue();
        player.addCredits(inventoryValue);
        player.removeAllItems();
        System.out.println("inventory sold for " + inventoryValue + " credits");
        waitForUser();
    }

    private static void printAllPlayerInventory() {
        clearConsole();
        System.out.println("--------------------------------");
        for (Player player : lootCrateSystem.getPlayers()) {
            System.out.println(player.getUsername() + " (credits: " + player.getCredits() + ")");
            player.getItems().forEach(System.out::println);
            System.out.println("--------------------------------");
        }
    }

    private static void promptOpenCrate()
            throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {
        clearConsole();

        printAllPlayers();
        System.out.println("--------------------------------");
        String username = validateString("Enter the username of the player who will open the crate");
        Player player = null;
        try {
            player = lootCrateSystem.getPlayerByUsername(username);
        } catch (PlayerNotFoundException e) {
            System.out.println(e.getMessage());
            waitForUser();
            return;
        }
        clearConsole();
        lootCrateSystem.printAllCrates();
        System.out.println("--------------------------------");
        String crateId = validateString("Enter the name of the crate to open");
        LootCrate crate = null;
        try {
            crate = lootCrateSystem.getLootCrateById(crateId);
        } catch (LootCrateNotFoundException e) {
            System.out.println(e.getMessage());
            waitForUser();
            return;
        }
        String result = lootCrateSystem.openCrate(player, crate);
        System.out.println(result);
        waitForUser();
    }

    private static void printAllPlayers() {
        for (Player player : lootCrateSystem.getPlayers()) {
            System.out.println(player.getUsername() + " (credits: " + player.getCredits() + ")");
        }
    }

    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static int validateInt(String message) {
        while (true) {
            System.out.print(message + ": ");
            String userStr = input.nextLine().trim();
            try {
                return Integer.parseInt(userStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static void waitForUser() {
        System.out.println("\nPress enter to continue...");
        input.nextLine();
    }

    public static String validateString(String message) {
        while (true) {
            System.out.print(message + ": ");
            String string = input.nextLine().trim();
            if (string.matches("^[A-Za-zÆØÅæøå\\s]+$")) {
                return string;
            } else {
                System.out.println("Invalid string. Please enter a valid string.");
            }
        }
    }
}
