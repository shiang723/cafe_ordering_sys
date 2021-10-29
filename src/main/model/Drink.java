package model;

import org.json.JSONObject;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
//Represent Drink object that is a subtype of MenuItem.
//It takes 3 parameter, name = name of drink, price = price of drink, description = a bit about the drink
//ADD_PRICE >= 0 as we can not charge a negative value, money wise
//PRICE_UPGRADE >= 0 as we can not charge a negative value, money wise
public class Drink implements MenuItem {

    private static final Integer ADD_PRICE = 5;
    private static final Integer PRICE_UPGRADE = 50;

    private String drinkName;
    private Integer price;
    private String drinkSize;
    private AddOns addOn;
    private String description;

    //REQUIRES: price >= 0
    //EFFECTS: creates new Drink and
    //         makes the given name, price and description equal to the already declared fields above.
    public Drink(String name, Integer price, String description) {
        this.drinkName = name;
        this.price = price;
        this.description = description;
        this.drinkSize = "small";
        this.addOn = new AddOns("", "");
    }

    //EFFECTS: return name of the drink
    @Override
    public String getName() {
        return this.drinkName;
    }

    // EFFECTS: returns the price of the drink in cents
    @Override
    public Integer getPrice() {
        return this.price;
    }

    // EFFECTS: returns the description of the drink
    @Override
    public String getDescription() {
        return this.description;
    }

    // EFFECTS: returns this as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("drink name", drinkName);
        json.put("drink price",price);
        json.put("drink description", description);
        json.put("drink addon", addOn.getName());
        json.put("drink addon description", addOn.getDescription());
        return json;
    }

    // EFFECTS: returns the drinkSize
    public String getDrinkSize() {
        return this.drinkSize;
    }

    // EFFECTS: return the list of AddOns
    public AddOns getAddOn() {
        return this.addOn;
    }

    // REQUIRES: size parameter can only be either "small", "medium", "large"
    // MODIFIES: this
    // EFFECTS: Changes the size of the drink and its price according to the given size, then return the changed Drink
    public Drink chooseSize(String size) {
        if (size.equals("small")) {
            return this;
        } else if (size.equals("medium")) {
            this.drinkSize = "medium";
            this.price = this.price + PRICE_UPGRADE;
            return this;
        } else {
            this.drinkSize = "large";
            this.price = this.price + (PRICE_UPGRADE * 2);
            return this;
        }
    }


    // MODIFIES: this
    // EFFECTS: makes a new AddOn and changes the price, then return the changed Drink
    public Drink addOptional(String addOnItem, String addOnDescription) {
        this.addOn = new AddOns(addOnItem, addOnDescription);
        this.price = this.price + ADD_PRICE;
        return this;
    }

    // MODIFIES: this
    // EFFECTS: makes a new AddOn then return the changed Drink
    public Drink addOptionalWithNoPrice(String addOnItem, String addOnDescription) {
        this.addOn = new AddOns(addOnItem, addOnDescription);
        return this;
    }


}
