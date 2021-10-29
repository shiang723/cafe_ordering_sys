package model.test;

import model.AddOns;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOnsTest {

    AddOns sugar;

    @BeforeEach
    public void setup() {
        sugar = new AddOns("sugar", "a teaspoon of sugar");
    }

    @Test
    public void testGetItem() {
        assertEquals("sugar", sugar.getItem());
    }

}
