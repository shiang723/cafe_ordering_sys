package ui;

import model.BakeryItem;
import model.Drink;
import model.OrderingSystem;

import java.util.Scanner;

// This [class/method] references code from this [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp.git]
// Cafe items ordering application
public class CafeOrderingMenuApp {

    private Drink espresso;
    private Drink icedcoffee;
    private BakeryItem cake;
    private BakeryItem appletart;
    private OrderingSystem order;
    private Scanner input;

    // EFFECTS: runs the CafeOrderingMenu application
    public CafeOrderingMenuApp() {
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
            processDrinkMenuEspresso(null);
        } else if (command.equals("c")) {
            order.addItemToCart(cake);
        } else if (command.equals("i")) {
            processsDrinkMenuIcedCoffee(null);
        } else if (command.equals("a")) {
            order.addItemToCart(appletart);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processDrinkMenuEspresso(String command) {
        displayDrinkSizeMenu();
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("s")) {
            espresso.chooseSize("small");
            processDrinkMenuAddOnsEspresso(null);
        } else if (command.equals("m")) {
            espresso.chooseSize("medium");
            processDrinkMenuAddOnsEspresso(null);
        } else if (command.equals("l")) {
            espresso.chooseSize("large");
            processDrinkMenuAddOnsEspresso(null);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processsDrinkMenuIcedCoffee(String command) {
        displayDrinkSizeMenu();
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("s")) {
            icedcoffee.chooseSize("small");
            processDrinkMenuAddOnsIcedCoffee(null);
        } else if (command.equals("m")) {
            icedcoffee.chooseSize("medium");
            processDrinkMenuAddOnsIcedCoffee(null);
        } else if (command.equals("l")) {
            icedcoffee.chooseSize("large");
            processDrinkMenuAddOnsIcedCoffee(null);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processDrinkMenuAddOnsEspresso(String command) {
        displayDrinkAddOnMenu();
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("s")) {
            espresso.addOptional("sugar");
            order.addItemToCart(espresso);
        } else if (command.equals("c")) {
            espresso.addOptional("creamer");
            order.addItemToCart(espresso);
        } else if (command.equals("n")) {
            order.addItemToCart(espresso);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processDrinkMenuAddOnsIcedCoffee(String command) {
        displayDrinkAddOnMenu();
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("s")) {
            icedcoffee.addOptional("sugar");
            order.addItemToCart(icedcoffee);
        } else if (command.equals("c")) {
            icedcoffee.addOptional("creamer");
            order.addItemToCart(icedcoffee);
        } else if (command.equals("n")) {
            order.addItemToCart(icedcoffee);
        } else {
            System.out.println("Selection not valid...");
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
    }

    // EFFECTS: displays a menu with options to user
    private void displayMainMenu() {
        System.out.println("\n Pick one item you want:");
        System.out.println("\te -> Espresso 300 cent; " + espresso.getDescription());
        System.out.println("\tc -> Cake 600 cent; " + cake.getDescription());
        System.out.println("\ti -> Iced Coffee 500 cent; " + icedcoffee.getDescription());
        System.out.println("\ta -> Apple Tart 600 cent; " + appletart.getDescription());
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays a menu with options to user
    private void displayDrinkSizeMenu() {
        System.out.println("\n Choose a size: ");
        System.out.println("\ts -> small");
        System.out.println("\tm -> medium, +50 cents");
        System.out.println("\tl -> large, +50 cents");
    }

    // EFFECTS: displays a menu with options to user
    private void displayDrinkAddOnMenu() {
        System.out.println("\n Pick one add on for 5 cents");
        System.out.println("\ts -> sugar");
        System.out.println("\tc -> creamer");
        System.out.println("\tn -> none");
    }


    // EFFECTS: prints receipt of the order
    private void printReceipt() {
        System.out.println(order.receipt());
    }
}

