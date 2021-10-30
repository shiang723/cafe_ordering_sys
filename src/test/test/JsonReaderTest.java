package test;

import model.MenuItem;
import model.OrderingSystem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            OrderingSystem os = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrderingSystem() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrderingSystem.json");
        try {
            OrderingSystem os = reader.read();
            assertEquals(0, os.getCart().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralOrderingSystem() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrderingSystem.json");
        try {
            OrderingSystem os = reader.read();
            ArrayList<MenuItem> items = os.getCart();
            assertEquals(3, items.size());
            checkMenuItem("Iced Coffee",500, "Classic Iced Coffee", items.get(0));
            checkMenuItem("Apple Tart", 600, "Sweet apple tart", items.get(1));
            checkMenuItem("Iced Coffee", 505, "Classic Iced Coffee", items.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralOrderingSystemNoBakeryItem() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrderingSystemNoBakeryItem.json");
        try {
            OrderingSystem os = reader.read();
            ArrayList<MenuItem> items = os.getCart();
            assertEquals(2, items.size());
            checkMenuItem("Iced Coffee",500, "Classic Iced Coffee", items.get(0));
            checkMenuItem("Iced Coffee", 505, "Classic Iced Coffee", items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}