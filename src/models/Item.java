package models;

public class Item {
    private String name;
    private int quantity;
    private int value;

    public Item(String name, int quantity, int value) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getValue() {
        return value;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setQuantity(int nextInt) {
        this.quantity = nextInt;
    }

    public String getData() {
        return name + "," + quantity + "," + value;
    }

    @Override
    public String toString() {
        return " - " + name + " x " + quantity + " (value: " + value * quantity + ")";
    }

}
