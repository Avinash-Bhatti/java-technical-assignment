package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemByUnitTest {

    @Test
    void noDiscountWhenFirstItem() {
        Item itemByUnit = new Product(new BigDecimal("1.00"), "productCode").oneOf();
        assertEquals(BigDecimal.ZERO, itemByUnit.discount());
    }

    @Test
    void priceAfterDiscountShouldCreateItemWithGivenPriceAfterDiscount() {
        Item itemByUnit = new Product(new BigDecimal("1.00"), "productCode").oneOf();
        Item itemAfterDiscount = itemByUnit.withDiscount(new BigDecimal("0.40"));
        assertEquals(new BigDecimal("0.40"), itemAfterDiscount.discount());
    }
}
