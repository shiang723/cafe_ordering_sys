package test;

import model.MenuItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonTest {
    protected void checkMenuItem(String name, Integer price , String description, MenuItem item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(description, item.getDescription());
    }
}
