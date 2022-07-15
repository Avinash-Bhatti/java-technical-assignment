package kata.supermarket;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOGOF extends Promotion {

    public BOGOF(String productCode) {
        super(productCode);
    }

    @Override
    public List<Item> apply(List<Item> items) {
        Map<Boolean, List<Item>> itemsForDiscount = items.stream()
                .collect(Collectors.partitioningBy(item -> item.getProductCode().equals(getProductCode())));

        List<Item> itemsWithApplicableProductCode = itemsForDiscount.get(true);

        if (itemsWithApplicableProductCode.isEmpty()) {
            return items;
        }

        int freeItems = itemsWithApplicableProductCode.size() / 2;
        Stream<Item> freeProducts = itemsWithApplicableProductCode.stream()
                .limit(freeItems)
                .map(item -> item.withDiscount(item.price()));
        Stream<Item> productsToPay = itemsWithApplicableProductCode.stream()
                .skip(freeItems);

        return Stream.of(freeProducts, productsToPay, itemsForDiscount.get(false).stream())
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .collect(Collectors.toList());
    }
}
