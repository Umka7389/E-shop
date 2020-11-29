package ru.gb.eshop.frontend;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.services.OrderService;

@Route("orders")
public class OrderView extends AbstractView {
    private final OrderService orderService;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        initOrderView();
    }

    private void initOrderView() {
        Grid<Order> orderGrid = new Grid<>(Order.class);
        orderGrid.setItems(orderService.getByUserId(1L));

        add(orderGrid);
    }
}
