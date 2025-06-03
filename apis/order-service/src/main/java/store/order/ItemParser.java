package store.order;

import store.product.ProductOut;

public class ItemParser {

    public static Item to(ItemIn in) {
        return in == null ? null :
            Item.builder()
                .product(ProductOut.builder().id(in.idProduct()).build())
                .quantity(in.quantity())
                .build();
    }

    public static ItemOut to(Item i) {
        return i == null ? null :
            ItemOut.builder()
                .id(i.id())
                .product(i.product())
                .quantity(i.quantity())
                .total(i.total())
                .build();
    }
    
}
