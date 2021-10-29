package model;

import org.json.JSONObject;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
//public interface called MenuItem
public interface MenuItem {
    // EFFECTS: returns name of the item
    String getName();

    // EFFECTS: returns the price of the item
    Integer getPrice();

    // EFFECTS: returns the description of the item
    String getDescription();

    // EFFECTS: returns this as JSON object
    public JSONObject toJson();



}
