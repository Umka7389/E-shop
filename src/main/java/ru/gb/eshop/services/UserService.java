package ru.gb.eshop.services;


import org.springframework.stereotype.Service;
import ru.gb.eshop.controllers.dto.UserDto;
import ru.gb.eshop.controllers.dto.UserType;
import ru.gb.eshop.entities.Role;
import ru.gb.eshop.entities.User;
import ru.gb.eshop.exceptions.ManagerIsEarlierThanNeedException;
import ru.gb.eshop.exceptions.UnknownUserTypeException;
import ru.gb.eshop.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User saveUser(UserDto userDto) {
        if (userDto.getUserType().equals(UserType.MANAGER)) {
            saveManager(userDto);
        } else if (userDto.getUserType().equals(UserType.USER)) {
            saveTypicallyUser(userDto);
        }

        throw new UnknownUserTypeException();
    }

    private User saveTypicallyUser(UserDto userDto) {
        User user = createUserFromDto(userDto);

        Role role = roleService.getByName("ROLE_USER");
        user.setRoles(List.of(role));

        return userRepository.save(user);
    }

    private User saveManager(UserDto userDto) {
        if (userDto.getAge() > 18) {
            User user = createUserFromDto(userDto);

            Role role = roleService.getByName("ROLE_MANAGER");
            user.setRoles(List.of(role));

            return userRepository.save(user);
        }

        throw new ManagerIsEarlierThanNeedException("Пользователь младше восемнадцати лет");
    }

    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setAge(userDto.getAge());

        return user;
    }

    public List<User> getAllUsersWithType(UserType userType) {
        return userRepository.findAll();
    }
}
