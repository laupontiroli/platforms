package store.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import store.product.ProductController;
import store.product.ProductOut;

@Service
public class OrderService {

    @Autowired
    private ProductController productController;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order order) {
        // 1) preencher data/total e salvar somente o pedido
        System.out.println(" ----------------------- [DEBUG] order.items() → " + order.items());
        order.date(new Date());
        order.total(0.0);
        order.items().forEach(item -> {
            ProductOut product = productController.findById(item.product().id()).getBody();
            item.product(product);
            item.total(item.quantity() * product.price());
            order.total(order.total() + item.total());
            System.out.println(" ----------------------- [DEBUG] product → " + product);
        });
        // Salva o OrderModel (sem itens)
        OrderModel orderModelSalvo = orderRepository.save(new OrderModel(order));
        Order saved = orderModelSalvo.to();

        // 2) agora salva cada item, associando ao orderModelSalvo
        for (Item item : order.items()) {
            item.order(saved);  // garante que item.order.id está preenchido
            ItemModel im = new ItemModel(item);
            ItemModel imSalvo = itemRepository.save(im);
            System.out.println(" ----------------------- [DEBUG] itemSalvo → ");
            // Converte de volta para o domínio e adiciona na lista de items do 'saved'
            Item domItemSalvo = imSalvo.to();
            saved.items().add(domItemSalvo);
            System.out.println(" ----------------------- [DEBUG] saved  → " + saved.items());
        }

        return saved;
    }

    public List<Order> findAll(String idAccount) {

        List<Order> orders = StreamSupport
            .stream(orderRepository.findByUserId(idAccount).spliterator(), false)
            .map(OrderModel::to)
            .toList();
        
        orders.forEach(order -> {
            order.items(
                StreamSupport
                .stream(itemRepository.findByIdOrder(order.id()).spliterator(), false)
                .map(ItemModel::to)
                .toList()
            );
        });
        
        return orders;
    }

    public Order findById(String idAccount, String id) {

        Order order = orderRepository.findById(id).orElse(null).to();
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        };

        if (!order.user().id().equals(idAccount)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }

        order.items(
            StreamSupport
            .stream(itemRepository.findByIdOrder(id).spliterator(), false)
            .map(ItemModel::to)
            .toList()
        );

        return order;
    }

    public void deleteOrder(String id) {
        OrderModel order = orderRepository.findById(id).get();
        orderRepository.delete(order);
    }
    
}