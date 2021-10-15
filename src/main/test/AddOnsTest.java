package test;

import model.AddOns;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOnsTest {

    AddOns sugar;

    @BeforeEach
    public void setup() {
        sugar = new AddOns("sugar", 1);
    }

    @Test
    public void testGetItem() {
        assertEquals("sugar", sugar.getItem());
    }

    @Test
    public void testGetAmount() {
        assertEquals(1, sugar.getAmount());
    }

}
