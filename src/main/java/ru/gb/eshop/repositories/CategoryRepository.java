package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
