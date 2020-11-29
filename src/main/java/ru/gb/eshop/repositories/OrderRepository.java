package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(long userId);
}
