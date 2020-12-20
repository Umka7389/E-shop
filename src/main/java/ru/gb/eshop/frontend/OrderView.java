package ru.gb.eshop.frontend;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.security.CustomPrincipal;
import ru.gb.eshop.services.OrderService;

import java.util.List;


@Transactional
public class OrderView extends AbstractView {
    private final OrderService orderService;
    private final Authentication authentication;

    protected Grid<Order> orderGrid;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
        initOrderView();
    }

    public void initOrderView() {
        orderGrid = new Grid<>(Order.class);
        List<Order> order = orderService.getByUserName(((CustomPrincipal)authentication.getPrincipal()).getUsername());
        orderGrid.setItems(order);
        orderGrid.setColumns("address", "items", "phoneNumber", "price", "status");

        add(orderGrid);
    }
}
