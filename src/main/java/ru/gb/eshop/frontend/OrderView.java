package ru.gb.eshop.frontend;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.security.CustomPrincipal;
import ru.gb.eshop.security.SecurityUtils;
import ru.gb.eshop.services.OrderService;

import java.util.List;


@Transactional
public class OrderView extends AbstractView {
    protected final OrderService orderService;
    private final CustomPrincipal principal;

    protected Grid<Order> orderGrid;

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
        this.principal = SecurityUtils.getPrincipal();
        initOrderView();
    }

    public void initOrderView() {
        orderGrid = new Grid<>(Order.class);
        List<Order> order = orderService.getByUserName(principal.getUsername());
        orderGrid.setItems(order);
        orderGrid.setColumns("address", "items", "phoneNumber", "price", "status");

        Button backButton = new Button("Назад", event -> UI.getCurrent().navigate("market"));
        add(orderGrid, backButton);
    }
}
