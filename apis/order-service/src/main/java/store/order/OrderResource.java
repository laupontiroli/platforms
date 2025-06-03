package store.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountOut;

@RestController
public class OrderResource implements OrderController{

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderOut> create(String idAccount, OrderIn orderIn) {
        Order orderDomain = OrderParser.to(orderIn)
            .user(AccountOut.builder().id(idAccount).build());
        Order created = orderService.create(orderDomain);
        return ResponseEntity.ok().body(OrderParser.to(created));
    }

    @Override
    public ResponseEntity<List<OrderOut>> findAll(String idAccount) {
        return ResponseEntity
            .ok()
            .body(orderService.findAll(idAccount).stream().map(OrderParser::to).toList());
    }

    @Override
    public ResponseEntity<OrderOut> findOrder(String idAccount, String id) {
        Order order = orderService.findById(idAccount, id);
        return ResponseEntity.ok().body(
            OrderParser.to(order)
        );
    }

    @Override
    public void deleteOrder(String idAccount, String id) {
        orderService.deleteOrder(id);
    }
}