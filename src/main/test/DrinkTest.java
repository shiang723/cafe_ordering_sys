package test;

import model.AddOns;
import model.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    Drink espresso;

    @BeforeEach
    public void setup() {
        espresso = new Drink("Espresso", 250, "A simple espresso");
    }

    @Test
    public void testGetName() {
        assertEquals("Espresso", espresso.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("A simple espresso", espresso.getDescription());
    }

    @Test
    public void testChooseSizeLarge() {
        Drink largeCoffee = espresso.chooseSize("large");
        assertEquals("large", largeCoffee.getDrinkSize());
        assertEquals(350, largeCoffee.getPrice());
    }

    @Test
    public void testChooseSizeMedium() {
        Drink mediumCoffee = espresso.chooseSize("medium");
        assertEquals("medium", mediumCoffee.getDrinkSize());
        assertEquals(300, mediumCoffee.getPrice());
    }

    @Test
    public void testChooseSizeSmall() {
        Drink smallCoffee = espresso.chooseSize("small");
        assertEquals("small", smallCoffee.getDrinkSize());
        assertEquals(250, smallCoffee.getPrice());
    }

    @Test
    public void testAddOptionalSugar() {
        Drink addSugar = espresso.addOptional("sugar");
        ArrayList<AddOns> espressoAddOn = addSugar.getAddOns();
        assertEquals(1, espressoAddOn.size());
        assertEquals(255, addSugar.getPrice());
    }

}
