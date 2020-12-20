package ru.gb.eshop.frontend;


import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.services.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Route("other-orders")
public class OtherOrderView extends OrderView {

    public OtherOrderView(OrderService orderService) {
        super(orderService);
    }

    @Override
    public void initOrderView() {
        super.initOrderView();

        orderGrid.removeColumnByKey("status");
        orderGrid.addColumn(new ComponentRenderer<>(item -> {
            Select<String> statusSelect = new Select<>();
            statusSelect.setItems(List.of("MANAGING", "DELIVERING", "DELIVERED"));

            List<Order.Status> statuses = orderGrid.getDataProvider().fetch(new Query<>()).map(Order::getStatus).collect(Collectors.toList());
            Optional<Order.Status> currentStatus = statuses.stream().filter(i -> i.name().equals(item.getStatus().name())).findFirst();
            statusSelect.setValue(currentStatus.map(Enum::name).orElse(null));

            return statusSelect;
        }));
    }
}
