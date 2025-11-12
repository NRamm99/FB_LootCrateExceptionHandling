import java.util.Scanner;

import crate.LootCrate;
import crate.LootCrateNotFoundException;
import crate.NotEnoughCreditsException;
import player.Player;
import player.PlayerNotFoundException;

public class App {
    private static final Scanner input = new Scanner(System.in);
    private static LootCrateSystem lootCrateSystem = new LootCrateSystem();

    public static void main(String[] args) throws Exception {

        addDefaultPlayers();
        addDefaultCrates();

        while (true) {
            promptMainMenu();
        }
    }

    private static void addDefaultCrates() {
        lootCrateSystem.addCrate(new LootCrate("Normal", 100));
        lootCrateSystem.addCrate(new LootCrate("Rare", 200));
        lootCrateSystem.addCrate(new LootCrate("Epic", 300));
    }

    private static void addDefaultPlayers() {
        lootCrateSystem.addPlayer(new Player("John", 100));
        lootCrateSystem.addPlayer(new Player("Jane", 200));
        lootCrateSystem.addPlayer(new Player("Jim", 300));
    }

    private static void promptMainMenu()
            throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {
        clearConsole();
        System.out.println("Welcome to the Loot Crate System");
        System.out.println("--------------------------------");
        System.out.println("""
                Select an option:
                1. Open Loot Crate
                2. Exit
                """);
        int choice = validateInt("Select an option");
        switch (choice) {
            case 1:
                promptOpenCrate();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

    }

    private static void promptOpenCrate()
            throws PlayerNotFoundException, LootCrateNotFoundException, NotEnoughCreditsException {
        clearConsole();
        lootCrateSystem.printAllPlayers();
        System.out.println("--------------------------------");
        System.out.println("Enter the username of the player who will open the crate: ");
        String username = input.nextLine().trim();
        Player player = lootCrateSystem.getPlayerByUsername(username);
        clearConsole();
        lootCrateSystem.printAllCrates();
        System.out.println("--------------------------------");
        System.out.println("Enter the name of the crate to open: ");
        String crateId = input.nextLine().trim();
        LootCrate crate = lootCrateSystem.getLootCrateById(crateId);
        lootCrateSystem.openCrate(player, crate);
        waitForUser();
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
}
