package store.order;
import java.util.ArrayList;
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
import store.account.AccountOut;

@Entity
@Table(name = "orders")
@Setter @Accessors(fluent = true)
@NoArgsConstructor
public class OrderModel {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "str_id_user")
    private String userId;

    @Column(name = "dt_order")
    private Date date;

    @Column(name = "vl_total")
    private Double total;

     
    public OrderModel(Order a) {
        this.id = a.id();
        this.userId = a.user().id();
        this.date = a.date();
        this.total = a.total();
    }

    public Order to() {
        return Order.builder()
            .id(this.id)
            .user(AccountOut.builder().id(this.userId).build())
            .date(this.date)
            .total(this.total)
            .items(new ArrayList<>())
            .build();
        }
    
}