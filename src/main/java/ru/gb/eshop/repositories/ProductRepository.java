package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
