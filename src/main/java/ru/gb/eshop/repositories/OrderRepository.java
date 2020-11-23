package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
