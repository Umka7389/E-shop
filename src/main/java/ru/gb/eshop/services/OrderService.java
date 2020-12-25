package ru.gb.eshop.services;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.eshop.aspect.Log;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.entities.User;
import ru.gb.eshop.repositories.OrderItemRepository;
import ru.gb.eshop.repositories.OrderRepository;

import java.util.List;

import static ru.gb.eshop.entities.Order.Status.MANAGING;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;
    private final Authentication authentication;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserService userService,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.orderItemRepository = orderItemRepository;
        this.authentication = SecurityContextHolder.getContext().getAuthentication();;
    }

    public void saveOrder() {
        User user = userService.findById(1L);

        Order order = new Order();
        order.setItems(cartService.getItems());
        order.setAddress(cartService.getAddress());
        order.setPhoneNumber(cartService.getPhone());
        order.setUser(user);
        order.setPrice(cartService.getPrice());
        order.setStatus(MANAGING);
        order.setPhoneNumber(user.getPhone());

        final Order savedOrder = orderRepository.save(order);
    }

    @Transactional
    public List<Order> getByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> getByUserName(String userName) {
        return orderRepository.findAllByUser_Phone(userName);
    }

}
