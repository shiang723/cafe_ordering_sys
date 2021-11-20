package ui;

import model.AddOns;
import model.BakeryItem;
import model.Drink;
import model.OrderingSystem;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

// A class that is a GUI and represents a Cafe Ordering system
public class CafeOrderingSystem extends JFrame implements ActionListener {
    private final JLabel heading;
    private OrderingSystem cart;
    private AddOns addOn1;
    private AddOns addOn2;
    private Drink espresso;
    private Drink icedCoffee;
    private BakeryItem cake;
    private BakeryItem appleTart;
    private ButtonGroup mainMenu;
    private JRadioButton icedCoffeeButton;
    private JRadioButton espressoButton;
    private JRadioButton cakeButton;
    private JRadioButton appleTartButton;
    private JComboBox addOnList1;
    private JComboBox addOnList2;
    private JButton addItem;
    private JPanel saveAndLoadPanel;
    private final Font font = new Font("Calibri", Font.PLAIN, 16);
    private JTextArea receipt;
    private final ImageIcon espressoImage = new ImageIcon(getClass().getResource("/images/expresso.jpg"));
    private final ImageIcon icedCoffeeImage = new ImageIcon(getClass().getResource("/images/IcedCoffee.jpg"));
    private final ImageIcon cakeImage = new ImageIcon(getClass().getResource("/images/StrawberryCake.jpg"));
    private final ImageIcon appleTartImage = new ImageIcon(getClass().getResource("/images/AppleTart.jpg"));
    private JLabel espressoLabel;
    private JLabel icedCoffeeLabel;
    private JLabel cakeLabel;
    private JLabel appleTartLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/order.json";
    private JButton clearItem;
    private JPanel addAndClear;

    // This [class/method] references code from these [repo/website]
    // Link: [https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application]
    //        and [https://stackoverflow.com/questions/15657569/how-to-set-icon-to-jframe]
    // EFFECTS: Constructs a CafeOrdering System GUI
    public CafeOrderingSystem() {
        super("Cafe Ordering Service");
        initCafe();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new FlowLayout());
        URL iconSource = getClass().getResource("/images/CaFe.png");
        ImageIcon icon = new ImageIcon(iconSource);
        setIconImage(icon.getImage());
        heading = new JLabel("Cafe Ordering System");
        heading.setFont(new Font("Calibri", Font.BOLD, 20));
        radioButtonMenu();
        menuActionListener();
        addOnsDropDown();
        itemImages();
        addAndClearItemButton();
        savingAndLoading();
        addButtons();
        cartDisplay();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: this
    // EFFECTS: initiates the BakeryItems and AddOns as well as a new cart
    public void initCafe() {
        cake = new BakeryItem("Cake", 600, "A strawberry short cake.");
        appleTart = new BakeryItem("Apple Tart", 600, "Sweet apple tart");
        espresso = new Drink("Espresso", 300, "A simple espresso.");
        icedCoffee = new Drink("Iced Coffee", 500, "Classic Iced Coffee");
        addOn1 = new AddOns("sugar", "a teaspoon of sugar");
        addOn2 = new AddOns("creamer", "a tablespoon of creamer");
        cart = new OrderingSystem();
    }

    //MODIFIES: this
    // EFFECTS: Create radiobuttons for the menu items
    public void radioButtonMenu() {
        String espressoString = espresso.getName() + " " + espresso.getPrice() + " cents";
        espressoButton = new JRadioButton(espressoString);
        espressoButton.setMnemonic(KeyEvent.VK_E);
        espressoButton.setActionCommand("espresso");
        espressoButton.setSelected(true);
        String icedCoffeeString = icedCoffee.getName() + " " + icedCoffee.getPrice() + " cents";
        icedCoffeeButton = new JRadioButton(icedCoffeeString);
        icedCoffeeButton.setMnemonic(KeyEvent.VK_I);
        icedCoffeeButton.setActionCommand("icedCoffee");
        String cakeString = cake.getName() + " " + cake.getPrice() + " cents";
        cakeButton = new JRadioButton(cakeString);
        cakeButton.setMnemonic(KeyEvent.VK_C);
        cakeButton.setActionCommand("cake");
        String appleTartString = appleTart.getName() + " " + appleTart.getPrice() + " cents";
        appleTartButton = new JRadioButton(appleTartString);
        appleTartButton.setMnemonic(KeyEvent.VK_A);
        appleTartButton.setActionCommand("appleTart");
        mainMenu = new ButtonGroup();
        mainMenu.add(espressoButton);
        mainMenu.add(icedCoffeeButton);
        mainMenu.add(cakeButton);
        mainMenu.add(appleTartButton);
    }

    //MODIFIES: this
    // EFFECTS: Add action listeners to the radio buttons
    public void menuActionListener() {
        espressoButton.addActionListener(this);
        icedCoffeeButton.addActionListener(this);
        cakeButton.addActionListener(this);
        appleTartButton.addActionListener(this);
    }

    //MODIFIES: this
    // EFFECTS: Create drop down selection boxs for the addons items
    public void addOnsDropDown() {
        String[] addOnStrings = {"None", addOn1.getName() + " " + AddOns.PRICE + " cents",
                addOn2.getName() + " " + AddOns.PRICE + " cents"};
        addOnList1 = new JComboBox(addOnStrings);
        addOnList1.setSelectedIndex(0);
        addOnList1.addActionListener(this);
        addOnList2 = new JComboBox(addOnStrings);
        addOnList2.setSelectedIndex(0);
        addOnList2.addActionListener(this);
    }

    // This [class/method] references code from these [repo/website]
    // Link: [https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon]
    //MODIFIES: this
    // EFFECTS: create images items of the menu items
    public void itemImages() {
        Image imageE = espressoImage.getImage().getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH);
        Image imageI = icedCoffeeImage.getImage().getScaledInstance(55, 55, java.awt.Image.SCALE_SMOOTH);
        Image imageC = cakeImage.getImage().getScaledInstance(70, 55, java.awt.Image.SCALE_SMOOTH);
        Image imageA = appleTartImage.getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH);

        espressoLabel = new JLabel(new ImageIcon(imageE));
        icedCoffeeLabel = new JLabel(new ImageIcon(imageI));
        cakeLabel = new JLabel(new ImageIcon(imageC));
        appleTartLabel = new JLabel(new ImageIcon(imageA));
    }

    //MODIFIES: this
    // EFFECTS: Create and Add button and a Clear button and adds it to the GUI
    public void addAndClearItemButton() {
        addAndClear = new JPanel(new GridLayout(0, 2));
        addItem = new JButton("Add Item to Cart");
        addItem.setActionCommand("add");
        addItem.addActionListener(this);
        clearItem = new JButton("Clear Cart");
        clearItem.setActionCommand("clear");
        clearItem.addActionListener(this);
        addAndClear.add(addItem);
        addAndClear.add(clearItem);
    }

    //MODIFIES: this
    //EFFECTS: Add the buttons, images and heading to the GUI
    public void addButtons() {
        JPanel menuPanel = new JPanel(new GridLayout(0, 1));
        menuPanel.add(heading);
        menuPanel.add(espressoButton);
        menuPanel.add(addOnList1);
        menuPanel.add(espressoLabel);
        menuPanel.add(icedCoffeeButton);
        menuPanel.add(addOnList2);
        menuPanel.add(icedCoffeeLabel);
        menuPanel.add(cakeButton);
        menuPanel.add(cakeLabel);
        menuPanel.add(appleTartButton);
        menuPanel.add(appleTartLabel);
        menuPanel.add(addAndClear);
        menuPanel.add(saveAndLoadPanel);
        add(menuPanel, BorderLayout.LINE_START);
    }

    //MODIFIES: this
    //EFFECTS: makes buttons for saving and loading order as JSON files and adding it to the GUI
    public void savingAndLoading() {
        saveAndLoadPanel = new JPanel(new GridLayout(0, 2));
        JButton save = new JButton("Save Order");
        JButton load = new JButton("Load Order");
        save.setActionCommand("save");
        save.addActionListener(this);
        load.setActionCommand("load");
        load.addActionListener(this);
        saveAndLoadPanel.add(save);
        saveAndLoadPanel.add(load);
    }

    // This [class/method] references code from these [repo/website]
    // Link: [https://kodejava.org/how-do-i-set-the-font-and-color-of-jtextarea/]
    // and [https://kodejava.org/how-do-i-wrap-the-text-lines-in-jtextarea/#:~:text=To%20wrap%20the%20lines%20of,we%20set%20it%20to%20true%20.]
    //MODIFIES: this
    //EFFECTS: makes a display for the order items made so far in the GUI
    public void cartDisplay() {
        receipt = new JTextArea(20, 25);
        receipt.setText("Your Order: \n");
        receipt.setEditable(false);
        receipt.setFont(font);
        receipt.setLineWrap(true);
        receipt.setWrapStyleWord(true);
        JScrollPane receiptScroll = new JScrollPane(receipt);
        receiptScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(receiptScroll, BorderLayout.LINE_END);
    }

    // EFFECTS: Creates a new CafeOrdering System
    public static void main(String[] args) {
        new CafeOrderingSystem();
    }

    // MODIFIES: this
    // EFFECTS: What happens when an action happens within the GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        if (e.getActionCommand().equals("add")) {
            espresso = new Drink("Espresso", 300, "A simple espresso.");
            icedCoffee = new Drink("Iced Coffee", 500, "Classic Iced Coffee");
            menuSelection();
            receipt.setText("Your Order: \n" + cart.receipt());
        }
        if (e.getActionCommand().equals("clear")) {
            receipt.setText("");
            cart = new OrderingSystem();
        }
        if (e.getActionCommand().equals("save")) {
            saveOrderingSystem();
        }
        if (e.getActionCommand().equals("load")) {
            loadOrderingSystem();
        }
    }

    // MODIFIES: this
    // EFFECTS: Saves ordering system as a JSON file
    private void saveOrderingSystem() {
        try {
            jsonWriter.open();
            jsonWriter.write(cart);
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
            cart = jsonReader.read();
            receipt.setText("Your Order: " + cart.receipt());
            System.out.println("Loaded order from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: what happens when one of the radio buttons is selected
    private void menuSelection() {
        drinkSelection();
        if (mainMenu.getSelection().getActionCommand().equals("cake")) {
            cart.addItemToCart(cake);
        }
        if (mainMenu.getSelection().getActionCommand().equals("appleTart")) {
            cart.addItemToCart(appleTart);
        }
    }

    // MODIFIES: this
    // EFFECTS: What happens when a drink is selected
    private void drinkSelection() {
        if (mainMenu.getSelection().getActionCommand().equals("espresso")) {
            addOnsSelection(espresso);
        }
        if (mainMenu.getSelection().getActionCommand().equals("icedCoffee")) {
            addOnsSelection(icedCoffee);
        }
    }

    // MODIFIES: this
    // EFFECTS: What happens when a drink is selected and an addon is also selected
    private void addOnsSelection(Drink drink) {
        if (addOnList1.getSelectedItem().equals(addOn1.getName() + " " + AddOns.PRICE + " cents")) {
            drink.addOptional(addOn1.getName(), addOn1.getDescription());
            cart.addItemToCart(drink);
        }
        if (addOnList1.getSelectedItem().equals(addOn2.getName() + " " + AddOns.PRICE + " cents")) {
            drink.addOptional(addOn2.getName(), addOn2.getDescription());
            cart.addItemToCart(drink);
        }
        if (addOnList1.getSelectedItem().equals("None")) {
            cart.addItemToCart(drink);
        }
    }
}
