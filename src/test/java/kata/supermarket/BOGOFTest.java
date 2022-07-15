package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BOGOFTest {

    @Test
    void shouldNotGiveDiscountForOneItem() {
        BOGOF bogof = new BOGOF("milk1");
        List<Item> items = List.of(new Product(new BigDecimal("0.49"), "milk1").oneOf());
        List<Item> itemsAfterDiscount = bogof.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(0).discount());
    }

    @Test
    void shouldGiveOneProductFreeWhenTwoBought() {
        BOGOF bogof = new BOGOF("milk1");
        Item milk = new Product(new BigDecimal("0.49"), "milk1").oneOf();
        List<Item> items = List.of(milk, milk);

        List<Item> itemsAfterDiscount = bogof.apply(items);

        assertEquals(new BigDecimal("0.49"), itemsAfterDiscount.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(1).discount());
    }

    @Test
    void shouldOnlyGetBuyOneGetOneFreeWhenProductCodesAreSame() {
        BOGOF bogof = new BOGOF("milk1");
        Item digestives = new Product(new BigDecimal("1.55"), "dig1").oneOf();
        List<Item> items = List.of(digestives, digestives);

        List<Item> itemsAfterDiscount = bogof.apply(items);

        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(1).discount());
    }

    @Test
    void buyOneGetOneFreeShouldApplyOnlyToProductsWithGivenProductCode() {
        BOGOF bogof = new BOGOF("milk1");
        Item milk = new Product(new BigDecimal("0.49"), "milk1").oneOf();
        Item digestives = new Product(new BigDecimal("1.55"), "dig1").oneOf();
        List<Item> items = List.of(milk, digestives, digestives, milk);

        List<Item> itemsAfterDiscount = bogof.apply(items);

        assertEquals(new BigDecimal("0.49"), itemsAfterDiscount.get(0).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(1).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(2).discount());
        assertEquals(BigDecimal.ZERO, itemsAfterDiscount.get(3).discount());
    }
}
