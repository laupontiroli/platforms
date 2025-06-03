package store.order;

import org.springframework.core.annotation.Order;

import lombok.Builder;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Builder @Accessors(fluent = true)
public record ItemOut(
    String id,
    ProductOut product,
    Integer quantity,
    Double total

) {
   
}