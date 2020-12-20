package ru.gb.eshop.frontend;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import ru.gb.eshop.entities.OrderItem;
import ru.gb.eshop.services.CartService;
import ru.gb.eshop.services.OrderService;

@Route("cart")
public class CartView extends AbstractView {
    private final CartService cartService;
    private final OrderService orderService;

    public CartView(CartService cartService,
                    OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
        initCartPage();
    }

    private void initCartPage() {
        Grid<OrderItem> grid = new Grid<>(OrderItem.class);
        grid.setItems(cartService.getItems());
        grid.setWidth("60%");
        grid.setColumns("product", "price", "quantity");

        grid.addColumn(new ComponentRenderer<>(item -> {
            Button plusButton = new Button("+", i -> {
                item.increment();
                grid.setItems(cartService.getItems());
            });

            Button minusButton = new Button("-", i -> {
                item.decrement();
                if (item.getQuantity() <= 0) cartService.getItems().remove(item);
                grid.setItems(cartService.getItems());
            });

            return new HorizontalLayout(plusButton, minusButton);
        }));

        TextField addressField = initTextFieldWithPlaceholder("Введите адрес доставки");
        TextField phoneField = initTextFieldWithPlaceholder("Введите номер телефона");

        Button toOrderButton = new Button("Создать заказ", e -> {
            String checkOnlyNumbers = "\\d+";
            if (!phoneField.getValue().matches(checkOnlyNumbers)) {
                Notification.show("Некорректно указан номер телефона");
            } else {
                cartService.setAddress(addressField.getValue());
                cartService.setPhone(phoneField.getValue());
                orderService.saveOrder();

                cartService.clear();
                UI.getCurrent().navigate("market");

                Notification.show("Заказ успешно сохранён и передан менеджеру");
            }
        });

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);

        add(grid, addressField, phoneField, toOrderButton);
    }
}
