package learn.commerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order = new Order(25);

    @Test
    void shouldHaveOrderId() {
        assertEquals(25, order.getOrderId());
    }

    @Test
    void shouldAddValidItems() {
        LineItem grassSeed = new LineItem("Grass Seed", 19.99, 2);
        boolean result = order.add(grassSeed);
        assertTrue(result);
        assertEquals(1, order.getLineItems().length);
        assertEquals(grassSeed, order.getLineItems()[0]);

        LineItem gardenRake = new LineItem("Garden Rake", 44.99, 1);
        result = order.add(gardenRake);
        assertTrue(result);
        assertEquals(2, order.getLineItems().length);
        assertEquals(gardenRake, order.getLineItems()[1]);

        LineItem hose = new LineItem("Garden Hose - 50ft", 38.49, 1);
        result = order.add(hose);
        assertTrue(result);
        assertEquals(3, order.getLineItems().length);
        assertEquals(hose, order.getLineItems()[2]);
    }

    // 1. Add test shouldNotAddInvalidItems: confirm that it's not possible to add items with <= 0 quantity or < 0 price.
    // 2. Test the order.getTotal() in various scenarios and confirm it's correct.
    // 3. If you tackle `order.remove`, test the method thoroughly.

}