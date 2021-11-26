package test;

import model.AddOns;
import model.Drink;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.ws.RequestWrapper;

import static org.junit.jupiter.api.Assertions.*;

//Test for Drink class
class DrinkTest {

    Drink espresso;
    JSONObject json1;

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
        Drink addSugar = espresso.addOptional("sugar","a teaspoon of sugar");
        AddOns espressoAddOn = addSugar.getAddOn();
        assertEquals("sugar", espressoAddOn.getName());
        assertEquals("a teaspoon of sugar", espressoAddOn.getDescription());
        assertEquals(255, addSugar.getPrice());
    }

    @Test
    public void testAddOptionalWithNoPrice(){
        Drink addSugar = espresso.addOptionalWithNoPrice("sugar", "yay sugar");
        AddOns espressoAddOn = addSugar.getAddOn();
        assertEquals("sugar", espressoAddOn.getName());
        assertEquals(250, addSugar.getPrice());
    }

    @Test
    public void testToJson() {
        json1 = espresso.toJson();
        assertEquals("Espresso", json1.getString("drink name"));
        assertEquals(250, json1.getInt("drink price"));
        assertEquals("A simple espresso", json1.getString("drink description"));

        espresso.addOptional("sugar", "white sugar");
        json1 = espresso.toJson();
        assertEquals("Espresso", json1.getString("drink name"));
        assertEquals(255, json1.getInt("drink price"));
        assertEquals("A simple espresso", json1.getString("drink description"));
        assertEquals("sugar", json1.getString("drink addon"));
        assertEquals("white sugar", json1.getString("drink addon description"));


    }

}
