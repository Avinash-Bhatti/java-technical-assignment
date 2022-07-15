package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    List<Promotion> promotions = List.of(new BOGOF("milk1"));
    Basket basket = new Basket();
    Checkout checkout = new Checkout(promotions, basket);
    Item item = new Product(new BigDecimal("1.00"), "milk1").oneOf();

    @Test
    void addItemShouldAddItemsToBasket() {
        checkout.addItem(item);

        assertEquals(1, basket.items().size());
    }

    @Test
    void shouldCalculateTheTotalTakingDiscountsIntoAccount() {
        checkout.addItem(item);
        checkout.addItem(item);

        assertEquals(new BigDecimal("1.00"), checkout.total());
    }
}
