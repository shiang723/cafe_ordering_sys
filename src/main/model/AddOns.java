package model;

import org.json.JSONObject;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
// Represents the Add Ons, such as sugar or cream, for the drink
// takes two parameters which are item = name of the item and amount = how much of it to add
public class AddOns {

    private String item;
    public static final Integer PRICE = 5;
    private String description;

    //EFFECTS: create new AddOns object and assign parameters to fields
    public AddOns(String item, String description) {
        this.item = item;
        this.description = description;
    }

    // EFFECTS: return the item
    public String getName() {
        return item;
    }

    public Integer getPrice() {
        return PRICE;
    }

    public String getDescription() {
        return description;
    }
}
