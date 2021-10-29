package ui;

import model.AddOns;
import model.BakeryItem;
import model.Drink;
import model.OrderingSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp.git]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
// Cafe items ordering application
public class CafeOrderingMenuApp {

    private Drink espresso;
    private Drink icedcoffee;
    private BakeryItem cake;
    private BakeryItem appletart;
    private OrderingSystem order;
    private Scanner input;
    private AddOns addOn1;
    private AddOns addOn2;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/order.json";

    // EFFECTS: runs the CafeOrderingMenu application
    public CafeOrderingMenuApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runOrderingMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runOrderingMenu() {
        boolean keepGoing = true;
        String command = null;
        init();
        initDrink();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainMenu(command);
            }
        }
        printReceipt();
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMainMenu(String command) {
        initDrink();
        if (command.equals("e")) {
            processDrinkMenuSize(null, espresso);
        } else if (command.equals("c")) {
            order.addItemToCart(cake);
        } else if (command.equals("i")) {
            processDrinkMenuSize(null, icedcoffee);
        } else if (command.equals("a")) {
            order.addItemToCart(appletart);
        } else if (command.equals("s")) {
            saveOrderingSystem();
        } else if (command.equals("l")) {
            loadOrderingSystem();
        } else {
            System.out.println("Selection not valid...");
        }

        printReceipt();
    }

    // MODIFIES: this and drink
    // EFFECTS: processes user command on what size they want
    private void processDrinkMenuSize(String command, Drink drink) {
        displayDrinkSizeMenu();
        command = input.next();
        command = command.toLowerCase();
        switch (command) {
            case "s":
                drink.chooseSize("small");
                processDrinkMenuAddOns(null, drink);
                break;
            case "m":
                drink.chooseSize("medium");
                processDrinkMenuAddOns(null, drink);
                break;
            case "l":
                drink.chooseSize("large");
                processDrinkMenuAddOns(null, drink);
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    // MODIFIES: this and drink
    // EFFECTS: processes user command on what addon they want
    private void processDrinkMenuAddOns(String command, Drink drink) {
        displayDrinkAddOnMenu();
        command = input.next();
        command = command.toLowerCase();
        switch (command) {
            case "s":
                drink.addOptional(addOn1.getName(), addOn1.getDescription());
                order.addItemToCart(drink);
                break;
            case "c":
                drink.addOptional(addOn2.getName(), addOn2.getDescription());
                order.addItemToCart(drink);
                break;
            case "n":
                order.addItemToCart(drink);
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes menu items and fields
    private void init() {
        cake = new BakeryItem("Cake", 600, "A strawberry short cake.");
        appletart = new BakeryItem("Apple Tart", 600, "Sweet apple tart");
        order = new OrderingSystem();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    // MODIFIES: this
    // EFFECTS: initializes drink items
    private void initDrink() {
        espresso = new Drink("Espresso", 300, "A simple espresso.");
        icedcoffee = new Drink("Iced Coffee", 500, "Classic Iced Coffee");
        addOn1 = new AddOns("sugar", "a teaspoon of sugar");
        addOn2 = new AddOns("creamer", "a tablespoon of creamer");
    }

    // EFFECTS: displays a menu with options to user
    private void displayMainMenu() {
        System.out.println("\n Pick one item you want or save or load previous order:");
        System.out.println("\te -> Espresso 300 cent; " + espresso.getDescription());
        System.out.println("\tc -> Cake 600 cent; " + cake.getDescription());
        System.out.println("\ti -> Iced Coffee 500 cent; " + icedcoffee.getDescription());
        System.out.println("\ta -> Apple Tart 600 cent; " + appletart.getDescription());
        System.out.println("\ts -> save");
        System.out.println("\tl -> load");
        System.out.println("\tq -> quit");

    }

    // EFFECTS: displays a menu of sizes to user
    private void displayDrinkSizeMenu() {
        System.out.println("\n Choose a size: ");
        System.out.println("\ts -> small");
        System.out.println("\tm -> medium, +50 cents");
        System.out.println("\tl -> large, +50 cents");
    }

    // EFFECTS: displays a menu addons to user
    private void displayDrinkAddOnMenu() {
        System.out.println("\n Pick one add on for " + AddOns.PRICE + " cents.");
        System.out.println("\ts -> sugar");
        System.out.println("\tc -> creamer");
        System.out.println("\tn -> none");
    }


    // EFFECTS: prints receipt of the order
    private void printReceipt() {
        System.out.println(order.receipt());
    }

    // EFFECTS: saves the orderingsystem (order) to file
    private void saveOrderingSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
            System.out.println("Saved order to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads orderingsystem (order) from file
    private void loadOrderingSystem() {
        try {
            order = jsonReader.read();
            System.out.println("Loaded order from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

