package kata.supermarket;

import java.util.List;

public abstract class Promotion {

    private String productCode;

    public Promotion(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public abstract List<Item> apply(List<Item> items);
}
