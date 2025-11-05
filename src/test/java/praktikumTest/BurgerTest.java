package praktikumTest;

import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;
    private Bun stubBun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    @Parameterized.Parameters(name = "{index}: Price with 2 ingredients = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 300f},
                {200f, 100f, 600f},
                {150f, 150f, 600f}
        });
    }

    public BurgerTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void setUp() {
        // Стабы вместо моков
        stubBun = new Bun("stubBun", bunPrice);
        ingredient1 = new Ingredient(IngredientType.FILLING, "ingredient1", ingredientPrice);
        ingredient2 = new Ingredient(IngredientType.SAUCE, "ingredient2", ingredientPrice);

        burger = new Burger();
        burger.setBuns(stubBun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @Test
    public void testGetPrice() {
        float price = burger.getPrice();
        // Цена: 2 * bun + ingredient1 + ingredient2
        assertEquals(expectedPrice, price, 0.001);
    }

    @Test
    public void testAddAndRemoveIngredient() {
        Ingredient ingredient3 = new Ingredient(IngredientType.FILLING, "ingredient3", 10f);
        burger.addIngredient(ingredient3);
        assertTrue(burger.ingredients.contains(ingredient3));

        burger.removeIngredient(2);
        assertFalse(burger.ingredients.contains(ingredient3));
    }

    @Test
    public void testMoveIngredient() {
        // Перемещаем ingredient1 (index 0) на место ingredient2 (index 1)
        burger.moveIngredient(0, 1);
        assertEquals(ingredient1, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testGetReceipt() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("stubBun"));
        assertTrue(receipt.contains("ingredient1"));
        assertTrue(receipt.contains("ingredient2"));
        assertTrue(receipt.contains("Price:"));
    }
}
