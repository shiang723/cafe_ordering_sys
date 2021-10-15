package model;

// Represents the Add Ons, such as sugar or cream, for the drink
// takes two parameters which are item = name of the item and amount = how much of it to add
public class AddOns {
    private String item;

    public AddOns(String item) {
        this.item = item;
    }

    // EFFECTS: return the item
    public String getItem() {
        return item;
    }
}
