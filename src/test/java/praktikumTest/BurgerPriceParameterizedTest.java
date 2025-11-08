package praktikumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

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

    @Parameterized.Parameters(name = "{index}: bun={0}, ingredient={1}, expected={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 300f},
                {200f, 100f, 600f},
                {150f, 150f, 600f}
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
