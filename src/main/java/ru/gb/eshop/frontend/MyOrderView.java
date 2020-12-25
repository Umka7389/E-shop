package ru.gb.eshop.frontend;


import com.vaadin.flow.router.Route;
import ru.gb.eshop.services.OrderService;

@Route("orders")
public class MyOrderView extends OrderView {
    public MyOrderView(OrderService orderService) {
        super(orderService);
    }
}
