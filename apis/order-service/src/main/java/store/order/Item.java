package store.order;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Builder
@Data @Accessors(fluent = true)
public class Item {

    private String id; 
    private ProductOut product;
    private Integer quantity;
    private Double total;
    private Order order;
    
}