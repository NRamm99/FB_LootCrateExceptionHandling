package player;

import crate.LootCrate;
import crate.NotEnoughCreditsException;
import exceptions.NegativeAmountException;

public class Player {
    private String username;
    private int credits;

    public Player(String username, int credits) {
        this.username = username;
        this.credits = credits;
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

    public void openCrate(LootCrate crate) throws NotEnoughCreditsException {
        if (credits < crate.getCreditPrice()) {
            throw new NotEnoughCreditsException(username + " does not have enough credits to open crate " + crate.getId());
        }
        credits -= crate.getCreditPrice();
        System.out.println(username + " opened: " + crate.getId());
    }

    @Override
    public String toString() {
        return username + " (credits: " + credits + ")";
    }
}
