package model;

// Represents the Add Ons, such as sugar or cream, for the drink
// takes two parameters which are item = name of the item and amount = how much of it to add
public class AddOns {
    private String item;
    private Integer amount;

    public AddOns(String item, Integer amount) {
        this.item = item;
        this.amount = amount;
    }

    // EFFECTS: return the amount
    public Integer getAmount() {
        return amount;
    }

    // EFFECTS: return the item
    public String getItem() {
        return item;
    }
}
