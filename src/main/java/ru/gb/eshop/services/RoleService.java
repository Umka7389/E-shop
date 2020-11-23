package ru.gb.eshop.services;


import org.springframework.stereotype.Service;
import ru.gb.eshop.entities.Role;
import ru.gb.eshop.exceptions.RoleNotFoundException;
import ru.gb.eshop.repositories.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name) {
        Optional<Role> roleOptional = roleRepository.findByName(name);

        if(roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new RoleNotFoundException(String.format("Роль с именем %s не найдена.", name));
        }
    }

}
