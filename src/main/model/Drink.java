package model;

import java.util.ArrayList;

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
    private ArrayList<AddOns> addOns;
    private String description;

    //Drink has a name, price, and description and its drinkSize is set at small, and it has a new Arraylist, addOns.
    //It makes the given name, price and description equal to the already declared fields above.
    //price >
    public Drink(String name, Integer price, String description) {
        this.drinkName = name;
        this.price = price;
        this.description = description;
        this.drinkSize = "small";
        this.addOns = new ArrayList<>();
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

    // EFFECTS: returns the drinkSize
    public String getDrinkSize() {
        return this.drinkSize;
    }

    // EFFECTS: return the list of AddOns
    public ArrayList<AddOns> getAddOns() {
        return this.addOns;
    }

    // REQUIRES: size parameter can only be either "small", "medium", "large"
    // MODIFIES: this
    // EFFECTS: Changes the size of the drink and its price according to the given size, then return the changed Drink
    public Drink chooseSize(String size) {
        if (size.equals("small")) {
            return this;
        } else if (size.equals("medium")) {
            if (!this.getDrinkSize().equals("medium")) {
                this.drinkSize = "medium";
                this.price = this.price + PRICE_UPGRADE;
            }
            return this;
        } else {
            if (!this.getDrinkSize().equals("large")) {
                this.drinkSize = "large";
                this.price = this.price + (PRICE_UPGRADE * 2);
            }
            return this;
        }
    }


    // MODIFIES: this
    // EFFECTS: Add an AddOn to the AddOn list and changes the price, then return the changed Drink
    public Drink addOptional(String addOnItem, Integer amount) {
        this.addOns.add(new AddOns(addOnItem, amount));
        this.price = this.price + ADD_PRICE;
        return this;
    }


}
