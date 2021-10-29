package model;

// Represents the Add Ons, such as sugar or cream, for the drink
// takes two parameters which are item = name of the item and amount = how much of it to add
public class AddOns implements MenuItem {

    private String item;
    public static final Integer PRICE = 5;
    private String description;

    public AddOns(String item, String description) {
        this.item = item;
        this.description = description;
    }

    // EFFECTS: return the item
    public String getItem() {
        return item;
    }

    @Override
    public String getName() {
        return item;
    }

    @Override
    public Integer getPrice() {
        return PRICE;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
