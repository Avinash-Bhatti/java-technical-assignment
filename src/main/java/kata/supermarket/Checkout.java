package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {

    private final List<Promotion> promotions;
    private final Basket basket;

    public Checkout(List<Promotion> promotions, Basket basket) {
        this.promotions = promotions;
        this.basket = basket;
    }

    public void addItem(Item item) {
        basket.add(item);
    }

    public BigDecimal total() {
        List<Item> itemsAfterDiscount = basket.items();
        for (Promotion promotion : promotions) {
            itemsAfterDiscount = promotion.apply(itemsAfterDiscount);
        }
        Basket basketAfterDiscounts = basket.withItems(itemsAfterDiscount);
        return basketAfterDiscounts.total();
    }
}
