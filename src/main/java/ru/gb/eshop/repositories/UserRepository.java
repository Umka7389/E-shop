package ru.gb.eshop.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.gb.eshop.entities.Role;
import ru.gb.eshop.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    List<User> findAllByRoles(Role role);
    Optional<User> findByPhone(String phone);

}
