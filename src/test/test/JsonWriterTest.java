package test;

import model.BakeryItem;
import model.Drink;
import model.MenuItem;
import model.OrderingSystem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            OrderingSystem os = new OrderingSystem();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrderingSystem() {
        try {
            OrderingSystem os = new OrderingSystem();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrderingSystem.json");
            writer.open();
            writer.write(os);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrderingSystem.json");
            os = reader.read();
            assertEquals(0, os.getNumOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralOrderingSystem() {
        try {
            OrderingSystem os = new OrderingSystem();
            os.addItemToCart(new Drink("Coffee", 500, "original coffee").
                    addOptional("sugar", "new sugar"));
            os.addItemToCart(new BakeryItem("Cake", 600, "cake"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOrderingSystem.json");
            writer.open();
            writer.write(os);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrderingSystem.json");
            os = reader.read();
            ArrayList<MenuItem> items = os.getCart();
            assertEquals(2, items.size());
            checkMenuItem("Coffee", 510, "original coffee", items.get(0));
            checkMenuItem("Cake", 600, "cake", items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

