package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();
}
