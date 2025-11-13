package praktikumTest;

import org.junit.Before;
import org.junit.Test;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    private static final String BUN_NAME = "TestBun";
    private static final String INGREDIENT_NAME_1 = "Sauce";
    private static final String INGREDIENT_NAME_2 = "Filling";
    private static final float BUN_PRICE = 100f;
    private static final float INGREDIENT_PRICE_1 = 30f;
    private static final float INGREDIENT_PRICE_2 = 50f;

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        when(bunMock.getName()).thenReturn(BUN_NAME);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);

        when(ingredientMock1.getName()).thenReturn(INGREDIENT_NAME_1);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getPrice()).thenReturn(INGREDIENT_PRICE_1);

        when(ingredientMock2.getName()).thenReturn(INGREDIENT_NAME_2);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getPrice()).thenReturn(INGREDIENT_PRICE_2);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);
        burger.addIngredient(newIngredient);
        assertTrue(burger.ingredients.contains(newIngredient));
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock1, burger.ingredients.get(1));
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void testReceiptContainsAllIngredientsAndBuns() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(BUN_NAME));
        assertTrue(receipt.contains(INGREDIENT_NAME_1));
        assertTrue(receipt.contains(INGREDIENT_NAME_2));
    }

    @Test
    public void testReceiptPriceIsCorrect() {
        float expectedTotal = (BUN_PRICE * 2) + INGREDIENT_PRICE_1 + INGREDIENT_PRICE_2;
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(String.valueOf(expectedTotal)));
    }
}
