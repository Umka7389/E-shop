package ru.gb.eshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.eshop.entities.Role;
import ru.gb.eshop.entities.User;
import ru.gb.eshop.exceptions.RoleNotFoundException;
import ru.gb.eshop.exceptions.UserNotFoundException;
import ru.gb.eshop.repositories.RoleRepository;
import ru.gb.eshop.services.RoleService;

import java.util.Optional;

@SpringBootTest(classes = EShopApplication.class)
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    @MockBean
    RoleRepository roleRepository;

    @Test
    public void findRoleTest() {

        Role roleFromDB = new Role();
        roleFromDB.setName("ROLE_TEST");

        Mockito.doReturn(Optional.of(roleFromDB))
                .when(roleRepository)
                .findByName("ROLE_TEST");

        Role roleTest = roleService.getByName("ROLE_TEST");
        Assertions.assertNotNull(roleTest);
        Mockito.verify(roleRepository, Mockito.times(1)).findByName(ArgumentMatchers.eq("ROLE_TEST"));

    }
    @Test
    public void checkThrow() {
        Role roleFromDB = new Role();
        roleFromDB.setName("ROLE_TEST");

        Mockito.doReturn(Optional.of(roleFromDB))
                .when(roleRepository)
                .findByName("ROLE_TEST");

        Assertions.assertThrows(RoleNotFoundException.class, () -> roleService.getByName("NO_SUCH_ROLE"));
    }
}
