package model;

// Represents a Bakery Item object that is a subtype of MenuItem
// It takes 3 parameters name = name of product, price = price of product, and description = a bit about the item
public class BakeryItem implements MenuItem {
    private Integer price;
    private String description;
    private String bakeryItemName;

    //BakeryItems take the 3 parameters and make them the value of the predeclared private fields above.
    //price >= 0
    public BakeryItem(String name, Integer price, String description) {
        this.bakeryItemName = name;
        this.price = price;
        this.description = description;

    }

    // EFFECTS: return the name of the item
    @Override
    public String getName() {
        return this.bakeryItemName;
    }

    // EFFECTS: return the price of the item
    @Override
    public Integer getPrice() {
        return this.price;
    }

    // EFFECTS: returns the description of the item
    @Override
    public String getDescription() {
        return description;
    }
}
