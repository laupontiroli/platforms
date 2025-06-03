package store.order;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import store.product.ProductOut;

@Entity
@Table(name = "items")
@Setter @Accessors(fluent = true)
@NoArgsConstructor
public class ItemModel {

    @Id 
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.UUID)
    String id; 

    @Column(name = "str_id_order")
    String idOrder; 

    @Column(name = "str_id_product")
    String product;


    @Column(name = "qt_item")
    Integer quantity;

    @Column(name = "vl_item")
    Double total;
    
    
    public ItemModel(Item i) {
        this.id = i.id();
        this.product = i.product().id();
        this.idOrder = i.order().id();
        this.quantity = i.quantity();
        this.total = i.total();
    }

    public Item to() {
        return Item.builder()
            .id(this.id)
            .product(ProductOut.builder().id(this.product).build())
            .order(Order.builder().id(this.idOrder).build())
            .quantity(this.quantity)
            .total(this.total)
            .build();
    }
}
