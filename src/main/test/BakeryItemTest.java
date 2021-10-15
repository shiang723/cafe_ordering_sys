package test;

import model.BakeryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BakeryItemTest {

    BakeryItem cake;

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

}
