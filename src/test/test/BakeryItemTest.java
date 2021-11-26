package test;

import model.BakeryItem;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for BakeryItem class
class BakeryItemTest {

    BakeryItem cake;
    JSONObject json1;

    @BeforeEach
    public void setup() {
        cake = new BakeryItem("Cake", 600, "A pretty cake");
    }

    @Test
    public void testGetName() {
        assertEquals("Cake", cake.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(600, cake.getPrice());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A pretty cake", cake.getDescription());
    }

    @Test
    public void testToJson() {
        json1 = new JSONObject();
        json1 = cake.toJson();
        assertEquals("Cake", json1.getString("bakeryitem name"));
        assertEquals(600, json1.getInt("bakeryitem price"));
        assertEquals("A pretty cake", json1.getString("bakeryitem description"));
    }
}
