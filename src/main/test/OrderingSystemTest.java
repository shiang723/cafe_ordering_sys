package test;

import model.BakeryItem;
import model.Drink;
import model.OrderingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingSystemTest {

    OrderingSystem order1;
    OrderingSystem order2;

    @BeforeEach
    public void setup() {
        order1 = new OrderingSystem();
        order2 = new OrderingSystem();
    }

    @Test
    public void testAddItemToCart() {
        order1.addItemToCart(new BakeryItem("Cake", 600, "A pretty cake"));
        order2.addItemToCart(new Drink("Green Tea", 400, "Refreshing Green Tea"));

        assertEquals(1, order1.getNumOfItems());
        assertEquals(600, order1.getTotalPrice());
        assertEquals(1, order1.getCart().size());

        assertEquals(1, order2.getNumOfItems());
        assertEquals(400, order2.getTotalPrice());
        assertEquals(1, order2.getCart().size());

        order1.addItemToCart(new Drink("Green Tea", 400, "Refreshing Green Tea"));

        assertEquals(2, order1.getNumOfItems());
        assertEquals(1000, order1.getTotalPrice());
        assertEquals(2, order1.getCart().size());

    }


    @Test
    public void testEmptyReceipt() {
        assertEquals("You have ordered: " + "[]" + "." + "Which is "
                + "0" + " item(s). That will be " + "0" + " cents.", order1.receipt());
    }

    @Test
    public void testNonEmptyReceipt() {
        order1.addItemToCart(new BakeryItem("Cake", 600, "A pretty cake"));
        assertEquals("You have ordered: " + "[Cake]" + "." + "Which is " + order1.getNumOfItems()
                + " item(s). That will be " + order1.getTotalPrice() + " cents.", order1.receipt());

        order1.addItemToCart(new Drink("Green Tea", 400, "Refreshing Green Tea"));
        assertEquals("You have ordered: " + "[Cake, Green Tea]" + "." + "Which is " + order1.getNumOfItems()
                + " item(s). That will be " + order1.getTotalPrice() + " cents.", order1.receipt());
    }




}


