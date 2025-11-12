package crate;

public class LootCrate {
    private String id;
    private int creditPrice;

    public LootCrate(String id, int creditPrice) {
        this.id = id;
        this.creditPrice = creditPrice;
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

}
