package persistence;

import model.BakeryItem;
import model.Drink;
import model.MenuItem;
import model.OrderingSystem;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
// Represents a reader that reads order from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public OrderingSystem read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrderingSystem(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses OrderingSystem from JSON object and returns it
    private OrderingSystem parseOrderingSystem(JSONObject jsonObject) {
        OrderingSystem os = new OrderingSystem();
        addItems(os, jsonObject);
        return os;
    }

    // MODIFIES: os
    // EFFECTS: parses menuitems from JSON object and adds them to OrderingSystem
    private void addItems(OrderingSystem os, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cart");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(os, nextItem);
        }
    }

    // REQUIRES: either key to have drink name or bakeryitem name
    // MODIFIES: os
    // EFFECTS: parses menuitem from JSON object and adds it to OrderingSystem
    private void addItem(OrderingSystem os, JSONObject jsonObject) {
        if (jsonObject.has("drink name")) {
            String drinkName = jsonObject.getString("drink name");
            Integer drinkPrice = jsonObject.getInt("drink price");
            String drinkDescription = jsonObject.getString("drink description");
            String drinkAddOn = jsonObject.getString("drink addon");
            String drinkAddOnDescription = jsonObject.getString("drink addon description");
            Drink drink = new Drink(drinkName, drinkPrice, drinkDescription);
            if (!(drinkAddOn.equals(""))) {
                drink.addOptionalWithNoPrice(drinkAddOn, drinkAddOnDescription);
            }
            os.addItemToCart(drink);
        } else  {
            String bakeryItemName = jsonObject.getString("bakeryitem name");
            Integer bakeryItemPrice = jsonObject.getInt("bakeryitem price");
            String bakeryItemDescription = jsonObject.getString("bakeryitem description");
            MenuItem bakeditem = new BakeryItem(bakeryItemName, bakeryItemPrice, bakeryItemDescription);
            os.addItemToCart(bakeditem);
        }
    }

}

