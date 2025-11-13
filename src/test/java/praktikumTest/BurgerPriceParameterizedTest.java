package praktikumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private Burger burger;

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float ingredientPrice;

    @Parameterized.Parameter(2)
    public float expectedPrice;

    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    private static final float BUN_PRICE_100 = 100f;
    private static final float BUN_PRICE_150 = 150f;
    private static final float BUN_PRICE_200 = 200f;

    private static final float INGREDIENT_PRICE_50 = 50f;
    private static final float INGREDIENT_PRICE_100 = 100f;
    private static final float INGREDIENT_PRICE_150 = 150f;

    private static final float EXPECTED_PRICE_300 = 300f;
    private static final float EXPECTED_PRICE_600 = 600f;

    @Parameterized.Parameters(name = "{index}: bun={0}, ingredient={1}, expected={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BUN_PRICE_100, INGREDIENT_PRICE_50, EXPECTED_PRICE_300},
                {BUN_PRICE_200, INGREDIENT_PRICE_100, EXPECTED_PRICE_600},
                {BUN_PRICE_150, INGREDIENT_PRICE_150, EXPECTED_PRICE_600}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        ingredientMock1 = mock(Ingredient.class);
        ingredientMock2 = mock(Ingredient.class);

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock1.getPrice()).thenReturn(ingredientPrice);
        when(ingredientMock2.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
    }

    @Test
    public void testBurgerPriceCalculation() {
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);
    }
}
