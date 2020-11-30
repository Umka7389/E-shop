package ru.gb.eshop.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.eshop.entities.Order;
import ru.gb.eshop.repositories.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

}
