package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
