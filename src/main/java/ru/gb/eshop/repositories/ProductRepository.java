package ru.gb.eshop.repositories;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> , JpaSpecificationExecutor<Product> {
    List<Product> findAll();
}
