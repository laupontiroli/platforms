package store.order;

import java.text.SimpleDateFormat;

public class OrderParser {

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static Order to(OrderIn in) {
        return in == null ? null :
            Order.builder()
                .items(in.items().stream()
                    .map(ItemParser::to)
                    .toList())
                .build();
    }

    public static OrderOut to(Order a) {
    return a == null ? null :
        OrderOut.builder()
            .id(a.id())
            .date(sdf.format(a.date()))
            .total(a.total())
            .items(
                a.items().stream()
                    .map(ItemParser::to)
                    .toList()
            )
            .build();
    }  
}